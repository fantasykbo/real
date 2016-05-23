package member.service;

import static fw.JdbcTemplate.close;
import static fw.JdbcTemplate.getConnect;

import java.sql.Connection;
import java.sql.SQLException;

import member.dao.KBODAO;
import member.dao.KBODAOImpl;
import member.dto.KBOLoginDTO;

public class KBOServiceImpl implements KBOService {

	@Override
	public KBOLoginDTO login(String email, String password) {
		Connection con = getConnect();
		KBOLoginDTO mylog = null;
		KBODAO dao = new KBODAOImpl();
		try {
			System.out.println("서비스");
			mylog = dao.login(email, password, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mylog;
	}

	@Override
	public KBOLoginDTO register(KBOLoginDTO register) {
		Connection con = getConnect();
		KBOLoginDTO myreg = null;
		KBODAO dao = new KBODAOImpl();
		try {
			myreg = dao.register(register, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myreg;

	}

	@Override
	public boolean emailcheck(String email) {
		Connection con = getConnect();
		KBODAO dao = new KBODAOImpl();
		boolean result = false;
		
		try {
			result = dao.regemailCheck(con, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(null, null, con);
		return result;

	}
	public static void main(String[] args){
		KBOServiceImpl obj = new KBOServiceImpl();
		obj.emailcheck("kim@naver.com");
	}

	@Override
	public boolean passcheck(String password) {
		Connection con = getConnect();
		KBODAO dao = new KBODAOImpl();
		boolean result = false;
		
		try {
			result = dao.passCheck(con, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(null, null, con);
		return result;
	}
	
	@Override
	public int memberleave(String email,String password) {
		Connection con = getConnect();
		int result=0;
		KBODAO dao = new KBODAOImpl();
		try {
			System.out.println("서비스");
			result = dao.memberleave(email, password, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean logincheck(String email, String password) {
		Connection con = getConnect();
		KBODAO dao = new KBODAOImpl();
		boolean result = false;
		
		try {
			result = dao.loginCheck(con,email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(null, null, con);
		return result;
	}

	@Override
	public int changepass(String email,String password) {
		Connection con = getConnect();
		KBOLoginDTO chpass = null;
		KBODAO dao = new KBODAOImpl();
		int result=0;
		try {
			result = dao.changepassword(email,password, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int register1(KBOLoginDTO register) {
		Connection con = getConnect();
		KBODAO dao = new KBODAOImpl();
		int result = 0;
		try{
			result = dao.insert(register, con);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(con);
		}
		return result;
	}

}
