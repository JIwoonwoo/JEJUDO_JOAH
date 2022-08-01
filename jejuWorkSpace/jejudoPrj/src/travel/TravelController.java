package travel;

public class TravelController {

	TravelMenu tm = new TravelMenu();

	// 카테고리별 여행지 메서드
	public void cateSelect() {

		while (true) {

			try {
				int input = tm.cateSelectMenu();

				switch (input) {
				case 1:
					System.out.println("==== 1. 관광지별 여행지 ===== ");
					new TravelDao().attraction(1);
					break;
				case 2:
					System.out.println("==== 2. 맛집별 여행지 ===== ");
					new TravelDao().attraction(2);
					break;
				case 3:
					System.out.println("==== 3. 액티비티별 여행지 ===== ");
					new TravelDao().attraction(3);
					break;
				case 4:
					System.out.println("==== 4. 카페별 여행지 ===== ");
					new TravelDao().attraction(4);
					break;
				case 5:
					// 뒤로가기
					return;
				default:
					System.out.println("");
					System.out.println("***************************");
					System.out.println(" 1~5번 숫자만 입력해주세요. ");
					System.out.println("***************************");
//				break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}// cateSelect

	// 추천 여행지 : 설문조사 관련..?
	public void recommTravel() {

	}

	// 여행 상세 정보 : [오류] order by에 값이 안 들어감..
	public void travelInfo() {
		while (true) {

			try {
				int input = tm.detailInfoMenu();

				switch (input) {
				case 1:
					System.out.println("==== 1. 모든 정보 조회 ===== ");
					new TravelDao().detailInfo("ORDER BY TRAVEL_NO");
					break;
				case 2:
					System.out.println("==== 2. 가격순 조회 ===== ");
					new TravelDao().detailInfo("ORDER BY TRAVEL_NO");
					break;
				case 3:
					System.out.println("==== 3. 좋아요순 조회 ===== ");
					new TravelDao().detailInfo("ORDER BY TRAVEL_NO");
					break;
					
				case 4:
					System.out.println("==== 4. 인원순 조회 ===== ");
					new TravelDao().detailInfo("ORDER BY TRAVEL_NO");
					return;
					
				case 5 :
					return;
					
				default:
					System.out.println("");
					System.out.println("***************************");
					System.out.println(" 1~5번 숫자만 입력해주세요. ");
					System.out.println("***************************");
//					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
