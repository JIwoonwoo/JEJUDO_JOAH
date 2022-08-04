package accom;

import java.util.Scanner;

import member.MemberController;
import member.MemberVo;
import util.InputUtil;

public class AccTestMenu {

	public static AccDto Accommodation;

	public static void main(String[] args) {

		new MemberController().viewLogin();

		// String 체크아웃 = 사용자한테 받은 체크아웃
		// Strgin 체크인 = 사용자한테 받은 체크인
		// Dto .set c
		System.out.println("1. 숙소조회 및 예약\n2. 내가 예약한 숙소조회");
		int Input = InputUtil.getInt();

		switch (Input) {
		case 1:
			new AccomController().AccSearch();// Dto 받아서 List 가지고옴

			AccDto dto = new AccomController().accSelect();// no 받아서 Dto가지고옴

			new AccomController().accReserve(dto);// Dto 받음 인설트

		case 2:
			new AccomController().accReservCheck();
		}

		// 예약확인

	}

}
