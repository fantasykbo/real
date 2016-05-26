package record.service;

import java.util.ArrayList;

import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;
import record.dto.PlayerDTO;

public interface RecordService {

	// 세부경기결과 크롤링(from Logic)
	String eventRecordData(String eventId);
	// 오늘경기일정결과 크롤링(from Logic)
	String eventTodayData(String year, String month, String day);
	
	// 기록실 선수기록 파싱해서 DB Insert
	String batterRecordData(String eventId);
	String pitcherRecordData(String eventId);
	
	// 경기일정결과 매일 파싱해서 DB Update
	int dailyRecordData(String year, String month, String day);
	
	// 경기일정결과 DB에서 불러오기(from DAO)
	ArrayList<EventDTO> eventList(String year, String month);
	
	// 팀 순위
	String teamTableData();

	// 선수정보 DB 호출
	PlayerDTO playerInfo(String playerCode);
	// 타자 성적 DB 호출
	BatterDTO batterStat(String playerCode);
	// 타자 최근 10경기 성적 DB 호출
	ArrayList<BatterDTO> batterLastStat(String playerCode);
	// 투수 성적 DB 호출
	PitcherDTO pitcherStat(String playerCode);
	// 투수 최근 10경기 성적 DB 호출
	ArrayList<PitcherDTO> pitcherLastStat(String playerCode);

}
