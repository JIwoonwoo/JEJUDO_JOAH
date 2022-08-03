package travel;

import java.sql.Connection;
import java.util.List;

import survey.SurveyVo;
import util.JDBCTemplate;

public class TravelService {

//	public List<TravelVo> recommTravel(){
//		
//		
//		Connection conn = null;
//		List<TravelVo> travelVoList = null;
//		
//		try {
//			conn = JDBCTemplate.getConnection();
//			
//			travelVoList = new TravelDao().recommTravel(conn);
//
//		} catch (Exception e) {
//			e.printStackTrace(); 
//		} finally {
//			JDBCTemplate.close(conn);
//		}
//		
//		return result;
//		
//		Connection conn = null;
//		List<TravelVo> travelVoList = null;
//		
//		try {
//			conn = JDBCTemplate.getConnection();
//			
//			new TravelDao().recommTravel(conn, vo);
//			SurveyVo svo = new SurveyService().search()
//			
//			}catch(Exception e) {
//				e.printStackTrace();
//			}finally{
//				JDBCTemplate.close(conn);
//			}
//			
//			return travelVoList;
//		}

//	public TravelVo cateSelect() {

//		Connection conn = null;
//		TravelVo travelVoList = null;
//		
//		try {
//		conn = JDBCTemplate.getConnection();
//		
//		travelVoList = new TravelDao().attraction(conn);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally{
//			JDBCTemplate.close(conn);
//		}
//		
//		return travelVoList;
//		
//	}

	public int like_sum(TravelVo vo) {


		Connection conn = null;
		int result = 0;
		try {

			conn = JDBCTemplate.getConnection();
			result = new TravelDao().like_sum(vo, conn);

			if (result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}
	

	
	// [오류] 좋아요 조회 서비스 : 현재 좋아요 수를 알려주고 싶은데 안됨.....
//	public TravelVo nowLike(int num) {
//		
//		Connection conn = null;
//		TravelVo vo = null;
//		
//		try {
//			conn = JDBCTemplate.getConnection();
//			vo = new TravelDao().nowLike(conn);
//			
//		}catch(Exception e) {
//			System.out.println("[ERROR]게시글 상세조회 중 예외 발생 !!! ");
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(conn);
//		}
//		
//		
//		
//		return vo;
		
	}
	

