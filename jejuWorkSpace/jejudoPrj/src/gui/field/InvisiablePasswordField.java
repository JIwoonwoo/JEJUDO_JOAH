package gui.field;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class InvisiablePasswordField extends JPasswordField {
	
	public InvisiablePasswordField(String s) {
		super(s);
		setFont(new Font("굴림", Font.PLAIN, 15));
		setOpaque(false);
		setBorder(null);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText(null);
			}
		});
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
