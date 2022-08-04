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

import accom.AccDto;
import car.CarVo;
import gui.button.ImgButton;
import gui.panel.ImgPanel;

public class AccomSearch extends JDialog {
	
	private JTable table;
	
	private int select = -1;
	private String price;
	
	public AccomSearch(Frame parent, String name) {
		super(parent,name,true);
	    Point loc = parent.getLocation();
	    setLocation(loc.x+225,loc.y+450);
	    
//		JPanel panel = new ImgPanel(new ImageIcon("D:/miniPrj/jejuWorkSpace/jejudoPrj/image/page/accomDialog.jpg").getImage());
		JPanel panel = new ImgPanel(new ImageIcon("././image/page/accomDialog.jpg").getImage());
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
					price = table.getValueAt(table.getSelectedRow(), 4).toString();
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
	
	public void set(List<AccDto> list) {
		
		Object[][] data = new Object[list.size()][10];
		String[] cols = {"번호" , "이름" , "주소" , "방이름", "가격", "수용인원" , "반려동물 출입가능" , "수영장 사용가능", "주변환경" , "뷰"};
		
//		dto.setRoomno(rs.getInt("ROOM_NO"));
//		dto.setAccomname(rs.getString("ACCOM_NAME"));
//		dto.setAddress(rs.getString("ACCOM_ADDRESS"));
//		dto.setPoolYN(rs.getString("POOL_YN"));
//		dto.setRoomname(rs.getString("ROOM_NAME"));
//		dto.setPrice(rs.getInt("ROOM_PRICE"));
//		dto.setCapacity(rs.getInt("CAPACITY"));
//		dto.setAnimalYN(rs.getString("ANIMAL_YN"));
//		dto.setPoolableYN(rs.getString("POOL_ABLE_YN"));
//		dto.setAround(rs.getString("ACCOM_AR"));
//		dto.setRoomview(rs.getString("ROOM_VIEW_INFO"));
		
		int i = 0;
		for(AccDto vo : list) {
				data[i][0] = vo.getRoomno();
				data[i][1] = vo.getAccomname();
				data[i][2] = vo.getAddress();
				data[i][3] = vo.getRoomname();
				data[i][4] = vo.getPrice();
				data[i][5] = vo.getCapacity();
				data[i][6] = vo.getAnimalYN();
				data[i][7] = vo.getPoolableYN();
				data[i][8] = vo.getAround();
				data[i][9] = vo.getRoomview();
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
