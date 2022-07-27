package gui.button;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class ButtonGroupController {
	
	public String getText(ButtonGroup g) {
		for (Enumeration<AbstractButton> buttons = g.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

}
