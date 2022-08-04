package accom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import accom.AccDto;
import main.Main;
import member.MemberVo;
import survey.SurveyVo;
import util.JDBCTemplate;

public class AccDao {

	public List<AccDto> accSearch(AccDto inputDto, Connection conn, SurveyVo svo) {

		List<AccDto> list = null;

		// 설문조사 정보 받아오기
		// 예산
		String budgetanswer;
		if (svo.getBudget().equals("Y")) {
			budgetanswer = "AND ROOM_PRICE >= (SELECT AVG(ROOM_PRICE) FROM ROOM WHERE CAPACITY >= "
					+ inputDto.getPeople() + "AND CAPACITY <= " + inputDto.getPeople() + 1 + ")";
		} else if (svo.getBudget().equals("N")) {
			budgetanswer = "AND ROOM_PRICE <= (SELECT AVG(ROOM_PRICE) FROM ROOM WHERE CAPACITY <= "
					+ inputDto.getPeople() + "AND CAPACITY <= " + inputDto.getPeople() + 1 + ")";
		} else {
			budgetanswer = "";
		}

		// 여행지역
		String locationanswer;
		if (svo.getLocation().equals("1")) {
			locationanswer = "%제주시%";
		} else if (svo.getLocation().equals("2")) {
			locationanswer = "%서귀포시%";
		} else {
			locationanswer = "%제주%";
		}

		// 호텔_게하 구분
		String HG;
		if (inputDto.getHG().equals("H")) {
			HG = "AND A.TYPE = 'H'";
		} else if (inputDto.getHG().equals("G")) {
			HG = "AND A.TYPE = 'G'";
		} else {
			HG = "";
		}

		// SQL 준비
		String sql = "SELECT A.ACCOM_NO, R.ROOM_NO, ACCOM_NAME, ACCOM_ADDRESS, A.POOL_YN, R.ROOM_NAME,R.ROOM_PRICE, R.CAPACITY, R.ANIMAL_YN, R.POOL_ABLE_YN, AA.ACCOM_AR, ROOM_VIEW_INFO FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_AR_INFO AA ON A.ACCOM_AROUND = AA.ACCOM_AR_NO JOIN ROOM_VIEW_INFO V ON R.ROOM_VIEW = V.ROOM_VIEW_NO WHERE CAPACITY >= ? AND CAPACITY <= ?"
				+ budgetanswer
				+ "AND ANIMAL_YN = ? AND ACCOM_ADDRESS LIKE ? AND R.ROOM_NO NOT IN (SELECT ROOM_NO FROM ACCOM_RESERVATION WHERE CANCEL_YN = 'N' AND (CHECK_OUT BETWEEN ? AND ?))"
				+ HG; // 위치

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputDto.getPeople());
			pstmt.setInt(2, inputDto.getPeople() + 1);
			pstmt.setString(3, svo.getAnimal_yn());// 설문결과로가져옴 반려동물
			pstmt.setString(4, locationanswer);// 설문결과 위치
			pstmt.setString(5, inputDto.getCheckin());
			pstmt.setString(6, inputDto.getCheckout());

			rs = pstmt.executeQuery();

			System.out.println("----- 숙소 검색 목록 -----");

			list = new ArrayList<AccDto>();

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

				System.out.println("1");
				System.out.println(dto);

				list.add(dto);

			}

		} catch (Exception e) {
			System.out.println("[ERROR] 숙소 검색 조회 중 오류 발생!");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		return list;

	}// accSearch

	public AccDto accSelect(AccDto inputDto, Connection conn) throws Exception {

		PreparedStatement pstmt = null; // sql을 담아주는 객체
		ResultSet rs = null;

		AccDto dto = null;

		// sql준비 --------------------------------------삭제가능
		String sql2 = "SELECT R.ROOM_NO, ACCOM_NAME, ACCOM_ADDRESS, A.POOL_YN, R.ROOM_NAME,R.ROOM_PRICE, R.CAPACITY, R.ANIMAL_YN, R.POOL_ABLE_YN, AR.ACCOM_AR, ROOM_VIEW_INFO FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_AR_INFO AR ON A.ACCOM_AROUND = AR.ACCOM_AR_NO JOIN ROOM_VIEW_INFO V ON R.ROOM_VIEW = V.ROOM_VIEW_NO WHERE R.ROOM_NO = ?";

		try {
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, inputDto.getRoomno());

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new AccDto();

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
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return dto;

	}// accSelect

	// 정한
	public int accReserve(AccDto dto, Connection conn) throws Exception {

//		System.out.println("선택하신 방을 예약하시겠습니까?  (Y/N 으로 대답)");
//		String answer = InputUtil.sc.nextLine();

		PreparedStatement pstmt = null; // sql을 담아주는 객체
		int result = 0;

		try {
			// 예약을 위한 sql 작성
			String sql3 = "INSERT INTO ACCOM_RESERVATION(ACCOM_NO, ROOM_NO, MEMBER_NO, CHECK_IN, CHECK_OUT) VALUES(SEQ_ACCOM_RESERVATION.NEXTVAL, ?, ?, ? , ?)";

			// sql날리기
			pstmt = conn.prepareStatement(sql3);
			MemberVo vo = new MemberVo();
			pstmt.setInt(1, dto.getRoomno());
			pstmt.setInt(2, Main.loginNo); // 여기에는 회원번호
			pstmt.setString(3, dto.getCheckin());
			pstmt.setString(4, dto.getCheckout());

			result = pstmt.executeUpdate();

		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;

	}// accReserve

	public List<AccDto> accReservCheck(Connection conn) throws Exception {

		PreparedStatement pstmt = null; // sql을 담아주는 객체
		ResultSet rs = null;
		List<AccDto> list = null;

		// sql준비 - 쿼리문, ? 채우는 setString문, pstmt

		String sql = "SELECT AR.ACCOM_NO, A.ACCOM_NAME, AR.RESERVE_DATE FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_RESERVATION AR ON AR.ROOM_NO = R.ROOM_NO WHERE MEMBER_NO = ?";

		try {
			list = new ArrayList<AccDto>();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);//회원번호

			rs = pstmt.executeQuery();

			if (rs.next()) {
				AccDto dto = new AccDto();
				dto.setReserveNo(rs.getInt("ACCOM_NO"));
				dto.setAccomname(rs.getString("ACCOM_NAME"));
				dto.setReserveDate(rs.getTimestamp("RESERVE_DATE"));

//				System.out.println(dto.getReserveNo() + "|" + dto.getAccomname() + "|" + dto.getReserveDate());
				list.add(dto);
			}
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return list;

	}//AccRC
	
	public AccDto accReCheckDetail(AccDto inputDto, Connection conn) {
		
		PreparedStatement pstmt = null; // sql을 담아주는 객체
		ResultSet rs = null;
		AccDto dto = null;
		
		String sql = "SELECT AR.ACCOM_NO, R.ROOM_NO, ACCOM_NAME, ACCOM_ADDRESS, A.POOL_YN, R.ROOM_NAME,R.ROOM_PRICE, R.CAPACITY , R.ANIMAL_YN, R.POOL_ABLE_YN, AA.ACCOM_AR, ROOM_VIEW_INFO, AR.RESERVE_DATE FROM ACCOM A JOIN ROOM R ON A.ACCOM_NO = R.ACCOM_NO JOIN ACCOM_AR_INFO AA ON A.ACCOM_AROUND = AA.ACCOM_AR_NO JOIN ROOM_VIEW_INFO V ON R.ROOM_VIEW = V.ROOM_VIEW_NO JOIN ACCOM_RESERVATION AR ON AR.ROOM_NO = R.ROOM_NO WHERE R.ROOM_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputDto.getReserveNo());//예약번호여야함

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new AccDto();
				dto.setReserveNo(rs.getInt("ACCOM_NO"));
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
				dto.setReserveDate(rs.getTimestamp("RESERVE_DATE"));

				System.out.print("예약번호 : " + dto.getReserveNo());
				System.out.print(dto);
				System.out.print("예약일자 : " + dto.getReserveDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR]선택하신 숙소 조회 오류");

		}
		return dto;
	}
}
