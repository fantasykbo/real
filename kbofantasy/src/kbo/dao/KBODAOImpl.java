package kbo.dao;

import static fw.JdbcTemplate.*;
import static fw.Query.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kbo.login.dto.KBOLoginDTO;

public class KBODAOImpl implements KBODAO{

	@Override
	public KBOLoginDTO login(String email, String password, Connection con)
			throws SQLException {

			PreparedStatement ptmt = con.prepareStatement(MEMBER_LOGIN);
			ptmt.setString(1, email);
			ptmt.setString(2,password);
			
			KBOLoginDTO mylog = null;
			
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()){
				System.out.println("로그인 성공");
				mylog=new KBOLoginDTO(rs.getString(2),rs.getString(3));
				System.out.println(mylog);
				
			}
			System.out.println("dao");
			
		
			close(rs);
			close(ptmt);
			return mylog;
		}


	@Override
	public KBOLoginDTO register(KBOLoginDTO register, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ptmt =con.prepareStatement(MEMBER_REGISTER1);
		ptmt.setString(1, register.getName());
		ptmt.setString(2, register.getEmail());
		ptmt.setString(3, register.getPassword());
		ptmt.setString(4, register.getTeam_cd());
		ptmt.setInt(5, register.getPoint());
		
		
		int result = ptmt.executeUpdate();
		if(result>0){
			System.out.println("입력성공");
		}else{
			System.out.println("입력실패");
		}
		close(ptmt);
		return register;
	}


	@Override
	public boolean regemailCheck(Connection con, String email) throws SQLException {
		boolean result = false;
		PreparedStatement ptmt = con.prepareStatement(emailCheck);
		ptmt.setString(1, email);
		ResultSet rs = ptmt.executeQuery();
		if (rs.next()) {
			System.out.println("데이터있음");
			result = true;
		}
		close(rs, ptmt, null);
		return result;
	}


	@Override
	public boolean passCheck(Connection con, String password)
			throws SQLException {
		boolean result = false;
		PreparedStatement ptmt = con.prepareStatement(passCheck);
		ptmt.setString(1, password);
		ResultSet rs = ptmt.executeQuery();
		if (rs.next()) {
			System.out.println("데이터있음");
			result = true;
		}
		close(rs, ptmt, null);
		return result;
	}

}


	



