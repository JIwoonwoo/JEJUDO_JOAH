package gui.panel;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import car.ReserveVo;

public class CarDetail extends ImgPanel {
	
	private ReserveVo vo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_1_4;
	private JLabel lblNewLabel_1_5;
	private JLabel lblNewLabel_1_6;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_9;
	
	public CarDetail(){
		super("carDetail");
		
		lblNewLabel = new JLabel(Integer.toString(vo.getReserveNo()));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(411, 147, 62, 32);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(vo.getReserveDate().substring(2,10));
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(159, 198, 98, 32);
		add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel(vo.getName());
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(173, 269, 98, 32);
		add(lblNewLabel_1_1);
		
		lblNewLabel_1_2 = new JLabel(vo.getCarUq());
		lblNewLabel_1_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(173, 310, 98, 32);
		add(lblNewLabel_1_2);
		
		lblNewLabel_1_3 = new JLabel(vo.getSize());
		lblNewLabel_1_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(345, 269, 98, 32);
		add(lblNewLabel_1_3);
		
		lblNewLabel_1_4 = new JLabel(vo.getFuel());
		lblNewLabel_1_4.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(345, 310, 98, 32);
		add(lblNewLabel_1_4);
		
		lblNewLabel_1_5 = new JLabel(vo.getRentalDate());
		lblNewLabel_1_5.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_5.setBounds(138, 411, 98, 32);
		add(lblNewLabel_1_5);
		
		lblNewLabel_1_6 = new JLabel(vo.getReturnDate());
		lblNewLabel_1_6.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(345, 411, 98, 32);
		add(lblNewLabel_1_6);
		
		lblNewLabel_1_7 = new JLabel(vo.getDayPrice());
		lblNewLabel_1_7.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_7.setBounds(145, 488, 98, 32);
		add(lblNewLabel_1_7);
		
		lblNewLabel_1_9 = new JLabel(vo.getInsurance());
		lblNewLabel_1_9.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_9.setBounds(145, 530, 98, 32);
		add(lblNewLabel_1_9);
	}
}
