package gui.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import flight3.Flight_Vo;
import flight3.Flight_Vo_MyFlight;
import gui.button.ImgButton;
import gui.panel.ImgPanel;

public class FlightSearch extends JDialog {
	
	private JTable table;
	private String price;
	private Flight_Vo_MyFlight select = new Flight_Vo_MyFlight();
	private JTable table2;

	public FlightSearch(Frame parent, String name, String msg) {
		super(parent,name,true);
	    Point loc = parent.getLocation();
	    setLocation(loc.x+225,loc.y+450);
	    
		JPanel panel = new ImgPanel(new ImageIcon("D:/miniPrj/jejuWorkSpace/jejudoPrj/image/page/flightDialog.jpg").getImage());
//		JPanel panel = new ImgPanel(new ImageIcon("././image/page/flightDialog.jpg").getImage());
//		JPanel panel = new ImgPanel("labelDialog");
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 849, 152);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(null);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(64, 64, 64)));
		table.getTableHeader().setBackground(new Color(244, 147, 73));
		table.getTableHeader().setForeground(Color.white);
		table.setBackground(Color.white);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBounds(25, 271, 849, 152);
		panel.add(scrollPane_1);
		
		table2 = new JTable();
		table2.setBorder(new LineBorder(new Color(64, 64, 64)));
		table2.getTableHeader().setBackground(new Color(244, 147, 73));
		table2.getTableHeader().setForeground(Color.white);
		table2.setBackground(Color.white);
		scrollPane_1.setViewportView(table2);
		
		
		JButton btnNewButton = new ImgButton("확인2");
		btnNewButton.setLocation(276, 442);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = null;
				String result2 = null;
				
				if(table.getSelectedRow() != -1) {
					result = table.getValueAt(table.getSelectedRow(), 0).toString();
				}
				if(table2.getSelectedRow() != -1) {
					result2 = table2.getValueAt(table2.getSelectedRow(), 0).toString();
				}
				System.out.println(result);
				System.out.println(result2);
				select.setMyDepartureFlightNo(result);
				select.setMyReturnFlightNo(result2);
				dispose();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton2 = new ImgButton("취소2");
		btnNewButton2.setLocation(458, 442);
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnNewButton2);
		
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void set(List<Flight_Vo> list, JTable t) {
		
		Object[][] data = new Object[list.size()][9];
		String[] cols = {"No" , "항공편명" , "항공사" , "날짜" , "출발시간" ,"좌석", "출발공항","도착공항","가격"};
		
		int i = 0;
		for(Flight_Vo vo : list) {

				data[i][0] = vo.getFlightNo();
				data[i][1] = vo.getFlightName();
				data[i][2] = vo.getAirline();
				data[i][3] = vo.getDepartureDate();
				data[i][4] = vo.getDepartureTime();
				data[i][5] = vo.getFlightPerson();
				data[i][6] = vo.getDepAirport();
				data[i][7] = vo.getArrAirport();
				data[i][8] = vo.getFlightPrice();
				i++;
		}
		
		t.setModel(new DefaultTableModel(data, cols) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		});
		
	}
	
	public Flight_Vo_MyFlight run() {
		this.setVisible(true);
		return select;
	}

	public JTable getTable() {
		return table;
	}

	public String getPrice() {
		return price;
	}

	public JTable getTable2() {
		return table2;
	}
	
	
}
