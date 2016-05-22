package record.logic;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;

public interface RecordLogic {
	
// 기록실 일정결과 관련 로직	
	// 세부경기결과 크롤링 로직
	String eventRecordData(String eventId) throws IOException;
	// 오늘경기일정결과 크롤링 로직
	String eventTodayData(String year, String month, String day) throws IOException;
	
// 기록실 선수기록 파싱 로직
	// 타자기록 파싱 로직
	ArrayList<BatterDTO> batterRecordData(String eventId, String eventRecordData) throws ParseException;
	// 투수기록 파싱 로직
	ArrayList<PitcherDTO> pitcherRecordData(String eventId, String eventRecordData) throws ParseException;

	// 팀 순위 테이블 파싱 로직
	String teamTableData(ArrayList<EventDTO> list) throws ParseException;
	
	
	// 선수기록 파싱 로직
//	ArrayList<RecordDTO> playerRecordData(String eventId, String eventRecordData) throws ParseException;
	
	
	
	//
	ArrayList<EventDTO> dailyRecordData(String eventTodayData) throws ParseException;
	
//	// 한달치 결과 크롤링(임시, 데이터 보관용 - 사용완료)
//	String eventMonthData(String year, String month);

	
	
}