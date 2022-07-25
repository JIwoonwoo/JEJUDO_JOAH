package gui.panel;

import javax.swing.JTextField;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;
import gui.field.InvisiableTextField;

public class FindPwdPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JTextField textFieldName;
	private JTextField textFieldId;
	private JTextField textFieldEmail;

	public FindPwdPanel() {
		super("findPwd");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		textFieldName = new InvisiableTextField(null);
		textFieldName.setBounds(147, 240, 318, 34);
		add(textFieldName);

		textFieldId = new InvisiableTextField(null);
		textFieldId.setBounds(147, 351, 318, 34);
		add(textFieldId);

		textFieldEmail = new InvisiableTextField(null);
		textFieldEmail.setBounds(147, 420, 318, 34);
		add(textFieldEmail);

	}

	public void reset() {
		textFieldEmail.setText(null);
		textFieldId.setText(null);
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

	public String getTextFieldId() {
		return textFieldId.getText();
	}

	public String getTextFieldEmail() {
		return textFieldEmail.getText();
	}

}
