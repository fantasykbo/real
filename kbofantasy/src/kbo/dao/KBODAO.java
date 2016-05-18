package kbo.dao;

import java.sql.Connection;
import java.sql.SQLException;

import kbo.login.dto.KBOLoginDTO;

public interface KBODAO {
	KBOLoginDTO login(String id, String pass,Connection con) 
			throws SQLException;
	
	KBOLoginDTO register(KBOLoginDTO register, Connection con) throws SQLException;

	boolean regemailCheck(Connection con, String email)throws SQLException;

	boolean passCheck(Connection con, String password) throws SQLException;
	
}
