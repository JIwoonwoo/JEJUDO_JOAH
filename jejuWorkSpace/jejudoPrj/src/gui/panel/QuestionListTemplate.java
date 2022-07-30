package gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class QuestionListTemplate extends JPanel {

	/**
	 * Create the panel.
	 */
	public QuestionListTemplate(String title, String answerYn) {
		setSize(464, 61);
		setOpaque(false);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 22, 117, 18);
		
		JLabel lblNewLabel_1 = new JLabel(answerYn);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(244, 147, 73));
		lblNewLabel_1.setBounds(366, 10, 85, 15);
		
		add(lblNewLabel);
		add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 59, 463, 2);
		add(separator);
	}

}
