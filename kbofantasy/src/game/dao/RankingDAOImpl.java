package game.dao;

import static fw.Query.GET_B_TOP_PLAYER;
import static fw.Query.GET_P_TOP_PLAYER;
import static fw.Query.GET_TOP_USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankingDAOImpl implements RankingDAO {

	@Override
	public ArrayList<String[]> get_b_Player(Connection con) throws SQLException {
		PreparedStatement ptmt = con.prepareStatement(GET_B_TOP_PLAYER);
		int result = 0;
		ArrayList<String[]> toplist = new ArrayList<String[]>();
		ResultSet rs =  ptmt.executeQuery();
		while(rs.next()){
			String[] player = new String[5];
			player[0] = rs.getString(1);
			player[1] = rs.getString(2);
			player[2] = rs.getString(3);
			player[3] = rs.getString(4);
			player[4] = rs.getString(5);
			toplist.add(player);
			
		}
		return toplist;

	}
	@Override
	public ArrayList<String[]> get_p_Player(Connection con) throws SQLException {
		PreparedStatement ptmt = con.prepareStatement(GET_P_TOP_PLAYER);
		int result = 0;
		ArrayList<String[]> toplist = new ArrayList<String[]>();
		ResultSet rs =  ptmt.executeQuery();
		while(rs.next()){
			String[] player = new String[5];
			player[0] = rs.getString(1);
			player[1] = rs.getString(2);
			player[2] = rs.getString(3);
			player[3] = rs.getString(4);
			player[4] = rs.getString(5);
			toplist.add(player);
			
		}
		return toplist;
	}

	@Override
	public ArrayList<String[]> getUser(Connection con) throws SQLException {
		ArrayList<String[]> toplist= new ArrayList<String[]>();
		PreparedStatement ptmt = con.prepareStatement(GET_TOP_USER);
		int result=0;
		ResultSet rs =  ptmt.executeQuery();
		while(rs.next()){
			String[] user = new String[3];
			user[0]=rs.getString(1); 
			user[1]=rs.getString(2);
			user[2]=rs.getString(3);
			toplist.add(user);
		}
		return toplist;

	}

}
