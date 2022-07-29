package travel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import member.MemberVo;
import util.JDBCTemplate;

public class TravelDao {

	// 카테고리 1 : 관광지 메소드
	public TravelVo attraction(String inputnum) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		String sql = "SELECT P.PURPOSE, C.CATEGORY, TRAVEL_NAME, TRAVEL_ADDRESS FROM TRAVEL T JOIN CATEGORY C ON T.CATEGORY = C.NO JOIN PURPOSE P ON T.THEME = P.NO WHERE T.CATEGORY = ? ";
	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputnum);
		
		ResultSet rs = pstmt.executeQuery();
		
		
		
		if(rs.next()) {
			String purpose = rs.getString("P.PURPOSE");
			String category = rs.getString("C.CATEGORY");
			String travelName = rs.getString("TRAVEL_NAME");
			String travelAddress = rs.getString("TRAVEL_ADDRESS");
			
			TravelVo vo = new 
			
			
		}
		
		return vo;
	
	}
	
}
