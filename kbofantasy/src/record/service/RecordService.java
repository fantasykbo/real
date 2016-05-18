package record.service;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import record.dto.PlayerDTO;
import record.dto.RecordDTO;


public interface RecordService {

	// 세부경기결과 크롤링(from Logic)
	String eventRecordData(String eventId);
	// 오늘경기일정결과 크롤링(from Logic)
	String eventTodayData(String year, String month, String day);
	
	// 기록실 선수기록 파싱(from Logic)
	ArrayList<PlayerDTO> playerRecordData(String eventId, String eventRecordData);

	
	// 경기일정결과 DB(from DAO(
	ArrayList<RecordDTO> eventList(String year, String month);
	
	
	
	// 한달치 결과 크롤링(임시, 데이터 보관용)
	String eventMonthData(String year, String month);

}
