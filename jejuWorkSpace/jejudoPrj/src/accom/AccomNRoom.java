package accom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.InputUtil;

public class AccomNRoom {

	public static AccDto dto;

	public static void main(String[] args) throws Exception {

		/*
		 * 숙소 예매 비행기 > 숙소 > 렌터카 숙소 예약시 검색할 것 입력받기 - 체크인날짜, 체크아웃날짜 - 인원수 검색할 때 설문조사에 따라
		 * 걸리는 필터- 인원, 위치, 예산, 반려동물
		 *
		 * 검색하고 사용자에게 보여줄 숙소 정보 = 전부 다(SELECT * 하면 됨)
		 *
		 *
		 * 숙소 예약 시 검색할 것
		 */

		// 체크인, 체크아웃, 인원수 입력받기

		System.out.println("---- 숙소 예약하기 ----");

		System.out.println("숙소에 머무실 일정을 입력해주세요.");
		System.out.print("체크인 날짜 (00/00/00 형식으로 입력하세요): ");
		String checkin = InputUtil.sc.nextLine();
		System.out.print("체크아웃 날짜 (00/00/00 형식으로 입력하세요) : ");
		String checkout = InputUtil.sc.nextLine();

		System.out.print("인원 수 : ");
		int people = InputUtil.getInt();
		
		//설문조사결과도 이쪽에서 받아오기
		//----------------------------------
		

		System.out.println("111");

		//--------------------
		Connection conn = null;
		
		//--------------------------
		PreparedStatement pstmt = null; // sql을 담아주는 객체
		ResultSet rs = null;

		System.out.println("222");
		try {
			String budgetanswer = "<=";

			// 연결 얻기
			conn = Accomconn.getConnection();

			System.out.println("333");

			String sql = "SELECT R.ROOM_NO, ACCOM_NAME, ACCOM_ADDRESS, A.POOL_YN,R.ROOM_NAME,R.ROOM_PRICE, R.CAPACITY, R.ANIMAL_YN,R.POOL_ABLE_YN, AR.ACCOM_AR, ROOM_VIEW_INFO FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_AR_INFO AR ON A.ACCOM_AROUND = AR.ACCOM_AR_NO JOIN ROOM_VIEW_INFO V ON R.ROOM_VIEW = V.ROOM_VIEW_NO WHERE CAPACITY >= ? AND CAPACITY <= ? AND ROOM_PRICE "+ budgetanswer +" (SELECT AVG(ROOM_PRICE) FROM ROOM WHERE CAPACITY >= ? AND CAPACITY <= ?) AND ANIMAL_YN = ? AND ACCOM_ADDRESS LIKE ?"; // 위치

			
			// SQL담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, people);
			pstmt.setInt(2, people + 1);
			pstmt.setInt(3, people);
			pstmt.setInt(4, people + 1);
			pstmt.setString(5, "Y");// 설문결과로가져옴 반려동물
			pstmt.setString(6, "%제주시%");// 설문결과 위치
			// sql문? SELECT..

			// 설문에서 정보 가져와서 sql문에 넣어야함 setString

			System.out.println("444");
			rs = pstmt.executeQuery();
			// rs.next() 는 실행결과가 boolean 타입으로 나온다.

			// 인원 수, 인원, 위치, 예산, 반려동물 설문 여부에 맞게 결과 검색해서 보여주기
			
			while (rs.next()) {
				AccDto dto = new AccDto();
				
				dto.setRoomno(rs.getInt("ROOM_NO"));
				dto.setAccomname(rs.getString("ACCOM_NAME"));
				dto.setAddress(rs.getString("ACCOM_ADDRESS"));
				dto.setPoolYN(rs.getString("POOL_YN"));
				dto.setRoomname(rs.getString("ROOM_NAME"));
				dto.setPrice(rs.getInt("ROOM_PRICE"));
				dto.setCapacity(rs.getInt("CAPACITY"));
				dto.setAnimalYN(rs.getString("ANIMAL_YN"));
				dto.setPoolableYN(rs.getString("POOL_ABLE_YN"));
				dto.setAround(rs.getString("ACCOM_AR"));
				dto.setRoomview(rs.getString("ROOM_VIEW_INFO"));

				System.out.println(dto);
				System.out.println("666");
			}
		} catch (Exception e) {
			System.out.println("숙소 검색 조회 중 예외 발생!");
			e.printStackTrace();
		} 
		
		//------------------------------------------
		
		System.out.println("777");
		
		//숙소 예약하기
		
		System.out.println("예약하실 숙소 번호를 입력하세요.");
		int reservno = InputUtil.getInt();
		
		System.out.println("888");
		//연결 얻기
		
		//----------------------------------------
		
		//------------------------------
		

		try {
			
			//sql문 날리기
			String sql2 = "SELECT R.ROOM_NO, ACCOM_NAME, ACCOM_ADDRESS, A.POOL_YN, R.ROOM_NAME,R.ROOM_PRICE, R.CAPACITY, R.ANIMAL_YN, R.POOL_ABLE_YN, AR.ACCOM_AR, ROOM_VIEW_INFO FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_AR_INFO AR ON A.ACCOM_AROUND = AR.ACCOM_AR_NO JOIN ROOM_VIEW_INFO V ON R.ROOM_VIEW = V.ROOM_VIEW_NO WHERE R.ROOM_NO = ?";

			System.out.println("999");
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, reservno);
			
			rs = pstmt.executeQuery();
			
			System.out.println("10 10 10");

			if(rs.next()) {
				AccDto dto = new AccDto();
				
				dto.setRoomno(rs.getInt("ROOM_NO"));
				dto.setAccomname(rs.getString("ACCOM_NAME"));
				dto.setAddress(rs.getString("ACCOM_ADDRESS"));
				dto.setPoolYN(rs.getString("POOL_YN"));
				dto.setRoomname(rs.getString("ROOM_NAME"));
				dto.setPrice(rs.getInt("ROOM_PRICE"));
				dto.setCapacity(rs.getInt("CAPACITY"));
				dto.setAnimalYN(rs.getString("ANIMAL_YN"));
				dto.setPoolableYN(rs.getString("POOL_ABLE_YN"));
				dto.setAround(rs.getString("ACCOM_AR"));
				dto.setRoomview(rs.getString("ROOM_VIEW_INFO"));

				System.out.println(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("선택하신 방을 예약하시겠습니까?  (Y/N 으로 대답)");
		String answer = InputUtil.sc.nextLine();

		if(answer.equals("Y")) {
			
			//예약을 위한 연결
			
			int result2 = 0;
			try {
				//예약을 위한 sql 작성
				String sql3 = "INSERT INTO ACCOM_RESERVATION(ACCOM_NO, ROOM_NO, MEMBER_NO, CHECK_IN, CHECK_OUT) VALUES(SEQ_ACCOM_RESERVATION.NEXTVAL, ?, ?, ? , ?)";
				
				System.out.println("11 11 11");
				
				//sql날리기
				pstmt = conn.prepareStatement(sql3);
				
				pstmt.setInt(1, reservno);
				pstmt.setInt(2, 1); //여기에는 회원번호
				pstmt.setString(3, checkin); //체크인일자
				pstmt.setString(4, checkout);	//체크아웃일자
				System.out.println("12 12 12");
				
				result2 = pstmt.executeUpdate();
				
				System.out.println(result2);
				
				System.out.println("예약 목록에 추가되었습니다. 결제 후 예약 확정됩니다.");
				
			} catch( Exception e) {
				e.printStackTrace();
				System.out.println("[ERROR] 예약 중 에러 발생. 다시 시도해 주세요.");
			}finally {
				try {conn.close();} catch (Exception e) {e.printStackTrace();}
				try {pstmt.close();} catch (Exception e) {e.printStackTrace();}
				try {rs.close();} catch (Exception e) {e.printStackTrace();}
			}
			

			
		}else {
			
			System.out.println("예약을 취소합니다.");
			return;
		}

	}//main

}
