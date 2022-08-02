package accom;

import java.sql.Connection;
import java.util.List;

import main.Main;
import survey.SurveyService;
import survey.SurveyVo;
import util.JDBCTemplate;

public class AccService {

		
		public void accSearch(AccDto dto) {
			
			//비지니스 로직
			if(dto.getPeople() < 1) {
				//인원이 1명이 안되므로 진행 불가
					return;
			}
			
			Connection conn = null;
				
			try {
				conn = JDBCTemplate.getConnection();
				SurveyVo svo = new SurveyService().search(Main.loginNo);
				 new AccDao().accSearch(dto, conn,svo);

			} catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				JDBCTemplate.close(conn);
			}
		
			
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
		
}
