package gui.panel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.button.ImgButton;
import gui.button.InvisiableButton;
import gui.field.InvisiablePasswordField;
import gui.field.InvisiableTextField;

public class LoginPanel extends ImgPanel {

	private JTextField idTextField;
	private JPasswordField passwordField;
	private JButton loginBtn;
	private JButton joinBtn;
	private JButton findIdBtn;
	private JButton findPwdBtn;

	public LoginPanel() {
		super("login");
		loginBtn = new ImgButton("loginBt");
		loginBtn.setBounds(225, 477, 101, 32);

		add(loginBtn);

		joinBtn = new InvisiableButton();
		joinBtn.setBounds(135, 526, 66, 23);
		add(joinBtn);

		findIdBtn = new InvisiableButton();
		findIdBtn.setBounds(213, 526, 97, 23);
		add(findIdBtn);

		findPwdBtn = new InvisiableButton();
		findPwdBtn.setBounds(322, 526, 97, 23);
		add(findPwdBtn);

		passwordField = new InvisiablePasswordField("0000");
		passwordField.setBounds(200, 412, 230, 32);
		add(passwordField);

		idTextField = new InvisiableTextField("아이디를 입력해 주세요");
		idTextField.setBounds(200, 357, 230, 32);
		add(idTextField);
		idTextField.setFocusTraversalKeysEnabled(false);
		idTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// 아이디 필드에서 tab키 누르면 패스워드 필드로 넘어감
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					passwordField.requestFocus();
					passwordField.setText(null);
				}
			}
		});
	}

	public void reset() {
		idTextField.setText("아이디를 입력해 주세요");
		passwordField.setText("0000");
	}

	public String getIdTextField() {
		return idTextField.getText();
	}

	public String getPasswordField() {
		return String.valueOf(passwordField.getPassword());
	}

	public JButton getLoginBtn() {
		return loginBtn;
	}

	public JButton getJoinBtn() {
		return joinBtn;
	}

	public JButton getFindIdBtn() {
		return findIdBtn;
	}

	public JButton getFindPwdBtn() {
		return findPwdBtn;
	}

}
