package flight3;

public class Flight_Main {
	
//	public static Flight_Vo reservation;

	//비행기 메인 메뉴
	//비행기 예약(reservation), 수정(change), 삭제(delete), 조회(search)
	public static void main(String[] args) {
		
		System.out.println("===== 비행기2 =====");
		
		Flight_Menu menu = new Flight_Menu();
		
		while(true) {
			//메뉴 보여주기
			int input = menu.showMenu();
			
			//선택한 값에 따라 동작
			switch(input) {
			case 1 : 
				new Flight_Controller().reservation(); //비행기 조회 , 예약
				break;
			case 2 : 
				new Flight_Controller().change(); //비행기 예약 수정 -> 날짜변경만 가능하다고 쓰기// 예약한거 다 출력하고 update...set...
				break;
			case 3 : 
				new Flight_Controller().delete(); //비행기 예약 삭제 //update... set... cancelYn = 'y' 
				break; 
			case 4 : 
				new Flight_Controller().search(); //내 예약 조회 //member 번호 이용해서 flight_reservation 다 출력해오기 
				break;							//(조회처럼) where = member = ? and cancelYn = 'n' >> join 이용해서 쓰기
			}
			
			
			
		}

	}

}
