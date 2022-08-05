package car;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import car_main.Main;

import util.JDBCTemplate;


public class CarService {
	
	CarDao dao =new CarDao();
	
	public List<CarVo> carInquiry(CarVo vo) {
		List<CarVo> carVoList = null;

		// 사용자 input String > int로
//		int person = Integer.parseInt(vo.getCarPerson());
//		int size = Integer.parseInt(vo.getCarSize());
//		int fuel = Integer.parseInt(vo.getCarFuel());
	    
		int person = Parsing.getInt(vo.getCarPerson());
		int size = Parsing.getInt(vo.getCarSize());
		int rentalDate = Parsing.getInt(vo.getRentalDate());
		int returnDate = Parsing.getInt(vo.getReturnDate());
		
		if(vo.getRentalDate().length() != 6) {
			System.out.println("날짜 형식을 맞춰주세요");
			return carVoList;
		}
		if(vo.getReturnDate().length() != 6) {
			System.out.println("날짜 형식을 맞춰주세요");
			return carVoList;
		}

		if(rentalDate >= returnDate) {
			System.out.println("날짜를 확인해주세요.");
			return carVoList;
		}
		
		int result = Parsing.getDate(vo.getRentalDate());
	    
		if(result >= 0) {
			System.out.println("날짜를 확인해주세요.");
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
		 	
		 	if(carVoList != null) {
				Main.inquiryCar = vo;
		 	} 
		 	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return carVoList;
		
	}
	private void Parsing() {
		// TODO Auto-generated method stub
		
	}
	public int carReserve(ReserveVo rVo) {
		
		// 사용자 input String > int로
		int insurance = Parsing.getInt(rVo.getInsurance());
		
		
		if(rVo.getRentalNo() > 100) {
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
				System.out.println("자동차 인설트 성공");
			} else {
				JDBCTemplate.rollback(conn);
				System.out.println("자동차 인설트 실패");
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			System.out.println("error 자동차 인설트 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	
	}
	
	public List<ReserveVo> reserveInquiry(int input) {
		Connection conn = null;
		List<ReserveVo> reserveVoList = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			reserveVoList = dao.reserveInquiry(conn, input);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return reserveVoList;
	}
	
	public int carCancel(ReserveVo rVo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = JDBCTemplate.getConnection();
			result = dao.carCancel(rVo,conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	public ReserveVo detailInquiry(int rentalNo) {
		Connection conn = null;
		ReserveVo vo = null;
		try {
			conn = JDBCTemplate.getConnection();
			vo = dao.detailInquiry(rentalNo,conn);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return vo;
	}
	public int insuranceEdit(ReserveVo rVo) {
		int insurance = Parsing.getInt(rVo.getInsurance());
		
		if(insurance >= 5) {
			System.out.println("잘못된 입력입니다.");
			return -2;
		}
		
		int result = 0;
		Connection conn = null;
		try {
			conn = JDBCTemplate.getConnection();
			result = dao.insuranceEdit(rVo,conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	

}
