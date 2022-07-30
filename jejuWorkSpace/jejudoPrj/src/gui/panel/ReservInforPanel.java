package gui.panel;

import gui.button.HomeBtn;
import gui.button.NextBtn;

public class ReservInforPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;

	public ReservInforPanel() {
		super("reservInfor");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

	}
	
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public NextBtn getNextBtn() {
		return nextBtn;
	}


}
