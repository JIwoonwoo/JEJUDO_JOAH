package gui.button;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class ImgButton extends InvisiableButton{

	public ImgButton(String button) {
		super();
		ImageIcon img = new ImageIcon("D:\\miniPrj\\jejuWorkSpace\\jejudoPrj\\image\\button\\"+button+".png"); //절대경로
//		ImageIcon img = new ImageIcon(".\\.\\image\\button\\"+button+".png"); //상대경로
		ImageIcon imgSelect = new ImageIcon("D:\\miniPrj\\jejuWorkSpace\\jejudoPrj\\image\\button\\"+button+"Select.png"); //절대경로
//		ImageIcon imgSelect = new ImageIcon(".\\.\\image\\button\\"+button+"Select.png"); //상대경로
		
		setSize(new Dimension(img.getIconWidth(),img.getIconHeight()));
		
		setIcon(imgSelect);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setIcon(img);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setIcon(imgSelect);
			}
		});
	}
	
}
