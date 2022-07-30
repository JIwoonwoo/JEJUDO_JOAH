package gui.panel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;

public class ListQnaPanelTest extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JButton writeBtn;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	
	public ListQnaPanelTest() {
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
		
//		panel_1 = new JPanel();
//		add(panel_1);
//		
//		panel_1.setOpaque(false);
//		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(25, 107, 500, 651);
		add(panel_2);
		panel_2.setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(483, 0, 17, 651);
		panel_2.add(scrollBar);
		
		JPanel a = new QuestionListTemplate("asdfasdf", "df");
		a.setBounds(12, 5, 463, 63);
		panel_2.add(a);
		JPanel b = new QuestionListTemplate("asdfasdf", "df");
		b.setBounds(12, 5+63, 463, 63);
		panel_2.add(b);
		
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}
}
