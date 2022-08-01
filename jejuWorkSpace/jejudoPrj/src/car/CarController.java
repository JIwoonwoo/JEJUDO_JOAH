package car;

import java.util.List;

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
		String retrun = InputUtil.sc.nextLine();
		
		System.out.print("탑승 인원 수 : ");
		String person = InputUtil.sc.nextLine();
		
		System.out.println("1.소형 2.중형 3.대형");
		String Size = InputUtil.sc.nextLine();
		
//		System.out.println("1.휘발유 2.경유 3.전기");
//		String Fuel = InputUtil.sc.nextLine();
		
		CarVo vo = new CarVo();
		vo.setRentalDate(rental);
		vo.setReturnDate(retrun);
		vo.setCarPerson(person);
		vo.setCarSize(Size);
		
		List<CarVo> carVoList = new CarService().carInquiry(vo);
		for(int i = 0; i<carVoList.size(); ++i) {
			CarVo resultVo = carVoList.get(i);
			
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
		String memberNo = InputUtil.sc.nextLine();
		System.out.print("*고유번호 입력 : ");
		int no = InputUtil.getInt();
		
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
		System.out.println("정말 예약 취소하시나요 ㅠㅠ");
		System.out.println("1. 예");
		System.out.println("2. 아니오");

		int input = InputUtil.getInt();
		if(input == 1) {
			new CarService().carReserve(null);
		} else {
			return;
		}
	}
	
}
