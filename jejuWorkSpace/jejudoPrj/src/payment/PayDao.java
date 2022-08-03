package payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import member.MemberVo;
import util.JDBCTemplate;

public class PayDao {

	// 예약정보가 맞는지 확인
	// 회원 id로 예약번호 조회와 금액 조회
	public MemberVo myInfo(int no, Connection conn) throws Exception {
		String sql = "SELECT MEMBER_NICK, POINT FROM MEMBER WHERE MEMBER_NO = ?";

		MemberVo vo = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				String nick = rs.getString("MEMBER_NICK");
				int point = rs.getInt("POINT");
				vo = new MemberVo();
				vo.setMemberNick(nick);
				vo.setPoint(point);

			}
		} finally {

			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}

		return vo;

	}

	// 가는 항공금액
	public PayVo gfPay(int no, Connection conn) throws Exception {
		String sql = "SELECT FR.FLIGHT_NO, F.FLIGHT_PRICE\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.DEPARTURE_FLIGHT = F.FLIGHT_NO\r\n"
				+ "WHERE M.MEMBER_NO = ? AND FR.CANCEL_YN = 'N' AND FR.PAID_YN = 'N'";

		PayVo vo = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			System.out.print("가는 비행기 행의 갯수 : "+rs.getRow() + "\r");
			for(int i = 0;i<rs.getRow();i++) {
				
				if(i==1) {
					return vo;
				}
				if(rs.next()) {
					int fNo = rs.getInt("FLIGHT_NO");
					int fp = rs.getInt("FLIGHT_PRICE");
					vo = new PayVo();
					vo.setFlightNo(fNo);
					vo.setFlightGoPay(fp);
	
				}
			}
		} finally {

			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}

		return vo;

	}

	// 오는 항공 금액
	public PayVo cfPay(int no, Connection conn) throws Exception {

		String sql = "SELECT F.FLIGHT_NO, F.FLIGHT_PRICE\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.RETURN_FLIGHT = F.FLIGHT_NO\r\n"
				+ "WHERE M.MEMBER_NO = ? AND FR.CANCEL_YN = 'N' AND FR.PAID_YN = 'N'";

		PayVo vo = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			System.out.print("오는 비행기 행의 갯수 : "+rs.getRow() + "\r");
			
			if (rs.next()) {
				int fNo = rs.getInt("FLIGHT_NO");
				int fp = rs.getInt("FLIGHT_PRICE");
				vo = new PayVo();
				vo.setFlightNo(fNo);
				vo.setFlightComePay(fp);
			}

		} finally {

			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}

		return vo;

	}

	// 방 금액
	public PayVo rPay(int no, Connection conn) throws Exception {
		
		String sql = "SELECT A.ACCOM_NO, R.ROOM_PRICE , TO_DATE(TO_CHAR(A.CHECK_OUT,'YYMMDD')) - TO_DATE(TO_CHAR(A.CHECK_IN,'YYMMDD')) AS DAYS \r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN ACCOM_RESERVATION A ON M.MEMBER_NO = A.MEMBER_NO\r\n"
				+ "JOIN ROOM R ON A.ROOM_NO = R.ROOM_NO\r\n"
				+ "WHERE M.MEMBER_NO = ? AND A.PAID_YN = 'N'";
		
		PayVo vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			System.out.print("방 행의 갯수 : "+rs.getRow() + "\r");
			
			if (rs.next()) {
				vo = new PayVo();
				
				int rNo = rs.getInt("ACCOM_NO");
				int rp = rs.getInt("ROOM_PRICE");
				int rDay = rs.getInt("DAYS");
				
				System.out.println(rNo);
				System.out.println(rp);
				System.out.println(rDay);
				rp = rp * rDay;
				
				vo.setAccomNo(rNo);
				vo.setAccomPay(rp);
			}

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		System.out.println(Main.loginNo);
		System.out.println(vo);
		
		return vo;	

	}

	// 렌트카 금액
	public PayVo cPay(int no, Connection conn) throws Exception {

		String sql = "SELECT C.CAR_NO, C.INSURANCE ,R.DAY_PRICE, TO_DATE(TO_CHAR(C.RETURN_DATE,'YYMMDD')) - TO_DATE(TO_CHAR(C.RENTAL_DATE,'YYMMDD')) AS DAYS\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN CAR_RESERVATION C ON M.MEMBER_NO = C.MEMBER_NO\r\n"
				+ "JOIN RENTAL_CAR R ON C.RENTAL_NO = R.RENTAL_NO\r\n"
				+ "WHERE M.MEMBER_NO = ? AND C.CANCEL_YN = 'N' AND C.PAID_YN = 'N'";
		PayVo vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			System.out.print("렌트카 행의 갯수 : "+rs.getRow() + "\r");
			if (rs.next()) {
				int cNo = rs.getInt("CAR_NO");
				int cp = rs.getInt("DAY_PRICE");
				int cDay = rs.getInt("DAYS");
				int cIns = rs.getInt("INSURANCE");
				
				int rentPay = rentPayCount(cp,cDay,cIns);
				
				
				vo = new PayVo();
				vo.setCarNo(cNo);
				vo.setCarPay(rentPay);

			}
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		return vo;
	}
	
	private int rentPayCount(int cp, int cDay, int cIns) {
		int rentPay = 0;
		switch(cIns) {
		case 1 : rentPay = cp*cDay*11/10;break;
		case 2 : rentPay = cp*cDay*12/10;break;
		case 3 : rentPay = cp*cDay*18/10;break;
		default : rentPay = cp*cDay;break;
		}
		
		return rentPay;
	}

	// 결제내역저장, INSERT
	public int payInsert(PayVo vo, Connection conn) throws Exception {

		String sql = "INSERT INTO PAYMENT(PAY_NO,FLIGHT_NO,ACCOM_NO,CAR_NO,TOTAL,POINT_USED,CUT_PRICE,POINT,PAY_METHOD) \r\n"
				+ "VALUES(SEQ_PAYMENT.NEXTVAL,?,?,?,?,?,?,?,?)";

		int result = 0;
		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getFlightNo());
			pstmt.setInt(2, vo.getAccomNo());
			
			if(vo.getCarNo() == 0) {
				pstmt.setNull(3,Types.INTEGER);
			}else {
				pstmt.setInt(3, vo.getCarNo());
			}
			pstmt.setInt(4, vo.getTotalPay());
			pstmt.setInt(5, vo.getPointUsed());
			pstmt.setInt(6, vo.getCutPrice());
			pstmt.setInt(7, vo.getMypoint());
			pstmt.setInt(8, vo.getPayMethod());

			result = pstmt.executeUpdate();

		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int pointUpdate(int no, int leavePoint, Connection conn) throws Exception {

		String sql = "UPDATE MEMBER SET POINT = ? WHERE MEMBER_NO = ?";

		int result = 0;
		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, leavePoint);
			pstmt.setInt(2, no);

			result = pstmt.executeUpdate();

		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}
	public int paidUpdate(PayVo vo, Connection conn) throws Exception {

		String sql1 = "UPDATE CAR_RESERVATION SET PAID_YN = 'Y' WHERE CAR_NO = ?";
		String sql2 = "UPDATE ACCOM_RESERVATION SET PAID_YN = 'Y' WHERE ACCOM_NO = ?";
		String sql3 = "UPDATE FLIGHT_RESERVATION SET PAID_YN = 'Y' WHERE FLIGHT_NO = ?";
		

		int result;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		try {

			pstmt1 = conn.prepareStatement(sql1);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);
			pstmt1.setInt(1, vo.getCarNo());
			pstmt2.setInt(1, vo.getAccomNo());
			pstmt3.setInt(1, vo.getFlightNo());

			int a = pstmt1.executeUpdate();
			int b = pstmt2.executeUpdate();
			int c = pstmt3.executeUpdate();
			
			result = a+b+c;
		} finally {
			JDBCTemplate.close(pstmt1);
			JDBCTemplate.close(pstmt2);
			JDBCTemplate.close(pstmt3);
		}

		return result;
	}

	public List<PayVo> pointAddList(Connection conn, int no) throws Exception {

		PreparedStatement pstmt = null;
		List<PayVo> pointList = new ArrayList<PayVo>();
		ResultSet rs = null;

		try {

			String sql = "SELECT P.PAY_DATE, P.CUT_PRICE, P.POINT \r\n"
					+ "FROM PAYMENT P\r\n"
					+ "JOIN FLIGHT_RESERVATION FR ON P.FLIGHT_NO = FR.FLIGHT_NO \r\n"
					+ "WHERE FR.MEMBER_NO = ? \r\n"
					+ "ORDER BY P.PAY_DATE DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PayVo vo = new PayVo();
				vo.setPayDate(rs.getTimestamp("PAY_DATE"));
				vo.setCutPrice(rs.getInt("CUT_PRICE"));
				vo.setMypoint(rs.getInt("POINT"));
				
				pointList.add(vo);
			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return pointList;
	}

	public List<PayVo> myPayment(int no, Connection conn) throws Exception {
		
		PreparedStatement pstmt = null;
		List<PayVo> payList = new ArrayList<PayVo>();
		ResultSet rs = null;

		try {

			String sql = "SELECT P.PAY_DATE, P.TOTAL, P.CUT_PRICE, P.POINT_USED, P.PAY_METHOD \r\n"
					+ "FROM PAYMENT P\r\n"
					+ "JOIN FLIGHT_RESERVATION FR ON P.FLIGHT_NO = FR.FLIGHT_NO\r\n"
					+ "JOIN PAY_METHOD_NO PM ON P.PAY_METHOD = PM.METHOD_NO\r\n"
					+ "WHERE FR.MEMBER_NO = 1 \r\n"
					+ "ORDER BY P.PAY_DATE DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PayVo vo = new PayVo();
				vo.setPayDate(rs.getTimestamp("PAY_DATE"));
				vo.setTotalPay(rs.getInt("TOTAL"));
				vo.setCutPrice(rs.getInt("CUT_PRICE"));
				vo.setPointUsed(rs.getInt("POINT_USED"));
				vo.setPayMethod(rs.getInt("PAY_METHOD"));

				payList.add(vo);
			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return payList;
	}
	

}