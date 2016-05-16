package record.service;

import java.util.ArrayList;

import record.dto.RecordDTO;


public interface RecordService {

	// 세부경기결과 크롤링
	String eventRecordData(String eventId);
	// 오늘경기일정결과 크롤링
	String eventTodayData(String year, String month, String day);
	// 경기일정결과 DB
	ArrayList<RecordDTO> eventList(String year, String month);
	
	// 한달치 결과 크롤링(임시, 데이터 보관용)
	String eventMonthData(String year, String month);

}
