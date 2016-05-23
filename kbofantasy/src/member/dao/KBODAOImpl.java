package member.dao;

import static fw.JdbcTemplate.close;
import static fw.Query.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.dto.KBOLoginDTO;

public class KBODAOImpl implements KBODAO {

	@Override
	public KBOLoginDTO login(String email, String password, Connection con)
			throws SQLException {

		PreparedStatement ptmt = con.prepareStatement(MEMBER_LOGIN);
		ptmt.setString(1, email);
		ptmt.setString(2, password);
		int result = 0;
		KBOLoginDTO mylog = null;

		ResultSet rs = ptmt.executeQuery();

		while (rs.next()) {
			System.out.println("로그인 성공");
			mylog = new KBOLoginDTO(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getInt(6));
			System.out.println("dao로그인객체" + mylog);

		}
		
		close(rs);
		close(ptmt);
		
		return mylog;
		
	}

	@Override
	public KBOLoginDTO register(KBOLoginDTO register, Connection con)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ptmt = con.prepareStatement(MEMBER_REGISTER1);
		ptmt.setString(1, register.getEmail());
		ptmt.setString(2, register.getPassword());
		ptmt.setString(3, register.getName());
		ptmt.setString(4, register.getTeam_cd());
		ptmt.setInt(5, register.getPoint());
		
		int result = ptmt.executeUpdate();
		if (result > 0) {
			System.out.println("입력성공");
		} else {
			System.out.println("입력실패");
		
			
		}
		close(ptmt);
		return register;
	}

	@Override
	public boolean regemailCheck(Connection con, String email)
			throws SQLException {
		boolean result = true;
		PreparedStatement ptmt = con.prepareStatement(emailCheck);
		ptmt.setString(1, email);
		ResultSet rs = ptmt.executeQuery();
		if (rs.next()) {
			System.out.println("이메일중복있음");
			result = false;
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

	@Override
	public int memberleave(String email, String password, Connection con)
			throws SQLException {
		PreparedStatement ptmt = con.prepareStatement(MEMBER_LEAVE);
		ptmt.setString(1, email);
		ptmt.setString(2, password);

		KBOLoginDTO mylog = null;

		int result = ptmt.executeUpdate();
		close(ptmt);

		System.out.println("dao의 result:" + result);

		return result;
	}

	@Override
	public boolean loginCheck(Connection con,String email, String password)
			throws SQLException {
		boolean result = false;
		PreparedStatement ptmt = con.prepareStatement(MEMBER_LOGIN);
		ptmt.setString(1, email);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		if (rs.next()) {
			System.out.println("데이터있음");
			result = true;
		}
		close(rs, ptmt, null);
		return result;
	}

	@Override
	public int changepassword(String email, String password, Connection con)
			throws SQLException {
		PreparedStatement ptmt = con.prepareStatement(CHANGE_PASSWORD);
		
		ptmt.setString(1, password);
		ptmt.setString(2, email);
		
		KBOLoginDTO chpass = null;
		int result=ptmt.executeUpdate();
		
		
		
		close(ptmt);
		return result;
	}

	@Override
	public int insert(KBOLoginDTO register, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ptmt = con.prepareStatement(MEMBER_REGISTER1);
		ptmt.setString(1, register.getEmail());
		ptmt.setString(2, register.getPassword());
		ptmt.setString(3, register.getName());
		ptmt.setString(4, register.getTeam_cd());
		ptmt.setInt(5, register.getPoint());
		
		int result = ptmt.executeUpdate();
		if (result > 0) {
			System.out.println("입력성공");
		} else {
			System.out.println("입력실패");
		
			
		}
		close(ptmt);
		return result;

	}

}
