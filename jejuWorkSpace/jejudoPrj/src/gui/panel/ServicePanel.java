package gui.panel;

import javax.swing.JButton;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;

public class ServicePanel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JButton writeBtn;
	
	public ServicePanel() {
		super("service");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		writeBtn = new ImgButton("글쓰기");
		writeBtn.setLocation(97, 736);
		add(writeBtn);
				
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	
	public JButton getWriteBtn() {
		return writeBtn;
	}

	
}
