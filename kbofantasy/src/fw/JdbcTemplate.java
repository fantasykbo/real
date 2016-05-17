package fw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}
	}

	public static Connection getConnect() {
		/*String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";*/
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

	public static void close(ResultSet rs, PreparedStatement ptmt,
			Connection con) {

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

	public static void close(ResultSet rs) {

		try {
			if (rs != null)
				rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(PreparedStatement ptmt) {

		try {
			if (ptmt != null)
				ptmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection con) {

		try {
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
