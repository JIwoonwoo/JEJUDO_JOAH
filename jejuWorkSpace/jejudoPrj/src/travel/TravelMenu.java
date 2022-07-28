package travel;

import util.InputUtil;

public class TravelMenu {

	public int travelMenu() throws Exception{
		
			System.out.println("===== 여행지 =====");
			System.out.println("1. 카테고리별 조회");// 이름 위치(시 까지만), 테마, 좋아요
			System.out.println("2. 추천 여행지"); // 설문조사 기반
			System.out.println("3. 여행지 상세 정보"); // 여행지의 모든 정보 보여주는게 맞나요?
			System.out.println("4. 추가 / 수정 / 삭제 "); // 관리자에게만 보이게?
			System.out.print("원하는 항목의 숫자 입력 : ");
			
			return InputUtil.getInt();
	
	}
	
	public int cateSelectMenu() throws Exception {
		System.out.println("----- 카테고리별 여행지 -----");
		System.out.println("1. 관광지");
		System.out.println("2. 맛집");
		System.out.println("3. 엑티비티");
		System.out.println("4. 카페");
		System.out.print("원하는 항목의 숫자 입력 : ");
		
		return InputUtil.getInt();
	}
	
}
