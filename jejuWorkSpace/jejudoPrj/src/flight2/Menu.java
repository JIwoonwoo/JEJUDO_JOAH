package flight2;

import util.InputUtil;

public class Menu {

	public int showMenu() throws Exception {
		//비행기 예약(reservation), 수정(change), 삭제(delete), 조회(search)
		System.out.println("1. 비행기 예약 ");
		System.out.println("2. 비행기 수정 ");
		System.out.println("3. 비행기 삭제 ");
		System.out.println("4. 비행기 조회 ");

		return InputUtil.getInt();
	}

}//class

