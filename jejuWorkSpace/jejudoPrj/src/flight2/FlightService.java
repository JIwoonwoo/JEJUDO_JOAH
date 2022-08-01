package flight2;

import java.sql.Connection;

import util.JDBCTemplate;

public class FlightService {
	
	public int reservation(ReservationVo vo) {
		
		//비즈니스 로직 (출발 날짜, 도착 날짜, 출발 공항 잘 입력하는지 확인하기)
		if(vo.getDeparture_date().equals(null)) {
			return -1;
		}
		
		if(vo.getArrival_date().equals(null)) {
			return -2;
		}
		
		if(!vo.getDep_airport().equals("김포공항")) {
			return -3;
		}
		
		if(!vo.getDep_airport().equals("김해공항")) {
			return -3;
		}
		
		if(!vo.getDep_airport().equals("청주공항")) {
			return -3;
		}
		
		//위의 조건들 모두 통과하면 insert 진행
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new FlightDao().reservation(vo, conn); //DB 에 insert 쿼리 날리고 result 에 담음
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTemplate.rollback(conn);
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
		
	}//reservation

}
