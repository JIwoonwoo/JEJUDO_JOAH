package car;

import java.util.List;

import car_main.CarMenu;
import car_main.Main;
import util.InputUtil;

public class CarController {
	
	public void carInquiry() {	
		
		System.out.println("-------- 렌터카 조회 --------");
		System.out.println();
		System.out.println("YYMMDD 형식으로 입력해주세요.");
		System.out.print("렌트 시작일 : ");
		String rental = InputUtil.sc.nextLine();
		
		System.out.print("렌트 반납일 : ");
		String returnDate = InputUtil.sc.nextLine();
		
		System.out.print("탑승 인원 수 : ");
		String person = InputUtil.sc.nextLine();
		
		System.out.println("1.소형 2.중형 3.대형");
		String size = InputUtil.sc.nextLine();
		
		
//		System.out.println("1.휘발유 2.경유 3.전기");
//		String Fuel = InputUtil.sc.nextLine();
		
		CarVo vo = new CarVo();
		vo.setRentalDate(rental);
		vo.setReturnDate(returnDate);
		vo.setCarPerson(person);
		vo.setCarSize(size);
		// 서비스로 간다.
		List<CarVo> carVoList = new CarService().carInquiry(vo);
		// 널인지 검사
		if (carVoList == null) {
			System.out.println("조회 실패...");
			return;
		}
		
		for(int i = 0; i < carVoList.size(); i++) {
			
			CarVo resultVo = carVoList.get(i);
			
			String no = resultVo.getRentalNo();
			String name = resultVo.getCarName();
			String size2 = resultVo.getCarSize();
			String fuel = resultVo.getCarFuel();
			String price = resultVo.getDayPrice();
			
			System.out.println("고유 번호 : "+no+" | "+name+" | "+size2+" | "+fuel+ " | "+price);
		}
	}
	
	public void carReserve() {
		
		if(Main.inquiryCar == null) {
			System.out.println("렌터카 조회 먼저 해주세요 ! ");
			return;
		}
		String rentDate = Main.inquiryCar.getRentalDate();
		String returnDate = Main.inquiryCar.getReturnDate();
		System.out.println("렌트 시작일 : "+rentDate);
		System.out.println("렌트 반납일 : "+returnDate);
		System.out.println("-------- 렌터카 예약 --------");
		System.out.println();
		System.out.print("회원번호(test) : ");
		int memberNo = Parsing.getInt();
		System.out.print("*고유번호 입력 : ");
		int no = Parsing.getInt();
		
//		System.out.println("YYMMDD 형식으로 입력해주세요.");
//		System.out.print("*렌트 시작일 : ");
//		String rentDate = InputUtil.sc.nextLine();
//		System.out.print("*렌트 반납일 : ");
//		String returnDate = InputUtil.sc.nextLine();
		
		System.out.println("---------- 보험 -----------");
		System.out.println("0.보험안함 1.일반자차 2.완전자차 3.슈퍼자차");
		String insurance = InputUtil.sc.nextLine();
		
		ReserveVo rVo = new ReserveVo();
		rVo.setMemberNo(memberNo);
		rVo.setRentalNo(no);
		rVo.setRentalDate(rentDate);
		rVo.setReturnDate(returnDate);
		rVo.setInsurance(insurance);
		
		int result = new CarService().carReserve(rVo);
		
		if(result == 1) {
			System.out.println("예약 완료");
		} else {
			System.out.println("[에러 ::: "+result+"] 예약 실패");
		}
	}

	
	public void carCancel() {
		
		ReserveVo rVo = new ReserveVo();
		
		//예약 자동차 조회
		reserveInquiry();
		
		int input = new CarMenu().showCarCancel();		
		if(input == 1) {
			System.out.println("------ 예약 취소 ------");
			
		}else {
			return;
		}
		
		
		//사용자한테 입력받기 멤버넘버와 자동차 예약번호로 취소 해주기
		System.out.print("회원 번호(test) : ");
		int memberNo = Parsing.getInt();
		System.out.print("취소하실 렌터카 예약 번호 : ");
		int reserveNO = Parsing.getInt();
		
		rVo.setMemberNo(memberNo);
		rVo.setReserveNo(reserveNO);
		
		int result = new CarService().carCancel(rVo);;
		
		if(result == 1) {
			System.out.println("취소 완료");
		}else {
			System.out.println("취소 실패");
		}
		
	}
	public void reserveInquiry() {
		System.out.println("회원 번호(test) : ");
		int input = Parsing.getInt();
		
		List<ReserveVo> reserveVoList = new CarService().reserveInquiry(input);
		
		if(reserveVoList == null) {
			System.out.println("예약된 렌터카가 없습니다.");
			return;
		}
		ReserveVo result = null;
		System.out.println("------ 내가 예약한 렌터카 ------");
		for(int i = 0; i < reserveVoList.size(); i++) {
			result = reserveVoList.get(i);
			
			int reserveNo = result.getReserveNo();
			int rentalNo = result.getRentalNo();
			String rentalDate = result.getRentalDate();
			String returnDate = result.getReturnDate();
			String reserveDate = result.getReserveDate();
			String insurance = result.getInsurance();
			String dayPrice = result.getDayPrice();
			System.out.println(result);
			
//			System.out.println("예약번호 : "+reserveNo +" | "+rentalNo+" | "+rentalDate+" | "+returnDate+" | " +reserveDate+" | "+insurance+" | "+dayPrice);
			result = new CarService().detailInquiry(rentalNo);
			
		}
		
	}

	public void dateEdit() {
		// TODO Auto-generated method stub
		
	}
	
}
