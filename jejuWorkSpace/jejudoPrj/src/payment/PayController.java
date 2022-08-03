package payment;

import main.Main;
import member.MemberVo;
import util.InputUtil;

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
	
	public void payment(int no) {
		
		
		PayService ps = new PayService();
		MemberVo mvo = ps.userInfo(no);
		
		PayVo vo = ps.reservation(no);
		if(vo == null) {
			return;
		}
		
		// 포인트 보여주기, 사용유무, 사용량
		int pointUsed = ps.usePoint(mvo);
		System.out.println("사용한 포인트 : " + pointUsed);
		
		// 결제금액 보여주기
		int cutPrice = vo.getTotalPay() - pointUsed;
		System.out.println("최종금액 : " + cutPrice);

		// 적립금
		int myPoint = cutPrice / 20;
		System.out.println("획득한 적립금 : " + myPoint);

		// 결제종류선택, 결제하기
		int payMethod = ps.howPay(cutPrice);
		System.out.println("결제 방법 : " + payMethod);
		
		//결제정보 집어넣기
		vo.setPointUsed(pointUsed);
		vo.setCutPrice(cutPrice);
		vo.setMypoint(myPoint);
		vo.setPayMethod(payMethod);
		
		//최종 포인트
		int leavePoint = (mvo.getPoint()-pointUsed)+myPoint;
		
		//결제 입력
		System.out.println("결제 정보");
		System.out.println(vo);
		System.out.println("\r결제를 완료?");
		System.out.println("1. 완료 , 0. 뒤로가기");
		while(true) {
			int a = InputUtil.getInt();
			if(a==1) {
				System.out.println("결제를 완료하러 갑니다.");
				ps.payEnd(no,leavePoint,vo);
				break;
			}if (a==0) {
				System.out.println("결제를 다시 진행해 주세요");
				return;
			}else {
				System.out.println("다시 입력해 주세요.");
				continue;
			}
		}
		//결제 내역 확인
//		
		
		//포인트 적립 내역
//		
		
		
		
		
	}
	
	public void payMenu() {
		int no = 1;
//		int no = Main.loginNo;
		//로그인 체크
		if(no == 0) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}
		
		System.out.println("\r---결제---");
		System.out.println("1. 결제 진행");
		System.out.println("2. 포인트 조회");
		System.out.println("0. 뒤로가기");
		
		PayService ps = new PayService();
		while(true) {
			int c = InputUtil.getInt();
			
			switch(c) {
			case 1 : payment(no);break;
			case 2 : ps.pointAddList(no);break;
			case 0 : return;
			default : System.out.println("다시 입력해 주세요");continue;
			}
		}
	}
	
	
}

