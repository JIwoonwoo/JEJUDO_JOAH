package payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import member.MemberVo;
import util.JDBCTemplate;

public class PayDao {

	// 예약정보가 맞는지 확인
	// 회원 id로 예약번호 조회와 금액 조회
	public MemberVo myInfo(int no) {
		String sql = "SELECT MEMBER_NICK, POINT FROM MEMBER WHERE MEMBER_NO = ?";

		Connection conn = null;
		MemberVo vo = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			if (rs.last()) {

				String nick = rs.getString("MEMBER_NICK");
				int point = rs.getInt("POINT");
				vo = new MemberVo();
				vo.setMemberNick(nick);
				vo.setPoint(point);

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
	
	// 가는 항공금액
	public PayVo gfPay(int no) {
		String sql = "SELECT FR.FLIGHT_NO, F.FLIGHT_PRICE\r\n" + "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.DEPARTURE_FLIGHT = F.FLIGHT_NO\r\n" + "WHERE M.MEMBER_NO = ? AND FR.PAID_YN = 'N'";

		Connection conn = null;
		PayVo vo = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			if (rs.last()) {

				int fNo = rs.getInt("FLIGHT_NO");
				int fp = rs.getInt("FLIGHT_PRICE");
				vo = new PayVo();
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
	// 오는 항공 금액
	public PayVo cfPay(int no) {

		String sqlCf = "SELECT F.FLIGHT_NO, F.FLIGHT_PRICE\r\n" + "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.RETURN_FLIGHT = F.FLIGHT_NO\r\n" + "WHERE M.MEMBER_NO = ? AND FR.PAID_YN = 'N'";

		Connection conn = null;
		PayVo vo = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sqlCf);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			if (rs.last()) {
				int fNo = rs.getInt("FLIGHT_NO");
				int fp = rs.getInt("FLIGHT_PRICE");
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
	// 방 금액
	public PayVo rPay(int no)  {
		String sqlRoom = "SELECT A.ACCOM_NO, R.ROOM_PRICE , A.CHECK_OUT - A.CHECK_IN AS DAYS\r\n" + "FROM MEMBER M\r\n"
				+ "JOIN ACCOM_RESERVATION A ON M.MEMBER_NO = A.MEMBER_NO\r\n"
				+ "JOIN ROOM R ON A.ROOM_NO = R.ROOM_NO\r\n" + "WHERE M.MEMBER_NO = ? AND A.PAID_YN = 'N'";
		Connection conn = null;
		PayVo vo = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sqlRoom);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.last()) {
				int fNo = rs.getInt("ACCOM_NO");
				int fp = rs.getInt("ROOM_PRICE");
				int fDay = rs.getInt("DAYS");
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
	// 렌트카 금액
	public PayVo cPay(int no) {

		String sql = "SELECT C.CAR_NO, R.DAY_PRICE,C.INSURANCE, C.RETURN_DATE-C.RENTAL_DATE AS DAYS\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN CAR_RESERVATION C ON M.MEMBER_NO = C.MEMBER_NO\r\n"
				+ "JOIN RENTAL_CAR R ON C.RENTAL_NO = R.RENTAL_NO\r\n"
				+ "WHERE M.MEMBER_NO = ? AND C.PAID_YN = 'N'";
		Connection conn = null;
		PayVo vo = null;
		
		PreparedStatement pstmt= null;
		ResultSet rs= null;
	
		
		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
				
			if(rs.last()) {
				int cNo = rs.getInt("CAR_NO");
				int cp = rs.getInt("DAY_PRICE");
				int cDay = rs.getInt("DAYS");
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
	
	//결제내역저장, INSERT
	public void payInsert(PayVo vogf, PayVo vocf, PayVo vor, PayVo voc, int totalPay, int myUsePoint, int lastPay, int earnPoint, char howPay) {
		
		
		
		String sql = "INSERT INTO MEMBER(PAY_NO,FLIGHT_NO,ACCOM_NO,CAR_NO,TOTAL,POINT_USED,CUT_PRICE,POINT,PAY_METHOD) \r\n"
				+ "VALUES(SEQ_PAYMENT.NEXTVAL,?,?,?,?,?,?,?,?');";
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = JDBCTemplate.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vogf.getFlightNo());
			pstmt.setInt(2, vor.getAccomNo());
			pstmt.setInt(3, voc.getCarNo());
			pstmt.setInt(4, totalPay);
			pstmt.setInt(5, myUsePoint);
			pstmt.setInt(6, lastPay);
			pstmt.setInt(7, earnPoint);
			pstmt.setInt(8, howPay);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		
		
	}
	
	
}