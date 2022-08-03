package flight3;

import java.util.List;


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
		System.out.print("금액 <= : ");
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
		new Flight_Service().reservation(vo,2);
		System.out.print("▶ 출발 비행기 NO 를 선택해 주세요. : "); 
		String d1 = InputUtil.sc.nextLine();

		new Flight_Service().reservation(vo,1);
		System.out.print("▶ 복귀 비행기를 선택해 주세요. : ");
		String r1 = InputUtil.sc.nextLine();
		// 출발/복귀 비행기 mVo에 담아줌
		Flight_Vo_MyFlight mVo = new Flight_Vo_MyFlight();
		mVo.setMyDepartureFlightNo(d1);
		mVo.setMyReturnFlightNo(r1);
		//여기서 내가 고른 예약들을 확인되게
		System.out.println(mVo);
		new Flight_Service().myReservation2(mVo);
		System.out.println("이대로 예약하시겠습니까 ? (1.예 2.아니오) : ");
		int input = InputUtil.getInt();
		
		if(input == 1) {
			Flight_Service fs = new Flight_Service();
			fs.realReservation(mVo);
		}else {
			return;
		}

	

		//실행 결과에 따른 응답 (result 가 1이 나오면 성공, 그게 아니면 실패)
//		if(result == 1) {
//			System.out.println("비행기 예약 성공 !!");
//		}else {
//			System.out.println("[Error:" + result + "] 비행기 예약 실패 ...");
//		}
		
		if(vo != null) {
			System.out.println("비행기 예약 성공 !!");

			
			
//			Flight_Controller fc = new Flight_Controller().myReservation();
		} 

		
		
	}


	public void change() {
		System.out.println("----- 비행기 예약 수정 -----");
		
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void showList() {// 내 예약 보여주기
		
		List<Flight_Vo_MyFlight> fvmf = new Flight_Service().showList();
		
		System.out.println("----- 비행기 예약 조회 -----");
		
		for(int i = 0; i < fvmf.size(); ++i) {
			Flight_Vo_MyFlight search = fvmf.get(i);
			
			int mmno = search.getMyMemberNo();
			String gmdf = search.getMyDepartureFlightNo();
			String gmrf = search.getMyReturnFlightNo();
			
			System.out.println(mmno);
			System.out.println(gmdf);
			System.out.println(gmrf);
		}

		//상세조회 물어보기
		int num = new Flight_Menu().showDetailByNo();
		
		//0번 -> 메인메뉴로 리턴
		if(num == 0) {
			System.out.println("메인메뉴로 돌아갑니다.");
			return;
		}

		//번호 받으면? -> 상세조회
		Flight_Vo_MyFlight vo = new Flight_Service().showDetailByNo(num);
		
		//실행결과
		System.out.println("----- 예약 상세조회 -----");
		System.out.print("회원번호 : " + vo.getMyMemberNo());
		System.out.print("출발 비행기No : " + vo.getMyDepartureFlightNo());
		System.out.print("복귀 비행기No : " + vo.getMyReturnFlightNo());

	}//showList




}
