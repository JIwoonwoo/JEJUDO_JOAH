package main;

import javax.swing.JDialog;

import gui.dialog.PopUpDialog;
import member.MemberController;
import util.InputUtil;

public class Main {
	
	public static String userNo;

	public static void main(String[] args) {
		
		MemberController mc = new MemberController();
		
		mc.viewLogin();
	}

}
