package travel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import survey.SurveyVo;
import util.InputUtil;
import util.JDBCTemplate;

public class TravelDao {

	// 카테고리 1 : 관광지 메소드
	public TravelVo attraction(int inputnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		TravelVo vo = null;
		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();
			String sql = "SELECT TRAVEL_NO, P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS FROM TRAVEL T JOIN CATEGORY C ON T.CATEGORY = C.NO JOIN PURPOSE P ON T.THEME = P.NO WHERE T.CATEGORY = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputnum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TravelVo();
				String no = rs.getString("TRAVEL_NO");
				String purpose = rs.getString("PURPOSE");
				String category = rs.getString("CATEGORY");
				String travelName = rs.getString("TRAVEL_NAME");
				String travelAddress = rs.getString("TRAVEL_ADDRESS");

				System.out.println("[여행지_No." + no + "]  " + travelName + " | [카테고리] " + category + " | [테마] " + purpose
						+ " | [주소] " + travelAddress);

			}

			

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		return vo;

	}

	// 카테고리 3 : 여행지 상세 정보

	public TravelVo infoSelect(int inputnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		TravelVo vo = null;
		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();

			String sql = "SELECT TRAVEL_NO, G.PERSON_GROUP ,P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS , ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO, TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT \r\n"
					+ "FROM TRAVEL T \r\n" + "JOIN CATEGORY C ON T.CATEGORY = C.NO \r\n"
					+ "JOIN PURPOSE P ON T.THEME = P.NO \r\n"
					+ "JOIN PERSON_GROUP G ON T.RECOMMEND_TYPE = G.NO WHERE TRAVEL_NO = ? ORDER BY TRAVEL_NO ";
			// TRAVEL_NO

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputnum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TravelVo();

				// TRAVEL_NO, PERSON_GROUP, PURPOSE, C.CATEGORY, TRAVEL_NAME,
				// TRAVEL_ADDRESS, ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO,
				// TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT
				vo.setTravel_no(rs.getString("TRAVEL_NO"));
				vo.setRecommend_type(rs.getString("PERSON_GROUP"));
				vo.setPurpose(rs.getString("PURPOSE"));
				vo.setCategory(rs.getString("CATEGORY"));
				vo.setTravel_name(rs.getString("TRAVEL_NAME"));
				vo.setTravel_address(rs.getString("TRAVEL_ADDRESS"));
				vo.setActivity_yn(rs.getString("ACTIVITY_YN"));
				vo.setTravel_price(rs.getString("TRAVEL_PRICE"));
				vo.setAnimal_yn(rs.getString("ANIMAL_YN"));
				vo.setTravel_info(rs.getString("TRAVEL_INFO"));
				vo.setTravel_phone(rs.getString("TRAVEL_PHONE"));
				vo.setOpen(rs.getString("OPEN"));
				vo.setClosed(rs.getString("CLOSED"));
				vo.setClosed_day(rs.getString("CLOSED_DAY"));
				vo.setLike_cnt(rs.getInt("LIKE_CNT"));

				/*
				 * String no = rs.getString("TRAVEL_NO");
				 */

				System.out.println(vo);

			}

			if (vo != null) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}

		return vo;

	}

	public TravelVo detailInfo() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		TravelVo vo = null;
		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();

			String sql = "SELECT TRAVEL_NO, G.PERSON_GROUP ,P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS , ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO, TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT \r\n"
					+ "FROM TRAVEL T \r\n" + "JOIN CATEGORY C ON T.CATEGORY = C.NO \r\n"
					+ "JOIN PURPOSE P ON T.THEME = P.NO \r\n"
					+ "JOIN PERSON_GROUP G ON T.RECOMMEND_TYPE = G.NO ORDER BY TRAVEL_NO ";
			// TRAVEL_NO

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TravelVo();

				// TRAVEL_NO, PERSON_GROUP, PURPOSE, C.CATEGORY, TRAVEL_NAME,
				// TRAVEL_ADDRESS, ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO,
				// TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT
				vo.setTravel_no(rs.getString("TRAVEL_NO"));
				vo.setRecommend_type(rs.getString("PERSON_GROUP"));
				vo.setPurpose(rs.getString("PURPOSE"));
				vo.setCategory(rs.getString("CATEGORY"));
				vo.setTravel_name(rs.getString("TRAVEL_NAME"));
				vo.setTravel_address(rs.getString("TRAVEL_ADDRESS"));
				vo.setActivity_yn(rs.getString("ACTIVITY_YN"));
				vo.setTravel_price(rs.getString("TRAVEL_PRICE"));
				vo.setAnimal_yn(rs.getString("ANIMAL_YN"));
				vo.setTravel_info(rs.getString("TRAVEL_INFO"));
				vo.setTravel_phone(rs.getString("TRAVEL_PHONE"));
				vo.setOpen(rs.getString("OPEN"));
				vo.setClosed(rs.getString("CLOSED"));
				vo.setClosed_day(rs.getString("CLOSED_DAY"));
				vo.setLike_cnt(rs.getInt("LIKE_CNT"));

				/*
				 * String no = rs.getString("TRAVEL_NO");
				 */

				System.out.println(vo);

			}

			if (vo != null) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}

		return vo;

	}

	public TravelVo detailLike() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		TravelVo vo = null;
		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();

			String sql = "SELECT TRAVEL_NO, G.PERSON_GROUP ,P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS , ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO, TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT \r\n"
					+ "FROM TRAVEL T \r\n" + "JOIN CATEGORY C ON T.CATEGORY = C.NO \r\n"
					+ "JOIN PURPOSE P ON T.THEME = P.NO \r\n"
					+ "JOIN PERSON_GROUP G ON T.RECOMMEND_TYPE = G.NO ORDER BY LIKE_CNT DESC ";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TravelVo();

				// TRAVEL_NO, PERSON_GROUP, PURPOSE, C.CATEGORY, TRAVEL_NAME,
				// TRAVEL_ADDRESS, ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO,
				// TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT
				vo.setTravel_no(rs.getString("TRAVEL_NO"));
				vo.setRecommend_type(rs.getString("PERSON_GROUP"));
				vo.setPurpose(rs.getString("PURPOSE"));
				vo.setCategory(rs.getString("CATEGORY"));
				vo.setTravel_name(rs.getString("TRAVEL_NAME"));
				vo.setTravel_address(rs.getString("TRAVEL_ADDRESS"));
				vo.setActivity_yn(rs.getString("ACTIVITY_YN"));
				vo.setTravel_price(rs.getString("TRAVEL_PRICE"));
				vo.setAnimal_yn(rs.getString("ANIMAL_YN"));
				vo.setTravel_info(rs.getString("TRAVEL_INFO"));
				vo.setTravel_phone(rs.getString("TRAVEL_PHONE"));
				vo.setOpen(rs.getString("OPEN"));
				vo.setClosed(rs.getString("CLOSED"));
				vo.setClosed_day(rs.getString("CLOSED_DAY"));
				vo.setLike_cnt(rs.getInt("LIKE_CNT"));

				System.out.println(vo);

			}

			if (vo != null) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}

		return vo;

	}

	public TravelVo detailPrice() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		TravelVo vo = null;
		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();

			String sql = "SELECT TRAVEL_NO, G.PERSON_GROUP ,P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS , ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO, TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT \r\n"
					+ "FROM TRAVEL T \r\n" + "JOIN CATEGORY C ON T.CATEGORY = C.NO \r\n"
					+ "JOIN PURPOSE P ON T.THEME = P.NO \r\n"
					+ "JOIN PERSON_GROUP G ON T.RECOMMEND_TYPE = G.NO ORDER BY TRAVEL_PRICE NULLS FIRST ";
			// TRAVEL_NO

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TravelVo();

				// TRAVEL_NO, PERSON_GROUP, PURPOSE, C.CATEGORY, TRAVEL_NAME,
				// TRAVEL_ADDRESS, ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO,
				// TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT
				vo.setTravel_no(rs.getString("TRAVEL_NO"));
				vo.setRecommend_type(rs.getString("PERSON_GROUP"));
				vo.setPurpose(rs.getString("PURPOSE"));
				vo.setCategory(rs.getString("CATEGORY"));
				vo.setTravel_name(rs.getString("TRAVEL_NAME"));
				vo.setTravel_address(rs.getString("TRAVEL_ADDRESS"));
				vo.setActivity_yn(rs.getString("ACTIVITY_YN"));
				vo.setTravel_price(rs.getString("TRAVEL_PRICE"));
				vo.setAnimal_yn(rs.getString("ANIMAL_YN"));
				vo.setTravel_info(rs.getString("TRAVEL_INFO"));
				vo.setTravel_phone(rs.getString("TRAVEL_PHONE"));
				vo.setOpen(rs.getString("OPEN"));
				vo.setClosed(rs.getString("CLOSED"));
				vo.setClosed_day(rs.getString("CLOSED_DAY"));
				vo.setLike_cnt(rs.getInt("LIKE_CNT"));

				System.out.println(vo);

			}

			if (vo != null) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}

		return vo;

	}

	public TravelVo detailanimalYn() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		TravelVo vo = null;
		ResultSet rs = null;

		try {
			conn = JDBCTemplate.getConnection();

			String sql = "SELECT TRAVEL_NO, G.PERSON_GROUP ,P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS , ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO, TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT \r\n"
					+ "FROM TRAVEL T \r\n" + "JOIN CATEGORY C ON T.CATEGORY = C.NO \r\n"
					+ "JOIN PURPOSE P ON T.THEME = P.NO \r\n"
					+ "JOIN PERSON_GROUP G ON T.RECOMMEND_TYPE = G.NO WHERE ANIMAL_YN = 'Y' ";
			// TRAVEL_NO

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TravelVo();

				// TRAVEL_NO, PERSON_GROUP, PURPOSE, C.CATEGORY, TRAVEL_NAME,
				// TRAVEL_ADDRESS, ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO,
				// TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT
				vo.setTravel_no(rs.getString("TRAVEL_NO"));
				vo.setRecommend_type(rs.getString("PERSON_GROUP"));
				vo.setPurpose(rs.getString("PURPOSE"));
				vo.setCategory(rs.getString("CATEGORY"));
				vo.setTravel_name(rs.getString("TRAVEL_NAME"));
				vo.setTravel_address(rs.getString("TRAVEL_ADDRESS"));
				vo.setActivity_yn(rs.getString("ACTIVITY_YN"));
				vo.setTravel_price(rs.getString("TRAVEL_PRICE"));
				vo.setAnimal_yn(rs.getString("ANIMAL_YN"));
				vo.setTravel_info(rs.getString("TRAVEL_INFO"));
				vo.setTravel_phone(rs.getString("TRAVEL_PHONE"));
				vo.setOpen(rs.getString("OPEN"));
				vo.setClosed(rs.getString("CLOSED"));
				vo.setClosed_day(rs.getString("CLOSED_DAY"));
				vo.setLike_cnt(rs.getInt("LIKE_CNT"));

				System.out.println(vo);

			}

			if (vo != null) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}

		return vo;

	}

	
	
	// 추천여행지 : service 실행 문제...
	public List<TravelVo> recommTravel(Connection conn, SurveyVo svo) throws Exception {

		List<TravelDao> list = null;
		// 설문조사 정보 받아오기

		// 위치 
		String locationanswer;
		if (svo.getLocation().equals("1")) {
			locationanswer = "%제주시%";
		} else if (svo.getLocation().equals("2")) {
			locationanswer = "%서귀포시%";
		} else {
			locationanswer = "%제주%";
		}

		// 목적1
		String purposeanswer = null;
		if (svo.getPurpose().equals("1")) {
			purposeanswer = "쇼핑";
		} else if (svo.getPurpose().equals("2")) {
			purposeanswer = "휴식";
		} else if (svo.getPurpose().equals("3")) {
			purposeanswer = "관광";
		} else if (svo.getPurpose().equals("4")) {
			purposeanswer = "경험";
		} else if (svo.getPurpose().equals("5")) {
			purposeanswer = "식도락";
		}

		// 목적2
		String purpose2answer = null;
		if (svo.getPurpose2().equals("1")) {
			purpose2answer = "쇼핑";
		} else if (svo.getPurpose2().equals("2")) {
			purpose2answer = "휴식";
		} else if (svo.getPurpose2().equals("3")) {
			purpose2answer = "관광";
		} else if (svo.getPurpose2().equals("4")) {
			purpose2answer = "경험";
		} else if (svo.getPurpose2().equals("5")) {
			purpose2answer = "식도락";
		}

		// 예산 :: 처리 못 함
		String budgetanswer = null;
		if (svo.getBudget().equals("Y")) {
			budgetanswer = "AND TRAVEL_PRICE >= (SELECT AVG (NVL(TRAVEL_PRICE,0)) FROM TRAVEL) ORDER BY TRAVEL_PRICE";
		} else if (svo.getBudget().equals("N")) {
			budgetanswer = "AND TRAVEL_PRICE <= (SELECT AVG (NVL(TRAVEL_PRICE,0)) FROM TRAVEL) ORDER BY TRAVEL_PRICE";
		} else {
			budgetanswer = null;
		}

		// 인원
		String personGroupanswer = null;
		if (svo.getGroup().equals("1")) {
			personGroupanswer = "%혼자%";
		} else if (svo.getGroup().equals("2")) {
			personGroupanswer = "%친구%";
		} else if (svo.getGroup().equals("3")) {
			personGroupanswer = "%가족%";
		} else if (svo.getGroup().equals("4")) {
			personGroupanswer = "%연인%";
		}
		
		
		// 반려동물
		String animal_ynanswer = null;
		if(svo.getAnimal_yn().equals("Y")) {
			animal_ynanswer = "Y";
		}else if(svo.getAnimal_yn().equals("N")){
			animal_ynanswer = "N";
		}

		String sql = "SELECT TRAVEL_NO, G.PERSON_GROUP , P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS, TRAVEL_PRICE, ANIMAL_YN FROM TRAVEL T "
				+ "JOIN CATEGORY C ON T.CATEGORY = C.NO JOIN PURPOSE P ON T.THEME = P.NO JOIN PERSON_GROUP G ON T.RECOMMEND_TYPE = G.NO "
				+ "WHERE (P.PURPOSE = ? ) OR (P.PURPOSE = ?) AND PERSON_GROUP = ? AND TRAVEL_ADDRESS LIKE ? AND ANIMAL_YN = ? "
				+ budgetanswer;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<TravelVo> travelVoList = new ArrayList<TravelVo>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, purposeanswer ); // 목적1
			pstmt.setString(2, purpose2answer); // 목적2
			pstmt.setString(3, locationanswer); // 위치
			pstmt.setString(4, personGroupanswer); // 인원
			pstmt.setString(5, animal_ynanswer ); // 반려동물

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString("TRAVEL_NO");
				String recommendType = rs.getString("PERSON_GROUP");
				String purpose = rs.getString("PURPOSE");
				String category = rs.getString("CATEGORY");
				String travelName = rs.getString("TRAVEL_NAME");
				String travelAddress = rs.getString("TRAVEL_ADDRESS");
				String animalYn = rs.getString("ANIMAL_YN");

				TravelVo vo = new TravelVo();
				vo.setTravel_no(no);
				vo.setRecommend_type(recommendType);
				vo.setPurpose(purpose);
				vo.setCategory(category);
				vo.setTravel_name(travelName);
				vo.setTravel_address(travelAddress);
				vo.setAnimal_yn(animalYn);

				travelVoList.add(vo);

//				System.out.println("[여행지_No." + no + "]  " + travelName + " | [카테고리] " + category + " | [테마] " + purpose
//						+ " | [주소] " + travelAddress);
			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);

		}

		return travelVoList;

	}// 추천여행지 dao

	public int like_sum(TravelVo vo, Connection conn) throws Exception {
		
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			
			// sql 작성
			String sql = "UPDATE TRAVEL SET LIKE_CNT =  LIKE_CNT + 1 WHERE TRAVEL_NO = ? ";
			
			// sql 객체에 담기 및 완성 (물음표 채우기)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vo.getTravel_no());
			
			// sql 실행 및 결과 저장
			result = pstmt.executeUpdate();
			
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	// 카운팅 후 좋아요 조회 메서드.

	public TravelVo nowLike(String likeCount) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TravelVo vo = null;
		
		String sql = 
				"SELECT LIKE_CNT FROM TRAVEL WHERE TRAVEL_NO = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, likeCount);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int likecnt = rs.getInt("LIKE_CNT");
				
				vo = new TravelVo();
				vo.setLike_cnt(likecnt);
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return vo;
		
	}
		
		
	


}
