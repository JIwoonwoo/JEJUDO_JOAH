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
	 * @param vo
	 * @return
	 */
	public boolean writeQna(QnaVo vo) {
		
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

}
