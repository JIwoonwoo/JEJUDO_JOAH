package accom;

import java.util.List;

import util.InputUtil;

/*
 * 게시글 작성
 * 데이터 받기(컨트롤러)
 * 비지니스 로직(서비스)
 * DB에 insert (DAO)
 */
public class AccomController {
	
	public void AccSearch() {
		

		System.out.println("---- 숙소 예약하기 ----");

		//필요한 데이터 받기 (체크인, 체크아웃, 인원수, 회원정보, 설문정보
		System.out.println("숙소에 머무실 일정을 입력해주세요.");
		System.out.print("체크인 날짜 (00/00/00 형식으로 입력하세요 /는빼고): ");
		String checkin = InputUtil.sc.nextLine();
		System.out.print("체크아웃 날짜 (00/00/00 형식으로 입력하세요 /는빼고) : ");
		String checkout = InputUtil.sc.nextLine();

		System.out.print("인원 수 : ");
		int people = InputUtil.getInt();
		
		System.out.println("한 숙소 타입만 골라서 보기 : 호텔은 H, 게스트하우스는 G 입력 (필수X)");
		String HG = InputUtil.sc.nextLine();
		
		
		//데이터 뭉치기
		AccDto dto = new AccDto();
		
		dto.setCheckin(checkin);
		dto.setCheckout(checkout);
		dto.setPeople(people);
		dto.setHG(HG);
		
		AccTestMenu.Accommodation = dto;
		
		//검색메소드 호출
		new AccService().accSearch(dto);
		
	}
	
	public void accSelect() {
		
		System.out.println("예약하실 숙소 번호를 입력하세요.");
		int selectno = InputUtil.getInt();
		
		String checkin = AccTestMenu.Accommodation.getCheckin();
		String checkout = AccTestMenu.Accommodation.getCheckout();
		
		AccDto dto = new AccDto();
		dto.setRoomno(selectno);
		dto.setCheckin(checkin);
		dto.setCheckout(checkout);
		
		int result = new AccService().accSelect(dto);
		
		if(result == 1) {
			//예약 성공
			System.out.println("선택하신 숙소가 예약 목록에 추가되었습니다. 결제 후 예약 확정됩니다.");
		}else {
			//예약 실패
			System.out.println("숙소 예약에 실패하였습니다. 다시 시도해 주세요.");
		}

		
	}

}
