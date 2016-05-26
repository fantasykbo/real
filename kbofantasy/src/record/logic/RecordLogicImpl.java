package record.logic;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;

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
<<<<<<< HEAD
				eventRecordData = m.group(1);	// 매칭된 패턴을 넘겨줄 String에 저장
=======
				eventRecordData = m.group(1);
>>>>>>> branch 'master' of https://github.com/fantasykbo/real.git
			}
<<<<<<< HEAD
		} else {
=======
>>>>>>> branch 'master' of https://github.com/fantasykbo/real.git
		}
		// 입력된 데이터 중 문제가 되는 패턴 찾아서 삭제
		eventRecordData = eventRecordData.replace("\\r\\n", "");
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
	    return eventTodayData;
	}
	
// 오늘 경기일정결과 파싱 로직
	@Override
	public ArrayList<EventDTO> dailyRecordData(String eventTodayData)
			throws ParseException {

		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		EventDTO dto = null;
		
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(eventTodayData.toString());
		JSONObject obj = (JSONObject) rawObj;
		JSONArray games = (JSONArray) obj.get("games");
		
		int size = games.size();
		for(int i = 0; i < size; i++) {

			dto = new EventDTO();
			
			JSONObject game = (JSONObject) games.get(i);
			JSONObject score = (JSONObject) game.get("score");
			
			dto.setEventCode((String) game.get("gameId"));
			dto.setaScore((Long) score.get("aScore") + "");
			dto.sethScore((Long) score.get("hScore") + "");
			dto.setEventStatus((String) game.get("statusCode"));
			dto.setCancelFlag((String) game.get("cancelFlag"));
			
			list.add(dto);
		}
		return list;
	}
	
	// 팀 순위 테이블 JSON 변환 로직
	@SuppressWarnings("unchecked")
	@Override
	public String teamTableData(ArrayList<EventDTO> list)
			throws ParseException {
		
		String teamTableData = new String();

		JSONObject teamTable = new JSONObject();
		JSONArray teamList = new JSONArray();
		JSONObject teamData = null;
		
		// 팀 코드를 담기 위한 ArrayList 생성
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("LG");
		temp.add("SK");
		temp.add("KT");
		temp.add("WO");
		temp.add("OB");
		temp.add("HH");
		temp.add("HT");
		temp.add("NC");
		temp.add("SS");
		temp.add("LT");
		// 팀 당 경기결과를 누적해서 담기 위한 반복문 
		for(int i = 0; i < temp.size(); i++) {
			teamData = new JSONObject();
			teamData.put("teamCode", temp.get(i));
			teamData.put("totalWin", 0);
			teamData.put("totalDraw", 0);
			teamData.put("totalLose", 0);
			teamData.put("awayWin", 0);
			teamData.put("awayDraw", 0);
			teamData.put("awayLose", 0);
			teamData.put("homeWin", 0);
			teamData.put("homeDraw", 0);
			teamData.put("homeLose", 0);
			teamData.put("wra", 0);
			teamData.put("gap", 0);
			teamData.put("teamName", "");
			teamList.add(teamData);
		}
		// 현재까지 진행된 경기 리스트로 for 돌림
		int size = list.size();
		for(int i = 0; i < size; i++) {
			EventDTO dto = list.get(i);
			// 한 DTO에 있는 점수의 차를 이용하여 승무패 구분
			int wdl = Integer.parseInt(dto.getaScore()) - Integer.parseInt(dto.gethScore());
			
			int listsize = teamList.size();
			for(int j = 0; j < listsize; j++) {
				
				teamData = (JSONObject) teamList.get(j);
				
				// 한 레코드에서 원정팀일 때 승무패 구분하여 저장
				if(dto.getaTeamCode().equals(teamData.get("teamCode"))) {
					teamData.put("teamName", dto.getaTeamSName());
					if(wdl > 0) {
						teamData.put("awayWin", (int) teamData.get("awayWin") + 1);
					} else if(wdl == 0) {
						teamData.put("awayDraw", (int) teamData.get("awayDraw") + 1);
					} else if(wdl < 0) {
						teamData.put("awayLose", (int) teamData.get("awayLose") + 1);
					}
				// 홈팀일 때 승무패 구분하여 저장 
				} else if(dto.gethTeamCode().equals(teamData.get("teamCode"))) {
					teamData.put("teamName", dto.gethTeamSName());
					if(wdl > 0) {
						teamData.put("homeLose", (int) teamData.get("homeLose") + 1);
					} else if(wdl == 0) {
						teamData.put("homeDraw", (int) teamData.get("homeDraw") + 1);
					} else if(wdl < 0) {
						teamData.put("homeWin", (int) teamData.get("homeWin") + 1);
					}
				}
				// 홈승무패 + 원정승무패 하여 총 승무패로 put
				teamData.put("totalWin", (int) teamData.get("homeWin") + (int) teamData.get("awayWin"));
				teamData.put("totalDraw", (int) teamData.get("homeDraw") + (int) teamData.get("awayDraw"));
				teamData.put("totalLose", (int) teamData.get("homeLose") + (int) teamData.get("awayLose"));

				// 순위는 승률로 결정하기 때문에, 승률을 계산해서 집어넣어야 한다
				// DivideByZeroException을 피하기 위해 기록이 없으면 0으로 표시
				if(teamData.get("totalWin").equals(0) & teamData.get("totalLose").equals(0)) {
					teamData.put("wra", "0.000");
				// 승률은 승리횟수 / (전체경기수 - 무승부)로 계산
				} else {
					int temp1 = (int) teamData.get("totalWin") + (int) teamData.get("totalLose");
					double wra = (int) teamData.get("totalWin") / (double) temp1;
					// 소수점 아래 세자리까지 고정하여 표시하기 위해 포맷을 설정
					DecimalFormat df = new DecimalFormat("0.000");
					teamData.put("wra", df.format(wra));
				}
				teamList.set(j, teamData);
			}
		}
		teamTable.put("teamList", teamList);
		teamTableData = teamTable.toJSONString();

		return teamTableData;
	}
	
	// 투수 기록 파싱 로직
	@Override
	public ArrayList<PitcherDTO> pitcherRecordData(String eventId, String eventRecordData) throws ParseException {

		ArrayList<PitcherDTO> list = new ArrayList<PitcherDTO>();
		PitcherDTO dto = null;

		// 넘겨받은 String JSON으로 파싱
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(eventRecordData.toString());
		JSONObject obj = (JSONObject) rawObj;

		// 투수 스코어박스 파싱
		JSONObject pitcher = (JSONObject) obj.get("pitchersBoxscore");
		
		// 메소드 호출
		PitcherData(eventId, "away", list, pitcher, dto);
		PitcherData(eventId, "home", list, pitcher, dto);
		
		return list;
	}
	
	// 투수기록 홈/원정 각각 파싱하는 메소드
	public static void PitcherData(String eventId, String awayHome, ArrayList<PitcherDTO> list, JSONObject pitcher, PitcherDTO dto) {
		// 홈/원정 파싱
		JSONArray pitcherRecord = (JSONArray) pitcher.get(awayHome);

		// 원정팀 투수선수별로 구분하는 for 시작
		for (int k = 0; k < pitcherRecord.size(); k++) {
			dto = new PitcherDTO();
			int point = 0;
			
			JSONObject pitcherRecordPlayer = (JSONObject) pitcherRecord.get(k);
			
			dto.setEventCode(eventId);	// 경기코드
			dto.setPlayerCode((String) pitcherRecordPlayer.get("pCode"));		// 선수코드
			
			// 승패홀세 구분 및 포인트
			String wlhs = "";
			wlhs = (String) pitcherRecordPlayer.get("wls");
			switch(wlhs) {
				case "승" :
					if(k == 0) {
						point += 125;
					} else {
						point += 100;
					}
					dto.setWlhs("W");
					break;
				case "패" :
					point += -25;
					dto.setWlhs("L");
					break;
				case "홀" :
					point += 40;
					dto.setWlhs("H");
					break;
				case "세" :
					point += 50;
					dto.setWlhs("S");
					break;
				case "" :
					break;
			}
			
			float era = Float.valueOf((String) pitcherRecordPlayer.get("era"));	// 평균자책점
			dto.setEra(era);
			
			// 이닝 계산
			int inn = 0;
			String innStr = (String) pitcherRecordPlayer.get("inn");
			if(innStr.contains(" ")) {		// 1/3, 2/3의 경우 분리해서 저장
				String[] innSplt = innStr.split(" ", 2);
				inn += Integer.parseInt(innSplt[0]) * 3;
				if(innSplt[1].equals("⅓")) {
					inn += 1;
				} else if(innSplt[1].equals("⅔")) {
					inn += 2;
				}
			} else {
				inn += Integer.parseInt(innStr) * 3;
			}
			dto.setInn(inn);												// 이닝저장

			dto.setPa(((Long)pitcherRecordPlayer.get("pa")).intValue());	// 상대타자
			dto.setBf(((Long)pitcherRecordPlayer.get("bf")).intValue());	// 투구수

			dto.setKk(((Long)pitcherRecordPlayer.get("kk")).intValue());	// 탈삼진
			dto.setHt(((Long)pitcherRecordPlayer.get("hit")).intValue());	// 피안타
			dto.setHr(((Long)pitcherRecordPlayer.get("hr")).intValue());	// 피홈런
			dto.setBb(((Long)pitcherRecordPlayer.get("bbhp")).intValue());	// 4사구
			dto.setRs(((Long)pitcherRecordPlayer.get("r")).intValue());		// 실점
			dto.setEr(((Long)pitcherRecordPlayer.get("er")).intValue());	// 자책점

			point += (dto.getInn() * 4)
				   + (dto.getKk() * 10)
				   + (dto.getHt() * -7)
				   + (dto.getHr() * -10)
				   + (dto.getBb() * -5)
				   + (dto.getRs() * -5)
				   + (dto.getEr() * -10);
			
			dto.setPoint(point);	// 점수계산

			list.add(dto);
		}
	}

	
	// 타자 기록 파싱 로직
	@Override
	public ArrayList<BatterDTO> batterRecordData(String eventId, String eventRecordData) throws ParseException {

		ArrayList<BatterDTO> list = new ArrayList<BatterDTO>();
		BatterDTO dto = null;

		// 넘겨받은 String JSON으로 파싱
		JSONParser parser = new JSONParser();
		Object rawObj = parser.parse(eventRecordData.toString());
		JSONObject obj = (JSONObject) rawObj;

		// 타자 스코어박스 파싱
		JSONObject batter = (JSONObject) obj.get("battersBoxscore");
		
		// static method 호출
		BatterData(eventId, "away", list, batter, dto);
		BatterData(eventId, "home", list, batter, dto);
		
		return list;
	}
	
	// 타자기록	홈/원정 각각 파싱하는 메소드
	public static void BatterData(String eventId, String awayHome, ArrayList<BatterDTO> list, JSONObject batter, BatterDTO dto) {
		// 홈/원정 파싱
		JSONArray batterRecord = (JSONArray) batter.get(awayHome);

		// 선수별로 구분하는 for 시작
		for (int k = 0; k < batterRecord.size(); k++) {
			dto = new BatterDTO();
			JSONObject batterRecordPlayer = (JSONObject) batterRecord.get(k);
			
			dto.setEventCode(eventId);	// 경기코드
			dto.setPlayerCode((String) batterRecordPlayer.get("playerCode"));	// 선수코드
			float hra = Float.valueOf((String) batterRecordPlayer.get("hra"));	// 타율
			dto.setHra(hra);

			// inn1 ~ inn12 까지 데이터 찾는 for 시작
			for (int j = 1; j <= 12; j++) {
				String str = (String) batterRecordPlayer.get("inn" + j);
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
					
					int ab = ((Long)batterRecordPlayer.get("ab")).intValue();		
					dto.setPa(ab + dto.getIb() + dto.getBb() + dto.getSh());		// 타석
					dto.setRs(((Long)batterRecordPlayer.get("run")).intValue());	// 득점
					dto.setRb(((Long)batterRecordPlayer.get("rbi")).intValue());	// 타점
					
					dto.setPoint((dto.getPa()) + (dto.getRs() * 5) + (dto.getRb() * 10));	// 점수계산
				}
			}
			list.add(dto);
		}
	}
	
	// 타격 결과 구분하는 메소드
	public static void Categorize(String bat, BatterDTO dto) {

		int len = bat.length();

		if (bat.contains("희")) {
			dto.setSh(dto.getSh() + 1);
			dto.setPoint(dto.getPoint() + dto.getSh() * 5);
//			System.out.println(bat + " : 희생타");
		} else {

			switch (bat.substring((len - 1), len)) {
			case "안":
				dto.setH1(dto.getH1() + 1);
				dto.setPoint(dto.getPoint() + dto.getH1() * 10);
//				System.out.println(bat + " : 1루타");
				break;

			case "2":
				dto.setH2(dto.getH2() + 1);
				dto.setPoint(dto.getPoint() + dto.getH2() * 20);
//				System.out.println(bat + " : 2루타");
				break;

			case "3":
				dto.setH3(dto.getH3() + 1);
				dto.setPoint(dto.getPoint() + dto.getH3() * 30);
//				System.out.println(bat + " : 3루타");
				break;

			case "홈":
				dto.setHr(dto.getHr() + 1);
				dto.setPoint(dto.getPoint() + dto.getHr() * 50);
//				System.out.println(bat + " : 홈런");
				break;

			case "구":
				dto.setBb(dto.getBb() + 1);
				dto.setPoint(dto.getPoint() + dto.getBb() * 5);
//				System.out.println(bat + " : 4사구");
				break;

			case "4":
				dto.setIb(dto.getIb() + 1);
				dto.setPoint(dto.getPoint() + dto.getIb() * 10);
//				System.out.println(bat + " : 고의4구");
				break;

			case "낫":
			case "진":
				dto.setSo(dto.getSo() + 1);
				dto.setPoint(dto.getPoint() + dto.getSo() * -10);
//				System.out.println(bat + " : 삼진");
				break;

			case "병":
			case "중":
				dto.setDp(dto.getDp() + 1);
				dto.setPoint(dto.getPoint() + dto.getDp() * -10);
//				System.out.println(bat + " : 병살타");
				break;

			case "땅":
			case "번":
			case "비":
			case "직":
			case "파":
				dto.setOt(dto.getOt() + 1);
				dto.setPoint(dto.getPoint() + dto.getOt() * -5);
//				System.out.println(bat + " : 아웃");
				break;

			default:
				break;
			}
		}
	}
}
