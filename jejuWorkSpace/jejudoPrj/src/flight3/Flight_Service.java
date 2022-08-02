package flight3;

import java.sql.Connection;

import util.JDBCTemplate;

public class Flight_Service {

	public int reservation(Flight_Vo vo) {
		
		//비즈니스 로직 (출발 날짜, 복귀 날짜, 출발 공항 잘 입력하는지 확인하기)
		if(vo.getDepartureDate().equals(null)) {
			System.out.println("출발 날짜 입력은 필수입니다.");
			return -1;
		}
		
		if(vo.getReturnDate().equals(null)) {
			System.out.println(" 복귀 날짜 입력은 필수입니다.");
			return -2;
		}
		
		if(vo.getDepAirport().equals(null)) {
			System.out.println("출발 공항 입력은 필수입니다.");
			return -3;
		}
		
		//위 조건들 모두 통과하면 ? -> insert 진행
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			new Flight_Dao().reservation(vo, conn);
			if(result == 0) {
				System.out.println("잘됨");
				JDBCTemplate.commit(conn);
			}else {
				System.out.println("잘 안됨");
				JDBCTemplate.rollback(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		}
		return result;
	}

	public int myReservation(Flight_Vo_MyFlight vo) {
		
		//비즈니스 로직 (출발 비행기No, 복귀 비행기NO 잘 입력하는지 확인)
		if(vo.getMyDepartureFlightNo().equals(null)) {
			System.out.println("출발 날짜 입력은 필수입니다.");
			return -1;
		}
		
		if(vo.getMyReturnFlightNo().equals(null)) {
			System.out.println(" 복귀 날짜 입력은 필수입니다.");
			return -2;
		}

		
		//위 조건들 모두 통과하면 ? -> insert 진행
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			new Flight_Dao().myReservation(vo, conn);
			if(result == 0) {
				System.out.println("잘됨");
				JDBCTemplate.commit(conn);
			}else {
				System.out.println("잘 안됨");
				JDBCTemplate.rollback(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		}
		return result;
		
	}
}
