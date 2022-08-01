package car;

import java.sql.Connection;
import java.util.List;

import car_main.Main;
import util.JDBCTemplate;

public class CarService {
	
	CarDao dao =new CarDao();
	
	public List<CarVo> carInquiry(CarVo vo) {
		List<CarVo> carVoList = null;
		
		// 사용자 input String > int로
		int person = Integer.parseInt(vo.getCarPerson());
		int size = Integer.parseInt(vo.getCarSize());
//		int fuel = Integer.parseInt(vo.getCarFuel());
	
		if(vo.getRentalDate().length() != 6) {
			System.out.println("날짜 형식을 맞춰주세요");
			return carVoList;
		}
		if(vo.getReturnDate().length() != 6) {
			System.out.println("날짜 형식을 맞춰주세요");
			return carVoList;
		}
		
		if(person >= 13) {
			System.out.println(person+"인승 차가 없습니다.");
			return carVoList;
		}
		
		if(size >= 4) {
			System.out.println("잘못된 입력입니다.");
			return carVoList;
		}
		
		Connection conn = null;
		try {
			conn = JDBCTemplate.getConnection();
		 	carVoList = dao.carInquiry(vo, conn);
		 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return carVoList;
		
	}
	public int carReserve(ReserveVo rVo) {
		// 사용자 input String > int로
		int insurance = Integer.parseInt(rVo.getInsurance());
		
		
		if(rVo.getRentalNo() >= 48) {
			System.out.println("준비된 차량이 없습니다.");
			return -1;
		}
		if(insurance >= 5) {
			System.out.println("잘못된 입력입니다.");
			return -2;
		}
		
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = dao.carReserve(rVo,conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			e.printStackTrace();
		}
		return result;
	
	}

}
