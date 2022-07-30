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

public class UpdateMemberPanel extends ImgPanel {
	
	private MemberService ms = new MemberService();

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private NextBtn nextBtn;
	private JCheckBox checkPwdBtn;
	private JTextField name;
	private JTextField id;
	private JPasswordField pwd;
	private JTextField nick;
	private JTextField phone;
	private JTextField email;
	
	public UpdateMemberPanel() {
		super("updateMember");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		nextBtn = new NextBtn();
		add(nextBtn);
		
		checkPwdBtn = new InvisiableCheck("비번확인");
		add(checkPwdBtn);
		checkPwdBtn.setLocation(464, 328);
		
		name = new InvisiableTextField(null);
		name.setBounds(146, 140, 321, 35);
		name.setColumns(10);
		name.setEnabled(false);
		add(name);
		
		
		id = new InvisiableTextField(null);
		id.setColumns(10);
		id.setBounds(146, 278, 321, 35);
		id.setEnabled(false);
		add(id);
		
		pwd = new InvisiablePasswordField(null);
		pwd.setColumns(10);
		pwd.setBounds(146, 352, 321, 35);
		add(pwd);
		
		nick = new InvisiableTextField(null);
		nick.setColumns(10);
		nick.setBounds(146, 427, 321, 35);
		nick.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!getNick().equals("")) {
					JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, nick.getParent());
					if (!ms.checkNick(getNick())) {
						PopUpDialog dialog = new PopUpDialog(f, "회원정보수정", "형식을 맞춰주세요");
						dialog.run();
						nick.requestFocus();
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		add(nick);
		
		phone = new InvisiableTextField(null);
		phone.setColumns(10);
		phone.setBounds(146, 566, 321, 35);
		phone.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!getPhone().equals("")) {
					JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, phone.getParent());
					if (!ms.checkPhone(getPhone())) {
						PopUpDialog dialog = new PopUpDialog(f, "회원정보수정", "형식을 맞춰주세요");
						dialog.run();
						phone.requestFocus();
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		add(phone);
		
		email = new InvisiableTextField(null);
		email.setColumns(10);
		email.setBounds(146, 640, 321, 35);
		email.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (!getEmail().equals("")) {
					JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, email.getParent());
					if (!ms.checkEmail(getEmail())) {
						PopUpDialog dialog = new PopUpDialog(f, "회원정보수정", "형식을 맞춰주세요");
						dialog.run();
						email.requestFocus();
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		add(email);
				
	}

	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public NextBtn getNextBtn() {
		return nextBtn;
	}

	public JCheckBox getCheckPwdBtn() {
		return checkPwdBtn;
	}

	public String getNameF() {
		return name.getText();
	}

	public String getId() {
		return id.getText();
	}

	public String getNick() {
		return nick.getText();
	}

	public String getPhone() {
		return phone.getText();
	}

	public String getEmail() {
		return email.getText();
	}
	
	public String getPwd() {
		return String.valueOf(pwd.getPassword());
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public void setId(String id) {
		this.id.setText(id);
	}

	public void setPwd(String pwd) {
		this.pwd.setText(pwd);
	}

	public void setNick(String nick) {
		this.nick.setText(nick);
	}

	public void setPhone(String phone) {
		this.phone.setText(phone);
	}

	public void setEmail(String email) {
		this.email.setText(email);
	}
	
	
	
}
