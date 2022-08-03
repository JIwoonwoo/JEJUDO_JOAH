package flight3;

import java.sql.Connection;
import java.util.List;


import util.JDBCTemplate;

public class Flight_Service {

	public int reservation(Flight_Vo vo, int fno) {
		
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
			new Flight_Dao().reservation(vo, conn, fno);
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
			new Flight_Dao().myReservation(conn,vo);
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
	// 조회 한 비행기 서비스로직
	public void myReservation2(Flight_Vo_MyFlight vo) {
		System.out.println("Flight_Service의 myReservation2");
		
		Connection conn = null;
		try {
			conn = JDBCTemplate.getConnection();
			new Flight_Dao().myReservation(conn, vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		
	}
	
	//realReservation
	public void realReservation(Flight_Vo_MyFlight vo) {
		
		Connection conn = null;
		try {
			conn = JDBCTemplate.getConnection();
			int result = new Flight_Dao().myRealReservation(vo,conn);
			
			if(result == 1) {
				System.out.println("비행기 예약 완료 !!!");
				JDBCTemplate.commit(conn);
			}else {
				System.out.println("안됨");
				JDBCTemplate.rollback(conn);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
	}

	public List<Flight_Vo_MyFlight> showList() {//내 예약 조회
		
		Connection conn = null;
		List<Flight_Vo_MyFlight> flightVoMyFlight = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			
			flightVoMyFlight = new Flight_Dao().showList(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return flightVoMyFlight;
		
	}

	/*
	 * 예약 상세조회
	 */
	public Flight_Vo_MyFlight showDetailByNo(int num) {
		
		Connection conn = null;
		Flight_Vo_MyFlight vo = null;

		try {
			conn = JDBCTemplate.getConnection();
			vo = new Flight_Dao().showDetailByNo(conn, num);
		}catch(Exception e) {
			System.out.println("error!");
			e.printStackTrace();
		}
		
		return null;
	}


}
