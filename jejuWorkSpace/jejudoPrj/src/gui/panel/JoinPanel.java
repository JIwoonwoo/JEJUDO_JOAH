package gui.panel;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.InvisiableCheck;
import gui.button.NextBtn;
import gui.dialog.PopUpDialog;
import gui.field.InvisiablePasswordField;
import gui.field.InvisiableTextField;
import member.MemberService;

public class JoinPanel extends ImgPanel {

	private MemberService ms = new MemberService();

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

		textFieldId = new InvisiableTextField("영어 소문자와 숫자만 가능합니다.");
		textFieldId.setBounds(150, 279, 318, 34);
		add(textFieldId);

		textFieldPwd = new InvisiablePasswordField(null);
		textFieldPwd.setBounds(150, 353, 318, 34);
		add(textFieldPwd);

		textFieldNick = new InvisiableTextField("특수문자 제외 2 ~ 8글자");
		textFieldNick.setBounds(150, 427, 318, 34);
		textFieldNick.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!getTextFieldNick().equals("")) {
					JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, textFieldNick.getParent());
					if (!ms.checkNick(textFieldNick.getText())) {
						PopUpDialog dialog = new PopUpDialog(f, "회원가입", "형식을 맞춰주세요");
						dialog.run();
						textFieldNick.requestFocus();
					}
				}else {textFieldNick.setText("특수문자 제외 2 ~ 8글자");}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		add(textFieldNick);

		textFieldPhone = new InvisiableTextField("-도 포함하여 작성해주세요");
		textFieldPhone.setBounds(150, 566, 318, 34);
		textFieldPhone.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!getTextFieldPhone().equals("")) {
					JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, textFieldPhone.getParent());
					if (!ms.checkPhone(textFieldPhone.getText())) {
						PopUpDialog dialog = new PopUpDialog(f, "회원가입", "형식을 맞춰주세요");
						dialog.run();
						textFieldPhone.requestFocus();
					}
				}else {textFieldPhone.setText("-도 포함하여 작성해주세요");}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		add(textFieldPhone);

		textFieldEmail = new InvisiableTextField(null);
		textFieldEmail.setBounds(150, 641, 318, 34);
		textFieldEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!getTextFieldEmail().equals("")) {
					JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, textFieldEmail.getParent());
					if (!ms.checkEmail(textFieldEmail.getText())) {
						PopUpDialog dialog = new PopUpDialog(f, "회원가입", "형식을 맞춰주세요");
						dialog.run();
						textFieldEmail.requestFocus();
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
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
		textFieldId.setText("영어 소문자와 숫자만 가능합니다.");
		textFieldEmail.setText(null);
		textFieldPwd.setText(null);
		textFieldPhone.setText("-도 포함하여 작성해주세요");
		textFieldNick.setText("특수문자 제외 2 ~ 8글자");
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

	public String getId() {
		return textFieldId.getText();
	}
	
	public JTextField getTextFieldId() {
		return textFieldId;
	}

	public JPasswordField getTextFieldPwd() {
		return textFieldPwd;
	}

	public String getTextFieldEmail() {
		return textFieldEmail.getText();
	}

	public String getPwd() {
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
