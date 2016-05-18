package record.logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import record.dto.PlayerDTO;

public class RecordLogicImpl implements RecordLogic{

	// 세부경기결과 크롤링 로직
	@Override
	public String eventRecordData(String eventId) throws IOException {

		String eventRecordData = "";

		String defUrl = "http://sports.news.naver.com/gameCenter/gameRecord.nhn?gameId=" + eventId + "&category=kbo";	// 파싱할 주소
		Document doc = Jsoup.connect(defUrl)
						.timeout(5000)
						.referrer("http://sports.news.naver.com/gameCenter/gameResult.nhn?&gameId=" + eventId + "&category=kbo")	// 리퍼러 - 네이버에 방문경로 통보
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// 유저에이전트 정의
						.get();
		
		if(doc != null) {
			Element elmt = doc.select("script").get(39);

			Pattern p = Pattern.compile("(?is)_data : (.+?),\n\t_homeTeamCode");	// JSON 형태의 게임기록 데이터의 패턴을 정규식으로 정의
			Matcher m = p.matcher(elmt.html());	// 위에서 정의한 패턴을 40번째 script 태그가 있는 html 소스 안에서 매칭하도록 정의
			
			while(m.find()) {
				eventRecordData = m.group(1);	// 
			}
		} else {
			System.out.println("로직널");
		}
		eventRecordData = eventRecordData.replace("\\r\\n", "");
		System.out.println("logic : " + eventId + eventRecordData);
		return eventRecordData;
	}
	
	// 오늘경기일정결과 크롤링 로직
	@Override
	public String eventTodayData(String year, String month, String day) throws IOException {
		
		String eventTodayData = "";

		String defUrl = "http://sportsdata.naver.com/ndata//kbo/" + year + "/" + month + "/" + year + month + day + ".nsd";	// 파싱할 주소

		Document doc;
		doc = Jsoup.connect(defUrl)
							.timeout(5000)
							.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// 유저에이전트 정의
							.get();
		
		if(doc != null) {
			Element elmt = doc.select("script").first();
			eventTodayData = elmt.html();
			// 앞 뒤로 JSON이 아닌 스크립트 찌꺼기 제거
			eventTodayData = eventTodayData.replace("document.domain=\"naver.com\";parent.sportscallback_gameList(document, ", "");
			eventTodayData = eventTodayData.replace(");", "");
		}
		System.out.println("logic : " + year + month + day + eventTodayData);
	    return eventTodayData;
	}

	@Override
	public ArrayList<PlayerDTO> playerRecordData(String eventId, String eventRecordData) throws ParseException {

		ArrayList<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PlayerDTO dto = null;

		// 넘겨받은 String JSON으로 파싱
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(eventRecordData.toString());
		JSONObject obj = (JSONObject) rawObj;

		// 타자 스코어박스 파싱
		JSONObject batter = (JSONObject) obj.get("battersBoxscore");
		// 원정팀 파싱
		JSONArray batterAway = (JSONArray) batter.get("away");

		// 선수별로 구분하는 for 시작
		for (int k = 0; k < batterAway.size(); k++) {
			dto = new PlayerDTO();
			JSONObject batterAwayPlayer = (JSONObject) batterAway.get(k);
			
			dto.setEventCode(eventId);
			dto.setPlayerCode((String) batterAwayPlayer.get("playerCode"));
//			dto.setHra((float) batterAwayPlayer.get("hra"));
			

			// inn1 ~ inn12 까지 데이터 찾는 for 시작
			for (int j = 1; j <= 12; j++) {
				String str = (String) batterAwayPlayer.get("inn" + j);
				// 빈 값일 경우 패스
				if (str.equals("")) {
				} else {
					// 한 타자가 한 이닝에 두번 타격할 경우(/로 구분된 경우) 분리
					if (str.contains("/")) {
						StringTokenizer tok = new StringTokenizer(str, "/");
						while (tok.hasMoreElements()) {
							Categorize(tok.nextToken(), dto);
						}
					} else {
						Categorize(str, dto);
					}
				}
			}
			list.add(dto);
		}

		// 결과 출력
		for (int z = 0; z < list.size(); z++) {
			System.out.println(list.get(z).toString());
		}
		return list;
	}
	
	
	// 타격 결과 구분하는 메소트
	public static void Categorize(String bat, PlayerDTO dto) {

		int len = bat.length();

		if (bat.contains("희")) {
			dto.setSh(dto.getSh() + 1);
			System.out.println(bat + " : 희생타");
		} else {

			switch (bat.substring((len - 1), len)) {
			case "안":
				dto.setH1(dto.getH1() + 1);
				System.out.println(bat + " : 1루타");
				break;

			case "2":
				dto.setH2(dto.getH2() + 1);
				System.out.println(bat + " : 2루타");
				break;

			case "3":
				dto.setH3(dto.getH3() + 1);
				System.out.println(bat + " : 3루타");
				break;

			case "홈":
				dto.setHr(dto.getHr() + 1);
				System.out.println(bat + " : 홈런");
				break;

			case "4":
				dto.setBb(dto.getBb() + 1);
				System.out.println(bat + " : 고의4구");
				break;

			case "구":
				dto.setIb(dto.getIb() + 1);
				System.out.println(bat + " : 4사구");
				break;

			case "낫":
			case "진":
				dto.setSo(dto.getSo() + 1);
				System.out.println(bat + " : 삼진");
				break;

			case "병":
			case "중":
				dto.setDp(dto.getDp() + 1);
				System.out.println(bat + " : 병살타");
				break;

			case "땅":
			case "번":
			case "비":
			case "직":
			case "파":
				dto.setOt(dto.getOt() + 1);
				System.out.println(bat + " : 아웃");
				break;

			default:
				break;
			}
		}

	}

	
	// nsd 합체 크롤링 로직(사용하지 않음)
	@Override
	public String eventMonthData(String year, String month) {
		
		String day = "";
		String data = "[";
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), (Integer.parseInt(month) - 1), 1);
		int lastDay = cal.getActualMaximum(Calendar.DATE);

		for (int i = 1; i <= lastDay; i++) {
		
			if(i < 10) {
				day = "0" + i;
			} else {
				day = "" + i;
			}
			String defUrl = "http://sportsdata.naver.com/ndata//kbo/" + year + "/" + month + "/" + year + month + day + ".nsd";	// 파싱할 주소
			Document doc;
			try {
				doc = Jsoup.connect(defUrl)
								.timeout(5000)
								.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// 유저에이전트 정의
								.get();
			} catch (IOException e) {
				doc = null;
			}
			String str = "";
			
			if(doc!=null) {
				Element elmt = doc.select("script").first();
				str = elmt.html();
				str = str.replace("document.domain=\"naver.com\";parent.sportscallback_gameList(document, ", "");
				str = str.replace(");", "");
				
			} else {
				str = "\"none\"";
			}

//			str = "\"" + year + month + "\":[" + str + ",";
			str = str + ",";
			data += str;
		}
		data = data.substring(0, data.length() - 1);
	    data += "]";

	    return data;
	}
}
