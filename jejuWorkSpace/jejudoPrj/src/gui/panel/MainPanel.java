package gui.panel;

import javax.swing.JButton;

import gui.button.HomeBtn;
import gui.button.ImgButton;
import gui.button.InvisiableButton;

public class MainPanel extends ImgPanel {

	private JButton homeBtn;
	private JButton gotoReserveBtn;
	private JButton gotoInfoBtn;
	private JButton gotoMyPageBtn;
	private JButton gotoServiceBtn;
	private JButton LogoutBtn;

	public MainPanel() {
		super("main");

		homeBtn = new HomeBtn();
		add(homeBtn);

		gotoReserveBtn = new ImgButton("예약하기");
		gotoReserveBtn.setLocation(55, 260);
		add(gotoReserveBtn);

		gotoInfoBtn = new ImgButton("여행정보");
		gotoInfoBtn.setLocation(288, 260);
		add(gotoInfoBtn);

		gotoMyPageBtn = new ImgButton("마이페이지");
		gotoMyPageBtn.setLocation(55, 460);
		add(gotoMyPageBtn);

		gotoServiceBtn = new ImgButton("고객센터");
		gotoServiceBtn.setLocation(288, 460);
		add(gotoServiceBtn);

		LogoutBtn = new InvisiableButton();
		LogoutBtn.setBounds(226, 835, 97, 23);
		add(LogoutBtn);

	}

	public JButton getHomeBtn() {
		return homeBtn;
	}

	public JButton getGotoReserveBtn() {
		return gotoReserveBtn;
	}

	public JButton getGotoInfoBtn() {
		return gotoInfoBtn;
	}

	public JButton getGotoMyPageBtn() {
		return gotoMyPageBtn;
	}

	public JButton getGotoServiceBtn() {
		return gotoServiceBtn;
	}

	public JButton getLogoutBtn() {
		return LogoutBtn;
	}

}
