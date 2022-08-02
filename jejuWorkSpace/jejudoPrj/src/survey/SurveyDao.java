package survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Main;
import util.JDBCTemplate;

public class SurveyDao {
	
	public boolean survey(Connection conn, SurveyVo vo) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
				
			String sql = "INSERT INTO SURVEY VALUES(SEQ_SURVEY.NEXTVAL,?,?,?,?,?,?,?,SYSDATE,DEFAULT)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);
			pstmt.setString(2, vo.getPurpose());
			pstmt.setString(3, vo.getPurpose2());
			pstmt.setString(4, vo.getLocation());
			pstmt.setString(5, vo.getGroup());
			pstmt.setString(6, vo.getBudget());
			pstmt.setString(7, vo.getAnimal_yn());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return (result == 1) ? true : false;
	}
	
	public SurveyVo search(Connection conn, int userNo) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		SurveyVo vo = null;
		
		try {
			
			String sql = "SELECT PURPOSE, PURPOSE2, LOCATION, PERSON_GROUP, BUDGET, ANIMAL_YN FROM SURVEY WHERE MEMBER_NO = ? AND DELETE_YN = 'N' ORDER BY SURVEY_DATE DESC";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rs = pstmt.executeQuery();
			
			vo = new SurveyVo();
			
			if(rs.next()) {
				vo.setPurpose(rs.getString("PURPOSE"));
				vo.setPurpose2(rs.getString("PURPOSE2"));
				vo.setLocation(rs.getString("LOCATION"));
				vo.setGroup(rs.getString("PERSON_GROUP"));
				vo.setBudget(rs.getString("BUDGET"));
				vo.setAnimal_yn(rs.getString("ANIMAL_YN"));
			}
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return vo;
		
	}
	
	



}
