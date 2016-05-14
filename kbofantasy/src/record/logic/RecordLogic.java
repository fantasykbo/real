package record.logic;

import java.io.IOException;
import java.util.ArrayList;

import record.dto.RecordDTO;

public interface RecordLogic {
	// 세부경기결과 크롤링 로직
	String eventRecordData(String eventId) throws IOException;
	// 오늘경기일정결과 크롤링 로직
	String eventTodayData(String year, String month, String day) throws IOException;
}