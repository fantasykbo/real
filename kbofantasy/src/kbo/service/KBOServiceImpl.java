package kbo.service;

import static fw.JdbcTemplate.close;
import static fw.JdbcTemplate.getConnect;

import java.sql.Connection;
import java.sql.SQLException;

import kbo.dao.KBODAO;
import kbo.dao.KBODAOImpl;
import kbo.login.dto.KBOLoginDTO;

public class KBOServiceImpl implements KBOService {

	@Override
	public KBOLoginDTO login(String email, String password) {
		Connection con = getConnect();
		KBOLoginDTO mylog = null;
		KBODAO dao = new KBODAOImpl();
		try {
			System.out.println("����");
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

}
