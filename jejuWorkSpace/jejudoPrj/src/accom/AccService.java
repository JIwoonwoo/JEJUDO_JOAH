package accom;

import java.sql.Connection;
import java.util.List;

import accom.AccDao;
import main.Main;
import survey.SurveyService;
import survey.SurveyVo;
import util.JDBCTemplate;

public class AccService {

		
		public List<AccDto> accSearch(AccDto dto) {
			
			//비지니스 로직
			if(dto.getPeople() < 1) {
				//인원이 1명이 안되므로 진행 불가
					return null;
			}
			
			Connection conn = null;
			List<AccDto> list = null;
				
			try {
				conn = JDBCTemplate.getConnection();
				SurveyVo svo = new SurveyService().search(Main.loginNo);
				
				list = new AccDao().accSearch(dto, conn,svo);
				
				if(list!=null) {
					System.out.println("성공");
				}else {
					System.out.println("숙소 조회 실패");
				}

			} catch (Exception e) {
				System.out.println("에러; 숙소 조회 실패");
				e.printStackTrace(); 
			} finally {
				JDBCTemplate.close(conn);
			}
			
			return list;
		
			
		}//accSearch

		public int accSelect(AccDto dto) {
			
			Connection conn = null;
			int result = 0;
			try {
				conn = JDBCTemplate.getConnection();
				
				result = new AccDao().accSelect(dto, conn);

			} catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				JDBCTemplate.close(conn);
			}
			
			return result;
			
		}//accSelect
		
		public void accReservCheck(accDto dto) {
			
			Connection conn = null;
			int result = 0;
			try {
				conn = JDBCTemplate.getConnection();
				
				result = new AccDao().accReservCheck(dto, conn);

			} catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				JDBCTemplate.close(conn);
			}
			
			
		}//accRC
		
}
