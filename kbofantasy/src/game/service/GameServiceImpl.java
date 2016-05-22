package game.service;

import static fw.JdbcTemplate.close;
import static fw.JdbcTemplate.getConnect;
import game.dao.GameDao;
import game.dao.GameDaoImpl;
import game.dto.GamePlayerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameServiceImpl implements GameService {

	@Override
	public ArrayList<GamePlayerDTO> SelectMyList(int member_cd, String game_dt) {
		System.out.println("¼­ºñ½º");
		ArrayList<GamePlayerDTO> mypinfolist = new ArrayList<GamePlayerDTO>(); 
		Connection con = getConnect();
		GameDao dao = new GameDaoImpl();
		try {
			mypinfolist = dao.SelectMyList(member_cd, game_dt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mypinfolist;
	}

}
