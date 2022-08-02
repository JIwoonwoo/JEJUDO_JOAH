package travel;

import util.InputUtil;

public class TravelMenu {

	public int travelMenu() throws Exception{
			System.out.println(" ");
			System.out.println("===== 여행지 =====");
			System.out.println("1. 카테고리별 조회");// 이름 위치(시 까지만), 테마, 좋아요
			System.out.println("2. 추천 여행지"); // 로그인 한 회원만 가능. 설문조사 기반
			//(임의) 정보 보여주기 -> 셀렉순, 가격순(높,낮), 인원순(많,적), 좋아요순,  ... 
			System.out.println("3. 여행지 상세 정보"); 
			System.out.println("4. 추가 / 수정 / 삭제 "); // 관리자에게만 보이게?
			System.out.print("원하는 항목의 숫자 입력 : ");
			
			return InputUtil.getInt();
	
	}
	
	// 1. 카테고리별 조회 : 2번 맛집 항목이 없음.
	public int cateSelectMenu() throws Exception  {

		System.out.println("----- 카테고리별 여행지 -----");
		System.out.println("1. 관광지");
		System.out.println("2. 맛집");
		System.out.println("3. 액티비티");
		System.out.println("4. 카페");
		System.out.println("5. 뒤로가기");
		System.out.print("원하는 항목의 숫자 입력 : ");
		
		
		return InputUtil.getInt();
			
		
	}
	
	// 2. 추천 여행지 : 설문조사 기반 (아직 어떻게 해야할지 모르겠음.)
	public void recommMenu() {
		
	}
	
	
	// 3. 여행지 상세 정보
	public int detailInfoMenu() throws Exception {
		System.out.println("----- 여행지 모든 정보 조회 -----");
		System.out.println("0. 원하는 정보 검색(* 여행지 넘버로 조희 가능합니다.)");
		System.out.println("1. 모든 정보 조회 ");
		System.out.println("2. 가격순 조회 ");
		System.out.println("3. 좋아요순 조회 ");
		System.out.println("4. 반려동물 동반 가능 여행지 조회");
		System.out.println("5. 뒤로가기 ");
		System.out.print("원하는 항목의 숫자 입력 : ");
		
		return InputUtil.getInt();
		
	}
}
