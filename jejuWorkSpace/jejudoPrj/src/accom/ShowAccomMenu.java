package accom;

import util.InputUtil;

public class ShowAccomMenu {
	
	public void ShowAccomMenu() {
	
		System.out.println("---- 숙소 예약하기 ----");
		
		System.out.println("숙소에 머무실 일정을 입력해주세요.");
		System.out.print("체크인 날짜 : ");
		String checkin = InputUtil.sc.nextLine();
		System.out.print("체크아웃 날짜 : ");
		String checkout = InputUtil.sc.nextLine();
		
		System.out.print("인원 수 : ");
		int people = InputUtil.sc.nextInt();
	
		
	}
	

}
