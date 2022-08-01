package payment;

import member.MemberVo;
import util.InputUtil;

public class PayService {
	
	public MemberVo userInfo(int no) {
		//회원 닉네임, 포인트 가져오기
		PayDao dao = new PayDao();
		return dao.myInfo(no);
		
	}


	public PayVo reservation(int no) {
		PayDao dao = new PayDao();
		
		// 이전예약정보 확인 후 예약번호 조회
		// 항공 예약정보 -> 트라이캐치 넣기, 데이터 뭉치기
		PayVo vogf = dao.cfPay(no);
		PayVo vocf = dao.gfPay(no);
		if(vogf.getFlightNo() != vocf.getFlightNo()) {
			 
		}
		// 방 예약정보
		PayVo vor = dao.rPay(no);
		// 차 예약정보
		PayVo voc = dao.cPay(no);

		// 총 금액 계산
		int totalPay = totalPay(vogf, vocf, vor, voc);
		
		//-> 여기서 넘버와 금액 포인트 뭉쳐서 리턴
		PayVo vo = new PayVo(vogf.getFlightNo(),vor.getAccomNo(),voc.getCarNo(),totalPay,vogf.getFlightGoPay(),vocf.getFlightComePay(),vor.getAccomPay(),voc.getCarPay());

		// 포인트 보여주기, 사용유무, 사용량
		int myUsePoint = usePoint(mvo);
		
		// 결제금액 보여주기
		int lastPay = vo.getTotalPay() - myUsePoint;

		// 적립금
		int earnPoint = lastPay / 10;

		// 결제종류선택, 결제하기
		char howPay = ps.howPay(lastPay);
	}
		
	public void payInsert() {
		
		// 결제 내역 저장 -> 리턴 int 가져와서 결과 확인
		dao.payInsert(vogf, vocf, vor, voc, totalPay, myUsePoint, lastPay, earnPoint, howPay);

	}

	// 포인트사용
	public int usePoint(MemberVo vo) {

		int myPoint = vo.getPoint();

		if (myPoint == 0) {
			System.out.println("현재 사용가능한 포인트가 없습니다.");
			return myPoint;
		}

		System.out.print("현재 보유 포인트 : " + vo.getPoint());
		System.out.println("1. 포인트 사용");
		System.out.println("0. 사용안함");

		while (true) {
			int c = InputUtil.getInt();

			if (c == 0) {
				System.out.println("결제를 바로 진행합니다.");
				return 0;
			} else if (c == 1) {
				System.out.println("포인트를 얼마나 사용하시겠습니까?");
				System.out.println("1. 모두사용");
				System.out.println("2. 직접입력");
				int a = InputUtil.getInt();

				if (a == 1) {
					System.out.println("포인트가 " + myPoint + "원 사용되었습니다.");
					return myPoint;
				} else if (c == 2) {
					int usePoint = InputUtil.getInt();

					while (true) {
						if (usePoint <= myPoint && usePoint > 0) {
							myPoint = usePoint;
							System.out.println("포인트가 " + myPoint + "원 사용되었습니다.");
							return myPoint;
						} else {
							System.out.println("입력값을 벗어났습니다.");
							continue;
						}
					}
				} else {
					System.out.println("다시 입력해 주세요");
					continue;
				}
			} else {
				System.out.println("다시 입력해 주세요");
				continue;
			}

		}
	}

	// 결제수단 선택
	public char howPay(int lastPay) {
		System.out.println("결제 수단을 선택 해 주세요");
		System.out.println("1. 신용카드");
		System.out.println("2. 계좌이체");
		while (true) {
			int choice = InputUtil.getInt();
			if (choice == 1) {
				System.out.println("신용카드를 선택하셨습니다.");
				paidCredit(lastPay);
				return 'c';
			}
			if (choice == 2) {
				System.out.println("계좌이체를 선택하셨습니다.");
				paidTransfer(lastPay);
				return 't';
			} else {
				System.out.println("다시 입력해 주세요.");
				continue;
			}
		}
	}

	// 계좌이체
	public void paidTransfer(int lastPay) {
		System.out.println("총 결제금액 : " + lastPay);
		System.out.println("싱싱한은행 오대식");
		System.out.println("110-987-123456");
		System.out.println("위 계좌로 입금해주세요.");

	}

	// 신용카드 사용
	public void paidCredit(int lastPay) {
		while (true) {
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

			if ((int) (Math.log10(a) + 1) > 16) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			} else if ((int) (Math.log10(b) + 1) > 2) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			} else if ((int) (Math.log10(c) + 1) > 2) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			} else if ((int) (Math.log10(d) + 1) > 3) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			} else {
				System.out.println("카드 정보가 입력되었습니다.");
				System.out.println(lastPay + "원 결제되었습니다.");
				break;
			}

		}

	}

	// 결제금액 더하기
	public int totalPay(PayVo vogf, PayVo vocf, PayVo vor, PayVo voc) {

		int fg = vogf.getFlightGoPay();
		int fc = vocf.getFlightComePay();
		int p = vor.getAccomPay();
		int c = vor.getCarPay();
		int total = fg + fc + p + c;
		return total;

	}

}
