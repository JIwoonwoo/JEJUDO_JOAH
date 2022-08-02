package gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import gui.button.InvisiableButton;

public class SugTemplate extends JPanel {
	
	private int no;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	
	public SugTemplate() {
		setSize(480,45);
		setOpaque(false);
		setLayout(null);
		
		btnNewButton = new InvisiableButton();
		btnNewButton.setBounds(0, 0, 480, 45);
		add(btnNewButton);
		
		lblNewLabel = new JLabel("엄청나고 어마어마한 카페");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(23, 10, 245, 26);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("서귀포시");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(258, 10, 57, 26);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("카페");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(338, 10, 66, 26);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("5");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(435, 10, 17, 26);
		add(lblNewLabel_3);
		
		separator = new JSeparator();
		separator.setBounds(245, 10, 1, 25);
		add(separator);
		separator.setOrientation(SwingConstants.VERTICAL);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(325, 10, 1, 25);
		add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(415, 10, 1, 25);
		add(separator_2);
		
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
		lblNewLabel_2.setForeground(Color.white);
		lblNewLabel_3.setForeground(Color.white);
		separator.setForeground(Color.white);
		separator_1.setForeground(Color.white);
		separator_2.setForeground(Color.white);
		separator_3.setForeground(Color.white);
	}
	
	public void resetColor() {
		setBackground(null);
		setOpaque(false);
		lblNewLabel.setForeground(Color.black);
		lblNewLabel_1.setForeground(Color.black);
		lblNewLabel_2.setForeground(Color.black);
		lblNewLabel_3.setForeground(Color.black);
		separator.setForeground(new Color(160,160,160));
		separator_1.setForeground(new Color(160,160,160));
		separator_2.setForeground(new Color(160,160,160));
		separator_3.setForeground(Color.LIGHT_GRAY);
	}

	
}

