package gui.panel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;
import java.awt.ScrollPane;
import java.awt.Color;

public class SugTripPanelTest extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private ScrollPane scrollPane;
	private JTable table;

	public SugTripPanelTest() {
		super("sugTrip");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
//		scrollPane = new ScrollPane();
//		scrollPane.setBackground(Color.WHITE);
//		scrollPane.setBounds(25, 122, 495, 193);
//		add(scrollPane);
		
		Integer[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		String[] cols = {"A", "B", "C"};
		
		table = new JTable(data, cols);
		table.setBorder(new LineBorder(new Color(64, 64, 64)));
	    JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.BLUE);

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
}
