package gui.panel;

import javax.swing.JButton;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.InvisiableButton;

public class Q1Panel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JButton btnNewButton;
	
	public Q1Panel() {
		super("Q1");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		btnNewButton = new InvisiableButton();
		btnNewButton.setBounds(50, 538, 430, 53);
		add(btnNewButton);
		
				
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	
	
}
