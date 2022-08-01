package qna;

import java.sql.Connection;
import java.util.List;

import util.JDBCTemplate;

public class QnaService {
	
	private QnaDao qd = new QnaDao();
	
	/**
	 * myQnaList
	 * 
	 * @return List<QnaVo> (no, title, answerYN, q_content, a_content)
	 */
	public List<QnaVo> myQnaList(){
		
		Connection conn = null;
		List<QnaVo> myQnaList = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			List<QnaVo> result = qd.myQnaList(conn);
			if(result!=null) {
				System.out.println("[SUCCESS] 문의게시판 가지고오기");
				myQnaList = result;
			}else {
				System.out.println("[FAILURE] 문의게시판 가지고오기");
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] 문의게시판 가지고오기");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return myQnaList;
		
	}
	
	/**
	 * 
	 * @param vo (title, qcontent)
	 * @return
	 */
	public boolean writeQna(QnaVo vo) {
		
		if(vo.getqContent()==null||vo.getqContent().length()<1) {
			return false;
		}
		
		if(vo.getQuestionTitle()==null||vo.getQuestionTitle().length()<1) {
			return false;
		}
		
		boolean result = false;
		Connection conn = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			if(qd.writeQna(conn, vo)) {
				System.out.println("[SUCCESS] 문의게시판 작성하기");
				JDBCTemplate.commit(conn);
				result = true;
			}else {
				System.out.println("[FAILURE] 문의게시판 작성하기");
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] 문의게시판 작성하기");
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public boolean answerQ(QnaVo vo) {
		
		if(vo.getaContent()==null || vo.getaContent().length()<1) {
			return false;
		}
		
		Connection conn = null;
		boolean result = false;
		
		try {
			conn = JDBCTemplate.getConnection();
			if(qd.answerQ(conn, vo)) {
				System.out.println("[SUCCESS] 답변 작성하기");
				JDBCTemplate.commit(conn);
				result = true;
			}else {
				System.out.println("[FAILURE] 답변 작성하기");
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] 답변 작성하기");
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
		
	}
	
	public QnaVo search(int questionNo) {
		
		Connection conn = null;
		QnaVo vo = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			QnaVo content = qd.search(conn, questionNo);
			if(content!=null) {
				System.out.println("[SUCCESS] 1개 글 조회");
				vo = content;
			}else {
				System.out.println("[FAILURE] 1개 글 조회");
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] 1개 글 조회");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return vo;
		
	}
	
	public boolean updateQ(QnaVo vo) {
		
		if(vo.getqContent()==null||vo.getqContent().length()<1) {
			return false;
		}
		
		if(vo.getQuestionTitle()==null||vo.getQuestionTitle().length()<1) {
			return false;
		}

		Connection conn = null;
		boolean result = false;
		
		try {
			conn = JDBCTemplate.getConnection();
			if(qd.updateQ(conn, vo)) {
				System.out.println("[SUCCESS] 문의게시판 수정");
				JDBCTemplate.commit(conn);
				result = true;
			}else {
				System.out.println("[FAILURE] 문의게시판 수정");
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] 문의게시판 수정");
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
		
	}
	
	public boolean deleteQ(int questionNo) {
		
		Connection conn = null;
		boolean result = false;
		
		try {
			conn = JDBCTemplate.getConnection();
			if(qd.deleteQ(conn, questionNo)) {
				System.out.println("[SUCCESS] 문의게시판 삭제");
				JDBCTemplate.commit(conn);
				result = true;
			}else {
				System.out.println("[FAILURE] 문의게시판 삭제");
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] 문의게시판 삭제");
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
		
	}

}
