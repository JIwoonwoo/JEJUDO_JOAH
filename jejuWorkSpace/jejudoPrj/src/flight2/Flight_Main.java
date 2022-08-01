package flight2;

public class Flight_Main {
	
	public static ReservationVo flightReservation;
	
	//비행기 메인 메뉴
	//비행기 예약(reservation), 수정(change), 삭제(delete), 조회(search)
	public static void main(String[] args) throws Exception {
		
		System.out.println("===== 비행기 =====");
		
		Menu menu = new Menu();
		
		
		while(true) {
			//메뉴 보여주기
			int input = menu.showMenu();
			
			//선택한 값에 따라 동작 >> 예약 수정 삭제 조회
			switch(input) {
			case 1:
				new FlightController().reservation();
				break;
			case 2:
				new FlightController().change();
			case 3:
				new FlightController().delete();
			case 4:
				new FlightController().search();
			}//switch
		}//while

	}//method

}//class
