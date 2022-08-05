package accom;

import java.util.Scanner;

import member.MemberController;
import member.MemberVo;
import util.InputUtil;

public class AccTestMenu {

	public static AccDto Accommodation;
	public static void main(String[] args) {
		
		
		new MemberController().viewLogin();
		
		System.out.println("메뉴선택");
		
		System.out.println("1. 숙소검색및예약\n2.숙소예약확인\n3.숙소예약취소");
		int input = InputUtil.getInt();
		
		switch(input) {
		case 1: 
			new AccomController().AccSearch();
			new AccomController().accSelect();
		case 2:
			new AccomController().accReservCheck();
			new AccomController().accReCheckDetail();
		case 3:
			new AccomController().reservCancel();
			break;
		}
		

	}

}
