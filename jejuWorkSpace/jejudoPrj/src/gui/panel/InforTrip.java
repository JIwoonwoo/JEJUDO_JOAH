package gui.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.GUI;
import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.InvisiableButton;
import travel.TravelDao;
import travel.TravelVo;
import javax.swing.JButton;

public class InforTrip extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JPanel panel_2;
	private JScrollPane scrollPane_2;
	private JButton activBtn;
	private JButton restraBtn;
	
	private TravelDao td = new TravelDao();
	
	public InforTrip() {
		super("inforTrip");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		
		scrollPane_2 = new JScrollPane();
//		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(25, 231, 495, 632);
		scrollPane_2.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane_2);

		panel_2.setBorder(null);
		panel_2.setBackground(new Color(250, 250, 250));
		scrollPane_2.setBorder(null);
		scrollPane_2.setBackground(null);
		scrollPane_2.setOpaque(false);
		
		activBtn = new InvisiableButton();
		activBtn.setBounds(196, 121, 159, 44);
		add(activBtn);
		
		restraBtn = new InvisiableButton();
		restraBtn.setBounds(370, 121, 159, 44);
		add(restraBtn);
		
				
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}
	
	public void setList(List<TravelVo> list) {
		panel_2.removeAll();
		panel_2.revalidate();
		panel_2.repaint();
		int i = 0;
		for (TravelVo vo : list) {
			SugTripTemplate b = new SugTripTemplate(vo);
			b.setBounds(0, 0 + (41 * i), 490, 41);
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
					TravelVo tvo = td.infoSelect(Integer.parseInt(vo.getTravel_no()));
					GUI.inforborad.set(tvo);
					GUI.where = "inforTrip";
					GUI.inforborad.setVisible(true);
				}
			});
			panel_2.add(b);

			i++;
		}
		
		panel_2.setSize(0, 41*(i+1));
		
		panel_2.setPreferredSize(panel_2.getPreferredSize());
		
		scrollPane_2.setViewportView(panel_2);
		
	}

	public JButton getActivBtn() {
		return activBtn;
	}

	public JButton getRestraBtn() {
		return restraBtn;
	}

	
}
