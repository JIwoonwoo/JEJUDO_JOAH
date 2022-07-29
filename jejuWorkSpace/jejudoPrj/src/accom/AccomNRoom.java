package accom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.InputUtil;

public class AccomNRoom {

	public static AccDto dto;

	public static void main(String[] args) {

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
		System.out.print("체크인 날짜 : ");
		String checkin = InputUtil.sc.nextLine();
		System.out.print("체크아웃 날짜 : ");
		String checkout = InputUtil.sc.nextLine();

		System.out.print("인원 수 : ");
		int people = InputUtil.getInt();

		System.out.println("111");

		Connection conn = null;
		PreparedStatement pstmt = null; // sql을 담아주는 객체
		ResultSet rs = null;

		System.out.println("222");
		try {
			String hi = ">=";

			// 연결 얻기
			conn = Accomconn.getConnection();

			System.out.println("333");

			String sql = "SELECT R.ROOM_NO, ACCOM_NAME, ACCOM_ADDRESS, A.POOL_YN,R.ROOM_NAME,R.ROOM_PRICE, R.CAPACITY, R.ANIMAL_YN,R.POOL_ABLE_YN, AR.ACCOM_AR, ROOM_VIEW_INFO FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_AR_INFO AR ON A.ACCOM_AROUND = AR.ACCOM_AR_NO JOIN ROOM_VIEW_INFO V ON R.ROOM_VIEW = V.ROOM_VIEW_NO WHERE CAPACITY >= ? AND CAPACITY <= ? AND ROOM_PRICE "+ hi +" (SELECT AVG(ROOM_PRICE) FROM ROOM WHERE CAPACITY >= ? AND CAPACITY <= ?) AND ANIMAL_YN = ? AND ACCOM_ADDRESS LIKE ?"; // 위치

			
			// SQL담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, people);
			pstmt.setInt(2, people + 1);
//			pstmt.setString(3, ); //설문결과로 예산 가져올거임
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
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
		}
		System.out.println("777");

	}

}
