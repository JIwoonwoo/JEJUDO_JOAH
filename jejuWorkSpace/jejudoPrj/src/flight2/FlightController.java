package flight2;

import util.InputUtil;

public class FlightController {

	public void reservation() {
		
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
		ReservationVo vo = new ReservationVo();
		vo.setDeparture_date(departure_date);
		vo.setArrival_date(arrival_date);
		vo.setFlight_person(flight_person);
		vo.setDep_airport(dep_airport);
		vo.setFlight_price(flight_price);
		
		//DB에 insert 하기 위해, DB insert 하는 서비스 메소드 호출
		int result = new FlightService().reservation(vo);
		
		//실행 결과에 따른 응답 (result 가 1 이 나오면 성공, 그게 아니면 실패)
		if(result == 1) {
			System.out.println("비행기 예약 성공 !");
		}else {
			System.out.println("[Error:" + result + "] 비행기 예약 실패 ...");
		}
		
		
	}//reservation
	
	public void change() {
		
		System.out.println("----- 비행기 예약 수정 -----"); //예약 번호, 출발 날짜로 조회
		System.out.println("예약 번호 : ");
		String reserve_no = InputUtil.sc.nextLine();
		System.out.println("출발 날짜 : ");
		String departure_date = InputUtil.sc.nextLine();
		
		try {
			ReservationVo vo = new FlightDao().change(reserve_no, departure_date);
			if(vo != null) {
				//예약 확인 성공
				System.out.println("예약 수정 페이지로 넘어갑니다");
			}else {
				//예약 조회 실패
				System.out.println("해당 예약 조회가 되지 않습니다.");
			}
		}catch (Exception e) {
			//예약 조회 실패
			System.out.println("예약 조회 실패 ");
			e.printStackTrace();
		}
	}//change
		
	
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public void search() {
		// TODO Auto-generated method stub
		
	}

	
	
}
