package gui.table;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class NewTable extends JTable {
	public NewTable() {
		setBorder(new LineBorder(new Color(64, 64, 64)));
		getTableHeader().setBackground(new Color(244, 147, 73));
		getTableHeader().setForeground(Color.white);
	}
	
	public void setData(Object[][] data, Object[] cols) {
		
		setModel(new DefaultTableModel(data, cols) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}

		});
		
	}


}
