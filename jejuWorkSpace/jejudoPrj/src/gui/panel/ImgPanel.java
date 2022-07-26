package gui.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImgPanel extends JPanel {

	private Image img;

	public ImgPanel(String page) {
//		this.img = new ImageIcon("D:\\dev\\miniPrj\\jejuWorkSpace\\jejudoPrj\\image\\page\\" + page + ".jpg").getImage(); // 절대경로
//		this.img = new ImageIcon("D:\\miniPrj\\jejuWorkSpace\\jejudoPrj\\image\\page\\" + page + ".jpg").getImage(); // 절대경로
		this.img = new ImageIcon(".\\.\\image\\page\\"+page+".jpg").getImage(); //상대경로
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public ImgPanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public int getWidth() {
		return img.getWidth(null);
	}

	public int getHeight() {
		return img.getHeight(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
