package travel;

import util.InputUtil;

public class TravelController {

	TravelMenu tm = new TravelMenu();

	// 카테고리별 여행지 메서드
	public void cateSelect() {

		while (true) {

			try {
				int input = tm.cateSelectMenu();

				switch (input) {
				case 0 : 
					System.out.println("==== 0. 원하는 여행지 정보 상세 조회 ==== ");
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
		// 로그인 후 추천여행지 조회 가능
		
		// 회원 설문조사 확인
		
		// 
//		new TravelDao().surveyRecomm();
		// "좋아요 누를 여행지의 넘버를 입력해주세요." <- 넣기
		
		
		
	}

	
	public void travelInfo() {
		while (true) {

			try {
				int input = tm.detailInfoMenu();

				switch (input) {
				case 0:
					System.out.println("===== 0. 원하는 선택 정보 조회 =====");
					System.out.println(" ");
					System.out.print("상세 조회할 여행지 넘버 입력 : ");
					int inputnum = InputUtil.getInt();
					System.out.println("-------------------------------------------------------------------------------------------------");
					new TravelDao().infoSelect(inputnum);
					break;
				case 1:
					System.out.println("==== 1. 모든 정보 조회 (* 여행지 NO.순 ) ===== ");
					new TravelDao().detailInfo();
					break;
				case 2:
					System.out.println("==== 2. 가격순 조회 (* 가격 낮은 순) ===== ");
					new TravelDao().detailPrice();
					
					break;
				case 3:
					System.out.println("==== 3. 좋아요순 조화(* 좋아요 높은 순) ===== ");
					new TravelDao().detailLike();
					break;
					
				case 4:
					// [수정] 인원 추천 순서 다시 !!
					System.out.println("==== 4.반려동물 동반 가능 여행지 조회  ===== ");
					new TravelDao().detailanimalYn();
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
