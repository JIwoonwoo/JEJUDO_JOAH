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

import car.CarVo;
import gui.button.ImgButton;
import gui.panel.ImgPanel;

public class CarSearch extends JDialog {
	
	private JTable table;
	
	private int select = -1;
	private String price;
	
	public CarSearch(Frame parent, String name) {
		super(parent,name,true);
	    Point loc = parent.getLocation();
	    setLocation(loc.x+225,loc.y+450);
	    
//		JPanel panel = new ImgPanel(new ImageIcon("D:/miniPrj/jejuWorkSpace/jejudoPrj/image/page/carDialog.jpg").getImage());
		JPanel panel = new ImgPanel(new ImageIcon("././image/page/carDialog.jpg").getImage());
//		JPanel panel = new ImgPanel("labelDialog");
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 849, 331);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(null);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(64, 64, 64)));
		table.getTableHeader().setBackground(new Color(244, 147, 73));
		table.getTableHeader().setForeground(Color.white);
		table.setBackground(Color.white);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new ImgButton("확인2");
		btnNewButton.setLocation(276, 442);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = null;
				if(table.getSelectedRow() != -1) {
					result = table.getValueAt(table.getSelectedRow(), 0).toString();
					price = table.getValueAt(table.getSelectedRow(), 3).toString();
				}
				select = Integer.parseInt(result);
				dispose();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton2 = new ImgButton("취소2");
		btnNewButton2.setLocation(458, 442);
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				select = -1;
				dispose();
			}
		});
		panel.add(btnNewButton2);
		
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public int run() {
		this.setVisible(true);
		return select;
	}
	
	public void set(List<CarVo> list) {
		
		Object[][] data = new Object[list.size()][5];
		String[] cols = {"번호" , "이름" , "사이즈" , "1박 금액" , "연료"};
		
		int i = 0;
		for(CarVo vo : list) {
				data[i][0] = vo.getRentalNo();
				data[i][1] = vo.getCarName();
				data[i][2] = vo.getCarSize();
				data[i][3] = vo.getDayPrice();
				data[i][4] = vo.getCarFuel();
				i++;
		}
		
		table.setModel(new DefaultTableModel(data, cols) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		});
		
	}

	public String getPrice() {
		return price;
	}
	
	
}
