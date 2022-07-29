package flight;

import util.InputUtil;

public class FlightController {
	
	/*
	 * 비행기 예약
	 * 
	 * 데이터 받기
	 * 
	 * 비지니스 로직 (서비스)
	 * 
	 * DB에 insert (DAO)
	 * 
	 */
	
	public void flightReservation() {
		
		System.out.println("----- 비행기 예약 -----");
		
		//데이터 받기
		System.out.print("출발 날짜 : ");
		String departure_date = InputUtil.sc.nextLine();
		System.out.print("복귀 날짜 : ");
		String arrival_date = InputUtil.sc.nextLine();
		System.out.print("인원 수 : ");
		String flight_person = InputUtil.sc.nextLine();
		System.out.print("출발 공항 : ");
		String dep_airport = InputUtil.sc.nextLine();
		System.out.print("금액 : ");
		String flight_price = InputUtil.sc.nextLine();
		
		//데이터 뭉치기
		FlightVo vo = new FlightVo();
		vo.setDeparture_date(departure_date);
		vo.setArrival_date(arrival_date);
		vo.setDep_airport(dep_airport);
		vo.setFlight_price(flight_price);

		//DB에 insert 하기 위해, DB insert 하는 서비스 메소드 호출
		int result = new FlightService().flightReservation(vo);
		
		//insert 결과에 따라 로직 처리
		if(result == 1) {
			System.out.println("비행기 예약 성공 !");
		} else {
			System.out.println("비행기 예약 실패 ...");
		}
		
	}

}
