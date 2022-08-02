package car_main;

import car.CarController;
import car.Parsing;
import util.InputUtil;

public class CarMenu {
	
	public int showMenu() {
		System.out.println("1. 렌터카 조회");
		System.out.println("2. 렌터카 예약");
		System.out.println("3. 렌터카 예약 조회");
		System.out.println("4. 렌터카 예약 수정");
		System.out.println("5. 렌터카 예약 취소");
		
		return Parsing.getInt();
		
	}
	public int showCarCancel() {
		System.out.println("정말 예약 취소하시나요 ㅠㅠ");
		System.out.println("1. 예");
		System.out.println("2. 아니오");
		
		return Parsing.getInt();
	}
	public int showReserveEdit() {		
		System.out.println("보험 변경하시겠습니까?");
		System.out.println("1. 예");
		System.out.println("2. 아니오");
		
		return Parsing.getInt();
	}
}
