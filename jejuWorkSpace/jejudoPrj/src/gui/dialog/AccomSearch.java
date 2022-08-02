package gui.dialog;

import java.awt.Frame;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

import gui.panel.ImgPanel;

public class AccomSearch extends JDialog {
	
	public AccomSearch(Frame parent, String name, String msg) {
		super(parent,name,true);
	    Point loc = parent.getLocation();
	    setLocation(loc.x+225,loc.y+450);
	    
//		JPanel panel = new ImgPanel(new ImageIcon("D:/miniPrj/jejuWorkSpace/jejudoPrj/image/page/accomDialog.jpg").getImage());
		JPanel panel = new ImgPanel(new ImageIcon("././image/page/accomDialog.jpg").getImage());
//		JPanel panel = new ImgPanel("labelDialog");
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void run() {
		this.setVisible(true);
	}
}
