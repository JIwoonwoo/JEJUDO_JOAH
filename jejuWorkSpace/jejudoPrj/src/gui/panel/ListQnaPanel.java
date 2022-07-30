package gui.panel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;

public class ListQnaPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JButton writeBtn;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	
	public ListQnaPanel() {
		super("listQA");

		homeBtn = new HomeBtn();
		homeBtn.setBounds(463, -5, 80, 80);
		add(homeBtn);

		backBtn = new BackBtn();
		backBtn.setBounds(25, 18, 35, 35);
		add(backBtn);
		
		writeBtn = new ImgButton("글쓰기");
		writeBtn.setBounds(96, 785, 358, 86);
		add(writeBtn);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane(panel_1);
		scrollPane.setBounds(29, 113, 489, 642);
		add(scrollPane);
		
		JPanel a = new QuestionListTemplate("asdfasdf", "df");
		a.setBounds(12, 5, 463, 63);
		panel_1.add(a);
		JPanel b = new QuestionListTemplate("asdfasdf", "df");
		b.setBounds(12, 5+63, 463, 63);
		panel_1.add(b);
		
		panel_1.setBorder(null);
		panel_1.setBackground(null);
		scrollPane.setBorder(null);
		scrollPane.setBackground(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public JButton getWriteBtn() {
		return writeBtn;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}
	
	
}
