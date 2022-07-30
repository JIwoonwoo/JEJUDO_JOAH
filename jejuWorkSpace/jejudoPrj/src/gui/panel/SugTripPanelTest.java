package gui.panel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;
import gui.table.NewTable;

public class SugTripPanelTest extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private NewTable tourism;
	private NewTable activity;
	private NewTable restaurant;
	private JScrollPane tourismScroll;
	private JScrollPane activityScroll;
	private JScrollPane restaurantScroll;

	public SugTripPanelTest() {
		super("sugTrip");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		Integer[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		String[] cols = { "A", "B", "C" };

		tourism = new NewTable();
		tourism.setData(data, cols);

		activity = new NewTable();
		activity.setData(data, cols);

		restaurant = new NewTable();
		restaurant.setData(data, cols);

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
