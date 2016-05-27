package updater;

import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import record.service.RecordService;
import record.service.RecordServiceImpl;

// DB 자동 업데이트 해주는 클래스(타이머 연결 예정)

public class Updater {

	public static void main(String[] args) throws ParseException {
		// 어제 날짜 가져오기
		Calendar cc = Calendar.getInstance();
		String year = cc.get(Calendar.YEAR) + "";
		String month = (cc.get(Calendar.MONTH) + 1) + "";
		String day = (cc.get(Calendar.DATE) - 1) + "";

		if(month.length() == 1) { 
			month = "0" + month;
		}
		if(day.length() == 1) { 
			day = "0" + day;
		}		
		
		System.out.println(year + month + day);
		
		String eventId = null;
		
		RecordService service = new RecordServiceImpl();

		// 오늘 경기 결과 JSON 받아오기
		// 받아오는 JSON 예제 주소 : http://sportsdata.naver.com/ndata//kbo/2016/05/20160526.nsd
		String data = service.eventTodayData(year, month, day);
		// 받아온 내용 파싱
		JSONParser jsonParser = new JSONParser();
		JSONObject games = (JSONObject) jsonParser.parse(data);
		JSONArray gameArr = (JSONArray) games.get("games");
			
			
		// 위에서 파싱한 오늘 경기 결과를 EVENT_TB에 업데이트
		int result = service.dailyRecordData(year, month, day);
		System.out.println(result + " Updates to Event_TB");
		int size = gameArr.size();
		for(int i = 0; i < size; i++) {
			JSONObject game = (JSONObject) gameArr.get(i);
			eventId = (String) game.get("gameId");
			// 각 경기(하루 5경기)의 선수기록 파싱한 내용을 DB로 insert
			String resultBatter = service.batterRecordData(eventId);
			System.out.println(eventId + "- B : " + resultBatter);
			String resultPitcher = service.pitcherRecordData(eventId);
			System.out.println(eventId + "- P : " + resultPitcher);
		}
	}
}