package payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.MemberVo;
import util.JDBCTemplate;

public class Pay {
	/*
	 * pay의 getter, setter 따로 만들기
	 * 로그인 확인
	 * id로 회원의 예약번호 와 금액조회
	 * sql에서 항공, 렌트, 숙박의 금액들과 멤버의 포인트 가져오기(예약 날짜를 활용 1박금액 및 출발, 도착의 항공 금액)
	 * 렌트는 널 허용, 나머지는 널 불가
	 * 포인트 사용 유무, 할인 금액 보여주기
	 * 결제선 -> 카드, 현금
	 * 결제내역 및 예약정보 insert
	 */
	
	public void payment() {
		//로그인 체크
		String id = "";
		if(loginCheck() != null) {
			id = loginCheck();
		} else {
			return;
		}
		//id로 회원의 예약번호 조회
		
		
	}
	//로그인 체크
	private String loginCheck(){
		MemberVo vo = new MemberVo();
		String id = vo.getId();
		return id;
	}
	
	// 회원 id로 예약번호 조회와 금액 조회
	private PayVo checkNo(String id) throws Exception{
		
		Connection conn = JDBCTemplate.getConnection();
		
		String sqlGf = "SELECT FR.FLIGHT_NO, F.FLIGHT_PRICE\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.DEPARTURE_FLIGHT = F.FLIGHT_NO\r\n"
				+ "WHERE M.ID = ?";
		String sqlCf = "SELECT F.FLIGHT_NO, F.FLIGHT_PRICE\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.RETURN_FLIGHT = F.FLIGHT_NO\r\n"
				+ "WHERE M.ID = ?";
		String sqlCar = "SELECT C.CAR_NO, R.DAY_PRICE, C.RETURN_DATE-C.RENTAL_DATE\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN CAR_RESERVATION C ON M.MEMBER_NO = C.MEMBER_NO\r\n"
				+ "JOIN RENTAL_CAR R ON C.RENTAL_NO = R.RENTAL_NO\r\n"
				+ "WHERE M.ID = ?";
		String sqlRoom = "SELECT A.ACCOM_NO, R.ROOM_PRICE , A.CHECK_OUT - A.CHECK_IN\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN ACCOM_RESERVATION A ON M.MEMBER_NO = A.MEMBER_NO\r\n"
				+ "JOIN ROOM R ON A.ROOM_NO = R.ROOM_NO\r\n"
				+ "WHERE M.ID = ?";
		
		PreparedStatement pstmtGf = conn.prepareStatement(sqlGf);
		PreparedStatement pstmtCf = conn.prepareStatement(sqlCf);
		PreparedStatement pstmtC = conn.prepareStatement(sqlCar);
		PreparedStatement pstmtR = conn.prepareStatement(sqlRoom);
		
		ResultSet rsGf = pstmtGf.executeQuery();
		ResultSet rsCf = pstmtCf.executeQuery();
		ResultSet rsC = pstmtC.executeQuery();
		ResultSet rsR = pstmtR.executeQuery();
		
		PayVo vo = null;

		if(rsGf.next()) {
			int fNo = rsGf.getInt("F.FLIGHT_NO");
			int fP = rsGf.getInt("F.FLIGHT_PRICE");
			vo = new PayVo();		
			vo.setFlightNo(fNo);
			vo.setFlightGoPay(fP);
		}
		if(rsCf.next()) {
			int fNo = rsCf.getInt("F.FLIGHT_NO");
			int fP = rsCf.getInt("F.FLIGHT_PRICE");
			vo = new PayVo();		
			vo.setFlightNo(fNo);
			vo.setFlightComePay(fP);
		}
		if(rsC.next()) {
			int cNo = rsC.getInt("C.CAR_NO");	
			vo = new PayVo();		
			vo.setFlightNo(cNo);
		}
		if(rsR.next()) {
			int fNo = rsR.getInt("NO");	
			vo = new PayVo();		
			vo.setFlightNo(fNo);
		}
		
		return vo;
	}

}
