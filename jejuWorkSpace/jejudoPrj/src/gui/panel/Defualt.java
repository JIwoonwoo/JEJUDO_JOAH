package gui.panel;

import javax.swing.JButton;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;
import gui.button.InvisiableButton;
import gui.button.NextBtn;

import javax.swing.JTextField;

public class Defualt extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private NextBtn nextBtn;
	
	public Defualt() {
		super("mypage");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		nextBtn = new NextBtn();
		add(nextBtn);
				
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public NextBtn getNextBtn() {
		return nextBtn;
	}
	
	
}
