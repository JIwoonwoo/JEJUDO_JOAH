package car;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDao {

	public void carInquiry(CarVo vo, Connection conn) throws Exception {
		String sql = "SELECT R.RENTAL_NO, T.CAR_SIZE, C.CAR_NAME, R.CAR_UQ , R.DAY_PRICE, R.OPENABLE\r\n"
				+ "FROM CAR C\r\n"
				+ "JOIN RENTAL_CAR R ON C.CAR_NO = R.CAR_NO\r\n"
				+ "JOIN TB_SIZE T ON C.CAR_SIZE = T.SIZE_NO\r\n"
				+ "WHERE R.RENTAL_NO NOT IN \r\n"
				+ "    (SELECT RENTAL_NO\r\n"
				+ "     FROM CAR_RESERVATION\r\n"
				+ "     WHERE RENTAL_DATE BETWEEN ? AND ?\r\n"
				+ "     OR RETURN_DATE BETWEEN ?' AND ?) \r\n"
				+ "AND C.CAR_PERSON > ?\r\n"
				+ "AND C.CAR_SIZE = ?\r\n"
				+ "AND C.FUEL = ?\r\n"
				+ "ORDER BY RENTAL_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			vo.setRentalNo(rs.getString("R.RENTAL_NO"));
			vo.setCarSize(rs.getString("T.CAR_SIZE"));
			vo.setCarName(rs.getString("C.CAR_NAME"));
		}
		
		
	}

}
