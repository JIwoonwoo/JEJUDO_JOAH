package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import util.JDBCTemplate;

public class QnaDao {

	/**
	 * myQnaList
	 * 
	 * @param conn
	 * @return List<QnaVo> (no, title, answerYN, q_content, a_content)
	 * @throws Exception
	 */
	public List<QnaVo> myQnaList(Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaVo> qnaList = new ArrayList<QnaVo>();

		try {
			String sql = "SELECT QUESTION_NO, QUESTION_TITLE, ANSWER_YN, Q_CONTENT, A_CONTENT\n" + "FROM QNA\n"
					+ "WHERE MEMBER_NO = ?\n" + "AND DELETE_YN = 'N'\n" + "ORDER BY QUESTION_TIME DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				QnaVo vo = new QnaVo();
				vo.setQuestionNo(rs.getInt("QUESTION_NO"));
				vo.setQuestionTitle(rs.getString("QUESTION_TITLE"));
				vo.setAnswerYN(getYNtoBoolean(rs.getString("ANSWER_YN")));
				vo.setqContent(rs.getString("Q_CONTENT"));
				vo.setaContent(rs.getString("A_CONTENT"));

				qnaList.add(vo);
			}

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		return qnaList;

	}

	/**
	 * 
	 * @param conn
	 * @param vo(  title, content )
	 * @return
	 * @throws Exception
	 */
	public boolean writeQna(Connection conn, QnaVo vo) throws Exception {

		PreparedStatement pstmt = null;
		int result = -1;

		try {
			String sql = "INSERT INTO QNA VALUES(SEQ_QNA.NEXTVAL, ?, ?, sysdate, default, ?, null, sysdate, default, null, null)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);
			pstmt.setString(2, vo.getQuestionTitle());
			pstmt.setString(3, vo.getqContent());

			result = pstmt.executeUpdate();

		} finally {
			JDBCTemplate.close(pstmt);
		}

		return (result == 1) ? true : false;

	}

	public boolean answerQ(Connection conn, QnaVo vo) throws Exception {
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
			String sql = "UPDATE QNA SET A_CONTENT = ? , ANSWER_DATE = SYSDATE WHERE QUESTION_NO = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getaContent());
			pstmt.setInt(2, vo.getQuestionNo());

			result = pstmt.executeUpdate();

		} finally {
			JDBCTemplate.close(pstmt);
		}
		return (result == 1) ? true : false;
	}

	private boolean getYNtoBoolean(String s) {
		if (s.equals("Y")) {
			return true;
		} else {
			return false;
		}
	}

}
