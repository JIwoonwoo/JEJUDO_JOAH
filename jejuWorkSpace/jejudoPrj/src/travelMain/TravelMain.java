package travelMain;

import travel.TravelController;
import travel.TravelMenu;

public class TravelMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("좋아요 카운트 연습");
		new TravelController().like_sum();

		try {
			while (true) {
				TravelMenu tm = new TravelMenu();
				int tmnum = tm.travelMenu();

				switch (tmnum) {
				// 카테고리별 조회 
				case 1:
					new TravelController().cateSelect();
					break;

				case 2:
					
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
