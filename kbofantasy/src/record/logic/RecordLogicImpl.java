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
				eventRecordData = m.group(1);	// 
			}
		} else {
			System.out.println("로직널");
		}
		eventRecordData = eventRecordData.replace("\\r\\n", "");
//		System.out.println("logic : " + eventId + eventRecordData);
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
//		System.out.println("logic : " + year + month + day + eventTodayData);
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
				
			int wdl = Integer.parseInt(dto.getaScore()) - Integer.parseInt(dto.gethScore());
			
			int listsize = teamList.size();
			for(int j = 0; j < listsize; j++) {
				
				teamData = (JSONObject) teamList.get(j);
				
				
				if(dto.getaTeamCode().equals(teamData.get("teamCode"))) {
					teamData.put("teamName", dto.getaTeamSName());
					if(wdl > 0) {
						teamData.put("awayWin", (int) teamData.get("awayWin") + 1);
					} else if(wdl == 0) {
						teamData.put("awayDraw", (int) teamData.get("awayDraw") + 1);
					} else if(wdl < 0) {
						teamData.put("awayLose", (int) teamData.get("awayLose") + 1);
					}
					
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
				
				teamData.put("totalWin", (int) teamData.get("homeWin") + (int) teamData.get("awayWin"));
				teamData.put("totalDraw", (int) teamData.get("homeDraw") + (int) teamData.get("awayDraw"));
				teamData.put("totalLose", (int) teamData.get("homeLose") + (int) teamData.get("awayLose"));

				if(teamData.get("totalWin").equals(0) & teamData.get("totalLose").equals(0)) {
					teamData.put("wra", "0.000");
				} else {
					int temp1 = (int) teamData.get("totalWin") + (int) teamData.get("totalLose");
					double wra = (int) teamData.get("totalWin") / (double) temp1;
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
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////

	//	// 선수 기록 파싱 로직
//		@Override
//		public ArrayList<RecordDTO> playerRecordData(String eventId, String eventRecordData) throws ParseException {
//
//			ArrayList<RecordDTO> list = new ArrayList<RecordDTO>();
//			RecordDTO dto = null;
//
//			// 넘겨받은 String JSON으로 파싱
//			JSONParser parser = new JSONParser();
//			Object rawObj = parser.parse(eventRecordData.toString());
//			JSONObject obj = (JSONObject) rawObj;
//
//			// 스코어박스 파싱
//			JSONObject pitcher = (JSONObject) obj.get("pitchersBoxscore");
//			JSONObject batter = (JSONObject) obj.get("battersBoxscore");
//			
//			// 메소드 호출
//			PitcherData(eventId, "away", list, pitcher, dto);
//			PitcherData(eventId, "home", list, pitcher, dto);
//			BatterData(eventId, "away", list, batter, dto);
//			BatterData(eventId, "home", list, batter, dto);
//			
//			return list;
//		}
//		
//	// 투수기록 홈/원정 각각 파싱하는 메소드
//		public static void PitcherData(String eventId, String awayHome, ArrayList<RecordDTO> list, JSONObject pitcher, RecordDTO dto) {
//			// 홈/원정 파싱
//			JSONArray pitcherRecord = (JSONArray) pitcher.get(awayHome);
//
//			// 투수별로 구분하는 for 시작
//			for (int k = 0; k < pitcherRecord.size(); k++) {
//				dto = new RecordDTO();
//				int point = 0;
//				
//				JSONObject pitcherRecordPlayer = (JSONObject) pitcherRecord.get(k);
//				
//				dto.setEventCode(eventId);	// 경기코드
//				dto.setPlayerCode((String) pitcherRecordPlayer.get("pCode"));	// 선수코드
//				
//				// 승패홀세 구분 및 포인트
//				String wlhs = "";
//				wlhs = (String) pitcherRecordPlayer.get("wls");
//				switch(wlhs) {
//					case "승" :
//						if(k == 0) {
//							point += 125;
//						} else {
//							point += 100;
//						}
//						dto.setPwlhs("W");
//						break;
//					case "패" :
//						point += -25;
//						dto.setPwlhs("L");
//						break;
//					case "홀" :
//						point += 40;
//						dto.setPwlhs("H");
//						break;
//					case "세" :
//						point += 50;
//						dto.setPwlhs("S");
//						break;
//					case "" :
//						break;
//				}
//				
//				float era = Float.valueOf((String) pitcherRecordPlayer.get("era"));	// 평균자책점
//				dto.setPera(era);
//				
//				// 이닝 계산
//				int inn = 0;
//				String innStr = (String) pitcherRecordPlayer.get("inn");
//				if(innStr.contains(" ")) {								// 1/3, 2/3의 경우 분리해서 저장
//					String[] innSplt = innStr.split(" ", 2);
//					inn += Integer.parseInt(innSplt[0]) * 3;
//					if(innSplt[1].equals("⅓")) {
//						inn += 1;
//					} else if(innSplt[1].equals("⅔")) {
//						inn += 2;
//					}
//				} else {
//					inn += Integer.parseInt(innStr) * 3;
//				}
//				dto.setPinn(inn);												// 이닝저장
//
//				dto.setPpa(((Long)pitcherRecordPlayer.get("pa")).intValue());	// 상대타자
//				dto.setPbf(((Long)pitcherRecordPlayer.get("bf")).intValue());	// 투구수
//
//				dto.setPkk(((Long)pitcherRecordPlayer.get("kk")).intValue());	// 탈삼진
//				dto.setPht(((Long)pitcherRecordPlayer.get("hit")).intValue());	// 피안타
//				dto.setPhr(((Long)pitcherRecordPlayer.get("hr")).intValue());	// 피홈런
//				dto.setPbb(((Long)pitcherRecordPlayer.get("bbhp")).intValue());	// 4사구
//				dto.setPrs(((Long)pitcherRecordPlayer.get("r")).intValue());	// 실점
//				dto.setPer(((Long)pitcherRecordPlayer.get("er")).intValue());	// 자책점
//
//				point += (dto.getPinn() * 4)
//					   + (dto.getPkk() * 10)
//					   + (dto.getPht() * -7)
//					   + (dto.getPhr() * -10)
//					   + (dto.getPbb() * -5)
//					   + (dto.getPrs() * -5)
//					   + (dto.getPer() * -10);
//				
//				dto.setPoint(dto.getPoint() + point);	// 점수계산
//
//				list.add(dto);
//			}
//		}
//
//	// 타자기록	홈/원정 각각 파싱하는 메소드
//		public static void BatterData(String eventId, String awayHome, ArrayList<RecordDTO> list, JSONObject batter, RecordDTO dto) {
//			// 홈/원정 파싱
//			JSONArray batterRecord = (JSONArray) batter.get(awayHome);
//
//			// 선수별로 구분하는 for 시작
//			for (int k = 0; k < batterRecord.size(); k++) {
//				dto = new RecordDTO();
//				int point = 0;
//				
//				JSONObject batterRecordPlayer = (JSONObject) batterRecord.get(k);
//				
//				dto.setEventCode(eventId);	// 경기코드
//				dto.setPlayerCode((String) batterRecordPlayer.get("playerCode"));	// 선수코드
//				float hra = Float.valueOf((String) batterRecordPlayer.get("hra"));	// 타율
//				dto.setHhra(hra);
//
//				// inn1 ~ inn12 까지 데이터 찾는 for 시작
//				for (int j = 1; j <= 12; j++) {
//					String str = (String) batterRecordPlayer.get("inn" + j);
//					// 빈 값일 경우 패스
//					if (str.equals("")) {
//					} else {
//						// 한 타자가 한 이닝에 두번 타격할 경우(/로 구분된 경우) 분리
//						if (str.contains("/")) {
//							StringTokenizer tok = new StringTokenizer(str, "/");
//							while (tok.hasMoreElements()) {
//								Categorize(point, tok.nextToken(), dto);
//							}
//						} else {
//							Categorize(point, str, dto);
//						}
//						
//						int ab = ((Long)batterRecordPlayer.get("ab")).intValue();		
//						dto.setHpa(ab + dto.getHib() + dto.getHbb() + dto.getHsh());		// 타석
//						dto.setHrs(((Long)batterRecordPlayer.get("run")).intValue());	// 득점
//						dto.setHrb(((Long)batterRecordPlayer.get("rbi")).intValue());	// 타점
//						
//						point += (dto.getHpa() * 1)
//							   + (dto.getHrs() * 5)
//							   + (dto.getHrb() * 10);
//						dto.setPoint(dto.getPoint() + point);	// 점수계산
//					}
//				}
//				list.add(dto);
//			}
//		}
//		
//		
//	// 타격 결과 구분하는 메소드
//		public static void Categorize( int point, String str, RecordDTO dto) {
//
//			int len = str.length();
//
//			if (str.contains("희")) {	// 희생타
//				dto.setHsh(dto.getHsh() + 1);
//				point += dto.getHsh() * 5;
//			} else {
//
//				switch (str.substring((len - 1), len)) {
//				case "안":	// 1루타
//					dto.setHh1(dto.getHh1() + 1);
//					point += dto.getHh1() * 10;
//					break;
//
//				case "2":	// 2루타
//					dto.setHh2(dto.getHh2() + 1);
//					point += dto.getHh2() * 20;
//					break;
//
//				case "3":	// 3루타
//					dto.setHh3(dto.getHh3() + 1);
//					point += dto.getHh3() * 30;
//					break;
//
//				case "홈":	// 홈런
//					dto.setHhr(dto.getHhr() + 1);
//					point += dto.getHhr() * 50;
//					break;
//
//				case "구":	// 4사구
//					dto.setHbb(dto.getHbb() + 1);
//					point += dto.getHbb() * 5;
//					break;
//
//				case "4":	// 고의4구
//					dto.setHib(dto.getHib() + 1);
//					point += dto.getHib() * 10;
//					break;
//
//				case "낫":	// 낫아웃
//				case "진":	// 삼진
//					dto.setHso(dto.getHso() + 1);
//					point += dto.getHso() * -10;
//					break;
//
//				case "병":	// 병살
//				case "중":	// 3중살
//					dto.setHdp(dto.getHdp() + 1);
//					point += dto.getHdp() * -10;
//					break;
//
//				case "땅":	// 땅볼아웃
//				case "번":	// 번트아웃
//				case "비":	// 플라이아웃
//				case "직":	// 직선타아웃
//				case "파":	// 파울플라이아웃
//					dto.setHot(dto.getHot() + 1);
//					point += dto.getHot() * -5;
//					break;
//
//				default:
//					break;
//				}
//			}
//
//		}
//
//	
//	
/////////////////////////////////////////////////////////////////////////////
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
//		System.out.println(pitcherRecord.size());
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

	
//	// nsd 합체 크롤링 로직(사용하지 않음)
//	@Override
//	public String eventMonthData(String year, String month) {
//		
//		String day = "";
//		String data = "[";
//		
//		Calendar cal = Calendar.getInstance();
//		cal.set(Integer.parseInt(year), (Integer.parseInt(month) - 1), 1);
//		int lastDay = cal.getActualMaximum(Calendar.DATE);
//
//		for (int i = 1; i <= lastDay; i++) {
//		
//			if(i < 10) {
//				day = "0" + i;
//			} else {
//				day = "" + i;
//			}
//			String defUrl = "http://sportsdata.naver.com/ndata//kbo/" + year + "/" + month + "/" + year + month + day + ".nsd";	// 파싱할 주소
//			Document doc;
//			try {
//				doc = Jsoup.connect(defUrl)
//								.timeout(5000)
//								.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")	// 유저에이전트 정의
//								.get();
//			} catch (IOException e) {
//				doc = null;
//			}
//			String str = "";
//			
//			if(doc!=null) {
//				Element elmt = doc.select("script").first();
//				str = elmt.html();
//				str = str.replace("document.domain=\"naver.com\";parent.sportscallback_gameList(document, ", "");
//				str = str.replace(");", "");
//				
//			} else {
//				str = "\"none\"";
//			}
//
////			str = "\"" + year + month + "\":[" + str + ",";
//			str = str + ",";
//			data += str;
//		}
//		data = data.substring(0, data.length() - 1);
//	    data += "]";
//
//	    return data;
//	}

}
