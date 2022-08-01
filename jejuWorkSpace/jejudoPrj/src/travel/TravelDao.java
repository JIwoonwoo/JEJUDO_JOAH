package travel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCTemplate;

public class TravelDao {

	// 카테고리 1 : 관광지 메소드
	public TravelVo attraction(int inputnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		TravelVo vo = null;

		try {
			conn = JDBCTemplate.getConnection();
			String sql = "SELECT TRAVEL_NO, P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS FROM TRAVEL T JOIN CATEGORY C ON T.CATEGORY = C.NO JOIN PURPOSE P ON T.THEME = P.NO WHERE T.CATEGORY = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputnum);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TravelVo();
				String no = rs.getString("TRAVEL_NO");
				String purpose = rs.getString("PURPOSE");
				String category = rs.getString("CATEGORY");
				String travelName = rs.getString("TRAVEL_NAME");
				String travelAddress = rs.getString("TRAVEL_ADDRESS");

//				vo.setPurpose(rs.getString("PURPOSE"));
//				vo.setCategory(rs.getString("CATEGORY"));
//				vo.setTravel_name(rs.getString("TRAVEL_NAME"));
//				vo.setTravel_address(rs.getString("TRAVEL_ADDRESS"));

				System.out.println(  "[여행지_No." + no + "]  " + travelName + " | [카테고리] " + category + " | [테마] " + purpose + " | [주소] "
						+ travelAddress);

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
		}

		return vo;

	}

	// 카테고리 3 : 여행지 상세 정보
	public TravelVo detailInfo(String order) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		TravelVo vo = null;

		try {
			conn = JDBCTemplate.getConnection();
			
				String sql = "SELECT TRAVEL_NO, RECOMMEND_TYPE, PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS, ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO, TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT\r\n"
						+ "FROM TRAVEL T JOIN CATEGORY C ON T.CATEGORY = C.NO JOIN PURPOSE P ON T.THEME = P.NO ? ";
			
			// TRAVEL_NO

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new TravelVo();

				// TRAVEL_NO, RECOMMEND_TYPE, PURPOSE, C.CATEGORY, TRAVEL_NAME,
				// TRAVEL_ADDRESS, ACTIVITY_YN, TRAVEL_PRICE, ANIMAL_YN, TRAVEL_INFO,
				// TRAVEL_PHONE, OPEN, CLOSED, CLOSED_DAY, LIKE_CNT
				vo.setTravel_no(rs.getString("TRAVEL_NO"));
				vo.setRecommend_type(rs.getString("RECOMMEND_TYPE"));
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
				vo.setLike_cnt(rs.getString("LIKE_CNT"));

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
		}

		return vo;

	}

}
