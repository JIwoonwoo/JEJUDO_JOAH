package gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import gui.button.InvisiableButton;
import travel.TravelVo;

public class SugTripTemplate extends JPanel {

	private String no;
	private JButton button;
	private JSeparator separator;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JSeparator separator_1_1;
	private JSeparator separator_1_1_1;
	private JSeparator separator_1_1_1_1;

	/**
	 * Create the panel.
	 */
	public SugTripTemplate(TravelVo vo) {
		
		no = vo.getTravel_no();
		
		setSize(490, 41);
		setOpaque(false);
		setLayout(null);

		button = new InvisiableButton();
		button.setBounds(0, 0, 490, 41);
		add(button);

		separator = new JSeparator();
		separator.setBounds(10, 39, 475, 2);
		separator.setForeground(Color.LIGHT_GRAY);
		add(separator);

		lblNewLabel_1 = new JLabel(vo.getTravel_name());
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(30, 11, 240, 18);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel(vo.getTravel_address());
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(260, 11, 58, 18);
		add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel(vo.getCategory());
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(340, 11, 58, 18);
		add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel(vo.getLike_cnt()+" ▲");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(410, 11, 51, 18);
		add(lblNewLabel_4);

		separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBounds(413, 11, 1, 18);
		add(separator_1_1);
		
		separator_1_1_1 = new JSeparator();
		separator_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1.setBounds(327, 11, 1, 18);
		add(separator_1_1_1);
		
		separator_1_1_1_1 = new JSeparator();
		separator_1_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1_1.setBounds(250, 11, 1, 18);
		add(separator_1_1_1_1);

	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setSelect() {
		setOpaque(true);
		setBackground(new Color(244, 147, 73));
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_2.setForeground(Color.white);
		lblNewLabel_3.setForeground(Color.white);
		lblNewLabel_4.setForeground(Color.white);
		separator.setForeground(new Color(244, 147, 73));
		separator_1_1.setForeground(Color.white);
		separator_1_1_1.setForeground(Color.white);
		separator_1_1_1_1.setForeground(Color.white);
	}

	public void resetColor() {
		setBackground(null);
		setOpaque(false);
		lblNewLabel_1.setForeground(Color.black);
		lblNewLabel_2.setForeground(Color.black);
		lblNewLabel_3.setForeground(Color.black);
		lblNewLabel_4.setForeground(Color.black);
		separator.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setForeground(new Color(160, 160, 160));
		separator_1_1_1.setForeground(new Color(160, 160, 160));
		separator_1_1_1_1.setForeground(new Color(160, 160, 160));
		separator_1_1.setForeground(new Color(160, 160, 160));
	}

	public JButton getButton() {
		return button;
	}
}
