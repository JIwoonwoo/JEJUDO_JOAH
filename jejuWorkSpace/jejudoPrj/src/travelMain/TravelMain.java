package travelMain;

import java.util.List;

import survey.SurveyVo;
import travel.TravelController;
import travel.TravelMenu;
import travel.TravelService;
import travel.TravelVo;

public class TravelMain {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

//		new TravelController().like_sum();
//
//	
//		
//		
//		SurveyVo svo = new SurveyVo();
//        svo.setPurpose("1");
//        svo.setPurpose2("2");
//        svo.setAnimal_yn("Y");
//        svo.setBudget("Y");
//        svo.setGroup("2");
//        svo.setLocation("1");
//
//        List<TravelVo> travelVoList = new TravelService().recommTravel(svo);
//
//        for (int i = 0; i < travelVoList.size(); ++i) {
//
//			// ArrayList 이용
//			// 꺼내오고
//			TravelVo temp = travelVoList.get(i);
//			// 담아주고
//			String no = temp.getTravel_no();
//			String recommendType = temp.getRecommend_type();
//			String purpose = temp.getPurpose();
//			String category = temp.getCategory();
//			String travelName = temp.getTravel_name();
//			String travelAddress = temp.getTravel_address();
//			String animalYn = temp.getAnimal_yn();
//			
//			// 출력문
//			System.out.println("[여행지_No." + no + "]  " + travelName + " | [카테고리] " + category + " | [테마] " + purpose
//					+ " | [주소] " + travelAddress + " | [인원] " + recommendType + " | [반려동물 동반 여부] " + animalYn);
//		}
		

		try {
			while (true) {
				TravelMenu tm = new TravelMenu();
				int tmnum = tm.travelMenu();

				switch (tmnum) {
				// 카테고리별 조회 
				case 1:
					new TravelController().cateSelect();
					break;
				
				// 추천 여행지 조회	
				case 2:
					new TravelController().recommTravel();
					new TravelController().like_sum();
					break;
					
				// 상세정보 조회
				case 3:
					new TravelController().travelInfo();
					break;
				case 4:

					break;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
