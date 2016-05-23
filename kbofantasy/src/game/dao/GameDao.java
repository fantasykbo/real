package game.dao;

import game.dto.GamePlayerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GameDao {
	ArrayList<GamePlayerDTO> SelectMyList(int member_cd, String game_dt,
											Connection con) throws SQLException;
	ArrayList<GamePlayerDTO> Select_DT_List();
	ArrayList<GamePlayerDTO> ScoutMyPlayer
	(String position_dt, Connection con) throws SQLException;
//  추후 작업해야 할부분	
/*	int InsertMyTeam(int member_cd, String game_dt, String sp_cd, String rp_cd, String dh_cd, String c_cd,
			 String fb_cd, String sb_cd, String tb_cd, String ss_cd,
			 String lf_cd, String cf_cd, String rf_cd, Connection con) throws SQLException;

	int UpdateMyTeam(int member_cd, String game_dt, String sp_cd, String rp_cd, String dh_cd, String c_cd,
	 		 String fb_cd, String sb_cd, String tb_cd, String ss_cd,
	 		 String lf_cd, String cf_cd, String rf_cd, Connection con) throws SQLException;*/	
}
