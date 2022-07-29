package gui.field;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class InvisiableTextField extends JTextField {

	public InvisiableTextField(String s) {
		super(s);
		setColumns(20);
		setFont(new Font("굴림", Font.PLAIN, 15));
		setOpaque(false);
		setBorder(null);

		addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				setBorder(null);
			}

			@Override
			public void focusGained(FocusEvent e) {
				setBorder(new LineBorder(new Color(144, 168, 94), 1, true));
			}
		});

	}
}
