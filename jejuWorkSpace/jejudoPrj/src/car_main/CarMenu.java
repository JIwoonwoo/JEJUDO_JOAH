package car_main;

import car.CarController;
import car.Parsing;
import util.InputUtil;

public class CarMenu {
	
	public int showMenu() {
		System.out.println("1. 렌터카 조회");
		System.out.println("2. 렌터카 예약");
		System.out.println("3. 렌터카 예약 수정");
		System.out.println("4. 렌터카 예약 취소");
		
		return Parsing.getInt();
		
	}
	public int showCarCancel() {
		System.out.println("정말 예약 취소하시나요 ㅠㅠ");
		System.out.println("1. 예");
		System.out.println("2. 아니오");
		
		return Parsing.getInt();
	}
	public void showReserveEdit() {
		System.out.println("----- 예약 수정 -----");
		System.out.println("1. 날짜 변경");
		System.out.println("2. 보험 변경");
		System.out.println("3. 자동차 변경");
		System.out.println("9. 뒤로 가기");
		
		int input = Parsing.getInt();
		switch(input) {
		case 1 :
			new CarController().dateEdit();
			break;
		case 2 :
			break;
		case 3 :
			break;
		case 9 :
			return;
		}
		
	}
}
