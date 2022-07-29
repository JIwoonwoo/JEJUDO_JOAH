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
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class TextFieldDialog extends JDialog {
	private JPasswordField passwordField;
	
	public TextFieldDialog(Frame parent, String name, String msg) {
		super(parent,name,true);
	    Point loc = parent.getLocation();
	    setLocation(loc.x+225,loc.y+450);
	    
//		JPanel panel = new ImgPanel(new ImageIcon("D:/miniPrj/jejuWorkSpace/jejudoPrj/image/page/textDialog.jpg").getImage());
		JPanel panel = new ImgPanel(new ImageIcon("././image/page/labelDialog.jpg").getImage());
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
		
		JLabel lblNewLabel = new JLabel(msg);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(61, 27, 183, 24);
		panel.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(25, 75, 249, 32);
		panel.add(passwordField);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public String run() {
		this.setVisible(true);
		String pwd = String.valueOf(passwordField.getPassword());
		return pwd;
	}
}
