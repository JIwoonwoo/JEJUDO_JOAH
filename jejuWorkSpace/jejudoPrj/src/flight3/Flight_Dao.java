package flight3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import util.JDBCTemplate;

import static util.JDBCTemplate.*;

public class Flight_Dao {
	

	public void reservation(Flight_Vo vo, Connection conn, int fno) throws SQLException {
		//DB 가서 사용자가 입력한 기준에 맞는 비행기 목록 조회
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		//SQL 준비
		String when = "";
		if(fno == 1) {
			when = "AND DEP_AIRPORT = '제주공항'";
		}else if(fno == 2) {
			when = "AND ARR_AIRPORT = '제주공항'";
		}
		String sql = "SELECT FT.FLIGHT_TIME_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, "
				+ "F.ARRIVAL_TIME, SEAT , AIRLINE, DEP_AIRPORT, ARR_AIRPORT, FLIGHT_PRICE "
				+ "FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO "
				+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = ? "
				+ "AND SEAT >= ? "
				+ "AND DEP_AIRPORT = ? "
				+ "AND FLIGHT_PRICE <= ? "
				+ "UNION ALL SELECT FT.FLIGHT_TIME_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, "
				+ "F.ARRIVAL_TIME, SEAT , AIRLINE, DEP_AIRPORT, ARR_AIRPORT, FLIGHT_PRICE "
				+ "FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO "
				+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = ? "
				+ "AND SEAT >= ? "
				+ "AND DEP_AIRPORT = ? "
				+ "AND FLIGHT_PRICE <= ? " + when
				+ "ORDER BY DEPARTURE_DATE, DEPARTURE_TIME";
		
		//SQL 객체에 담기
		pstmt = conn.prepareStatement(sql);		
		
		pstmt.setString(1, vo.getDepartureDate());
		pstmt.setString(2, vo.getFlightPerson());
		pstmt.setString(3, vo.getDepAirport());
		pstmt.setString(4, vo.getFlightPrice());
		pstmt.setString(5, vo.getReturnDate());
		pstmt.setString(6, vo.getFlightPerson());
		pstmt.setString(7, vo.getArrAirport());
		pstmt.setString(8, vo.getFlightPrice());

		ResultSet rs = pstmt.executeQuery();
		System.out.println("");
		System.out.println("_______________________________________________________________________________");
		System.out.println("");
		System.out.println("No | 항공편명 |   항공사  |   날짜     |출발 시간|남은 좌석|  출발 공항  |  도착 공항  |  가격  |");
		System.out.println("_______________________________________________________________________________");
		while(rs.next()) {
			String flightNo = rs.getString("FLIGHT_TIME_NO");
			Date departureDate = rs.getDate("DEPARTURE_DATE");
			String airline = rs.getString("AIRLINE");
			String flightPerson = rs.getString("SEAT");
			String depAirport = rs.getString("DEP_AIRPORT");
			String flightPrice = rs.getString("FLIGHT_PRICE");
			String arrAirport = rs.getString("ARR_AIRPORT");
			String departureTime = rs.getString("DEPARTURE_TIME");
			String flightName = rs.getString("FLIGHT_NAME");
			System.out.println(flightNo + " | " + flightName + " | " + airline + " | " +departureDate + " | " + departureTime+ " | " + " "+ flightPerson + " " + " | " + " "+ depAirport + " "+ " | " + " "+arrAirport+ " "+ " | " + flightPrice+"원" );

		}
		
		

	}
	// 사용자 입력 비행기 조회
	public void myReservation(Connection conn, Flight_Vo_MyFlight vo ) throws Exception {
		
		int result = 0;
		PreparedStatement pstmt = null;
		

		String sql = "SELECT FT.FLIGHT_TIME_NO, F.FLIGHT_NAME, AIRLINE, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, F.ARRIVAL_TIME\r\n"
				+ ",DEP_AIRPORT, ARR_AIRPORT, FLIGHT_PRICE\r\n"
				+ "FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO\r\n"
				+ "WHERE FT.FLIGHT_TIME_NO = ? \r\n"
				+ "\r\n"
				+ "UNION ALL\r\n"
				+ "\r\n"
				+ "SELECT FT.FLIGHT_TIME_NO, F.FLIGHT_NAME, AIRLINE, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, F.ARRIVAL_TIME\r\n"
				+ ",DEP_AIRPORT, ARR_AIRPORT, FLIGHT_PRICE\r\n"
				+ "FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO\r\n"
				+ "WHERE FT.FLIGHT_TIME_NO = ? ";
		//WHERE 절에 AND 넣어서 DEP_AIRPORT 가 제주공항이면.. 이건 복귀비행기
		//ARR_AIRPORT 가 제주공항이면 .. 이건 출발비행기
		
		//SQL 객체에 담기
		pstmt = conn.prepareStatement(sql);		
		pstmt.setString(1, vo.getMyDepartureFlightNo());
		pstmt.setString(2, vo.getMyReturnFlightNo());


		ResultSet rs = pstmt.executeQuery();
		System.out.println("");
		System.out.println("_______________________________________________________________________________");
		System.out.println("");
		System.out.println("No |   항공편명    | 항공사 |  탑승 날짜    |출발 시간|도착 시간| 출발 공항 | 도착 공항 |  가격  |");
		System.out.println("_______________________________________________________________________________");
		while(rs.next()) {
			String myDepartureFlightNo = rs.getString("FLIGHT_TIME_NO");
			Date departureDate = rs.getDate("DEPARTURE_DATE");
			String departureTime = rs.getString("DEPARTURE_TIME");
			String arrivalTime = rs.getString("ARRIVAL_TIME");
			String depAirport = rs.getString("DEP_AIRPORT");
			String arrAirport = rs.getString("ARR_AIRPORT");
			String flightPrice = rs.getString("FLIGHT_PRICE");
			String flightName = rs.getString("FLIGHT_NAME");
			String airline = rs.getString("AIRLINE");
			System.out.println(myDepartureFlightNo + " | " + flightName + " | " +  airline + " | " +departureDate + " | " + departureTime + " | " + arrivalTime + " | " + depAirport + " | " + arrAirport + ""+ " | " + flightPrice+ "원");
		}

		
	}
	public int myRealReservation(Flight_Vo_MyFlight vo, Connection conn) throws Exception {
		//예약 -> insert
		int result = 0;
		PreparedStatement pstmt = null;
		
		
		try {
			//SQL 준비
			String sql = "INSERT INTO FLIGHT_RESERVATION(FLIGHT_NO, DEPARTURE_FLIGHT, RETURN_FLIGHT, MEMBER_NO) "
					+ "VALUES(SEQ_FLIGHT_RESERVATION.NEXTVAL, ?, ?, ?)";
			//SQL 담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMyDepartureFlightNo());
			pstmt.setString(2, vo.getMyReturnFlightNo());
			pstmt.setString(3, "1");
			
			//SQL 실행 및 결과 저장
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
			finally {
		
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
		
	}

	

	public void search(Flight_Vo_MyFlight myvo, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//SQL 준비
//		String sql = "SELECT 회원넘버, 예약일, 출발날짜, 항공편명, 항공사, 출발시간, 복귀날짜, 복귀항공편명, 복귀항공사, 복귀출발시간\r\n"
//				+ "FROM GO\r\n"
//				+ "JOIN BACK USING(회원넘버, 예약일)\r\n"
//				+ "WHERE 회원넘버 = ?";
		String sql = "SELECT 회원넘버, 예약일, 출발항공선택번호, 출발날짜, 항공편명, 항공사, 출발시간, 복귀항공선택번호, 복귀날짜, 복귀항공편명, 복귀항공사, 복귀출발시간\r\n"
				+ "FROM GO\r\n"
				+ "JOIN BACK USING(회원넘버, 예약일)\r\n"
				+ "WHERE 회원넘버 = ?";
		
		
		//SQL 객체에 담기
				pstmt = conn.prepareStatement(sql);		
				
				pstmt.setString(1, myvo.getMyMemberNo());

				ResultSet rs = pstmt.executeQuery();
				System.out.println("");
				System.out.println("_______________________________________________________________________________________________________________________________");
				System.out.println("");
				System.out.println("회원번호|        예약일        |          출발날짜       | 항공편명 |  항공사  | 출발 시간 |          복귀날짜       | 항공편명 |  항공사 | 출발 시간");
				System.out.println("_______________________________________________________________________________________________________________________________");
				while(rs.next()) {
					String myMemberNo = rs.getString("회원넘버");
					String reservDate = rs.getString("예약일");
					String myDepartureNo = rs.getString("출발항공선택번호");
					String departureDate = rs.getString("출발날짜");
					String flightName = rs.getString("항공편명");
					String airline = rs.getString("항공사");
					String departureTime = rs.getString("출발시간");
					String myReturnNo = rs.getString("복귀항공선택번호");
					String returnDate = rs.getString("복귀날짜");
					String returnFlightName = rs.getString("복귀항공편명");
					String returnAirline = rs.getString("복귀항공사");
					String returnTime = rs.getString("복귀출발시간");

					System.out.println("  " +myMemberNo + " " +" | " + reservDate + " | " + myDepartureNo+ " | " +departureDate + " | " 
					+flightName + " | " + airline+ " | " + " "+ departureTime + " " + " | " +myReturnNo+ " | "
					+ returnDate+ " | " +  returnFlightName + " | " + returnAirline + " | " +returnTime);

				}
		
		

		
		
	}
	
	
}