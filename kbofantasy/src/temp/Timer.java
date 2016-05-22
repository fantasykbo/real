package temp;

import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import record.service.RecordService;
import record.service.RecordServiceImpl;

public class Timer {

	public static void main(String[] args) throws ParseException {
		
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

			String data = service.eventTodayData(year, month, day);
			JSONParser jsonParser = new JSONParser();
			JSONObject games = (JSONObject) jsonParser.parse(data);
			JSONArray gameArr = (JSONArray) games.get("games");
			
			
			// 
			int result = service.dailyRecordData(year, month, day);
			System.out.println(result + " Updates to Event_TB");
			
			int size = gameArr.size();
			for(int i = 0; i < size; i++) {
				JSONObject game = (JSONObject) gameArr.get(i);
				eventId = (String) game.get("gameId");

				String resultBatter = service.batterRecordData(eventId);
				System.out.println(eventId + "- B : " + resultBatter);
				String resultPitcher = service.pitcherRecordData(eventId);
				System.out.println(eventId + "- P : " + resultPitcher);
			}
	}
}