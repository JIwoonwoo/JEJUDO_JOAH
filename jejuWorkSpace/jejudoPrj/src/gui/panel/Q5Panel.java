package gui.panel;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;

public class Q5Panel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	
	public Q5Panel() {
		super("Q5");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
				
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	
	
}
