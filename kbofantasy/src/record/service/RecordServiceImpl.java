package record.service;

import static fw.JdbcTemplate.close;
import static fw.JdbcTemplate.getConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import record.dao.RecordDAO;
import record.dao.RecordDAOImpl;
import record.dto.PlayerDTO;
import record.dto.RecordDTO;
import record.logic.RecordLogic;
import record.logic.RecordLogicImpl;


public class RecordServiceImpl implements RecordService {

	// ���ΰ���� ũ�Ѹ�
	@Override
	public String eventRecordData(String eventId) {
		String data = new String();
		RecordLogic logic = new RecordLogicImpl();
		try {
			data = logic.eventRecordData(eventId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("service : " + eventId + data);
		return data;
	}

	// ���ð��������� ũ�Ѹ�
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

	// ���������� DB
	@Override
	public ArrayList<RecordDTO> eventList(String year, String month) {
		ArrayList<RecordDTO> list = new ArrayList<RecordDTO>();
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

	// ��Ͻ� ���� ��� �Ľ� ����
	@Override
	public ArrayList<PlayerDTO> playerRecordData(String eventId, String eventRecordData) {
		ArrayList<PlayerDTO> list = new ArrayList<PlayerDTO>();
		
		return null;
	}
	
	// �Ѵ�ġ ũ�Ѹ�(�ӽ�)
	@Override
	public String eventMonthData(String year, String month) {
		String data = new String();
		RecordLogic logic = new RecordLogicImpl();
		data = logic.eventMonthData(year, month);
		return data;
	}
	
}
