package gui.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import car.ReserveVo;
import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;

public class ReservInforPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;

	public ReservInforPanel() {
		super("reservInfor");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25, 155, 496, 149);
		add(scrollPane);

		panel = new JPanel();
		panel.setLayout(null);
		scrollPane.setViewportView(panel);

		panel.setBorder(null);
		panel.setBackground(new Color(250, 250, 250));
		scrollPane.setBorder(null);
		scrollPane.setBackground(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(25, 398, 496, 149);
		add(scrollPane_1);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		scrollPane_1.setViewportView(panel_1);

		panel_1.setBorder(null);
		panel_1.setBackground(new Color(250, 250, 250));
		scrollPane_1.setBorder(null);
		scrollPane_1.setBackground(null);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(25, 642, 495, 140);
		add(scrollPane_2);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		scrollPane_2.setViewportView(panel_2);

		panel_2.setBorder(null);
		panel_2.setBackground(new Color(250, 250, 250));
		scrollPane_2.setBorder(null);
		scrollPane_2.setBackground(null);
		scrollPane_2.setOpaque(false);
		scrollPane_2.getViewport().setOpaque(false);

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

	public void set() {
		// TODO Auto-generated method stub

	}

	public void setCarList(List<ReserveVo> list) {
		int i = 0;
		System.out.println(list);
		for (ReserveVo vo : list) {

			CarListTemplate b = new CarListTemplate(vo);
			b.setNo(vo.getRentalNo());
			b.setBounds(15, 0 + (41 * i), 464, 41);
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
//					setVisible(false);
				}
			});
			panel_2.add(b);

			i++;
		}
	}

}
