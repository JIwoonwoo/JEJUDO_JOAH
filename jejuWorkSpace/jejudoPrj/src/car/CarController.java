package car;

import util.InputUtil;

public class CarController {
	
	// 차 조회
	public void carInquiry() {
		CarVo vo = new CarVo();
		
		System.out.println("-------- 렌터카 조회 --------");
		System.out.println("*렌트 시작일 : ");
		String rental = InputUtil.sc.nextLine();
		vo.setReserveDate(rental);
		
		System.out.println("*렌트 반납일 : ");
		String retrun = InputUtil.sc.nextLine();
		vo.setReturnDate(retrun);
	
		String person = InputUtil.sc.nextLine();
		vo.setCarPerson(person);
		
		System.out.println("1.소형 2.중형 3.대형");
		String Size = InputUtil.sc.nextLine();
		vo.setCarSize(Size);
		
		System.out.println("1.휘발유 2.경유 3.전기");
		String Fuel = InputUtil.sc.nextLine();
		vo.setCarFuel(Fuel);
		
		new CarService().carInquiry(vo);
	}
	
	
}
