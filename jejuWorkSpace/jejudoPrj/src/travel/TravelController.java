package travel;

public class TravelController {

	// 카테고리별 여행지 메서드
	public void cateSelect() {
		
		TravelMenu tm = new TravelMenu();
		while(true) {
		
		int input;

		
		try {
			input = tm.cateSelectMenu();
			
			switch(input) {
			case 1: 
				attraction();
				break;
			case 2: 
				
				break;
			case 3: 
				
				break;
			case 4: 
				
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		}

	}//cateSelect
	
	public void attraction() throws Exception {
//		TravelVo tv = new TravelDao().
		
	}
	

	// 추천 여행지
	public void recommTravel() {

	}

	// 여행 상세 정보
	public void travelInfo() {

	}

}
