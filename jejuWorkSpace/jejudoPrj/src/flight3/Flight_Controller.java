package flight3;

import util.InputUtil;

public class Flight_Controller {

	public void reservation() {
		
		System.out.println("----- 비행기 조회 -----");
		//데이터 받기
		System.out.print("출발 날짜(YY/MM/DD) : ");
		String departure_date = InputUtil.sc.nextLine();
		System.out.print("복귀 날짜(YY/MM/DD) : ");
		String return_date = InputUtil.sc.nextLine();
		System.out.print("인원 수 : ");
		String flight_person = InputUtil.sc.nextLine();
		System.out.print("출발 공항(김포공항, 김해공항, 청주공항) : ");
		String dep_airport = InputUtil.sc.nextLine();
		System.out.print("복귀 공항(제주공항) : ");
		String arr_airport = InputUtil.sc.nextLine();
		System.out.print("금액 : ");
		String flight_price = InputUtil.sc.nextLine();
		
		//데이터 뭉치기
		Flight_Vo vo = new Flight_Vo();
		
		vo.setDepartureDate(departure_date);
		vo.setReturnDate(return_date);
		vo.setFlightPerson(flight_person);
		vo.setDepAirport(dep_airport);
		vo.setArrAirport(arr_airport);
		vo.setFlightPrice(flight_price);
		
		//DB에 insert 하기 위해 DB insert 하는 서비스 메소드 호출
		new Flight_Service().reservation(vo);
		
		//실행 결과에 따른 응답 (result 가 1이 나오면 성공, 그게 아니면 실패)
//		if(result == 1) {
//			System.out.println("비행기 예약 성공 !!");
//		}else {
//			System.out.println("[Error:" + result + "] 비행기 예약 실패 ...");
//		}
		
		if(vo != null) {
			System.out.println("비행기 예약 성공 !!");
		} 

		
		
	}

	public Flight_Controller myReservation() {
		System.out.println("----- 비행기 예약 -----");
		System.out.println("출발 비행기 No : ");
		String myDepartureFlightNo = InputUtil.sc.nextLine();
		System.out.println("출발 비행기 No : ");
		String myReturnFlightNo = InputUtil.sc.nextLine();
		
		//데이터 뭉치기
		Flight_Vo_MyFlight vo = new Flight_Vo_MyFlight();
		vo.setMyDepartureFlightNo(myDepartureFlightNo);
		vo.setMyReturnFlightNo(myReturnFlightNo);
		
		return null;
		
	}

	public void change() {
		System.out.println("----- 비행기 예약 수정 -----");
		
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void search() {
		// TODO Auto-generated method stub
		
	}




}
