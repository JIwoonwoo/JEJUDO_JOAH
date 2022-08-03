package payment;

import java.sql.Connection;
import java.util.List;

import VoBox.VoBox;
import gui.GUI;
import gui.dialog.YesOrNo;
import member.MemberVo;
import util.InputUtil;
import util.JDBCTemplate;

public class PayService {
	
	public PayVo getNo(int no) {
		Connection conn = null;
		PayVo vo = null;
		
		PayDao dao = new PayDao();
		try {
			conn = JDBCTemplate.getConnection();
			
			// 항공 예약정보 비행기 예약번호, 금액 출발
			PayVo vogf = dao.gfPay(no, conn);
			
			if(vogf==null) {
				System.out.println("항공 예약 정보가 없습니다.");
				return vo;
			}
			
			vo.setFlightNo(vogf.getFlightNo());
			
		
			// 방 예약정보
			PayVo vor = dao.rPay(no, conn);
			
			if(vor!=null) {
				System.out.println("숙박 예약 정보가 없습니다.");
				return vo;
			}
			
			vo.setAccomNo(vor.getAccomNo());
			
			// 차 예약정보
			PayVo voc = dao.cPay(no, conn);
			
			if (voc==null) {
				vo.setCarNo(0);
			}else {
				vo.setCarNo(voc.getCarNo());
			}
			
		}catch(Exception e){
			
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return vo;
	}

	//포인트도가지고옴
	public MemberVo userInfo(int no) {
		// 회원 닉네임, 포인트 가져오기
		Connection conn = null;
		MemberVo mvo = null;
		try {
			conn = JDBCTemplate.getConnection();
			mvo = new PayDao().myInfo(no, conn);

		} catch (Exception e) {
			System.out.println("회원정보 조회 오류");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		System.out.println("로그인 상태 입니다");
		return mvo;

	}
	
	public PayVo reservation(int no, VoBox voBox) {
		PayDao dao = new PayDao();
		Connection conn = null;
		PayVo vo = null;
		// 이전예약정보 확인 후 예약번호 조회

		try {
			conn = JDBCTemplate.getConnection();
			// 항공 예약정보 비행기 예약번호, 금액 출발
			PayVo vogf = voBox.getVogf();
			
			if(vogf==null) {
				System.out.println("항공 예약 정보가 없습니다.");
				return vo;
			}
			//항공 예약정보 비행기 예약번호, 금액 복귀
			PayVo vocf = voBox.getVocf();
			
			if(vocf==null) {
				System.out.println("항공 예약 정보가 없습니다.");
				return vo;
			}
		
			// 방 예약정보
			PayVo vor = voBox.getVor();
			
			if(vor==null) {
				System.out.println("숙박 예약 정보가 없습니다.");
				return vo;
			}
			// 차 예약정보
			
			PayVo voc = voBox.getVoc();
			
			
			if (voc==null) {
//				System.out.println("렌트카를 예약하지 않았습니다. 이대로 진행 하시겠습니까?");
//				System.out.println("1. 진행, 0.다시 예약하기");
				YesOrNo dialog = new YesOrNo(GUI.frame, "결제", "렌트카를 예약하지 않았습니다. 진행 하시겠습니까?");
				
				while(true) {
//					int c = InputUtil.getInt();
					int c = dialog.run();
						if(c == -1) {
							voc = new PayVo();
							return vo;
						}else if(c==1) {
							break;
						}else {
							System.out.println("다시 입력해 주세요.");
							continue;
						}
				}
			}

			// 총 금액 계산
			int totalPay = totalPay(vogf, vocf, vor, voc);

			// -> 여기서 넘버와 금액 포인트 뭉쳐서 리턴
			vo = new PayVo(vogf.getFlightNo(), vor.getAccomNo(), voc.getCarNo(), totalPay, vogf.getFlightGoPay(),
					vocf.getFlightComePay(), vor.getAccomPay(), voc.getCarPay());

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}

		return vo;// 출발비행기예약번호, ㅅ숙소 예약넘버, 자동차 예약넘버, 총가격, 가는비행기 가격, 오는비행기가격, 숙소 가격, 자동차 가격
	}

	// 예약정보 조회
	public PayVo reservation(int no) {
		PayDao dao = new PayDao();
		Connection conn = null;
		PayVo vo = null;
		// 이전예약정보 확인 후 예약번호 조회

		try {
			conn = JDBCTemplate.getConnection();
			// 항공 예약정보 비행기 예약번호, 금액 출발
			PayVo vogf = dao.gfPay(no, conn);
			
			if(vogf==null) {
				System.out.println("항공 예약 정보가 없습니다.");
				return vo;
			}
			//항공 예약정보 비행기 예약번호, 금액 복귀
			PayVo vocf = dao.cfPay(no, conn);
			
			if(vocf==null) {
				System.out.println("항공 예약 정보가 없습니다.");
				return vo;
			}
		
			// 방 예약정보
			PayVo vor = dao.rPay(no, conn);
			
			if(vor==null) {
				System.out.println("숙박 예약 정보가 없습니다.");
				return vo;
			}
			// 차 예약정보
			
			PayVo voc = dao.cPay(no, conn);
			
			
			if (voc==null) {
//				System.out.println("렌트카를 예약하지 않았습니다. 이대로 진행 하시겠습니까?");
//				System.out.println("1. 진행, 0.다시 예약하기");
				YesOrNo dialog = new YesOrNo(GUI.frame, "결제", "렌트카를 예약하지 않았습니다. 진행 하시겠습니까?");
				
				while(true) {
//					int c = InputUtil.getInt();
					int c = dialog.run();
						if(c == -1) {
							voc = new PayVo();
							return vo;
						}else if(c==1) {
							break;
						}else {
							System.out.println("다시 입력해 주세요.");
							continue;
						}
				}
			}

			// 총 금액 계산
			int totalPay = totalPay(vogf, vocf, vor, voc);

			// -> 여기서 넘버와 금액 포인트 뭉쳐서 리턴
			vo = new PayVo(vogf.getFlightNo(), vor.getAccomNo(), voc.getCarNo(), totalPay, vogf.getFlightGoPay(),
					vocf.getFlightComePay(), vor.getAccomPay(), voc.getCarPay());

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}

		return vo;// 출발비행기예약번호, ㅅ숙소 예약넘버, 자동차 예약넘버, 총가격, 가는비행기 가격, 오는비행기가격, 숙소 가격, 자동차 가격
	}

	// 포인트사용
	public int usePoint(MemberVo mvo) {

		int myPoint = mvo.getPoint();

		if (myPoint == 0) {
			System.out.println("현재 사용가능한 포인트가 없습니다.");
			return myPoint;
		}

		System.out.print("현재 보유 포인트 : " + mvo.getPoint());
		System.out.println("\r1. 포인트 사용");
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
	public int howPay(int lastPay) {
		System.out.println("결제 수단을 선택 해 주세요");
		System.out.println("1. 신용카드");
		System.out.println("2. 계좌이체");
		while (true) {
			int choice = InputUtil.getInt();
			if (choice == 1) {
				System.out.println("신용카드를 선택하셨습니다.");
				paidCredit(lastPay);
				return 1;
			}
			if (choice == 2) {
				System.out.println("계좌이체를 선택하셨습니다.");
				paidTransfer(lastPay);
				return 2;
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
			System.out.println("카드 번호 16자리를 입력해 주세요");
			System.out.println("ex) 1234-1234-1234-1234");
			int a1 = InputUtil.getInt();
			System.out.print(" - ");
			int a2 = InputUtil.getInt();
			System.out.print(" - ");
			int a3 = InputUtil.getInt();
			System.out.print(" - ");
			int a4 = InputUtil.getInt();
			System.out.println();
			

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

			if ((int) (Math.log10(a1) + 1) > 4) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}if ((int) (Math.log10(a2) + 1) > 4) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}if ((int) (Math.log10(a3) + 1) > 4) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}if ((int) (Math.log10(a4) + 1) > 4) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}if ((int) (Math.log10(b) + 1) > 2) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}if ((int) (Math.log10(c) + 1) > 2) {
				System.out.println("번호를 다시 입력해 주세요");
				continue;
			}if ((int) (Math.log10(d) + 1) > 3) {
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

		System.out.println();
		int fg = vogf.getFlightGoPay();	
		int fc = vocf.getFlightComePay();
		int p = vor.getAccomPay();
		int c;
		if(voc !=null) {
			c=voc.getCarPay();;
		}else {
			c=0;
		}
		
		System.out.println("가는 비행기 : " + fg);
		System.out.println("오는 비행기 : " + fc);
		System.out.println("방값 : " + p);
		System.out.println("차값 : " + c);
		int total = fg + fc + p + c;
		System.out.println("\r총 금액 : " + total);
		return total;

	}

	// 회원넘버, 가져야할 포인트(계산완료) 기존포인트 + 적립포인트 - 사용한포인트 , vo
	// 출발비행기예약번호, ㅅ숙소 예약넘버, 자동차 예약넘버, 총가격, 가는비행기 가격, 오는비행기가격, 숙소 가격, 자동차 가격
//	vo.setPointUsed(pointUsed);
//	vo.setCutPrice(cutPrice);
//	vo.setMypoint(myPoint);
//	vo.setPayMethod(payMethod);
	// 결제 내역 저장
	public boolean payEnd(int no, int leavePoint, PayVo vo) {
		Connection conn = null;
		boolean result = false;
		// 결제 내역 저장 -> 리턴 int 가져와서 결과 확인
		try {
			conn = JDBCTemplate.getConnection();
			int result1 = new PayDao().payInsert(vo, conn);
			int result2 = new PayDao().pointUpdate(no, leavePoint, conn);
			int result3 = new PayDao().paidUpdate(vo, conn);
			
			if (result1 == 1 && result2 == 1 && (result3 == 2 || result3 == 3)) {
				System.out.println("결제 정보 저장 완료");
				JDBCTemplate.commit(conn);
				result = true;
			}else {
				System.out.println("결제 정보 인서트 오류");
				JDBCTemplate.rollback(conn);
			}
			
			
		} catch (Exception e) {
			System.out.println("결제 인서트 오류");
			e.printStackTrace();
			JDBCTemplate.rollback(conn);
		} finally {
			JDBCTemplate.commit(conn);
			JDBCTemplate.close(conn);
		}
		
		return result;

	}

	// 포인트 내역
	public List<PayVo> pointAddList(int no) {

		Connection conn = null;
		List<PayVo> list = null;

		try {

			conn = JDBCTemplate.getConnection();

			list = new PayDao().pointAddList(conn, no);
			if (list != null) {
				System.out.println("성공");
				System.out.println(list);
			} else {
				System.out.println("실패");
			}

		} catch (Exception e) {
			System.out.println("에러");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return list;
	}

	public void choicePayment(int no) {
		System.out.println("\r---결제 내역 조회---");
		System.out.println("1. 항공 결제 내역");
		System.out.println("2. 숙박 결제 내역");
		System.out.println("3. 렌트카 결제 내역");
		System.out.println("4. 전체 결제 내역");
		System.out.println("0. 뒤로가기");
		
		PayService ps = new PayService();
		while(true) {
			int c = InputUtil.getInt();
			
			switch(c) {
			case 1 : ;break;
			case 2 : ;break;
			case 3 : ;break;
			case 4 : checkPayment(no);break;
			case 0 : return;
			default : System.out.println("다시 입력해 주세요");continue;
			}
		}
		
	}
	
	// 전체 결제 내역 확인
	public void checkPayment(int no) {
		Connection conn = null;
		List<PayVo> list = null;
		try {
			conn = JDBCTemplate.getConnection();
			list = new PayDao().myPayment(no, conn);
			System.out.println(list);

		} catch (Exception e) {
			System.out.println("회원정보 조회 오류");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
	}

}
