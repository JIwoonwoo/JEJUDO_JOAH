package payment;

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
	
	public void payment() {
		//로그인 체크
		String id = loginCheck();
		
		//이전예약정보 확인 후
		//id로 회원의 예약번호 조회
		PayDao dao = new PayDao();
		
		//항공 예약정보
		PayVo vogf = dao.cfPay(id);
		PayVo vocf = dao.gfPay(id);
		//방 예약정보
		PayVo vor = dao.rPay(id);
		//차 예약정보
		PayVo voc = dao.cPay(id);
		
		//총 금액 계산
		int totalPay = totalPay(vogf,vocf,vor,voc);
		
		//포인트 보여주기, 사용유무, 사용량
		int myUsePoint = usePoint(vogf);
		
		//결제금액 보여주기
		int lastPay = totalPay - myUsePoint;
		
		//적립금
		int earnPoint = lastPay/10;
		
		//결제종류선택, 결제하기
		char howPay = howPay(lastPay);
		
		//결제 내역 저장
		dao.payInsert(vogf,vocf,vor,voc,totalPay,myUsePoint,lastPay,earnPoint,howPay);
		
		
		//예약 내역 확인
		System.out.println("예약 내역을 확인 하시겠습니까?");
		System.out.println("1. 확인");
		System.out.println("2. 건너뛰기");
		
		
	}
	
	//로그인 체크
	private String loginCheck(){
		MemberVo vo = new MemberVo();
		String id = vo.getId();
		if(loginCheck() != null) {
			return id;
		} else {
			System.out.println("로그인 상태가 아닙니다.");
			return "이거 어떻게 하지....";
		}
		
	}

	
	//포인트사용
	private int usePoint(PayVo vo) {
		
		int myPoint = vo.getMypoint();
		
		if(myPoint == 0) {
			System.out.println("현재 사용가능한 포인트가 없습니다.");
			 return myPoint;
		}
		
		System.out.print("현재 보유 포인트 : " + vo.getMypoint());
		System.out.println("1. 포인트 사용");
		System.out.println("0. 사용안함");
			
	
		while(true) {
			int c = InputUtil.getInt();
			
			if(c == 0) {
				System.out.println("결제를 바로 진행합니다.");
				return 0;
			}else if (c == 1) {
				System.out.println("포인트를 얼마나 사용하시겠습니까?");
				System.out.println("1. 모두사용");
				System.out.println("2. 직접입력");
				int a = InputUtil.getInt();
				
				if(a==1) {
					System.out.println("포인트가 " + myPoint + "원 사용되었습니다.");
					return myPoint;
				}else if(c==2) {
					int usePoint = InputUtil.getInt();
					
					while(true) {
						if(usePoint <= myPoint && usePoint > 0) {
							myPoint = usePoint;
							System.out.println("포인트가 "+ myPoint + "원 사용되었습니다.");
							return myPoint;
						}else {
							System.out.println("입력값을 벗어났습니다.");
							continue;
						}
					}	
				}else {
					System.out.println("다시 입력해 주세요");
					continue;
				}
			}else {
				System.out.println("다시 입력해 주세요");
				continue;
			}
			
		}
	}
	//결제수단 선택
	private char howPay(int lastPay) {
		System.out.println("결제 수단을 선택 해 주세요");
		System.out.println("1. 신용카드");
		System.out.println("2. 계좌이체");
		while(true){
			int choice = InputUtil.getInt();
			if(choice == 1) {
				System.out.println("신용카드를 선택하셨습니다.");
				paidCredit(lastPay);
				return 'c';
			}if(choice == 2) {
				System.out.println("계좌이체를 선택하셨습니다.");
				paidTransfer(lastPay);
				return 't';
			}else {
				System.out.println("다시 입력해 주세요.");
				continue;
			}
		}
	}
	// 계좌이체
	private void paidTransfer(int lastPay) {
		System.out.println("총 결제금액 : " + lastPay);
		System.out.println("싱싱한은행 오대식");
		System.out.println("110-987-123456");
		System.out.println("위 계좌로 입금해주세요.");
		
	}
	// 신용카드 사용
	private void paidCredit(int lastPay) {
		while(true) {
			System.out.println("총 결제금액 : " + lastPay);
			System.out.println("카드 정보를 입력해 주세요");
			System.out.println("카드 번호");
			System.out.print("> ");
			int a = InputUtil.getInt();
			
			System.out.println("MM/YY");
			System.out.println("MM");
			System.out.print(">");
			int b = InputUtil.getInt();
			
			System.out.println("YY");
			System.out.print(">");
			int c = InputUtil.getInt();
			
			System.out.println("cvc");
			System.out.print(">");
			int d = InputUtil.getInt();
			
			if((int)(Math.log10(a)+1)>16) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}
			else if((int)(Math.log10(b)+1)>2) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}
			else if((int)(Math.log10(c)+1)>2) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}
			else if((int)(Math.log10(d)+1)>3) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}else {
				System.out.println("카드 정보가 입력되었습니다.");
				System.out.println(lastPay + "원 결제되었습니다.");
				break;
			}
			
		}
		
	}
	//결제금액 더하기
	private int totalPay(PayVo vogf,PayVo vocf, PayVo vor, PayVo voc) {
		
		int fg = vogf.getFlightGoPay();
		int fc = vocf.getFlightComePay();
		int p = vor.getAccomPay();
		int c = vor.getCarPay();
		int total =  fg+fc+p+c;
		return total;
		
	}
	
	
}

