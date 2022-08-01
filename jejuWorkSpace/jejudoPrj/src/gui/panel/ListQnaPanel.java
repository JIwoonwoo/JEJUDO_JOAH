package gui.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import gui.GUI;
import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;
import qna.QnaService;
import qna.QnaVo;

public class ListQnaPanel extends ImgPanel {
	
	QnaService qs = new QnaService();

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JButton writeBtn;
	private JScrollPane scrollPane;
	private JPanel panel;
	
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
		
		panel = new JPanel();
		panel.setLayout(null);
		
		scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(25, 106, 500, 653);
		add(scrollPane);
		
		panel.setBorder(null);
		panel.setBackground(Color.white);
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
	
	/**
	 * 
	 * @param list vo(no, title, answerYN, qContent, aContent)
	 */
	public void setList(List<QnaVo> list) {
		int i = 0;
		for(QnaVo vo : list) {
			String title = vo.getQuestionTitle();
			
			String answer = "답변대기";
			if(vo.getAnswerYN()) {
				answer = "답변완료";
			}
			
			QuestionListTemplate b = new QuestionListTemplate(title, answer);
			b.setNo(vo.getQuestionNo());
			b.setBounds(19, 5+(63*i), 462, 63);
			b.getButton().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					b.setSelect();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					b.resetColor();
				}
			});
			b.getButton().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					GUI.viewQna.set(vo);
					System.out.println(vo.getQuestionNo());
					GUI.viewQna.setVisible(true);
				}
			});
			panel.add(b);
			
			i++;
		}
	}

	
}
