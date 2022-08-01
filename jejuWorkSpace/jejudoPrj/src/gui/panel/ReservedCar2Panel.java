package gui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import gui.button.BackBtn;
import gui.button.ButtonGroupController;
import gui.button.HomeBtn;
import gui.button.InvisiableRadio;
import gui.button.NextBtn;
import gui.field.InvisiableTextField;

public class ReservedCar2Panel extends ImgPanel {
	
	private ButtonGroupController bgc = new ButtonGroupController();

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private InvisiableTextField driver;
	private ButtonGroup bg;
	private JRadioButton noInsurance;
	private JRadioButton nomalInsurance;
	private JRadioButton veryInsurance;
	private JRadioButton superInsurance;

	public ReservedCar2Panel() {
		super("reservedCar2");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		driver = new InvisiableTextField(null);
		driver.setBounds(156, 195, 312, 41);
		add(driver);
		
		noInsurance = new InvisiableRadio("보험없음");
		noInsurance.setLocation(28, 288);
		add(noInsurance);
		
		nomalInsurance = new InvisiableRadio("일반자차");
		nomalInsurance.setLocation(283, 288);
		add(nomalInsurance);
		
		veryInsurance = new InvisiableRadio("완전자차");
		veryInsurance.setLocation(28, 463);
		add(veryInsurance);
		
		superInsurance = new InvisiableRadio("슈퍼자차");
		superInsurance.setLocation(283, 463);
		add(superInsurance);
		
	}
	
	public void reset() {
		driver.setText(null);
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

	public String getDriver() {
		return driver.getText();
	}
	
	public String getInsurance() {
		return bgc.getText(bg);
	}


}
