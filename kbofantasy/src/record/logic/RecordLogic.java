package record.logic;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import record.dto.PlayerDTO;

public interface RecordLogic {
	
// 기록실 일정결과 관련 로직	
	// 세부경기결과 크롤링 로직
	String eventRecordData(String eventId) throws IOException;
	// 오늘경기일정결과 크롤링 로직
	String eventTodayData(String year, String month, String day) throws IOException;
	
	
	// 기록실 선수기록 파싱 로직
	ArrayList<PlayerDTO> playerRecordData(String eventId, String eventRecordData) throws ParseException;
	
	

	
	
	
	// 한달치 결과 크롤링(임시, 데이터 보관용)
	String eventMonthData(String year, String month);

	
	
}