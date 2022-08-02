package gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import gui.GUI;
import gui.button.BackBtn;
import gui.button.HomeBtn;
import payment.PayVo;
import qna.QnaVo;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.List;

public class PointPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JLabel point;
	private JPanel panel;
	
	public PointPanel() {
		super("point");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		point = new JLabel("0000");
		point.setFont(new Font("굴림", Font.BOLD, 80));
		point.setHorizontalAlignment(SwingConstants.CENTER);
		point.setBounds(0, 264, 550, 113);
		add(point);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25, 553, 491, 321);
		add(scrollPane);
		scrollPane.setBorder(null);
		scrollPane.setBackground(null);
		scrollPane.setOpaque(false);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		panel.setBorder(null);
		panel.setBackground(new Color(250, 250, 250));
		
		JLabel lblNewLabel = new JLabel("날짜");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel.setBounds(48, 530, 57, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("결제금액");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(180, 530, 57, 15);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("적립포인트");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(413, 530, 80, 15);
		add(lblNewLabel_1_1);
		scrollPane.getViewport().setOpaque(false);
		

		
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public void setPoint(String s) {
		this.point.setText(s);
	}
	
	public void setList(List<PayVo> list) {
		int i = 0;
		for(PayVo vo : list) {
			Timestamp d = vo.getPayDate();
			int p = vo.getCutPrice();
			int po = vo.getMypoint();
			
			System.out.println(d);
			
			String date = d.toString().substring(2,10);
			String price = Integer.toString(p);
			String point = Integer.toString(po);
			
			
			PointTemplate b = new PointTemplate(date, price, point);
			b.setBounds(0, 0+(45*i), 480, 45);
			panel.add(b);
			
			i++;
		}
	}
}
