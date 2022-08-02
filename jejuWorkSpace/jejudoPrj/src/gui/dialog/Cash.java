package gui.dialog;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.button.ImgButton;
import gui.panel.ImgPanel;

public class Cash extends JDialog {
	
	public Cash(Frame parent, String name, String msg) {
		super(parent,name,true);
	    Point loc = parent.getLocation();
	    setLocation(loc.x+225,loc.y+450);
	    
		JPanel panel = new ImgPanel(new ImageIcon("D:/miniPrj/jejuWorkSpace/jejudoPrj/image/page/cash.jpg").getImage());
//		JPanel panel = new ImgPanel(new ImageIcon("././image/page/cash.jpg").getImage());
//		JPanel panel = new ImgPanel("labelDialog");
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new ImgButton("확인");
		btnNewButton.setLocation(87, 133);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnNewButton);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void run() {
		this.setVisible(true);
	}
}
