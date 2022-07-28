package main;

import member.MemberController;
import member.MemberService;
import util.InputUtil;

public class Main {
	
	public static int loginNo;

	public static void main(String[] args) {
		
		MemberController mc = new MemberController();
		
//		mc.viewLogin();
		String s = InputUtil.sc.nextLine();
		boolean b = new MemberService().checkPwd(s);
		System.out.println(b);
		
	}

}
