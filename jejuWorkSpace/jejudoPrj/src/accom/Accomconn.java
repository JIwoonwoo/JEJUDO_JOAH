package accom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Accomconn {// 여기는 그냥 JDBCTemplate인데 숙소 연결해서 검색하려고 만든.

	public static Connection getConnection() throws Exception {

		// 클래스 파일 준비
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 접속 정보
		String url = "jdbc:oracle:thin:@192.168.219.100:1521:xe";
		String id = "C##JEJU";
		String pwd = "KH3";

		// 연결 얻기
		Connection conn = DriverManager.getConnection(url, id, pwd);
		conn.setAutoCommit(false);

		return conn;
	}

	// 커밋
	public static void commit(Connection conn) {
		try {
			if (conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// commit

	// 롤백
	public static void rollback(Connection conn) {
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// rollback

	// close : conn, rs, stmt

	public static void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// close

	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// close

	public static void close(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// close

}
