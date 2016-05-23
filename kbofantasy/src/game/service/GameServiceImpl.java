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
		System.out.println("서비스");
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

	@Override
	public ArrayList<GamePlayerDTO> ScoutPlayer(String position_dt) {
		System.out.println("선수영입 서비스");
		ArrayList<GamePlayerDTO> myscoutplayer = new ArrayList<GamePlayerDTO>(); 
		Connection con = getConnect();
		System.out.println("서비스포지션"+position_dt);
		GameDao dao = new GameDaoImpl();
		try {
			myscoutplayer = dao.ScoutMyPlayer(position_dt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myscoutplayer;
	}

}
