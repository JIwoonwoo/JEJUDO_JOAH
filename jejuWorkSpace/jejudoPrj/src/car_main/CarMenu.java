package car_main;

import util.InputUtil;

public class CarMenu {
	
	public int showMenu() {
		System.out.println("1. 렌터카 조회");
		System.out.println("2. 렌터카 예약");
		System.out.println("3. 렌터카 예약 수정");
		System.out.println("4. 렌터카 예약 취소");
		
		return InputUtil.getInt();
		
	}
}
