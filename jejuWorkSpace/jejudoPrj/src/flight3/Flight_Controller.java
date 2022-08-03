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
		new Flight_Service().myReservation2(mVo);
		System.out.println("이대로 예약하시겠습니까 ? (1.예 2.아니오) : ");
		int input = InputUtil.getInt();
		
		if(input == 1) {
			Flight_Service fs = new Flight_Service();
			fs.realReservation(mVo);
		}else {
			return;
		}


		
		if(vo != null) {
			System.out.println("비행기 예약 성공 !!");
			
		} 

		
		
	}


	public void change() {
		System.out.println("----- 비행기 예약 수정 -----");
		
		
	}

	public void delete() { //비행기 예약 삭제
		
		System.out.println("----- 비행기 예약 삭제 -----");
		System.out.println("▶ 비행기 예약을 삭제하려면, 우선 예약 조회가 필요합니다.  \n");
		System.out.print("▶ 예약 조회 하려면 (1)번, 돌아가려면 (2)번을 눌러 주세요. :\n ");
		int input = InputUtil.getInt();
		if(input == 1) {
			Flight_Controller.search2();
		}else if(input == 2) {
			return;
		}else {
			System.out.println("▷ (1)번과 (2)번 중에서 눌러 주세요. 다시 돌아갑니다...\n");
			return;
		}

		
	}




	public static void search() { //내 비행기 예약 조회하기
		
		System.out.println("----- 내 비행기 예약 조회 -----");
		System.out.print("회원 번호를 입력해 주세요 : ");
		String myMemberNo = InputUtil.sc.nextLine();
		
		Flight_Vo_MyFlight myvo = new Flight_Vo_MyFlight();
		myvo.setMyMemberNO(myMemberNo);
		
		new Flight_Service().search(myvo);
	}

	private static void search2() {
		System.out.println("----- 내 비행기 예약 조회 -----");
		System.out.print("회원 번호를 입력해 주세요 : ");
		String myMemberNo = InputUtil.sc.nextLine();
		
		Flight_Vo_MyFlight myvo = new Flight_Vo_MyFlight();
		myvo.setMyMemberNO(myMemberNo);
		
		new Flight_Service().search(myvo);
		
	}




}
