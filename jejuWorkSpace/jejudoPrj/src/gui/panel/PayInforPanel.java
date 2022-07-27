package gui.panel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import gui.button.BackBtn;
import gui.button.ButtonGroupController;
import gui.button.HomeBtn;
import gui.button.InvisiableRadio;
import gui.button.NextBtn;
import gui.field.InvisiableTextField;

public class PayInforPanel extends ImgPanel {
	
	private ButtonGroupController bgc = new ButtonGroupController();

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private InvisiableTextField usePoint;
	private JLabel sumPrice;
	private JLabel havePoint;
	private JLabel carPrice;
	private JLabel accomPrice;
	private JLabel flightPrice;
	private ButtonGroup g;
	private JRadioButton card;
	private JRadioButton cash;

	public PayInforPanel() {
		super("payInfor");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		flightPrice = new JLabel();
		flightPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		flightPrice.setFont(new Font("굴림", Font.PLAIN, 15));
		flightPrice.setBounds(327, 225, 124, 29);
		add(flightPrice);
		
		accomPrice = new JLabel();
		accomPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		accomPrice.setFont(new Font("굴림", Font.PLAIN, 15));
		accomPrice.setBounds(327, 279, 124, 29);
		add(accomPrice);
		
		carPrice = new JLabel();
		carPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		carPrice.setFont(new Font("굴림", Font.PLAIN, 15));
		carPrice.setBounds(327, 332, 124, 29);
		add(carPrice);
		
		havePoint = new JLabel();
		havePoint.setHorizontalAlignment(SwingConstants.RIGHT);
		havePoint.setFont(new Font("굴림", Font.PLAIN, 15));
		havePoint.setBounds(327, 532, 124, 29);
		add(havePoint);
		
		sumPrice = new JLabel();
		sumPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		sumPrice.setFont(new Font("굴림", Font.PLAIN, 10));
		sumPrice.setBounds(385, 441, 54, 18);
		add(sumPrice);
		
		usePoint = new InvisiableTextField(null);
		usePoint.setHorizontalAlignment(SwingConstants.RIGHT);
		usePoint.setBounds(224, 467, 214, 35);
		add(usePoint);
		
		g = new ButtonGroup();
		
		card =  new InvisiableRadio("카드");
		card.setLocation(60, 685);
		g.add(card);
		add(card);
		
		cash =  new InvisiableRadio("계좌이체");
		cash.setLocation(291, 685);
		g.add(cash);
		add(cash);
		
		

		
	}
	
	public void reset() {
		usePoint.setText(null);
		if(g!=null)g.clearSelection();
		
	}
	
	public String getCardorCash() {
		return bgc.getText(g);
	}

	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public NextBtn getNextBtn() {
		return nextBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public String getUsePoint() {
		return usePoint.getText();
	}

	public void setSumPrice(String s) {
		this.sumPrice.setText(s);
	}

	public void setHavePoint(String s) {
		this.havePoint.setText(s);
	}

	public void setCarPrice(String s) {
		this.carPrice.setText(s);
	}

	public void setAccomPrice(String s) {
		this.accomPrice.setText(s);
	}

	public void setFlightPrice(String s) {
		this.flightPrice.setText(s);
	}

	public String getSumPrice() {
		return sumPrice.getText();
	}

	public String getHavePoint() {
		return havePoint.getText();
	}

	public String getCarPrice() {
		return carPrice.getText();
	}

	public String getAccomPrice() {
		return accomPrice.getText();
	}

	public String getFlightPrice() {
		return flightPrice.getText();
	}
	
}
