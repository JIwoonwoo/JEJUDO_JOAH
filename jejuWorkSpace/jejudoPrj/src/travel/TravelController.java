package travel;

import java.util.List;

import main.Main;
import survey.SurveyService;
import survey.SurveyVo;
import util.InputUtil;

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

		// 설문조사 받기
		SurveyVo svo = new SurveyService().search(Main.loginNo);
		
		// 회원 설문조사 확인
			List<TravelVo> travelVoList = new TravelService().recommTravel(svo);

		for (int i = 0; i < travelVoList.size(); ++i) {

			// ArrayList 이용
			// 꺼내오고
			TravelVo temp = travelVoList.get(i);
			// 담아주고
			String no = temp.getTravel_no();
			String recommendType = temp.getRecommend_type();
			String purpose = temp.getPurpose();
			String category = temp.getCategory();
			String travelName = temp.getTravel_name();
			String travelAddress = temp.getTravel_address();
			String animalYn = temp.getAnimal_yn();
			
			// 출력문
			System.out.println("[여행지_No." + no + "]  " + travelName + " | [카테고리] " + category + " | [테마] " + purpose
					+ " | [주소] " + travelAddress + " | [인원] " + recommendType + " | [반려동물 동반 여부] " + animalYn);
		}
		

	}//
	
	// 여행지 좋아요 +1 하기
	public void like_sum()  {
		System.out.println("좋아요 누를 여행지 번호 입력 : ");
		String likeCount = InputUtil.sc.nextLine();
		
		TravelVo vo = new TravelVo();
		vo.setTravel_no(likeCount);
		
		int result = new TravelService().like_sum(vo);
		
			// 글 작성 성공
			try {
				if (result == 1) {
					System.out.println("좋아요 카운팅 성공 !!" );
					TravelVo vo2 = new TravelDao().nowLike(likeCount);
					System.out.println(vo2.getLike_cnt());
	
			}else {
				// 글 작성 실패
				System.out.println("좋아요 카운팅 실패 ..");
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
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

				case 5:
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
