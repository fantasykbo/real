package record.dao;

import static fw.JdbcTemplate.close;
import static fw.Query.*;
import game.dto.MyTeamDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;
import record.dto.PlayerDTO;

public class RecordDAOImpl implements RecordDAO {

	// 경기일정결과 목록 DB Select
	@Override
	public ArrayList<EventDTO> eventList(String year, String month,
			Connection con) throws SQLException {

		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		EventDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(EVENT_LIST);
		ptmt.setString(1, year + month);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			dto = new EventDTO(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), rs.getString(11),
					rs.getString(12));
			list.add(dto);
		}
		close(rs);
		close(ptmt);
		return list;
	}
	// 순위표 DB select
	@Override
	public ArrayList<EventDTO> teamTable(Connection con) throws SQLException {
		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		EventDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(EVENT_TABLE);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			dto = new EventDTO(rs.getString(1),
							   rs.getString(2),
							   rs.getString(3),
							   rs.getString(4),
							   rs.getString(5),
							   rs.getString(6),
							   rs.getString(7));
			list.add(dto);
		}
		close(rs);
		close(ptmt);
		return list;
	}
	// 선수정보 DB select
	@Override
	public PlayerDTO playerInfo(String playerCode, Connection con) throws SQLException {
		PlayerDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(PLAYER_INFO);
		ptmt.setString(1, playerCode);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			dto = new PlayerDTO(rs.getString(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getString(7),
								rs.getString(8),
								rs.getString(9),
								rs.getString(10),
								rs.getString(11),
								rs.getInt(12),
								rs.getString(13));
		}
		close(rs);
		close(ptmt);
		return dto;
	}
	// 타자 성적 DB select
	@Override
	public BatterDTO batterStat(String playerCode, Connection con)
			throws SQLException {
		BatterDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(B_STAT);
		ptmt.setString(1, playerCode);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			dto = new BatterDTO(rs.getInt(1),
								rs.getInt(2),
								rs.getFloat(3),
								rs.getFloat(4),
								rs.getFloat(5),
								rs.getFloat(6),
								rs.getInt(7),
								rs.getInt(8),
								rs.getInt(9),
								rs.getInt(10),
								rs.getInt(11),
								rs.getInt(12),
								rs.getInt(13),
								rs.getInt(14),
								rs.getInt(15),
								rs.getInt(16),
								rs.getInt(17),
								rs.getInt(18),
								rs.getInt(19));
		}
		close(rs);
		close(ptmt);
		return dto;
	}
	// 타자 최근 10경기 성적 DB select
	@Override
	public ArrayList<BatterDTO> batterLastStat(String playerCode, Connection con)
			throws SQLException {
		ArrayList<BatterDTO> list = new ArrayList<BatterDTO>();
		BatterDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(B_LAST_STAT);
		ptmt.setString(1, playerCode);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			dto = new BatterDTO(rs.getString(1),
								rs.getString(2),
								rs.getInt(3),
								rs.getFloat(4),
								rs.getInt(5),
								rs.getInt(6),
								rs.getInt(7),
								rs.getInt(8),
								rs.getInt(9),
								rs.getInt(10),
								rs.getInt(11),
								rs.getInt(12),
								rs.getInt(13),
								rs.getInt(14),
								rs.getInt(15),
								rs.getInt(16),
								rs.getInt(17));
			list.add(dto);
		}
		close(rs);
		close(ptmt);
		return list;
	}
	// 투수 성적 DB select
	@Override
	public PitcherDTO pitcherStat(String playerCode, Connection con)
			throws SQLException {
		PitcherDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(P_STAT);
		ptmt.setString(1, playerCode);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			dto = new PitcherDTO(rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getFloat(7),
								rs.getInt(8),
								rs.getInt(9),
								rs.getInt(10),
								rs.getInt(11),
								rs.getInt(12),
								rs.getInt(13),
								rs.getInt(14),
								rs.getInt(15),
								rs.getInt(16));
		}
		close(rs);
		close(ptmt);
		return dto;
	}
	// 투수 최근 10경기 성적 DB select
	@Override
	public ArrayList<PitcherDTO> pitcherLastStat(String playerCode,
			Connection con) throws SQLException {
		ArrayList<PitcherDTO> list = new ArrayList<PitcherDTO>();
		PitcherDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(P_LAST_STAT);
		ptmt.setString(1, playerCode);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			dto = new PitcherDTO(rs.getString(1),
								rs.getString(2),
								rs.getString(3),
								rs.getFloat(4),
								rs.getInt(5),
								rs.getInt(6),
								rs.getInt(7),
								rs.getInt(8),
								rs.getInt(9),
								rs.getInt(10),
								rs.getInt(11),
								rs.getInt(12),
								rs.getInt(13),
								rs.getInt(14));
			list.add(dto);
		}
		close(rs);
		close(ptmt);
		return list;
	}
	
	// 전날경기기록 DB update
	@Override
	public int dailyRecord(ArrayList<EventDTO> list, Connection con)
			throws SQLException {
		
		int result = 0;
		
		PreparedStatement ptmt = con.prepareStatement(EVENT_UPDATE);
		int size = list.size();
		for(int i = 0; i < size; i++) {
			ptmt.setString(1, list.get(i).getaScore());
			ptmt.setString(2, list.get(i).gethScore());
			ptmt.setString(3, list.get(i).getEventStatus());
			ptmt.setString(4, list.get(i).getCancelFlag());
			ptmt.setString(5, list.get(i).getEventCode());
			result += ptmt.executeUpdate();
		}
		close(ptmt);
		return result;
	}	
	
	// 전날경기 타자기록 DB insert
	@Override
	public String batterRecord(ArrayList<BatterDTO> list, Connection con)
			throws SQLException {
		String resultStr = "";
		int resultInsert = 0;
		int resultMerge = 0;
		
		PreparedStatement ptmt = con.prepareStatement(RECORD_B_INSERT);
		int size = list.size();
		for(int i = 0; i < size; i++) {
			ptmt.setString(1, list.get(i).getEventCode());
			ptmt.setString(2, list.get(i).getPlayerCode());
			ptmt.setInt(3, list.get(i).getPa());
			ptmt.setFloat(4, list.get(i).getHra());
			ptmt.setInt(5, list.get(i).getH1());
			ptmt.setInt(6, list.get(i).getH2());
			ptmt.setInt(7, list.get(i).getH3());
			ptmt.setInt(8, list.get(i).getHr());
			ptmt.setInt(9, list.get(i).getRs());
			ptmt.setInt(10, list.get(i).getRb());
			ptmt.setInt(11, list.get(i).getBb());
			ptmt.setInt(12, list.get(i).getIb());
			ptmt.setInt(13, list.get(i).getSh());
			ptmt.setInt(14, list.get(i).getOt());
			ptmt.setInt(15, list.get(i).getSo());
			ptmt.setInt(16, list.get(i).getDp());
			ptmt.setInt(17, list.get(i).getPoint());
			resultInsert += ptmt.executeUpdate();
		}
		ptmt = con.prepareStatement(PLAYER_B_POINT_MERGE);
		resultMerge = ptmt.executeUpdate();
		close(ptmt);
		
		resultStr = resultInsert + " Updates to RECORD_B_TB / " + resultMerge + " Merges to PLAYER_TB";
		return resultStr;
	}
	// 전날경기 투수기록 DB insert
	@Override
	public String pitcherRecord(ArrayList<PitcherDTO> list, Connection con)
			throws SQLException {
		String resultStr = "";
		int resultInsert = 0;
		int resultMerge = 0;
		
		PreparedStatement ptmt = con.prepareStatement(RECORD_P_INSERT);
		int size = list.size();
		for(int i = 0; i < size; i++) {
			ptmt.setString(1, list.get(i).getEventCode());
			ptmt.setString(2, list.get(i).getPlayerCode());
			ptmt.setString(3, list.get(i).getWlhs());
			ptmt.setFloat(4, list.get(i).getEra());
			ptmt.setInt(5, list.get(i).getInn());
			ptmt.setInt(6, list.get(i).getPa());
			ptmt.setInt(7, list.get(i).getBf());
			ptmt.setInt(8, list.get(i).getKk());
			ptmt.setInt(9, list.get(i).getHt());
			ptmt.setInt(10, list.get(i).getHr());
			ptmt.setInt(11, list.get(i).getBb());
			ptmt.setInt(12, list.get(i).getRs());
			ptmt.setInt(13, list.get(i).getEr());
			ptmt.setInt(14, list.get(i).getPoint());
			resultInsert += ptmt.executeUpdate();
		}
		ptmt = con.prepareStatement(PLAYER_P_POINT_MERGE);
		resultMerge = ptmt.executeUpdate();
		close(ptmt);
		
		resultStr = resultInsert + " Updates to RECORD_P_TB / " + resultMerge + " Merges to PLAYER_TB";
		return resultStr;
	}

	// GAME_TB에서 각 선수가 획득한 포인트 POINT 컬럼으로 MERGE
	@Override
	public int gamePoint(ArrayList<MyTeamDTO> list, Connection con)
			throws SQLException {
		int result = 0;
		MyTeamDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(GAME_POINT_MERGE);
		int size = list.size();
		for(int i = 0; i < size; i++) {
			dto = list.get(i);
			ptmt.setInt(1, dto.getMember_cd());
			ptmt.setString(2, dto.getGame_dt());
			ptmt.setInt(3, dto.getMember_cd());
			ptmt.setString(4, dto.getGame_dt());
			ptmt.setInt(5, dto.getMember_cd());
			ptmt.setString(6, dto.getGame_dt());
			ptmt.setInt(7, dto.getMember_cd());
			ptmt.setString(8, dto.getGame_dt());
			ptmt.setInt(9, dto.getMember_cd());
			ptmt.setString(10, dto.getGame_dt());
			ptmt.setInt(11, dto.getMember_cd());
			ptmt.setString(12, dto.getGame_dt());
			ptmt.setInt(13, dto.getMember_cd());
			ptmt.setString(14, dto.getGame_dt());
			ptmt.setInt(15, dto.getMember_cd());
			ptmt.setString(16, dto.getGame_dt());
			ptmt.setInt(17, dto.getMember_cd());
			ptmt.setString(18, dto.getGame_dt());
			ptmt.setInt(19, dto.getMember_cd());
			ptmt.setString(20, dto.getGame_dt());
			ptmt.setInt(21, dto.getMember_cd());
			ptmt.setString(22, dto.getGame_dt());
			ptmt.setInt(23, dto.getMember_cd());
			result += ptmt.executeUpdate();
		}
		close(ptmt);
		return result;
	}

	// GAME_TB에서 얻은 누적 포인트를 MEMBER_TB로 DB MERGE
	@Override
	public int memberPoint(Connection con) throws SQLException {
		int result = 0;
		PreparedStatement ptmt = con.prepareStatement(MEMBER_POINT_MERGE);
		result = ptmt.executeUpdate();
		return result;
	}
}
