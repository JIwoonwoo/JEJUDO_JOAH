package gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import gui.button.InvisiableButton;

public class PointTemplate extends JPanel {
	
	private int no;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JSeparator separator_3;
	
	public PointTemplate(String date, String pay, String point) {
		setSize(480,45);
		setOpaque(false);
		setLayout(null);
		
		btnNewButton = new InvisiableButton();
		btnNewButton.setBounds(0, 0, 480, 45);
		add(btnNewButton);
		
		lblNewLabel = new JLabel(date);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(23, 10, 245, 26);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(pay);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(136, 10, 106, 26);
		add(lblNewLabel_1);
		
		lblNewLabel_3 = new JLabel("+ "+point);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(331, 10, 121, 26);
		add(lblNewLabel_3);
		
		separator_3 = new JSeparator();
		separator_3.setForeground(Color.LIGHT_GRAY);
		separator_3.setBounds(10, 44, 470, 2);
		add(separator_3);
		
	}
	
	public void setSelect() {
		setOpaque(true);
		setBackground(new Color(244, 147, 73));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_3.setForeground(Color.white);
		separator_3.setForeground(Color.white);
	}
	
	public void resetColor() {
		setBackground(null);
		setOpaque(false);
		lblNewLabel.setForeground(Color.black);
		lblNewLabel_1.setForeground(Color.black);
		lblNewLabel_3.setForeground(Color.black);
		separator_3.setForeground(Color.LIGHT_GRAY);
	}

	
}

