package game.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RankingDAO {
	//Ÿ�ڷ�ŷ ���
	public ArrayList<String[]> get_b_Player(Connection con)throws SQLException;
	//������ŷ ���
	public ArrayList<String[]> get_p_Player(Connection con)throws SQLException;
	//������ŷ ���
	public ArrayList<String[]> getUser(Connection con)throws SQLException;
}
