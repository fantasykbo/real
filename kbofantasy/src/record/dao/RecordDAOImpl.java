package record.dao;

import static fw.JdbcTemplate.close;
import static fw.Query.EVENT_LIST;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import record.dto.PlayerDTO;
import record.dto.RecordDTO;

public class RecordDAOImpl implements RecordDAO {

	@Override
	public ArrayList<RecordDTO> eventList(String year, String month,
			Connection con) throws SQLException {

		ArrayList<RecordDTO> list = new ArrayList<RecordDTO>();
		RecordDTO dto = null;
		PreparedStatement ptmt = con.prepareStatement(EVENT_LIST);
		ptmt.setString(1, year + month);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			dto = new RecordDTO(rs.getString(1), rs.getString(2),
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
}
