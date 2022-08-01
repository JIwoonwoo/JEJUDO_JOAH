package gui.panel;

import javax.swing.JButton;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;
import gui.button.InvisiableButton;
import javax.swing.JTextField;

public class MyPagePanel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JButton memberUpdateBtn;
	private JButton pointBtn;
	private JButton suggestionBtn;
	private JButton reservationBtn;
	private JButton inquiryBtn;
	private JButton quit;
	

	public MyPagePanel() {
		super("mypage");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		memberUpdateBtn = new ImgButton("회원정보수정");
		memberUpdateBtn.setLocation(34, 233);
		add(memberUpdateBtn);
		
		pointBtn = new ImgButton("포인트확인");
		pointBtn.setLocation(34, 323);
		add(pointBtn);
		
		suggestionBtn = new ImgButton("추천여행지");
		suggestionBtn.setLocation(34, 415);
		add(suggestionBtn);
		
		reservationBtn = new ImgButton("예약관리");
		reservationBtn.setLocation(34, 505);
		add(reservationBtn);
		
		inquiryBtn = new ImgButton("문의확인");
		inquiryBtn.setLocation(34, 595);
		add(inquiryBtn);
		
		quit = new InvisiableButton();
		quit.setBounds(228, 833, 97, 23);
		add(quit);
		
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public JButton getMemberUpdateBtn() {
		return memberUpdateBtn;
	}

	public JButton getPointBtn() {
		return pointBtn;
	}

	public JButton getSuggestionBtn() {
		return suggestionBtn;
	}

	public JButton getReservationBtn() {
		return reservationBtn;
	}

	public JButton getInquiryBtn() {
		return inquiryBtn;
	}

	public JButton getQuit() {
		return quit;
	}
	
	
}
