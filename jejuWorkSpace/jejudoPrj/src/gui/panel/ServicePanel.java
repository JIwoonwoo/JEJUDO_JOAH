package gui.panel;

import javax.swing.JButton;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;

public class ServicePanel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JButton writeBtn;
	private JButton q1;
	private JButton q2;
	private JButton q3;
	private JButton q4;
	private JButton q5;
	
	public ServicePanel() {
		super("service");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		writeBtn = new ImgButton("글쓰기");
		writeBtn.setLocation(97, 736);
		add(writeBtn);
		
		q1 = new ImgButton("q1");
		q1.setLocation(30, 169);
		add(q1);
		
		q2 = new ImgButton("q2");
		q2.setLocation(30, 275);
		add(q2);
		
		q3 = new ImgButton("q3");
		q3.setLocation(30, 378);
		add(q3);
		
		q4 = new ImgButton("q4");
		q4.setLocation(30, 484);
		add(q4);
		
		q5 = new ImgButton("q5");
		q5.setLocation(30, 588);
		add(q5);
		
				
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

	public JButton getQ1() {
		return q1;
	}

	public JButton getQ2() {
		return q2;
	}

	public JButton getQ3() {
		return q3;
	}

	public JButton getQ4() {
		return q4;
	}

	public JButton getQ5() {
		return q5;
	}

	
	
}
