package survey;

import java.sql.Connection;

import util.JDBCTemplate;

public class SurveyService {

	SurveyDao sd = new SurveyDao();

	public boolean survey(SurveyVo vo) {

		if (vo.getPurpose() == null || vo.getLocation() == null || vo.getAnimal_yn() == null || vo.getBudget() == null
				|| vo.getGroup() == null) {
			return false;
		}
		
		vo.setLocation(checkLocation(vo.getLocation()));
		vo.setPurpose(checkPurpose(vo.getPurpose()));
		if(vo.getPurpose2()==null) {
			vo.setPurpose2(null);
		}else {
			vo.setPurpose2(checkPurpose(vo.getPurpose2()));
		}
		vo.setGroup(checkGroup(vo.getGroup()));
		
		Connection conn = null;
		boolean result = false;

		try {

			conn = JDBCTemplate.getConnection();

			if (sd.survey(conn, vo)) {
				System.out.println("[SUCCESS] 설문조사 성공");
				result = true;
				JDBCTemplate.commit(conn);
			} else {
				System.out.println("설문조사 실패");
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 설문조사 실패");
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}

	public SurveyVo search(int userNo) {

		Connection conn = null;
		SurveyVo result = null;

		try {

			conn = JDBCTemplate.getConnection();

			result = sd.search(conn, userNo);

			if (result != null) {
				System.out.println("[SUCCESS] 설문조사 조회 성공");
			} else {
				System.out.println("설문조사 조회 실패");
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 설문조사 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}
	
	private String checkPurpose(String s) {
		switch(s) {
		case "쇼핑":return "1";
		case "휴식":return "2";
		case "관광":return "3";
		case "경험":return "4";
		case "식도락":return "5";
		default : return "-1";
		}
	}
	
	private String checkLocation(String s) {
		switch(s) {
		case "제주시":return "1";
		case "서귀포시":return "2";
		case "제주전체":return "3";
		default : return "-1";
		}
	}
	
	private String checkGroup(String s) {
		switch(s) {
		case "가족":return "1";
		case "연인":return "2";
		case "친구":return "3";
		case "혼자":return "4";
		default : return "-1";
		}
	}	

}
