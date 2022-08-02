package gui.panel;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;

public class SugTripPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;

	public SugTripPanel() {
		super("sugTrip");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(441, 100, 80, 23);
		add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 155, 491, 159);
		add(scrollPane);
		
		JPanel panel = new JPanel();
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
		scrollPane_1.setBounds(30, 398, 491, 159);
		add(scrollPane_1);
		
		JPanel panel_1 = new JPanel();
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
		scrollPane_2.setBounds(30, 642, 491, 159);
		add(scrollPane_2);
		
		JPanel panel_2 = new JPanel();
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
		
		
	}
}
