package gui.panel;

import javax.swing.JTextField;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;
import gui.field.InvisiableTextField;

public class FindIdPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JTextField textFieldName;
	private JTextField textFieldEmail;

	public FindIdPanel() {
		super("findId");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		textFieldName = new InvisiableTextField(null);
		textFieldName.setBounds(147, 240, 318, 34);
		add(textFieldName);

		textFieldEmail = new InvisiableTextField(null);
		textFieldEmail.setBounds(147, 360, 318, 34);
		add(textFieldEmail);

	}

	public void reset() {
		textFieldEmail.setText(null);
		textFieldName.setText(null);
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

	public String getTextFieldName() {
		return textFieldName.getText();
	}

	public String getTextFieldEmail() {
		return textFieldEmail.getText();
	}

}
