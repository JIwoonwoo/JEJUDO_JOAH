package flight3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import util.JDBCTemplate;

import static util.JDBCTemplate.*;

public class Flight_Dao {
	

	public List<Flight_Vo> reservation(Flight_Vo vo, Connection conn, int fno) throws SQLException {
		//DB 가서 사용자가 입력한 기준에 맞는 비행기 목록 조회
		
//		int result = 0;
		PreparedStatement pstmt = null;
		List<Flight_Vo> list = null;
		
		try {
			list = new ArrayList<Flight_Vo>();
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
					+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = TO_DATE(?) "
					+ "AND SEAT >= ? "
					+ "AND DEP_AIRPORT = ? "
					+ "AND FLIGHT_PRICE <= ? " + when
//					+ "UNION ALL SELECT FT.FLIGHT_TIME_NO, F.FLIGHT_NAME, FT.DEPARTURE_DATE, F.DEPARTURE_TIME, "
//					+ "F.ARRIVAL_TIME, SEAT , AIRLINE, DEP_AIRPORT, ARR_AIRPORT, FLIGHT_PRICE "
//					+ "FROM FLIGHT F JOIN FLIGHT_TIME FT ON F.FLIGHT_NO = FT.FLIGHT_NO "
//					+ "WHERE TO_CHAR(FT.DEPARTURE_DATE) = TO_DATE(?) "
//					+ "AND SEAT >= ? "
//					+ "AND DEP_AIRPORT = ? "
//					+ "AND FLIGHT_PRICE <= ? " + when
					+ "ORDER BY DEPARTURE_DATE, DEPARTURE_TIME";
			
			//SQL 객체에 담기
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setString(1, vo.getDepartureDate());
			pstmt.setString(2, vo.getFlightPerson());
			pstmt.setString(3, vo.getDepAirport());
			pstmt.setString(4, vo.getFlightPrice());
//			pstmt.setString(5, vo.getReturnDate());
//			pstmt.setString(6, vo.getFlightPerson());
//			pstmt.setString(7, vo.getArrAirport());
//			pstmt.setString(8, vo.getFlightPrice());
	
			ResultSet rs = pstmt.executeQuery();
	//		System.out.println("");
	//		System.out.println("▶ 선택하신 항공 스케쥴입니다.");
	//		System.out.println("_______________________________________________________________________________");
	//		System.out.println("");
	//		System.out.println("항공No| 항공편명 |   항공사  |   날짜     |출발 시간|남은 좌석|  출발 공항  |  도착 공항  |  가격  |");
	//		System.out.println("_______________________________________________________________________________");
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
				
				Flight_Vo fvo = new Flight_Vo();
				fvo.setFlightNo(flightNo);
				fvo.setDepartureDate(departureDate.toString());
				fvo.setAirline(airline);
				fvo.setFlightPerson(flightPerson);
				fvo.setDepAirport(depAirport);
				fvo.setFlightPrice(flightPrice);
				fvo.setArrAirport(arrAirport);
				fvo.setDepartureTime(departureTime);
				fvo.setFlightName(flightName);
				
				list.add(fvo);
	//			System.out.println(flightNo + " | " + flightName + " | " + airline + " | " +departureDate + " | " + departureTime+ " | " + " "+ flightPerson + " " + " | " + " "+ depAirport + " "+ " | " + " "+arrAirport+ " "+ " | " + flightPrice+"원" );
			}
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return list;

	}
	// 사용자 입력 비행기 조회
	public List<Flight_Vo> myReservation(Connection conn, Flight_Vo_MyFlight vo ) throws Exception {
		
//		int result = 0;
		PreparedStatement pstmt = null;
		List<Flight_Vo> list = null;
		

		try {
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
					+ "WHERE FT.FLIGHT_TIME_NO = ? "
					;
			
			list = new ArrayList<>();
			//SQL 객체에 담기
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, vo.getMyDepartureFlightNo());
			pstmt.setString(2, vo.getMyReturnFlightNo());
	
	
			ResultSet rs = pstmt.executeQuery();
			System.out.println("");
			System.out.println("_______________________________________________________________________________");
			System.out.println("");
			System.out.println("항공No|   항공편명    | 항공사 |  탑승 날짜    |출발 시간|도착 시간| 출발 공항 | 도착 공항 |  가격  |");
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
				
				Flight_Vo fvo = new Flight_Vo();
				fvo.setFlightNo(myDepartureFlightNo);
				fvo.setDepartureDate(departureDate.toString());
				fvo.setDepartureTime(departureTime);
				fvo.setArrivalTime(arrivalTime);
				fvo.setDepAirport(depAirport);
				fvo.setArrAirport(arrAirport);
				fvo.setFlightPrice(flightPrice);
				fvo.setFlightName(flightName);
				fvo.setAirline(airline);
				
				list.add(fvo);
				System.out.println(myDepartureFlightNo + " | " + flightName + " | " +  airline + " | " +departureDate + " | " + departureTime + " | " + arrivalTime + " | " + depAirport + " | " + arrAirport + ""+ " | " + flightPrice+ "원");
			}
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return list;

		
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
			pstmt.setInt(3, Main.loginNo);
			
			//SQL 실행 및 결과 저장
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
		
	}

	

	public void search(Flight_Vo_MyFlight myvo, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//SQL 준비
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
				System.out.println("회원번호|        예약일        | 출 |     출발날짜       | 항공편명 |  항공사  | 출발 시간 |   |     복귀날짜      | 항공편명 |  항공사 | 출발 시간");
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
	
	public List<Flight_Vo_MyFlight> searchAndDelete(Connection conn) throws Exception {

		
		//SQL 준비
		String sql = "SELECT 회원넘버,예약번호, 예약일, 출발날짜, 항공편명, 항공사, 출발시간, 복귀날짜, 복귀항공편명, 복귀항공사, 복귀출발시간\r\n"
				+ "FROM GO\r\n"
				+ "JOIN BACK USING(회원넘버, 예약일)\r\n"
				+ "WHERE 회원넘버 = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Flight_Vo_MyFlight> myFlightVoList = null;
		
		try {
			//SQL 담을 객체 준비 및 SQL 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);

			
			//SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();
			
			//커서 내리고, 칼럼별로 읽어오기, 객체로 만들기  << 반복
			//  rs.next,  rs.getXXX("칼럼명"), vo.setXXX
			
			myFlightVoList = new ArrayList<Flight_Vo_MyFlight>();
			
			while(rs.next()) {
				String myMemberNo = rs.getString("회원넘버");
				String reservDate = rs.getString("예약일");
//				String myDepartureNo = rs.getString("출발항공선택번호");
				String departureDate = rs.getString("출발날짜");
				String flightName = rs.getString("항공편명");
				String airline = rs.getString("항공사");
				String departureTime = rs.getString("출발시간");
//				String myReturnNo = rs.getString("복귀항공선택번호");
				String returnDate = rs.getString("복귀날짜");
				String returnFlightName = rs.getString("복귀항공편명");
				String returnAirline = rs.getString("복귀항공사");
				String returnTime = rs.getString("복귀출발시간");
				
				
				Flight_Vo_MyFlight vo = new Flight_Vo_MyFlight();
				vo.setReserveNo(rs.getInt("예약번호"));
				vo.setMyMemberNO(myMemberNo);
				vo.setReservDate(reservDate);
//				vo.setMyDepartureNo(myDepartureNo);
				vo.setDepartureDate(departureDate);
				vo.setFlightName(flightName);
				vo.setAirline(returnAirline);
				vo.setDepartureTime(departureTime);
//				vo.setMyReturnNo(myReturnNo);
				vo.setReturnDate(returnDate);
				vo.setReturnFlightName(returnFlightName);
				vo.setReturnAirline(returnAirline);
				vo.setReturnTime(returnTime);
				
//				System.out.println(vo);
				
//				System.out.println("  " +myMemberNo + " " +" | " + reservDate + " | " + myDepartureNo+ " | " +departureDate + " | " 
//				+flightName + " | " + airline+ " | " + " "+ departureTime + " " + " | " +myReturnNo+ " | "
//				+ returnDate+ " | " +  returnFlightName + " | " + returnAirline + " | " +returnTime);
				
				myFlightVoList.add(vo);

			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		//SQL 실행 결과 리턴
//		Flight_Controller fc = new Flight_Controller();
//		fc.delete2();
		return myFlightVoList;
		
	}
	
	public static int delete(Flight_Vo_MyFlight myvo, Connection conn) throws Exception {
		System.out.println("----- 비행기 예약 삭제 -----");
//		Flight_Controller fc = new Flight_Controller();
//		fc.delete2();
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			//SQL 준비
			String sql = "UPDATE FLIGHT_RESERVATION SET CANCEL_YN = 'Y' WHERE DEPARTURE_FLIGHT = ? AND RETURN_FLIGHT = ?";
			
			//SQL 담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, myvo.getMyDepartureFlightNo());
			pstmt.setString(2, myvo.getMyReturnFlightNo());	
//			pstmt.setString(1, myvo.getMyDepartureNo());	
//			pstmt.setString(2, myvo.getMyReturnNo());
			//SQL 실행 및 결과 저장
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("비행기 예약 삭제가 완료되었습니다.");
				JDBCTemplate.commit(conn);
			} else {
				System.out.println("삭제 안됨");
				JDBCTemplate.rollback(conn);
			}
			
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
		
		

		
	}
	
	
}