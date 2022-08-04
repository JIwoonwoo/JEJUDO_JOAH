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

public class YesOrNo extends JDialog {
	
	private int result = -1;
	
	public YesOrNo(Frame parent, String name, String msg) {
		super(parent,name,true);
	    Point loc = parent.getLocation();
	    setLocation(loc.x+225,loc.y+450);
	    
//		JPanel panel = new ImgPanel(new ImageIcon("D:/miniPrj/jejuWorkSpace/jejudoPrj/image/page/labelDialog.jpg").getImage());
		JPanel panel = new ImgPanel(new ImageIcon("././image/page/labelDialog.jpg").getImage());
//		JPanel panel = new ImgPanel("labelDialog");
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new ImgButton("확인2");
		btnNewButton.setSize(109, 37);
		btnNewButton.setLocation(30, 125);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = 1;
				dispose();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton2 = new ImgButton("취소2");
		btnNewButton2.setSize(109, 37);
		btnNewButton2.setLocation(165, 125);
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnNewButton2);
		
		JLabel lblNewLabel = new JLabel(msg);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(30, 76, 244, 32);
		panel.add(lblNewLabel);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public int run() {
		result = -1;
		this.setVisible(true);
		return result;
	}
}
