package gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import java.awt.ScrollPane;

public class PointPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JTable table;
	private JLabel point;
	private JScrollPane getPointList;
	
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
		
		Integer[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		String[] cols = { "A", "B", "C" };
		
		table = new JTable();
		table.setModel(new DefaultTableModel(data, cols) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		});
		table.setBorder(new LineBorder(new Color(64, 64, 64)));
		table.getTableHeader().setBackground(new Color(244, 147, 73));
		table.getTableHeader().setForeground(Color.white);

		getPointList = new JScrollPane(table);
		getPointList.setBounds(25, 521, 491, 352);
		add(getPointList);
		

		
	}
	
	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}


	public void setPoint(String s) {
		this.point.setText(s);
	}

	

}
