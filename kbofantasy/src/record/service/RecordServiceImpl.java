package record.service;

import static fw.JdbcTemplate.close;
import static fw.JdbcTemplate.getConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import record.dao.RecordDAO;
import record.dao.RecordDAOImpl;
import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;
import record.dto.PlayerDTO;
import record.logic.RecordLogic;
import record.logic.RecordLogicImpl;


public class RecordServiceImpl implements RecordService {

	// record-경기일정/결과
	// 세부경기결과 크롤링
	@Override
	public String eventRecordData(String eventId) {
		String data = new String();
		RecordLogic logic = new RecordLogicImpl();
		try {
			data = logic.eventRecordData(eventId);
		} catch (IOException e) {
			e.printStackTrace();
			data = "fail";
		}
		return data;
	}

	// 오늘경기일정결과 크롤링(from nsd)
	@Override
	public String eventTodayData(String year, String month, String day) {
		String data = new String();
		RecordLogic logic = new RecordLogicImpl();
		try {
			data = logic.eventTodayData(year, month, day);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	// 경기일정결과 DB 호출
	@Override
	public ArrayList<EventDTO> eventList(String year, String month) {
		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		Connection con = getConnect();
		RecordDAO dao = new RecordDAOImpl();
		try {
			list = dao.eventList(year, month, con);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con);
		}
		return list;
	}
	
	// 기록실 타자 기록 파싱 로직
	@Override
	public String batterRecordData(String eventId) {
		String eventRecordData = new String();
		ArrayList<BatterDTO> list = new ArrayList<BatterDTO>();
		String result = "";
		Connection con = getConnect();

		RecordLogic logic = new RecordLogicImpl();
		RecordDAO dao = new RecordDAOImpl();

		try {
			eventRecordData = logic.eventRecordData(eventId);
			list = logic.batterRecordData(eventId, eventRecordData);
			result = dao.batterRecord(list, con);
		} catch (IOException | ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 기록실 투수 기록 파싱 로직
	@Override
	public String pitcherRecordData(String eventId) {
		String eventRecordData = new String();
		ArrayList<PitcherDTO> list = new ArrayList<PitcherDTO>();
		Connection con = getConnect();
		
		RecordLogic logic = new RecordLogicImpl();
		RecordDAO dao = new RecordDAOImpl();
		String result = "";
		
		try {
			eventRecordData = logic.eventRecordData(eventId);
			list = logic.pitcherRecordData(eventId, eventRecordData);
			result = dao.pitcherRecord(list, con);
		} catch (IOException | ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 경기일정결과 매일 파싱해서 DB 업데이트
	@Override
	public int dailyRecordData(String year, String month, String day) {
		String eventTodayData = new String();
		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		int result = 0;
		Connection con = getConnect();

		RecordLogic logic = new RecordLogicImpl();
		RecordDAO dao = new RecordDAOImpl();
		
		try {
			eventTodayData = logic.eventTodayData(year, month, day);
			list = logic.dailyRecordData(eventTodayData);
			result = dao.dailyRecord(list, con);
			//System.out.println(list.get(0).toString());
		} catch (IOException | ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String teamTableData() {
		String teamTableData = new String();
		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		Connection con = getConnect();
		
		RecordLogic logic = new RecordLogicImpl();
		RecordDAO dao = new RecordDAOImpl();

		try {
			list = dao.teamTable(con);
			teamTableData = logic.teamTableData(list);
			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return teamTableData;
	}

	@Override
	public PlayerDTO playerInfo(String playerCode) {
		PlayerDTO dto = new PlayerDTO();
		Connection con = getConnect();
		RecordDAO dao = new RecordDAOImpl();
		try {
			dto = dao.playerInfo(playerCode, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public BatterDTO batterStat(String playerCode) {
		BatterDTO dto = new BatterDTO();
		Connection con = getConnect();
		RecordDAO dao = new RecordDAOImpl();
		try {
			dto = dao.batterStat(playerCode, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public ArrayList<BatterDTO> batterLastStat(String playerCode) {
		ArrayList<BatterDTO> list = new ArrayList<BatterDTO>();
		Connection con = getConnect();
		RecordDAO dao = new RecordDAOImpl();
		try {
			list = dao.batterLastStat(playerCode, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		}

	@Override
	public PitcherDTO pitcherStat(String playerCode) {
		PitcherDTO dto = new PitcherDTO();
		Connection con = getConnect();
		RecordDAO dao = new RecordDAOImpl();
		try {
			dto = dao.pitcherStat(playerCode, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public ArrayList<PitcherDTO> pitcherLastStat(String playerCode) {
		ArrayList<PitcherDTO> list = new ArrayList<PitcherDTO>();
		Connection con = getConnect();
		RecordDAO dao = new RecordDAOImpl();
		try {
			list = dao.pitcherLastStat(playerCode, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
