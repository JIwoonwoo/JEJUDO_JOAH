package gui.panel;

import javax.swing.JButton;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;

public class ReservedPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JButton nomalBtn;
	private JButton packBtn;
	

	public ReservedPanel() {
		super("reserved");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		nomalBtn = new ImgButton("일반예약");
		add(nomalBtn);
		nomalBtn.setLocation(34, 306);
		
		packBtn = new ImgButton("패키지예약");
		add(packBtn);
		packBtn.setLocation(34, 426);

		
	}

	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public JButton getNomalBtn() {
		return nomalBtn;
	}

	public JButton getPackBtn() {
		return packBtn;
	}


}
