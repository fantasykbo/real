package game.dao;

import static fw.JdbcTemplate.close;
import static fw.Query.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import game.dto.GamePlayerDTO;
import game.dto.MyTeamDTO;

public class GameDaoImpl implements GameDao {

	@Override
	public ArrayList<GamePlayerDTO> SelectMyList(int member_cd, String game_dt,
			Connection con) throws SQLException {
		System.out.println("dao");
		int i=0;
		ArrayList<MyTeamDTO> myteamlist = new ArrayList<MyTeamDTO>();
		MyTeamDTO myteam = null;		
		PreparedStatement ptmt = con.prepareStatement(MY_LIST);
		/*String mem_code = Integer.toString(member_cd);*/
		ptmt.setInt(1, member_cd);
		ptmt.setString(2, game_dt);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			myteam = new MyTeamDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								   rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
								   rs.getString(9), rs.getString(10), rs.getString(11));
			/*i++;
			myteam = new String(rs.getString(i));
			for(i=1; i<=11; i++){
			myteamlist.add(rs.getString(i));*/
			myteamlist.add(myteam);
		}
		close(rs);
		close(ptmt);
		
		ArrayList<GamePlayerDTO> mypinfolist = new ArrayList<GamePlayerDTO>();
		GamePlayerDTO mypinfo = null;
		PreparedStatement ptmt1 = con.prepareStatement(MY_PINFOLIST);		
		ptmt1.setString(1, myteamlist.get(0).getSp_cd());
		ptmt1.setString(2, myteamlist.get(0).getRp_cd());
		ptmt1.setString(3, myteamlist.get(0).getDh_cd());
		ptmt1.setString(4, myteamlist.get(0).getC_cd());
		ptmt1.setString(5, myteamlist.get(0).getFb_cd());
		ptmt1.setString(6, myteamlist.get(0).getSb_cd());
		ptmt1.setString(7, myteamlist.get(0).getTb_cd());
		ptmt1.setString(8, myteamlist.get(0).getSs_cd());
		ptmt1.setString(9, myteamlist.get(0).getLf_cd());
		ptmt1.setString(10, myteamlist.get(0).getCf_cd());
		ptmt1.setString(11, myteamlist.get(0).getRf_cd());
		ResultSet rs1 = ptmt1.executeQuery();
		while(rs1.next()){
			mypinfo = new GamePlayerDTO(rs1.getString(1), rs1.getString(2), rs1.getString(3),
										rs1.getString(4), rs1.getString(5), rs1.getString(6),
										rs1.getString(7));
			System.out.println(mypinfo);
			mypinfolist.add(mypinfo);			
		}
		close(rs1);
		close(ptmt1);
		return mypinfolist;
	}

	@Override
	public ArrayList<GamePlayerDTO> Select_DT_List() {
		
		return null;
	}

	@Override
	public ArrayList<GamePlayerDTO> ScoutMyPlayer (String position_dt,Connection con) 
									throws SQLException {
		System.out.println("scout dao");
		int i=0;
		ArrayList<GamePlayerDTO> myscoutlist = new ArrayList<GamePlayerDTO>();
		GamePlayerDTO scoutlist = null;
		System.out.println("dao포지션"+position_dt);
		
		if(position_dt=="1B" | position_dt=="2B" | position_dt=="3B" | position_dt=="SS"){
			PreparedStatement ptmt = con.prepareStatement(SCOUT_LIST2);
			ptmt.setString(1, position_dt);
			ptmt.setString(2, "IF");
			
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				scoutlist = new GamePlayerDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
									   rs.getString(5), rs.getString(6), rs.getString(7));
				
				myscoutlist.add(scoutlist);
			}
		}else if(position_dt=="LF" | position_dt=="RF" | position_dt=="CF"){
			PreparedStatement ptmt = con.prepareStatement(SCOUT_LIST2);
			ptmt.setString(1, position_dt);
			ptmt.setString(2, "OF");
			
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				scoutlist = new GamePlayerDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
									   rs.getString(5), rs.getString(6), rs.getString(7));
			
				myscoutlist.add(scoutlist);
			}
			
		}else if(position_dt=="DH"){
			PreparedStatement ptmt = con.prepareStatement(SCOUT_LIST3);
			ptmt.setString(1, "P");
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				scoutlist = new GamePlayerDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
									   rs.getString(5), rs.getString(6), rs.getString(7));
								myscoutlist.add(scoutlist);
			}
		}
		// C(포수)나 SS(유격수)일경우 그포지션_dt를 가지고 있는 선수만 검색
		PreparedStatement ptmt = con.prepareStatement(SCOUT_LIST1);
		ptmt.setString(1, position_dt);

		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			scoutlist = new GamePlayerDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								   rs.getString(5), rs.getString(6), rs.getString(7));
			myscoutlist.add(scoutlist);
		}
		System.out.println("daoimpl쿼리실행후"+myscoutlist.toString());
		close(rs);
		close(ptmt);
		
		return myscoutlist;
	}
//  추후 작업해야 할부분	
/*	@Override
	public int InsertMyTeam(int member_cd, String game_dt, String sp_cd,
			String rp_cd, String dh_cd, String c_cd, String fb_cd,
			String sb_cd, String tb_cd, String ss_cd, String lf_cd,
			String cf_cd, String rf_cd, Connection con) throws SQLException {
		PreparedStatement ptmt = con.prepareStatement(INSERT_MY_LIST);
		ptmt.setInt(1, member_cd);
		ptmt.setString(2, game_dt);
		ptmt.setString(3, sp_cd);
		ptmt.setString(4, rp_cd);
		ptmt.setString(5, dh_cd);
		ptmt.setString(6, c_cd);
		ptmt.setString(7, fb_cd);
		ptmt.setString(8, sb_cd);
		ptmt.setString(9, tb_cd);
		ptmt.setString(10, ss_cd);
		ptmt.setString(11, lf_cd);
		ptmt.setString(12, cf_cd);
		ptmt.setString(13, rf_cd);		
		int result = ptmt.executeUpdate();
		if(result>0){
			System.out.println("입력성공");
		}else{
			System.out.println("입력실패");
		}
		close(ptmt);
		return result;
	}
	@Override
	public int UpdateMyTeam(int member_cd, String game_dt, String sp_cd,
			String rp_cd, String dh_cd, String c_cd, String fb_cd,
			String sb_cd, String tb_cd, String ss_cd, String lf_cd,
			String cf_cd, String rf_cd, Connection con) throws SQLException {
		PreparedStatement ptmt = con.prepareStatement(UPDATE_MY_LIST);
		ptmt.setString(1, sp_cd);
		ptmt.setString(2, rp_cd);
		ptmt.setString(3, dh_cd);
		ptmt.setString(4, c_cd);
		ptmt.setString(5, fb_cd);
		ptmt.setString(6, sb_cd);
		ptmt.setString(7, tb_cd);
		ptmt.setString(8, ss_cd);
		ptmt.setString(9, lf_cd);
		ptmt.setString(10, cf_cd);
		ptmt.setString(11, rf_cd);
		ptmt.setInt(12, member_cd);
		ptmt.setString(13, game_dt);
		int result = ptmt.executeUpdate();
		if(result>0){
			System.out.println("수정성공");
		}else{
			System.out.println("수정실패");
		}
		close(ptmt);
		return result;
	}*/

}
