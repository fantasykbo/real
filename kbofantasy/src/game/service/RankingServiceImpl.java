package game.service;

import static fw.JdbcTemplate.getConnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import game.dao.RankingDAO;
import game.dao.RankingDAOImpl;

public class RankingServiceImpl implements RankingService{

	@Override
	public ArrayList<String[]> getUser(){
		
		Connection con = getConnect();
		ArrayList<String[]> toplist= new ArrayList<String[]>();
		RankingDAO dao = new RankingDAOImpl();
		try {
			toplist = dao.getUser(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toplist;	
	}

	@Override
	public ArrayList<String[]> get_b_player() {
		Connection con = getConnect();
		ArrayList<String[]> toplist= new ArrayList<String[]>();
		RankingDAO dao = new RankingDAOImpl();
		try {
			toplist = dao.get_b_Player(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toplist;
	}

	@Override
	public ArrayList<String[]> get_p_player() {
		Connection con = getConnect();
		ArrayList<String[]> toplist= new ArrayList<String[]>();
		RankingDAO dao = new RankingDAOImpl();
		try {
			toplist = dao.get_p_Player(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toplist;
	}


}
	