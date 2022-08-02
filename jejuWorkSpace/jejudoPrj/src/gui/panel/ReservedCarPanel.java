package gui.panel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JSpinner;

import gui.button.BackBtn;
import gui.button.ButtonGroupController;
import gui.button.HomeBtn;
import gui.button.InvisiableRadio;
import gui.button.NextBtn;
import gui.field.InvisiableTextField;

public class ReservedCarPanel extends ImgPanel {
	
	private ButtonGroupController bgc = new ButtonGroupController();

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JSpinner contPerson;
	private InvisiableTextField goDay;
	private InvisiableTextField backDay;
	private ButtonGroup sizeG;
	private InvisiableRadio carSize1;
	private InvisiableRadio carSize2;
	private InvisiableRadio carSize3;

	public ReservedCarPanel() {
		super("reservedCar");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		contPerson = new JSpinner();
		contPerson.setFont(new Font("굴림", Font.PLAIN, 15));
		contPerson.setBounds(159, 407, 324, 41);
		contPerson.setOpaque(false);
		contPerson.setBorder(null);
		add(contPerson);

		goDay = new InvisiableTextField("YYMMDD");
		goDay.setBounds(171, 196, 312, 41);
		add(goDay);

		backDay = new InvisiableTextField("YYMMDD");
		backDay.setBounds(171, 290, 312, 41);
		add(backDay);

		sizeG = new ButtonGroup();
		
		carSize1 = new InvisiableRadio("소형");
		carSize1.setText("1");
		sizeG.add(carSize1);
		add(carSize1);
		
		carSize2 = new InvisiableRadio("중형");
		carSize2.setText("2");
		sizeG.add(carSize2);
		add(carSize2);
		
		carSize3 = new InvisiableRadio("대형");
		carSize3.setText("3");
		sizeG.add(carSize3);
		add(carSize3);
		
		carSize3.setLocation(361, 509);
		carSize2.setLocation(198, 509);
		carSize1.setLocation(34, 509);
			
	}
	
	public void reset() {
		contPerson.setValue(Integer.valueOf(0));
		goDay.setText("YYMMDD");
		backDay.setText("YYMMDD");
		if(sizeG!=null)sizeG.clearSelection();
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

	public int getContPerson() {
		return (Integer) contPerson.getValue();
	}

	public String getGoDay() {
		return goDay.getText();
	}

	public String getBackDay() {
		return backDay.getText();
	}

	public String getCarSize() {
		return bgc.getText(sizeG);
	}
	

}
