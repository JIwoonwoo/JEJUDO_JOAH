package gui.button;

import javax.swing.JButton;

public class InvisiableButton extends JButton {
	
	public InvisiableButton() {
		setBorder(null);
		setContentAreaFilled(false);
		setFocusPainted(false);
	}

}
