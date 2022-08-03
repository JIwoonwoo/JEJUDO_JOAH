package accom;

import java.util.Scanner;

import member.MemberController;
import member.MemberVo;

public class AccTestMenu {

	public static AccDto Accommodation;
	public static void main(String[] args) {
		
//		Scanner sc = new Scanner(System.in);
//		MemberVo mvo = new MemberVo();
//		System.out.println("id입력");
//		String id = sc.nextLine();
//		System.out.println("pwd입력");
//		String pwd = sc.nextLine();		
//		mvo.setId(id);
//		mvo.setPwd(pwd);
		
		new MemberController().viewLogin();
		
		new AccomController().AccSearch();
		new AccomController().accSelect();

	}

}
