package gui.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import gui.GUI;
import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;
import main.Main;
import survey.SurveyService;
import survey.SurveyVo;
import travel.TravelDao;
import travel.TravelService;
import travel.TravelVo;

public class SugTripPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private JPanel panel_1;
	private JScrollPane scrollPane_2;
	private JPanel panel_2;
	
	private TravelDao td = new TravelDao();
	private TravelService ts = new TravelService();

	public SugTripPanel() {
		super("sugTrip");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 155, 491, 159);
		add(scrollPane);

		panel = new JPanel();
		panel.setLayout(null);

		panel.setBorder(null);
		panel.setBackground(new Color(250, 250, 250));
		scrollPane.setBorder(null);
		scrollPane.setBackground(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(30, 398, 491, 159);
		add(scrollPane_1);

		panel_1 = new JPanel();
		panel_1.setLayout(null);

		panel_1.setBorder(null);
		panel_1.setBackground(new Color(250, 250, 250));
		scrollPane_1.setBorder(null);
		scrollPane_1.setBackground(null);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(30, 642, 491, 159);
		add(scrollPane_2);

		panel_2 = new JPanel();
		panel_2.setLayout(null);

		panel_2.setBorder(null);
		panel_2.setBackground(new Color(250, 250, 250));
		scrollPane_2.setBorder(null);
		scrollPane_2.setBackground(null);
		scrollPane_2.setOpaque(false);
		scrollPane_2.getViewport().setOpaque(false);
		
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane_1.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane_2.getVerticalScrollBar().setUnitIncrement(16);

	}
	
	public boolean set() {
		SurveyVo svo = new SurveyService().search(Main.loginNo);
		if(svo==null) {
			return false;
		}else {
			svo.setCategory("1");	
			ts.recommTravel(svo);
			set(ts.recommTravel(svo), panel, scrollPane);
			svo.setCategory("3");	
			ts.recommTravel(svo);
			set(ts.recommTravel(svo), panel_1, scrollPane_1);
			svo.setCategory("2");	
			List<TravelVo> list = ts.recommTravel(svo);
			svo.setCategory("4");	
			list.addAll(ts.recommTravel(svo));
			set(list, panel_2, scrollPane_2);
	
			return true;
		}
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

	private void set(List<TravelVo> list, JPanel p, JScrollPane s) {
		p.removeAll();
		p.revalidate();
		p.repaint();
		
		int i = 0;
		for (TravelVo vo : list) {

			SugTripTemplate b = new SugTripTemplate(vo);
			b.setNo(vo.getTravel_no());
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
					GUI.where2 = "SugTripPanel";
					GUI.inforborad.set(tvo);
					GUI.inforborad.setVisible(true);
				}
			});
			p.add(b);

			i++;
		}
		
		p.setSize(0, 41*(i+1));
		
		p.setPreferredSize(p.getPreferredSize());
		
		s.setViewportView(p);
	}
}
