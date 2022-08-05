package gui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;
import gui.field.InvisiableTextField;

public class WriteQna extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private NextBtn nextBtn;
	private JTextField textField;
	private JTextArea textArea;
	
	private int no;
	
	public WriteQna() {
		super("writeQA");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		nextBtn = new NextBtn();
		add(nextBtn);
		
		textField = new InvisiableTextField(null);
		textField.setFont(new Font("굴림", Font.PLAIN, 18));
		textField.setBounds(103, 120, 397, 46);
		add(textField);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("굴림", Font.PLAIN, 15));
		textArea.setBounds(47, 202, 456, 565);
		textArea.setOpaque(false);
		textArea.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				textArea.setBorder(null);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textArea.setBorder(new LineBorder(new Color(144, 168, 94), 1, true));
			}
		});
		add(textArea);
				
	}
	
	public void reset() {
		textField.setText(null);
		textArea.setText(null);
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

	public String getTextField() {
		return textField.getText();
	}

	public String getTextArea() {
		return textArea.getText();
	}

	
	public void setTextField(String textField) {
		this.textField.setText(textField);
	}

	public void setTextArea(String textArea) {
		this.textArea.setText(textArea);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
}
