package gui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import flight3.Flight_Vo_MyFlight;
import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;
import payment.PayService;
import payment.PayVo;

public class FlightDetail extends ImgPanel {
	
	private int no;

	private HomeBtn homeBtn;
	private BackBtn backBtn;
//	private JButton updateBtn;
	private JButton deleteBtn;

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;

	public FlightDetail() {
		super("flightDetail");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);

//		updateBtn = new ImgButton("예약변경");
//		add(updateBtn);
//		
		deleteBtn = new ImgButton("예약취소");
		deleteBtn.setLocation(0, 818);
		deleteBtn.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		add(deleteBtn);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(129, 142, 259, 40);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(151, 254, 102, 40);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(339, 254, 102, 40);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(151, 304, 102, 40);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(339, 304, 102, 40);
		add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(151, 429, 102, 40);
		add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel();
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(151, 479, 102, 40);
		add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel();
		lblNewLabel_7.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(339, 429, 102, 40);
		add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel();
		lblNewLabel_8.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(339, 479, 102, 40);
		add(lblNewLabel_8);



	}

	public void set(Flight_Vo_MyFlight vo) {
		no = vo.getReserveNo();
		System.out.println(no);
		lblNewLabel.setText(vo.getReservDate().substring(2,10));
		lblNewLabel_1.setText(vo.getDepartureDate().substring(2,10));
		lblNewLabel_2.setText(vo.getDepartureTime());
		lblNewLabel_3.setText(vo.getFlightName());
		lblNewLabel_4.setText(vo.getAirline());
		lblNewLabel_5.setText(vo.getReturnDate().substring(2,10));
		lblNewLabel_6.setText(vo.getReturnFlightName());
		lblNewLabel_7.setText(vo.getReturnTime());
		lblNewLabel_8.setText(vo.getReturnAirline());
	}

	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
	}
	
	
	
	
}
