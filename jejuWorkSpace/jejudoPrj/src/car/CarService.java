package car;

import java.sql.Connection;

import util.JDBCTemplate;

public class CarService {

	public void carInquiry(CarVo vo) {
		
		int person = Integer.parseInt(vo.getCarPerson());
		int size = Integer.parseInt(vo.getCarSize());
		int fuel = Integer.parseInt(vo.getCarFuel());
		
		if(person >= 12) {
			System.out.println("선택하신 인원 수의 차량은 없습니다.");
		}
		
		if(size >= 3 || fuel >= 3 ) {
			System.out.println("잘못된 입력입니다.");
		}
		
		Connection conn = null;
		try {
			conn = JDBCTemplate.getConnection();
			new CarDao().carInquiry(vo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
