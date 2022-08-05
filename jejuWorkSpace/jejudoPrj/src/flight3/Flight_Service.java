package flight3;

import java.sql.Connection;
import java.util.List;


import util.JDBCTemplate;

public class Flight_Service {

	public List<Flight_Vo> reservation(Flight_Vo vo, int fno) {
		
		if(vo.getFlightPrice().equals("0")) {
			vo.setFlightPrice("10000000");
		}
		
		//비즈니스 로직 (출발 날짜, 복귀 날짜, 출발 공항 잘 입력하는지 확인하기)
		if(vo.getDepartureDate()==null) {
			System.out.println("출발 날짜 입력은 필수입니다.");
			return null;
		}
		
//		if(vo.getReturnDate()==null) {
//			System.out.println(" 복귀 날짜 입력은 필수입니다.");
//			return null;
//		}
		
		if(vo.getDepAirport()==null) {
			System.out.println("출발 공항 입력은 필수입니다.");
			return null;
		}
		
		//위 조건들 모두 통과하면 ? -> insert 진행
		Connection conn = null;
		List<Flight_Vo> list = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			list = new Flight_Dao().reservation(vo, conn, fno);
			
			if(list != null) {
//				System.out.println("잘됨");
				JDBCTemplate.commit(conn);
			}else {
				System.out.println("잘 안됨");
				JDBCTemplate.rollback(conn);
			}
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		}
		return list;
	}

	public List<Flight_Vo> myReservation(Flight_Vo_MyFlight vo) {
		
		//비즈니스 로직 (출발 비행기No, 복귀 비행기NO 잘 입력하는지 확인)
		if(vo.getMyDepartureFlightNo().equals(null)) {
			System.out.println("출발 날짜 입력은 필수입니다.");
			return null;
		}
		
		if(vo.getMyReturnFlightNo().equals(null)) {
			System.out.println(" 복귀 날짜 입력은 필수입니다.");
			return null;
		}

		
		//위 조건들 모두 통과하면 ? -> insert 진행
		Connection conn = null;
		List<Flight_Vo> result = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new Flight_Dao().myReservation(conn,vo);
			if(result != null) {
//				System.out.println("예약 잘됨~");
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
		
		Connection conn = null;
		try {
			conn = JDBCTemplate.getConnection();
			new Flight_Dao().myReservation(conn, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
    
		
	}
	
	//realReservation
	public int realReservation(Flight_Vo_MyFlight vo) {
		
		Connection conn = null;
		int result = -1;
		try {
			conn = JDBCTemplate.getConnection();
			result = new Flight_Dao().myRealReservation(vo,conn);
			
			if(result == 1) {
//				System.out.println("비행기 예약 완료 !!!");
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
		
		return result;
		
	}


	public int search(Flight_Vo_MyFlight myvo) {
		//비즈니스 로직 (출발 날짜, 복귀 날짜, 출발 공항 잘 입력하는지 확인하기)
		if(myvo.getMyMemberNo().equals(null)) {
			System.out.println("입력하신 회원 정보가 없습니다.");
			return -1;
		}
		//위 조건들 모두 통과하면 ? -> insert 진행
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			new Flight_Dao().search(myvo, conn);
			//여기에다가 복귀비행기
			if(result == 0) {
//				System.out.println("조회 잘됨~~");
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
		
	public List<Flight_Vo_MyFlight> search2() {
//		//비즈니스 로직
//		if(myvo.getMyMemberNo().equals(null)) {
//			System.out.println("입력하신 회원 정보가 없습니다.");
//			return -1;
//		}
//		//위 조건들 모두 통과하면 ? -> insert 진행
//		Connection conn = null;
//		int result = 0;
//		
//		try {
//			conn = JDBCTemplate.getConnection();
//			new Flight_Dao().searchAndDelete(myvo, conn);
//			//여기에다가 복귀비행기
//			if(result == 0) {
//				System.out.println("조회 잘됨~~~");
//				Flight_Dao f1 = new Flight_Dao();
//				f1.delete(myvo, conn);
//				
//				JDBCTemplate.commit(conn);
//			}else {
//				System.out.println("잘 안됨");
//				JDBCTemplate.rollback(conn);
//			}
//		} catch (Exception e) {
//			JDBCTemplate.rollback(conn);
//			e.printStackTrace();
//		}
//		return result;                       >>>>>>>List 로 만들어보면서 주석처리
		
		Connection conn = null;
		List<Flight_Vo_MyFlight> myFlightVoList = null;
		try {
			conn = JDBCTemplate.getConnection();
			
			myFlightVoList = new Flight_Dao().searchAndDelete(conn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return myFlightVoList;
		
	}
	
	public int delete(Flight_Vo_MyFlight myvo) {
		//비즈니스 로직 (출발 날짜, 복귀 날짜, 출발 공항 잘 입력하는지 확인하기)
		if(myvo.getMyDepartureFlightNo().equals(null)) {
			System.out.println("출발 항공 번호를 입력해 주세요.");
			return -1;
		}
		//위 조건들 모두 통과하면 ? -> insert 진행
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			new Flight_Dao();
			Flight_Dao.delete(myvo, conn);
			//여기에다가 복귀비행기
			if(result == 0) {
				System.out.println("삭제 잘됨 :)");
				Flight_Dao f1 = new Flight_Dao();
				Flight_Dao.delete(myvo, conn);
				
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
