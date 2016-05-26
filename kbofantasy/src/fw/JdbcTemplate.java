package fw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//��� DBó�� �޼ҵ忡�� �ߺ��Ǵ� ����� ��Ƴ��� Ŭ����
public class JdbcTemplate {
	// ����̹��ε�
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����̹� �ε� ����");
		}
	}

	public static Connection getConnect() {
		String url = "jdbc:oracle:thin:@130.211.247.92:1521:xe";
		String user = "test";
		String password = "test";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(ResultSet rs, PreparedStatement ptmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (ptmt != null)
				ptmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//ResultSet��ȯ
	public static void close(ResultSet rs) {
		try {
			if (rs != null)	rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Statement��ȯ
	public static void close(PreparedStatement ptmt) {
		try {
			if (ptmt != null) ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Connection��ȯ
	public static void close(Connection con) {
		try {
			if (con != null)	con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}