package flight3;

import util.InputUtil;

public class Flight_Menu {

	public int showMenu() {
		
		//비행기 조회(reservation), 예약(myReservation), 수정(change), 삭제(delete), 조회(search)
		System.out.println("1. 비행기 조회 ");
		System.out.println("2. 비행기 예약 ");
		System.out.println("3. 비행기 수정 ");
		System.out.println("4. 비행기 삭제 ");
		System.out.println("5. 비행기 조회 ");

		return InputUtil.getInt();
	}

}
