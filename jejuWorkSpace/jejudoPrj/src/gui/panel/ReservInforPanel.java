package gui.panel;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;

public class ReservInforPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;

	public ReservInforPanel() {
		super("reservInfor");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);
		
		backBtn = new BackBtn();
		add(backBtn);

	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public NextBtn getNextBtn() {
		return nextBtn;
	}

	
	
	public BackBtn getBackBtn() {
		return backBtn;
	}

	public void set() {
		// TODO Auto-generated method stub
		
	}


}
