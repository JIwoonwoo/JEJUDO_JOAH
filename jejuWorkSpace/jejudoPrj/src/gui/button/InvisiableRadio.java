package gui.button;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class InvisiableRadio extends JRadioButton {
	
	public InvisiableRadio(String button) {
		super(button);
//		ImageIcon img = new ImageIcon("D:\\dev\\miniPrj\\jejuWorkSpace\\jejudoPrj\\image\\button\\"+button+".png"); //절대경로
		ImageIcon img = new ImageIcon(".\\.\\image\\button\\"+button+".png"); //상대경로
//		ImageIcon imgSelect = new ImageIcon("D:\\dev\\miniPrj\\jejuWorkSpace\\jejudoPrj\\image\\button\\"+button+"Select.png"); //절대경로
		ImageIcon imgSelect = new ImageIcon(".\\.\\image\\button\\"+button+"Select.png"); //상대경로
		
		setSize(new Dimension(img.getIconWidth(),img.getIconHeight()));
		setIcon(imgSelect);
		setSelectedIcon(img);
		
		setBorder(null);
		setContentAreaFilled(false);
		setFocusPainted(false);
	}

}
