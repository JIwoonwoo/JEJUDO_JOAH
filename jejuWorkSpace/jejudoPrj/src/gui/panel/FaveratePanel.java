package gui.panel;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import gui.button.BackBtn;
import gui.button.ButtonGroupController;
import gui.button.HomeBtn;
import gui.button.InvisiableRadio;
import gui.button.NextBtn;

public class FaveratePanel extends ImgPanel {
	
	private ButtonGroupController bgc = new ButtonGroupController();

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private ButtonGroup groupQ1;
	private ButtonGroup groupQ2;
	private ButtonGroup groupQ3;
	private ButtonGroup groupQ4;
	private ButtonGroup groupQ5;

	public FaveratePanel() {
		super("faverate");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		groupQ1 = new ButtonGroup();

		JRadioButton q1Num1 = new InvisiableRadio("제주시");
		q1Num1.setLocation(72, 166);
		groupQ1.add(q1Num1);
		add(q1Num1);

		JRadioButton q1Num2 = new InvisiableRadio("서귀포시");
		q1Num2.setLocation(213, 166);
		groupQ1.add(q1Num2);
		add(q1Num2);

		JRadioButton q1Num3 = new InvisiableRadio("제주전체");
		q1Num3.setLocation(354, 166);
		groupQ1.add(q1Num3);
		add(q1Num3);

		groupQ2 = new ButtonGroup();

		JRadioButton q2Num1 = new InvisiableRadio("쇼핑");
		q2Num1.setLocation(74, 306);
		groupQ2.add(q2Num1);
		add(q2Num1);

		JRadioButton q2Num2 = new InvisiableRadio("휴식");
		q2Num2.setLocation(159, 306);
		groupQ2.add(q2Num2);
		add(q2Num2);

		JRadioButton q2Num3 = new InvisiableRadio("관광");
		q2Num3.setLocation(243, 306);
		groupQ2.add(q2Num3);
		add(q2Num3);

		JRadioButton q2Num4 = new InvisiableRadio("경험");
		q2Num4.setLocation(327, 306);
		groupQ2.add(q2Num4);
		add(q2Num4);

		JRadioButton q2Num5 = new InvisiableRadio("식도락");
		q2Num5.setLocation(410, 306);
		groupQ2.add(q2Num5);
		add(q2Num5);

		groupQ3 = new ButtonGroup();

		JRadioButton q3Num1 = new InvisiableRadio("너무무겁다");
		q3Num1.setLocation(72, 446);
		groupQ3.add(q3Num1);
		add(q3Num1);

		JRadioButton q3Num2 = new InvisiableRadio("모르겠다");
		q3Num2.setLocation(213, 446);
		groupQ3.add(q3Num2);
		add(q3Num2);

		JRadioButton q3Num3 = new InvisiableRadio("날라다닌다");
		q3Num3.setLocation(354, 446);
		groupQ3.add(q3Num3);
		add(q3Num3);

		groupQ4 = new ButtonGroup();

		JRadioButton q4Num1 = new InvisiableRadio("혼자");
		q4Num1.setLocation(72, 587);
		groupQ4.add(q4Num1);
		add(q4Num1);

		JRadioButton q4Num2 = new InvisiableRadio("친구");
		q4Num2.setLocation(178, 587);
		groupQ4.add(q4Num2);
		add(q4Num2);

		JRadioButton q4Num3 = new InvisiableRadio("가족");
		q4Num3.setLocation(284, 587);
		groupQ4.add(q4Num3);
		add(q4Num3);

		JRadioButton q4Num4 = new InvisiableRadio("연인");
		q4Num4.setLocation(392, 587);
		groupQ4.add(q4Num4);
		add(q4Num4);

		groupQ5 = new ButtonGroup();

		JRadioButton q5Num1 = new InvisiableRadio("있다");
		q5Num1.setLocation(72, 726);
		groupQ5.add(q5Num1);
		add(q5Num1);

		JRadioButton q5Num2 = new InvisiableRadio("없다");
		q5Num2.setLocation(275, 726);
		groupQ5.add(q5Num2);
		add(q5Num2);

	}

	public void reset() {
		if(groupQ1!=null)groupQ1.clearSelection();
		if(groupQ2!=null)groupQ2.clearSelection();
		if(groupQ3!=null)groupQ3.clearSelection();
		if(groupQ4!=null)groupQ4.clearSelection();
		if(groupQ5!=null)groupQ5.clearSelection();
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

	public String getQ1() {
		return bgc.getText(groupQ1);
	}

	public String getQ2() {
		return bgc.getText(groupQ2);
	}

	public String getQ3() {
		return bgc.getText(groupQ3);
	}

	public String getQ4() {
		return bgc.getText(groupQ4);
	}

	public String getQ5() {
		return bgc.getText(groupQ5);
	}

}
