package gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import flight3.Flight_Vo_MyFlight;
import gui.button.InvisiableButton;

public class FlightListTemplate extends JPanel {

	private int no;
//	private JLabel lblNewLabel;
	private JButton button;
	private JSeparator separator;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JSeparator separator_1;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2_2;
	private JLabel lblNewLabel_2_3;
	private JSeparator separator_1_1;

	/**
	 * Create the panel.
	 */
	public FlightListTemplate(Flight_Vo_MyFlight rVo) {
		
		no = rVo.getReserveNo();
		
		setSize(464, 41);
		setOpaque(false);
		setLayout(null);

		button = new InvisiableButton();
		button.setBounds(0, 0, 463, 41);
		add(button);

//		lblNewLabel_1 = new JLabel(rVo.getReservDate().substring(2,10));
//		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 12));
//		lblNewLabel.setBounds(12, 11, 35, 18);
//
//		add(lblNewLabel);

		separator = new JSeparator();
		separator.setBounds(0, 39, 463, 2);
		separator.setForeground(Color.LIGHT_GRAY);
		add(separator);

		lblNewLabel_1 = new JLabel(rVo.getReservDate().substring(2,10));
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(30, 11, 78, 18);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel(rVo.getDepartureDate().substring(2,10));
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(140, 11, 78, 18);
		add(lblNewLabel_2);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(120, 11, 1, 18);
		add(separator_1);
		
		lblNewLabel_2_1 = new JLabel(rVo.getAirline());
		lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(210, 11, 78, 18);
		add(lblNewLabel_2_1);
		
		lblNewLabel_2_2 = new JLabel(rVo.getReturnDate().substring(2,10));
		lblNewLabel_2_2.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(305, 11, 78, 18);
		add(lblNewLabel_2_2);
		
		lblNewLabel_2_3 = new JLabel(rVo.getReturnAirline());
		lblNewLabel_2_3.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_2_3.setBounds(375, 11, 78, 18);
		add(lblNewLabel_2_3);
		
		separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBounds(290, 11, 1, 18);
		add(separator_1_1);

	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setSelect() {
		setOpaque(true);
		setBackground(new Color(244, 147, 73));
//		lblNewLabel.setForeground(Color.white);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_2.setForeground(Color.white);
		separator.setForeground(Color.white);
		separator_1.setForeground(Color.white);
		lblNewLabel_2_1.setForeground(Color.white);
		lblNewLabel_2_2.setForeground(Color.white);
		lblNewLabel_2_3.setForeground(Color.white);
		separator_1_1.setForeground(Color.white);

	}

	public void resetColor() {
		setBackground(null);
		setOpaque(false);
//		lblNewLabel.setForeground(Color.black);
		lblNewLabel_1.setForeground(Color.black);
		lblNewLabel_2.setForeground(Color.black);
		separator.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_1.setForeground(Color.black);
		lblNewLabel_2_2.setForeground(Color.black);
		lblNewLabel_2_3.setForeground(Color.black);
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setForeground(new Color(160, 160, 160));
	}

	public JButton getButton() {
		return button;
	}
}
