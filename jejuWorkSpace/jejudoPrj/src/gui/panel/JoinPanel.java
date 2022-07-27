package gui.panel;

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.InvisiableCheck;
import gui.button.NextBtn;
import gui.field.InvisiablePasswordField;
import gui.field.InvisiableTextField;

public class JoinPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JTextField textFieldName;
	private JTextField textFieldId;
	private JTextField textFieldEmail;
	private JPasswordField textFieldPwd;
	private JTextField textFieldPhone;
	private JTextField textFieldNick;
	private JCheckBox checkIdBtn;
	private JCheckBox checkPwdBtn;

	public JoinPanel() {
		super("join");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		textFieldName = new InvisiableTextField(null);
		textFieldName.setBounds(150, 141, 318, 34);
		add(textFieldName);

		textFieldId = new InvisiableTextField(null);
		textFieldId.setBounds(150, 279, 318, 34);
		add(textFieldId);

		textFieldPwd = new InvisiablePasswordField(null);
		textFieldPwd.setBounds(150, 353, 318, 34);
		add(textFieldPwd);

		textFieldNick = new InvisiableTextField(null);
		textFieldNick.setBounds(150, 427, 318, 34);
		add(textFieldNick);

		textFieldPhone = new InvisiableTextField(null);
		textFieldPhone.setBounds(150, 566, 318, 34);
		add(textFieldPhone);

		textFieldEmail = new InvisiableTextField(null);
		textFieldEmail.setBounds(150, 641, 318, 34);
		add(textFieldEmail);

		checkIdBtn = new InvisiableCheck("중복확인");
		add(checkIdBtn);
		checkIdBtn.setLocation(464, 257);

		checkPwdBtn = new InvisiableCheck("비번확인");
		add(checkPwdBtn);
		checkPwdBtn.setLocation(464, 328);

	}

	public void reset() {

		textFieldName.setText(null);
		textFieldId.setText(null);
		textFieldEmail.setText(null);
		textFieldPwd.setText(null);
		textFieldPhone.setText(null);
		textFieldNick.setText(null);
		checkIdBtn.setSelected(false);
		checkPwdBtn.setSelected(false);

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

	public String getTextFieldPwd() {
		return String.valueOf(textFieldPwd.getPassword());
	}

	public String getTextFieldPhone() {
		return textFieldPhone.getText();
	}

	public String getTextFieldNick() {
		return textFieldNick.getText();
	}

	public JCheckBox getCheckIdBtn() {
		return checkIdBtn;
	}

	public JCheckBox getCheckPwdBtn() {
		return checkPwdBtn;
	}

}
