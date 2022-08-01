package flight2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCTemplate;

public class FlightDao {
	
	public int reservation(ReservationVo vo, Connection conn) throws Exception {
		//DB insert
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			//커넥션 준비 -> FlightService 에서 만듬
			
			//SQL 준비
			String sql = "INSERT INTO FLIGHT_MYINFO(FLIGHT_MYINFO_NO, DEPARTURE_DATE, ARRIVAL_DATE, "
					+ "FLIGHT_PERSON, DEP_AIRPORT, FLIGHT_PRICE) \r\n"
					+ "VALUES(SEQ_FLIGHT_MYINFO.NEXTVAL, ?, ?, ?, ?, ?)";
			
			//SQL 담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDeparture_date());
			pstmt.setString(2, vo.getArrival_date());
			pstmt.setString(3, vo.getFlight_person());
			pstmt.setString(4, vo.getDep_airport());
			pstmt.setString(5, vo.getFlight_price());
			
			//SQL 실행 및 결과 저장 (insert -> executeUpdate)
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			throw e; //예외 만들어서 service 한테 던지기 
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}//reservation
	
	public ReservationVo change(String reserve_no, String departure_date) throws Exception {//사용자로부터 입력받은 예약 번호, 출발 날짜
		//DB에 가서, 예약 번호, 출발 날짜 일치하는 행 조회
		
		//1.Connection 준비
		Connection conn = JDBCTemplate.getConnection();
		
		//2.SQL
		String sql = "SELECT ";
		
		//3.SQL 객체에 담기
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, reserve_no);
		pstmt.setString(2, departure_date);
		
		//4.SQL 실행
		ResultSet rs = pstmt.executeQuery();
		
		
		
		ReservationVo vo = null;
		
		
		return vo;
		
		
		
		
	}

}
