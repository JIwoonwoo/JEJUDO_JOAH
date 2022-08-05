package gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import accom.AccDto;
import gui.button.InvisiableButton;

public class AccListTemplate extends JPanel {

	private int no;
	private JLabel lblNewLabel;
	private JButton button;
	private JSeparator separator;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JSeparator separator_1;

	/**
	 * Create the panel.
	 */
	public AccListTemplate(AccDto rVo) {
		
		no = rVo.getReserveNo();
		
		setSize(464, 41);
		setOpaque(false);
		setLayout(null);

		button = new InvisiableButton();
		button.setBounds(0, 0, 463, 41);
		add(button);

		lblNewLabel = new JLabel(Integer.toString(rVo.getReserveNo()));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel.setBounds(12, 11, 35, 18);

		add(lblNewLabel);

		separator = new JSeparator();
		separator.setBounds(0, 39, 463, 2);
		separator.setForeground(Color.LIGHT_GRAY);
		add(separator);

		lblNewLabel_1 = new JLabel(rVo.getReserveDate().toString().substring(2,10));
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(55, 11, 117, 18);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel(rVo.getAccomname());
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(180, 11, 165, 18);
		add(lblNewLabel_2);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(150, 11, 1, 18);
		add(separator_1);

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
		lblNewLabel.setForeground(Color.white);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_2.setForeground(Color.white);
		separator.setForeground(Color.white);
		separator_1.setForeground(Color.white);
	}

	public void resetColor() {
		setBackground(null);
		setOpaque(false);
		lblNewLabel.setForeground(Color.black);
		lblNewLabel_1.setForeground(Color.black);
		lblNewLabel_2.setForeground(Color.black);
		separator.setForeground(Color.LIGHT_GRAY);
		separator_1.setForeground(new Color(160, 160, 160));
	}

	public JButton getButton() {
		return button;
	}
}
