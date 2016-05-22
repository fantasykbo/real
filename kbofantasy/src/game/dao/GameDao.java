package game.dao;

import game.dto.GamePlayerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GameDao {
	ArrayList<GamePlayerDTO> SelectMyList(int member_cd, String game_dt,
											Connection con) throws SQLException;
	ArrayList<GamePlayerDTO> Select_DT_List();
}
