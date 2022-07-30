package payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCTemplate;

public class PayDao {

	// 예약정보가 맞는지 확인
	// 회원 id로 예약번호 조회와 금액 조회
	
	public PayVo gfPay(String id) {
		String sql = "SELECT FR.FLIGHT_NO, F.FLIGHT_PRICE, M.POINT\r\n" + "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.DEPARTURE_FLIGHT = F.FLIGHT_NO\r\n" + "WHERE M.ID = ? AND FR.PAID_YN = 'N'";

		Connection conn = null;
		PayVo vo = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.last()) {

				int myPoint = rs.getInt("M.POINT");
				int fNo = rs.getInt("F.FLIGHT_NO");
				int fp = rs.getInt("F.FLIGHT_PRICE");
				vo = new PayVo();
				vo.setMypoint(myPoint);
				vo.setFlightNo(fNo);
				vo.setFlightGoPay(fp);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
			
		}

		
		return vo;
		
	}
	
	public PayVo cfPay(String id) {

		String sqlCf = "SELECT F.FLIGHT_NO, F.FLIGHT_PRICE\r\n" + "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.RETURN_FLIGHT = F.FLIGHT_NO\r\n" + "WHERE M.ID = ? AND FR.PAID_YN = 'N'";

		Connection conn = null;
		PayVo vo = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sqlCf);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.last()) {
				int fNo = rs.getInt("F.FLIGHT_NO");
				int fp = rs.getInt("F.FLIGHT_PRICE");
				vo = new PayVo();
				vo.setFlightNo(fNo);
				vo.setFlightComePay(fp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {

			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
			
		}

		
		return vo;

	}

	public PayVo rPay(String id)  {
		String sqlRoom = "SELECT A.ACCOM_NO, R.ROOM_PRICE , A.CHECK_OUT - A.CHECK_IN\r\n" + "FROM MEMBER M\r\n"
				+ "JOIN ACCOM_RESERVATION A ON M.MEMBER_NO = A.MEMBER_NO\r\n"
				+ "JOIN ROOM R ON A.ROOM_NO = R.ROOM_NO\r\n" + "WHERE M.ID = ? AND A.PAID_YN = 'N'";
		Connection conn = null;
		PayVo vo = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sqlRoom);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.last()) {
				int fNo = rs.getInt("A.ACCOM_NO");
				int fp = rs.getInt("R.ROOM_PRICE");
				int fDay = rs.getInt("A.CHECK_OUT - A.CHECK_IN");
				fp = fp * fDay;
				vo = new PayVo();
				vo.setFlightNo(fNo);
				vo.setAccomPay(fDay);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		
		
		return vo;

	}

	public PayVo cPay(String id) {

		String sql = "SELECT C.CAR_NO, R.DAY_PRICE,C.INSURANCE, C.RETURN_DATE-C.RENTAL_DATE\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN CAR_RESERVATION C ON M.MEMBER_NO = C.MEMBER_NO\r\n"
				+ "JOIN RENTAL_CAR R ON C.RENTAL_NO = R.RENTAL_NO\r\n"
				+ "WHERE M.ID = ? AND C.PAID_YN = 'N'";
		Connection conn = null;
		PayVo vo = null;
		
		PreparedStatement pstmt= null;
		ResultSet rs= null;
	
		
		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
				
			if(rs.last()) {
				int cNo = rs.getInt("C.CAR_NO");
				int cp = rs.getInt("R.DAY_PRICE");
				int cDay = rs.getInt("C.RETURN_DATE-C.RENTAL_DATE");
				cp = cp*cDay;
				vo = new PayVo();		
				vo.setFlightNo(cNo);
				vo.setCarPay(cp);
			
			}
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		
		

		return vo;
	}
}