package flight3;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import util.InputUtil;
import util.JDBCTemplate;

public class Flight_Controller {

	public void reservation() {
		
		System.out.println("----- 비행기 조회 -----");
		//데이터 받기
		System.out.print("출발 날짜(YYMMDD) : ");
		String departure_date = InputUtil.sc.nextLine();
		System.out.print("복귀 날짜(YYMMDD) : ");
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

	public void delete() throws Exception { //비행기 예약 삭제
		
		System.out.println("----- 비행기 예약 삭제 -----");
		System.out.println("▶ 비행기 예약을 삭제하려면, 우선 예약 조회가 필요합니다." );
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


	public int delete2() throws Exception {
		Flight_Vo_MyFlight mvmo = new Flight_Vo_MyFlight();
		
		Connection conn = null;
		
		System.out.print("▶ 출발 항공 번호를 선택해 주세요 : ");
		String departureFlight = InputUtil.sc.nextLine();
		System.out.print("▶ 출발 항공 번호를 선택해 주세요 : ");
		String returnFlight = InputUtil.sc.nextLine();
		
		mvmo.setMyDepartureFlightNo(departureFlight);
		mvmo.setMyReturnFlightNo(returnFlight);
		
		//여기부터 작업 이어서 하기!!
		System.out.println("▶ '"+departureFlight + "'번 출발 항공과, '"+returnFlight+"'번 복귀 항공 예약을 삭제합니다. (1)번 '네', (2)번 '다시 선택'");
		int input = InputUtil.getInt();
		if(input == 1) {
			conn = JDBCTemplate.getConnection();
			Flight_Dao.delete(mvmo, conn);
		}else if(input == 2) {
			Flight_Controller.search2();
			
		}else {
			System.out.println("▷ (1)번과 (2)번 중에서 눌러 주세요. 다시 돌아갑니다...\n");
			return 0;
		}
		return -1;

		
	}


	public static void search() { //내 비행기 예약 조회하기
		
		System.out.println("----- 내 비행기 예약 조회 -----");
		System.out.print("회원 번호를 입력해 주세요 : ");
		String myMemberNo = InputUtil.sc.nextLine();
		
		Flight_Vo_MyFlight myvo = new Flight_Vo_MyFlight();
		myvo.setMyMemberNO(myMemberNo);
		
		new Flight_Service().search(myvo);
	}

	private static void search2() throws Exception {// 비행기 예약 취소 누를 때 비행기 예약을 조회하는 메소드
		System.out.println("----- 내 비행기 예약 조회 -----");
		System.out.print("회원 번호를 입력해 주세요 : ");
		String myMemberNo = InputUtil.sc.nextLine();
		
		Flight_Vo_MyFlight myvo = new Flight_Vo_MyFlight();
		myvo.setMyMemberNO(myMemberNo);
		
		List<Flight_Vo_MyFlight> myFlightVoList = new Flight_Service().search2();
		 
		
		for(int i = 0 ; i < myFlightVoList.size(); ++i) {
			Flight_Vo_MyFlight resultVo = myFlightVoList.get(i);
			String mmno = resultVo.getMyMemberNo(); //아래에 다 추가하기
			String reservDate = resultVo.getReservDate();
			String myDepartureNo = resultVo.getMyDepartureNo();
			String departureDate = resultVo.getDepartureDate();
			String flightName = resultVo.getFlightName();
			String returnAirline = resultVo.getReturnAirline();
			String departureTime = resultVo.getDepartureTime();
			String myReturnNo = resultVo.getMyReturnNo();
			String returnDate = resultVo.getReturnDate();
			String returnFlightName = resultVo.getReturnFlightName();
			String returnTime = resultVo.getReturnTime();
			String airline = resultVo.getAirline();

			//출력문 작성
			System.out.println("  " +mmno + " " +" | " + reservDate + " | " + myDepartureNo+ " | " +departureDate + " | " 
			+flightName + " | " + airline+ " | " + " "+ departureTime + " " + " | " +myReturnNo+ " | "
			+ returnDate+ " | " +  returnFlightName + " | " + returnAirline + " | " +returnTime);
		}//for 문
		
		Flight_Controller fc = new Flight_Controller();
		fc.delete2();
	}






}
