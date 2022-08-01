package payment;

import main.Main;
import member.MemberVo;

public class PayController {
	/*
	 * pay의 getter, setter 따로 만들기
	 * 로그인 확인
	 * id로 회원의 예약번호 와 금액조회
	 * sql에서 항공, 렌트, 숙박의 금액들과 멤버의 포인트 가져오기(예약 날짜를 활용 1박금액 및 출발, 도착의 항공 금액)
	 * 렌트는 널 허용, 나머지는 널 불가
	 * 포인트 사용 유무, 할인 금액 보여주기
	 * 결제선 -> 카드, 현금
	 * 결제내역 및 예약정보 insert
	 */
	
	public void payment() {
		
		int no = Main.loginNo;
		//로그인 체크
		if(no == 0) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}
		PayService ps = new PayService();
		MemberVo mvo = ps.userInfo(no);
		
		PayVo vo = ps.reservation(no);
		// 포인트 보여주기, 사용유무, 사용량
		int myUsePoint = ps.usePoint(mvo);
		
		// 결제금액 보여주기
		int lastPay = totalPay - myUsePoint;

		// 적립금
		int earnPoint = lastPay / 10;

		// 결제종류선택, 결제하기
		char howPay = ps.howPay(lastPay);

		
		
		
		
		//예약 내역 확인
		System.out.println("예약 내역을 확인 하시겠습니까?");
		System.out.println("1. 확인");
		System.out.println("2. 건너뛰기");
		
		
	}
	
	
}

