package game.dao;

import static fw.JdbcTemplate.close;
import static fw.Query.MY_LIST;
import static fw.Query.MY_PINFOLIST;
import game.dto.GamePlayerDTO;
import game.dto.MyTeamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
/*	@Override
	public ArrayList<GamePlayerDTO> SelectMyList(int member_cd, String game_dt, position_Dt
			Connection con) throws SQLException {
		System.out.println("dao");
		int i=0;
		ArrayList<MyTeamDTO> myteamlist = new ArrayList<MyTeamDTO>();
		MyTeamDTO myteam = null;		
		PreparedStatement ptmt = con.prepareStatement(MY_LIST);
		String mem_code = Integer.toString(member_cd);
		ptmt.setInt(1, member_cd);
		ptmt.setString(2, game_dt);
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			myteam = new MyTeamDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								   rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
								   rs.getString(9), rs.getString(10), rs.getString(11));
			i++;
			myteam = new String(rs.getString(i));
			for(i=1; i<=11; i++){
			myteamlist.add(rs.getString(i));
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
										rs1.getString(4), rs1.getString(5));
			System.out.println(mypinfo);
			mypinfolist.add(mypinfo);			
		}
		close(rs1);
		close(ptmt1);
		return mypinfolist;
	}*/

}
