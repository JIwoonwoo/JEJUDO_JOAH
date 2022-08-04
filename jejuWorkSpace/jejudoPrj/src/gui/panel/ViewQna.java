package gui.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;
import qna.QnaVo;

public class ViewQna extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JLabel qTitle;
	private JTextArea qContent;
	private JTextArea aContent;
	private JButton updateBtn;
	private JButton deleteBtn;
	private int no;
	
	public ViewQna() {
		super("viewQA");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		qTitle = new JLabel();
		qTitle.setBounds(105, 120, 394, 46);
		qTitle.setFont(new Font("굴림", Font.BOLD, 18));
		add(qTitle);
		
		qContent = new JTextArea();
		qContent.setEditable(false);
		qContent.setBounds(53, 198, 446, 272);
		qContent.setFont(new Font("굴림", Font.PLAIN, 15));
		qContent.setOpaque(false);
		add(qContent);
		
		aContent = new JTextArea();
		aContent.setEditable(false);
		aContent.setBounds(53, 605, 446, 181);
		aContent.setFont(new Font("굴림", Font.PLAIN, 15));
		aContent.setOpaque(false);
		add(aContent);
		
		updateBtn = new ImgButton("수정하기");
		updateBtn.setLocation(0, 828);
		add(updateBtn);
		
		deleteBtn = new ImgButton("삭제하기");
		deleteBtn.setLocation(275, 828);
		add(deleteBtn);
						
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public String getqTitle() {
		return qTitle.getText();
	}

	public String getqContent() {
		return qContent.getText();
	}

	public String getaContent() {
		return aContent.getText();
	}

	public JButton getUpdateBtn() {
		return updateBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public int getNo() {
		return no;
	}

	public void set(QnaVo vo) {
		no = vo.getQuestionNo();
		qTitle.setText(vo.getQuestionTitle());
		qContent.setText(vo.getqContent());
		aContent.setText(vo.getaContent());
	}
}
