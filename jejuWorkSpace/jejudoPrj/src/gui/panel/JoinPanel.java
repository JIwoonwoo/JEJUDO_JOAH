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
		textFieldId.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				JTextField jt = (JTextField) e.getSource();
				JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class , jt.getParent());
				if(!ms.checkId(textFieldId.getText())) {
					PopUpDialog dialog = new PopUpDialog(f, "회원가입", "형식을 맞춰주세요");
					dialog.run();
					textFieldId.requestFocus();
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		add(textFieldId);

		textFieldPwd = new InvisiablePasswordField(null);
		textFieldPwd.setBounds(150, 353, 318, 34);
		textFieldPwd.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				JPasswordField jt = (JPasswordField) e.getSource();
				JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class , jt.getParent());
				if(!ms.checkPwd(textFieldPwd.getText())) {
					PopUpDialog dialog = new PopUpDialog(f, "회원가입", "형식을 맞춰주세요");
					dialog.run();
					textFieldPwd.requestFocus();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		add(textFieldPwd);

		textFieldNick = new InvisiableTextField(null);
		textFieldNick.setBounds(150, 427, 318, 34);
		textFieldNick.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				JTextField jt = (JTextField) e.getSource();
				JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class , jt.getParent());
				if(!ms.checkNick(textFieldNick.getText())) {
					PopUpDialog dialog = new PopUpDialog(f, "회원가입", "형식을 맞춰주세요");
					dialog.run();
					textFieldNick.requestFocus();
				}				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(textFieldNick);

		textFieldPhone = new InvisiableTextField("-도 포함하여 작성해주세요");
		textFieldPhone.setBounds(150, 566, 318, 34);
		textFieldPhone.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				JTextField jt = (JTextField) e.getSource();
				JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class , jt.getParent());
				if(!ms.checkPhone(textFieldPhone.getText())) {
					PopUpDialog dialog = new PopUpDialog(f, "회원가입", "형식을 맞춰주세요");
					dialog.run();
					textFieldPhone.requestFocus();
				}				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(textFieldPhone);

		textFieldEmail = new InvisiableTextField(null);
		textFieldEmail.setBounds(150, 641, 318, 34);
		textFieldEmail.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				JTextField jt = (JTextField) e.getSource();
				JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class , jt.getParent());
				if(!ms.checkEmail(textFieldEmail.getText())) {
					PopUpDialog dialog = new PopUpDialog(f, "회원가입", "형식을 맞춰주세요");
					dialog.run();
					textFieldEmail.requestFocus();
				}				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
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
