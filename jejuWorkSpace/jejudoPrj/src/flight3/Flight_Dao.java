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
		System.out.println("_______________________________________________________________");
		System.out.println("");
		System.out.println("No |     날짜     |출발 시간|남은 좌석|  출발 공항  |  도착 공항  |  가격  |");
		System.out.println("_______________________________________________________________");
		while(rs.next()) {
			String flightNo = rs.getString("FLIGHT_TIME_NO");
			Date departureDate = rs.getDate("DEPARTURE_DATE");
			String airline = rs.getString("AIRLINE");
			String flightPerson = rs.getString("SEAT");
			String depAirport = rs.getString("DEP_AIRPORT");
			String flightPrice = rs.getString("FLIGHT_PRICE");
			String arrAirport = rs.getString("ARR_AIRPORT");
			String departureTime = rs.getString("DEPARTURE_TIME");
			System.out.println(flightNo + " | " + departureDate + " | " + departureTime+ " | " + " "+ flightPerson + " " + " | " + " "+ depAirport + " "+ " | " + " "+arrAirport+ " "+ " | " + flightPrice+"원" );

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
		System.out.println("_____________________________________________________________");
		System.out.println("");
		System.out.println("No |    탑승 날짜    |출발 시간|도착 시간| 출발 공항 | 도착 공항 |  가격  |");
		System.out.println("_____________________________________________________________");
		while(rs.next()) {
			String myDepartureFlightNo = rs.getString("FLIGHT_TIME_NO");
			Date departureDate = rs.getDate("DEPARTURE_DATE");
			String departureTime = rs.getString("DEPARTURE_TIME");
			String arrivalTime = rs.getString("ARRIVAL_TIME");
			String depAirport = rs.getString("DEP_AIRPORT");
			String arrAirport = rs.getString("ARR_AIRPORT");
			String flightPrice = rs.getString("FLIGHT_PRICE");
			System.out.println(myDepartureFlightNo + " | " + departureDate + " | " + departureTime + " | " + arrivalTime + " | " + depAirport + " | " + arrAirport + ""+ " | " + flightPrice+ "원");
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
	public List<Flight_Vo_MyFlight> showList(Connection conn) throws Exception {
		//SQL 준비
		String sql = "SELECT MEMBER_NO, RESERVE_DATE, FT.DEPARTURE_DATE, DEPARTURE_FLIGHT, DEPARTURE_TIME, "
				+ "FT.ARRIVAL_DATE, RETURN_FLIGHT, ARRIVAL_TIME\r\n"
				+ "FROM FLIGHT_RESERVATION FR\r\n"
				+ "JOIN FLIGHT F ON F.FLIGHT_NO = FR.FLIGHT_NO\r\n"
				+ "JOIN FLIGHT_TIME FT ON FLIGHT_TIME_NO = FR.FLIGHT_NO\r\n"
				+ "WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Flight_Vo_MyFlight> fvmf = new ArrayList<Flight_Vo_MyFlight>();
		
		try {
			//SQL 담을 객체 준비 및 SQL 완성
			pstmt = conn.prepareStatement(sql);
			
			//SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				Date reserveDate = rs.getDate("RESERVE_DATE");
				Date departureDate = rs.getDate("DEPARTURE_DATE");
				String departureFlight = rs.getString("DEPARTURE_FLIGHT");
				Date arrivalDate = rs.getDate("ARRIVAL_DATE");
				String returnFlight = rs.getString("RETURN_FLIGHT");

				
				Flight_Vo_MyFlight vo = new Flight_Vo_MyFlight();
				vo.setMyMemberNO(memberNo);
				vo.setMyDepartureFlightNo(departureFlight);
				vo.setMyReturnFlightNo(returnFlight);
				
				fvmf.add(vo);
			
			}

			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		//SQL 실행 결과 리턴
		return fvmf;
	
	}
	public Flight_Vo_MyFlight showDetailByNo(Connection conn, int num) throws Exception { //BoardDao 참고
		
		//SQL 준비
		String sql = "SELECT MEMBER_NO, RESERVE_DATE, FT.DEPARTURE_DATE, DEPARTURE_FLIGHT, "
				+ "DEPARTURE_TIME, FT.ARRIVAL_DATE, RETURN_FLIGHT, ARRIVAL_TIME\r\n"
				+ "FROM FLIGHT_RESERVATION FR\r\n"
				+ "JOIN FLIGHT F ON F.FLIGHT_NO = FR.FLIGHT_NO\r\n"
				+ "JOIN FLIGHT_TIME FT ON FLIGHT_TIME_NO = FR.FLIGHT_NO\r\n"
				+ "WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Flight_Vo_MyFlight vo = null;
		
		try {
		//SQL 객체에 담기 및 쿼리 완성하기
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		
		//SQL 실행 및 결과 저장
		rs = pstmt.executeQuery();
		
		//ResultSet -> 자바객체
		if(rs.next()) {
			int no = rs.getInt("MEMBER_NO");
			String rd = rs.getString("RESERVE_DATE");
			String dd = rs.getString("DEPARTURE_DATE");
			String df = rs.getString("DEPARTURE_FLIGHT");
			String ad = rs.getString("ARRIVAL_DATE");
			String rf = rs.getString("RETURN_FLIGHT");

			///여기서부터 이어서 하기
			//↑위에서 꺼내온 데이터를 관리하기 편하게 한 개의 자바 객체로 만들어줌 ↓
			vo = new Flight_Vo_MyFlight();
			vo.setMyMemberNO(no);
			vo.setMyDepartureFlightNo(rf);
			vo.setMyReturnFlightNo(rf);
			}
		
		
		}catch (Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		close(pstmt);
		close(rs);
	}
		
		return vo;
	}
}