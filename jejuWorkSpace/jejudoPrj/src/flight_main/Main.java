package flight_main;

import flight.Flight;
import flight.FlightController;
import util.InputUtil;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("===== 메인페이지 =====");
		System.out.println("1) 예약하기");
		System.out.println("2) 여행정보");
		System.out.println("3) 마이페이지");
		System.out.println("4) 고객센터");
		int mainAnswer = InputUtil.sc.nextInt();
		if(mainAnswer == 1) {
			Flight fm = new Flight();
			
			
			while(true) {
				//메뉴 보여주기
				int flightAnswer  = Flight.flightMain();
				if(flightAnswer == 1) {
					//비행기 관련 메뉴 
					FlightController fc = new FlightController();
					fc.flightReservation();

				}
				
				//선택한 값에 따라 동작
				
			}//while
			
			
			
		} else {
			System.out.println("1번 말고 다른거 누름");
		}
		
			
		}


		




}
