package flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCTemplate;
 
public class FlightDao {
	
	public int FlightReservation(FlightVo vo, Connection conn) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null; 
		
		try {
			//SQL 작성
			String sql = "INSERT INTO BOARD(NO, DEPARTURE_DATE, ARRIVAL_DATE, DEP_AIRPORT) VALUES(SEQ_FLIGHT_NO.NEXTVAL, ?, ?, ?)";

			//SQL 객체에 담기 및 완성(물음표 채우기)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDeparture_date());
			pstmt.setString(2, vo.getArrival_date());
			pstmt.setString(3, vo.getDep_airport());
			
			//SQL 실행 및 결과 저장
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}
	


}
