package record.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import record.dto.RecordDTO;

public interface RecordDAO {
	// 경기일정결과 DB
	ArrayList<RecordDTO> eventList(String year, String month, Connection con) throws SQLException;
}