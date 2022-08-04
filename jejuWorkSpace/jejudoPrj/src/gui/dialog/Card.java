package gui.dialog;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.PlainDocument;

import gui.button.ImgButton;
import gui.document.MyIntFilter;
import gui.panel.ImgPanel;
import javax.swing.JTextField;

public class Card extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	public Card(Frame parent, String name, String msg) {
		super(parent,name,true);
	    Point loc = parent.getLocation();
	    setLocation(loc.x+225,loc.y+450);
	    
//		JPanel panel = new ImgPanel(new ImageIcon("D:/miniPrj/jejuWorkSpace/jejudoPrj/image/page/card.jpg").getImage());
		JPanel panel = new ImgPanel(new ImageIcon("././image/page/card.jpg").getImage());
//		JPanel panel = new ImgPanel("labelDialog");
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new ImgButton("확인");
		btnNewButton.setLocation(87, 133);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(12, 34, 54, 21);
		panel.add(textField);
		PlainDocument doc = (PlainDocument) textField.getDocument();
		doc.setDocumentFilter(new MyIntFilter());
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(87, 34, 54, 21);
		PlainDocument doc1 = (PlainDocument) textField_1.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(159, 34, 54, 21);
		PlainDocument doc2 = (PlainDocument) textField_2.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(230, 34, 54, 21);
		PlainDocument doc3 = (PlainDocument) textField_3.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(159, 95, 54, 21);
		PlainDocument doc4 = (PlainDocument) textField_4.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(25, 95, 23, 21);
		PlainDocument doc5 = (PlainDocument) textField_5.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(60, 95, 23, 21);
		PlainDocument doc6 = (PlainDocument) textField_6.getDocument();
		doc6.setDocumentFilter(new MyIntFilter());
		panel.add(textField_6);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void run() {
		this.setVisible(true);
	}
}
