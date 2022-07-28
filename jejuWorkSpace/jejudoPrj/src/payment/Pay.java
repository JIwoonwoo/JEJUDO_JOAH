package payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import member.MemberVo;
import util.InputUtil;
import util.JDBCTemplate;

public class Pay {
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
		String id = "";
		if(loginCheck() != null) {
			id = loginCheck();
		} else {
			return;
		}
		//id로 회원의 예약번호 조회
		PayVo vo = checkNo(id);
		
		//포인트 보여주기, 사용유무, 사용량
		int myPoint = usePoint(vo);
		
		//결제종류선택, 결제하기
		
		
		
	}
	//로그인 체크
	private String loginCheck(){
		MemberVo vo = new MemberVo();
		String id = vo.getId();
		return id;
	}
	
	// 회원 id로 예약번호 조회와 금액 조회
	private PayVo checkNo(String id){
		
		String sqlGf = "SELECT FR.FLIGHT_NO, F.FLIGHT_PRICE, M.POINT\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.DEPARTURE_FLIGHT = F.FLIGHT_NO\r\n"
				+ "WHERE M.ID = ?";
		
		String sqlCf = "SELECT F.FLIGHT_NO, F.FLIGHT_PRICE\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN FLIGHT_RESERVATION FR ON M.MEMBER_NO = FR.MEMBER_NO\r\n"
				+ "JOIN FLIGHT F ON FR.RETURN_FLIGHT = F.FLIGHT_NO\r\n"
				+ "WHERE M.ID = ?";
		
		String sqlCar = "SELECT C.CAR_NO, R.DAY_PRICE, C.RETURN_DATE-C.RENTAL_DATE\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN CAR_RESERVATION C ON M.MEMBER_NO = C.MEMBER_NO\r\n"
				+ "JOIN RENTAL_CAR R ON C.RENTAL_NO = R.RENTAL_NO\r\n"
				+ "WHERE M.ID = ?";
		
		String sqlRoom = "SELECT A.ACCOM_NO, R.ROOM_PRICE , A.CHECK_OUT - A.CHECK_IN\r\n"
				+ "FROM MEMBER M\r\n"
				+ "JOIN ACCOM_RESERVATION A ON M.MEMBER_NO = A.MEMBER_NO\r\n"
				+ "JOIN ROOM R ON A.ROOM_NO = R.ROOM_NO\r\n"
				+ "WHERE M.ID = ?";
		
		Connection conn = null;
		PayVo vo = null;
		
		PreparedStatement pstmtGf = null;
		PreparedStatement pstmtCf= null;
		PreparedStatement pstmtC= null;
		PreparedStatement pstmtR= null;
		
		ResultSet rsGf= null;
		ResultSet rsCf= null;
		ResultSet rsC= null;
		ResultSet rsR= null;
		try {
			conn = JDBCTemplate.getConnection();
			
			pstmtGf = conn.prepareStatement(sqlGf);
			pstmtCf = conn.prepareStatement(sqlCf);
			pstmtC = conn.prepareStatement(sqlCar);
			pstmtR = conn.prepareStatement(sqlRoom);
			
			rsGf = pstmtGf.executeQuery();
			rsCf = pstmtCf.executeQuery();
			rsC = pstmtC.executeQuery();
			rsR = pstmtR.executeQuery();
			
			

			if(rsGf.next()) {
				int myPoint = rsGf.getInt("M.POINT");
				int fNo = rsGf.getInt("F.FLIGHT_NO");
				int fp = rsGf.getInt("F.FLIGHT_PRICE");
				vo = new PayVo();
				vo.setMypoint(myPoint);
				vo.setFlightNo(fNo);
				vo.setFlightGoPay(fp);
			}
			if(rsCf.next()) {
				int fNo = rsCf.getInt("F.FLIGHT_NO");
				int fp = rsCf.getInt("F.FLIGHT_PRICE");
				vo = new PayVo();		
				vo.setFlightNo(fNo);
				vo.setFlightComePay(fp);
			}
			if(rsC.next()) {
				int cNo = rsC.getInt("C.CAR_NO");
				int cp = rsC.getInt("R.DAY_PRICE");
				int cDay = rsC.getInt("C.RETURN_DATE-C.RENTAL_DATE");
				cp = cp*cDay;
				vo = new PayVo();		
				vo.setFlightNo(cNo);
				vo.setCarPay(cp);
			}
			if(rsR.next()) {
				int fNo = rsR.getInt("A.ACCOM_NO");	
				int fp = rsR.getInt("R.ROOM_PRICE");	
				int fDay = rsR.getInt("A.CHECK_OUT - A.CHECK_IN");	
				fp = fp*fDay;
				vo = new PayVo();		
				vo.setFlightNo(fNo);
				vo.setAccomPay(fDay);
			}
		} catch (Exception e) {
			System.out.println("결제 조회 에러~");
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmtGf);
			JDBCTemplate.close(pstmtCf);
			JDBCTemplate.close(pstmtC);
			JDBCTemplate.close(pstmtR);
			JDBCTemplate.close(rsGf);
			JDBCTemplate.close(rsCf);
			JDBCTemplate.close(rsC);
			JDBCTemplate.close(rsR);
		}
		return vo;
	}
	
	private int usePoint(PayVo vo) {
		
		int myPoint = vo.getMypoint();
		
		if(myPoint == 0) {
			System.out.println("현재 사용가능한 포인트가 없습니다.");
			 return myPoint;
		}
		
		System.out.print("현재 보유 포인트 : " + vo.getMypoint());
		System.out.println("1. 포인트 사용");
		System.out.println("0. 사용안함");
			
		try {
			while(true) {
				int c = InputUtil.getInt();
				
				if(c == 0) {
					return 0;
				}else if (c == 1) {
					System.out.println("포인트를 얼마나 사용하시겠습니까?");
					System.out.println("1. 모두사용");
					System.out.println("2. 직접입력");
					int a = InputUtil.getInt();
					
					if(a==1) {
						return myPoint;
					}else if(c==2) {
						int usePoint = InputUtil.getInt();
						
						while(true) {
							if(usePoint <= myPoint && usePoint > 0) {
								myPoint = usePoint;
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
			
		} catch (Exception e) {
			System.out.println("입력 오류");
			e.printStackTrace();
		}
		return 0;
	}
	
}
