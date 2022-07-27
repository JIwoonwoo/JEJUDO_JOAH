package gui.panel;

import java.awt.Component;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.InvisiableRadio;
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
