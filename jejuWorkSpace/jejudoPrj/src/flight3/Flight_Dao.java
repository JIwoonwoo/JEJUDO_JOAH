package flight3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import flight2.ReservationVo;
import util.InputUtil;
import util.JDBCTemplate;


public class Flight_Dao {
	
	Connection conn = null;

	public void reservation(Flight_Vo vo, Connection conn) throws SQLException {
		//DB 가서 사용자가 입력한 기준에 맞는 비행기 목록 조회
		
		int result = 0;
		PreparedStatement pstmt = null;
//		List<Flight_Vo> flight_VoList = new ArrayList<Flight_Vo>();
		
		//SQL 준비
//		String sql = "SELECT F.FLIGHT_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, FT.ARRIVAL_DATE, F.ARRIVAL_TIME , AIRLINE, DEP_AIRPORT, FLIGHT_PRICE, SEAT FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO WHERE TO_CHAR(FT.DEPARTURE_DATE) = ? AND TO_CHAR(FT.ARRIVAL_DATE) = ? AND SEAT > ? AND DEP_AIRPORT = ? AND FLIGHT_PRICE > ?";
//		String sql = "SELECT F.FLIGHT_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, "
//				+ "F.DEPARTURE_TIME, F.ARRIVAL_TIME, SEAT , AIRLINE, DEP_AIRPORT, "
//				+ "FLIGHT_PRICE FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO "
//				+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = ? "
//				+ "AND SEAT > ? AND DEP_AIRPORT = ? "
//				+ "AND FLIGHT_PRICE > ? "
//				+ "UNION ALL SELECT F.FLIGHT_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, "
//				+ "F.ARRIVAL_TIME, SEAT , AIRLINE, ARR_AIRPORT, FLIGHT_PRICE "
//				+ "FROM FLIGHT F JOIN FLIGHT_TIME FT "
//				+ "ON F.FLIGHT_NO = FT.FLIGHT_NO "
//				+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = ? "
//				+ "AND SEAT > ? AND ARR_AIRPORT = ? "
//				+ "AND FLIGHT_PRICE > ? ORDER BY DEPARTURE_DATE, DEPARTURE_TIME ";
		
//		String sql = "SELECT F.FLIGHT_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, F.ARRIVAL_TIME, SEAT\r\n"
//				+ ", AIRLINE, DEP_AIRPORT, FLIGHT_PRICE\r\n"
//				+ "FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO\r\n"
//				+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = ? \r\n"
//				+ "AND SEAT > ? \r\n"
//				+ "AND DEP_AIRPORT = ? \r\n"
//				+ "AND FLIGHT_PRICE > ? \r\n"
//				+ "\r\n"
//				+ "UNION ALL\r\n"
//				+ "\r\n"
//				+ "SELECT F.FLIGHT_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, F.ARRIVAL_TIME, SEAT\r\n"
//				+ ", AIRLINE, ARR_AIRPORT, FLIGHT_PRICE\r\n"
//				+ "FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO\r\n"
//				+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = '22/08/03' \r\n"
//				+ "AND SEAT > ? \r\n"
//				+ "AND ARR_AIRPORT = ? \r\n"
//				+ "AND FLIGHT_PRICE > ? \r\n"
//				+ "ORDER BY DEPARTURE_DATE, DEPARTURE_TIME\r\n"
//				+ ";";
		
		String sql = "SELECT F.FLIGHT_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, "
				+ "F.DEPARTURE_TIME, F.ARRIVAL_TIME, SEAT , AIRLINE, DEP_AIRPORT, "
				+ "FLIGHT_PRICE FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO "
				+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = ? "
				+ "AND SEAT > ? AND DEP_AIRPORT = ? "
				+ "AND FLIGHT_PRICE > ? "
				+ "UNION ALL SELECT F.FLIGHT_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, "
				+ "F.ARRIVAL_TIME, SEAT , AIRLINE, ARR_AIRPORT, FLIGHT_PRICE "
				+ "FROM FLIGHT F JOIN FLIGHT_TIME FT "
				+ "ON F.FLIGHT_NO = FT.FLIGHT_NO "
				+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = ? "
				+ "AND SEAT > ? AND ARR_AIRPORT = ? "
				+ "AND FLIGHT_PRICE > ? ORDER BY DEPARTURE_DATE, DEPARTURE_TIME ";
		
		//SQL 객체에 담기
		pstmt = conn.prepareStatement(sql);		

//		pstmt.setString(1, vo.getDepartureDate());
//		pstmt.setString(2, vo.getArrivalDate());
//		pstmt.setString(3, vo.getFlightPerson());
//		pstmt.setString(4, vo.getDepAirport());
//		pstmt.setString(5, vo.getFlightPrice());
//		ResultSet rs = pstmt.executeQuery();
		
		pstmt.setString(1, vo.getDepartureDate());
		pstmt.setString(2, vo.getFlightPerson());
		pstmt.setString(3, vo.getDepAirport());
		pstmt.setString(4, vo.getFlightPrice());
		pstmt.setString(5, vo.getReturnDate());
		pstmt.setString(6, vo.getFlightPerson());
		pstmt.setString(7, vo.getArrAirport());
		pstmt.setString(8, vo.getFlightPrice());

		ResultSet rs = pstmt.executeQuery();

		System.out.println("____________________________________________________________");
		System.out.println("");
		System.out.println("NO|       출발 시간        |  항공사 | 출발 공항 |남은 좌석|   가격   |");
		System.out.println("____________________________________________________________");
		while(rs.next()) {
		String flightNo = rs.getString("FLIGHT_NO"); //FLIGHT_TIME_NO 지만 변수는 flightNo 로 함
		String departureDate = rs.getString("DEPARTURE_DATE");
		String airline = rs.getString("AIRLINE");
		String depAirport = rs.getString("DEP_AIRPORT");
		String flightPerson = rs.getString("SEAT");
		String flightPrice = rs.getString("FLIGHT_PRICE");
		System.out.println(flightNo+ " | " + departureDate + " | " + airline + " | " + " "+ depAirport + " "+ " | " + " "+ flightPerson+ " " + " | " + flightPrice+"원" );

		System.out.println("\n ▶ 예약을 원하는 비행기 No 를 골라주세요. (출발, 복귀 비행기 모두 선택 필요)");
		Flight_Controller fc = new Flight_Controller().myReservation();


	}
		
}

	public void myReservation(Flight_Vo_MyFlight vo, Connection conn2) {
		
		
	}

}
