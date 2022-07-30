package gui.panel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;

public class SugTripPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JTable tourism;
	private JTable activity;
	private JTable restaurant;
	private JScrollPane tourismScroll;
	private JScrollPane activityScroll;
	private JScrollPane restaurantScroll;

	public SugTripPanel() {
		super("sugTrip");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		Integer[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		String[] cols = { "A", "B", "C" };

		tourism = new JTable();
		tourism.setModel(new DefaultTableModel(data, cols) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		});
		tourism.setBorder(new LineBorder(new Color(64, 64, 64)));
		tourism.getTableHeader().setBackground(new Color(244, 147, 73));
		tourism.getTableHeader().setForeground(Color.white);

		activity = new JTable();
		activity.setModel(new DefaultTableModel(data, cols) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		});
		activity.setBorder(new LineBorder(new Color(64, 64, 64)));
		activity.getTableHeader().setBackground(new Color(244, 147, 73));
		activity.getTableHeader().setForeground(Color.white);

		restaurant = new JTable();
		restaurant.setModel(new DefaultTableModel(data, cols) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		});
		restaurant.setBorder(new LineBorder(new Color(64, 64, 64)));
		restaurant.getTableHeader().setBackground(new Color(244, 147, 73));
		restaurant.getTableHeader().setForeground(Color.white);

		tourismScroll = new JScrollPane(tourism);
		tourismScroll.setBounds(25, 124, 495, 191);
		add(tourismScroll);

		activityScroll = new JScrollPane(activity);
		activityScroll.setBounds(25, 367, 495, 191);
		add(activityScroll);

		restaurantScroll = new JScrollPane(restaurant);
		restaurantScroll.setBounds(25, 610, 495, 191);
		add(restaurantScroll);

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

	public JTable getTourism() {
		return tourism;
	}

	public JTable getActivity() {
		return activity;
	}

	public JTable getRestaurant() {
		return restaurant;
	}

	public JScrollPane getTourismScroll() {
		return tourismScroll;
	}

	public JScrollPane getActivityScroll() {
		return activityScroll;
	}

	public JScrollPane getRestaurantScroll() {
		return restaurantScroll;
	}

}
