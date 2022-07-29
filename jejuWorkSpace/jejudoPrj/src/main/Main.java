package main;

import member.MemberService;
import qna.QnaService;

public class Main {
	
	public static int loginNo;

	public static void main(String[] args) {
		
		loginNo = 1;
		
//		MemberService md = new MemberService();
//		String eamil = "asdf";
//		eamil = md.search().getEmail();
//		
//		System.out.println(eamil.equals("null"));
		
		QnaService qs = new QnaService();
		System.out.println(qs.myQnaList().get(0));
		
	}

}
