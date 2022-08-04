package accom;

import java.util.Scanner;

import member.MemberController;
import member.MemberVo;

public class AccTestMenu {

	public static AccDto Accommodation;
	public static void main(String[] args) {
		
		
		new MemberController().viewLogin();
		
		new AccomController().AccSearch();
		new AccomController().accSelect();

	}

}
