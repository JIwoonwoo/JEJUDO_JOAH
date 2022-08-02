package gui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import car.ReserveVo;
import gui.button.BackBtn;
import gui.button.ButtonGroupController;
import gui.button.HomeBtn;
import gui.button.InvisiableRadio;
import gui.button.NextBtn;

public class ReservedCar2Panel extends ImgPanel {
	
	private ButtonGroupController bgc = new ButtonGroupController();

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private ButtonGroup bg;
	private JRadioButton noInsurance;
	private JRadioButton nomalInsurance;
	private JRadioButton veryInsurance;
	private JRadioButton superInsurance;
	private ReserveVo vo;

	public ReservedCar2Panel() {
		super("reservedCar2");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		bg = new ButtonGroup();
		
		noInsurance = new InvisiableRadio("보험없음");
		noInsurance.setText("0");
		noInsurance.setLocation(28, 288);
		bg.add(noInsurance);
		add(noInsurance);
		
		nomalInsurance = new InvisiableRadio("일반자차");
		nomalInsurance.setText("1");
		nomalInsurance.setLocation(283, 288);
		bg.add(nomalInsurance);
		add(nomalInsurance);
		
		veryInsurance = new InvisiableRadio("완전자차");
		veryInsurance.setText("2");
		veryInsurance.setLocation(28, 463);
		bg.add(veryInsurance);
		add(veryInsurance);
		
		superInsurance = new InvisiableRadio("슈퍼자차");
		superInsurance.setText("3");
		superInsurance.setLocation(283, 463);
		bg.add(superInsurance);
		add(superInsurance);
		
	}
	
	public void reset() {
		vo = null;
		if(bg!=null)bg.clearSelection();
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

	public String getInsurance() {
		return bgc.getText(bg);
	}

	public ReserveVo getVo() {
		return vo;
	}

	public void setVo(ReserveVo vo) {
		this.vo = vo;
	}


}
