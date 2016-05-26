package record.dao;

import game.dto.MyTeamDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;
import record.dto.PlayerDTO;

public interface RecordDAO {
	// 경기일정결과 DB 호출
	ArrayList<EventDTO> eventList(String year, String month, Connection con) throws SQLException;
	// 팀 순위를 위한 경기결과 DB 호출
	ArrayList<EventDTO> teamTable(Connection con) throws SQLException;

	// 선수정보 DB 호출
	PlayerDTO playerInfo(String playerCode, Connection con) throws SQLException;
	// 타자 성적 DB 호출
	BatterDTO batterStat(String playerCode, Connection con) throws SQLException;
	// 타자 최근 10경기 성적 DB 호출
	ArrayList<BatterDTO> batterLastStat(String playerCode, Connection con) throws SQLException;
	// 투수 성적 DB 호출
	PitcherDTO pitcherStat(String playerCode, Connection con) throws SQLException;
	// 투수 최근 10경기 성적 DB 호출
	ArrayList<PitcherDTO> pitcherLastStat(String playerCode, Connection con) throws SQLException;
	
	// 오늘 경기 결과 DB Insert
	int dailyRecord(ArrayList<EventDTO> list, Connection con) throws SQLException;
	// 오늘 경기 기록 DB Insert
	String batterRecord(ArrayList<BatterDTO> list, Connection con) throws SQLException;
	String pitcherRecord(ArrayList<PitcherDTO> list, Connection con) throws SQLException;
	// 오늘 경기 기록 포인트 내 게임 DB update
	int gamePoint(ArrayList<MyTeamDTO> list, Connection con) throws SQLException;
	// 내 게임 포인트 누적 저장 update
	int memberPoint(Connection con) throws SQLException;

	
}