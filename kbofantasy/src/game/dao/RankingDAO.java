package game.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RankingDAO {
	//타자랭킹 얻기
	public ArrayList<String[]> get_b_Player(Connection con)throws SQLException;
	//투수랭킹 얻기
	public ArrayList<String[]> get_p_Player(Connection con)throws SQLException;
	//유저랭킹 얻기
	public ArrayList<String[]> getUser(Connection con)throws SQLException;
}
