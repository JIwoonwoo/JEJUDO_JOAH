package gui.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;

public class ViewQna extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JLabel lblNewLabel;
	private JTextPane textPane;
	private JLabel lblNewLabel_1;
	private JTextPane textPane_1;
	private JButton updateBtn;
	
	public ViewQna() {
		super("viewQA");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(105, 120, 394, 46);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setBounds(53, 185, 446, 285);
		textPane.setFont(new Font("굴림", Font.PLAIN, 15));
		add(textPane);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(105, 534, 394, 46);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		add(lblNewLabel_1);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(53, 597, 446, 189);
		textPane_1.setFont(new Font("굴림", Font.PLAIN, 15));
		add(textPane_1);
		
		updateBtn = new ImgButton("수정하기");
		updateBtn.setLocation(0, 828);
		add(updateBtn);
		
		updateBtn = new ImgButton("삭제하기");
		updateBtn.setLocation(275, 828);
		add(updateBtn);
				
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public JTextPane getTextPane_1() {
		return textPane_1;
	}

	
}
