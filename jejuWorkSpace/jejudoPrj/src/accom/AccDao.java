package accom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.InputUtil;
import util.JDBCTemplate;

public class AccDao {
	
	public void accSearch(AccDto dto, Connection conn) {
		
		//conn 준비
		
		String budgetanswer = "<";
		
//		  if (예산에서 지갑이 무겁다를 골랐을 경우) {
//			 budgetanswer = ">";
//		  }else {
//			  budgetanswer = "<";
//			  
//		  } 
		
		//SQL 준비
		String sql = "SELECT R.ROOM_NO, ACCOM_NAME, ACCOM_ADDRESS, A.POOL_YN,R.ROOM_NAME,R.ROOM_PRICE, R.CAPACITY, R.ANIMAL_YN,R.POOL_ABLE_YN, AR.ACCOM_AR, ROOM_VIEW_INFO FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_AR_INFO AR ON A.ACCOM_AROUND = AR.ACCOM_AR_NO JOIN ROOM_VIEW_INFO V ON R.ROOM_VIEW = V.ROOM_VIEW_NO WHERE CAPACITY >= ? AND CAPACITY <= ? AND ROOM_PRICE "+ budgetanswer +"= (SELECT AVG(ROOM_PRICE) FROM ROOM WHERE CAPACITY >= ? AND CAPACITY <= ?) AND ANIMAL_YN = ? AND ACCOM_ADDRESS LIKE ?"; // 위치

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getPeople());
			pstmt.setInt(2, dto.getPeople() + 1);
			pstmt.setInt(3, dto.getPeople());
			pstmt.setInt(4, dto.getPeople() + 1);
			pstmt.setString(5, "Y");// 설문결과로가져옴 반려동물
			pstmt.setString(6, "%제주시%");// 설문결과 위치
			
			rs = pstmt.executeQuery();
			
			System.out.println("----- 숙소 검색 목록 -----");
			
			while (rs.next()) {
								
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
			
		}catch(Exception e) {
			System.out.println("[ERROR] 숙소 검색 조회 중 오류 발생!");
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		
	}//accSearch
	



	public int accSelect(AccDto dto, Connection conn) {
		
		//sql준비
		String sql2 = "SELECT R.ROOM_NO, ACCOM_NAME, ACCOM_ADDRESS, A.POOL_YN, R.ROOM_NAME,R.ROOM_PRICE, R.CAPACITY, R.ANIMAL_YN, R.POOL_ABLE_YN, AR.ACCOM_AR, ROOM_VIEW_INFO FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_AR_INFO AR ON A.ACCOM_AROUND = AR.ACCOM_AR_NO JOIN ROOM_VIEW_INFO V ON R.ROOM_VIEW = V.ROOM_VIEW_NO WHERE R.ROOM_NO = ?";

		PreparedStatement pstmt = null; // sql을 담아주는 객체
		ResultSet rs = null;
		
		try {
		pstmt = conn.prepareStatement(sql2);
		pstmt.setInt(1, dto.getRoomno());
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
//			dto.setRoomno(rs.getInt("ROOM_NO"));
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
			System.out.println("[ERROR]선택하신 숙소 조회 오류");
		}
		
		System.out.println("선택하신 방을 예약하시겠습니까?  (Y/N 으로 대답)");
		String answer = InputUtil.sc.nextLine();
		
		int result = 0;
		
		if(answer.equals("Y")) {
			try {
				//예약을 위한 sql 작성
				String sql3 = "INSERT INTO ACCOM_RESERVATION(ACCOM_NO, ROOM_NO, MEMBER_NO, CHECK_IN, CHECK_OUT) VALUES(SEQ_ACCOM_RESERVATION.NEXTVAL, ?, ?, ? , ?)";
				
				//sql날리기
				pstmt = conn.prepareStatement(sql3);
				
				pstmt.setInt(1, dto.getRoomno());
				pstmt.setInt(2, 1); //여기에는 회원번호
				pstmt.setString(3, dto.getCheckin());
				pstmt.setString(4, dto.getCheckout());
				
				result = pstmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("[ERROR] 예약 중 에러 발생. 다시 시도해 주세요.");
				result = -2;
			}finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rs);
			}
		}else {
			
			System.out.println("예약을 취소합니다.");
			result = 0;
		}
		
		return result;
	}//accSelect
	
}