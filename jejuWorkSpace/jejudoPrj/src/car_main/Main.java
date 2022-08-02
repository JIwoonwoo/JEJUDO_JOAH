package car_main;

import car.CarController;
import car.CarVo;

public class Main {
	// 예약할 때 필요
	public static CarVo inquiryCar;
	
	public static void main(String[] args) {
		CarController carCon = new CarController();
	
		
		while(true) {
			System.out.println("======== 렌터카 ========");
			int input = new CarMenu().showMenu();
			switch(input) {
			case 1 :
				carCon.carInquiry();
				break;
			case 2 :
				carCon.carReserve();
				break;
			case 3 :
				break;
			case 4 :
				break;
			}
		}
	}

}
