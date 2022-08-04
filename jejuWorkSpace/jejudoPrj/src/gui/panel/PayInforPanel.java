package gui.panel;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.button.BackBtn;
import gui.button.ButtonGroupController;
import gui.button.HomeBtn;
import gui.button.InvisiableRadio;
import gui.button.NextBtn;
import gui.document.LengthRestrictedDocument;
import gui.field.InvisiableTextField;
import payment.PayVo;

public class PayInforPanel extends ImgPanel {

	private ButtonGroupController bgc = new ButtonGroupController();

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private InvisiableTextField usePoint;
	private JLabel havePoint;
	private JLabel sumPrice;
	private JLabel carPrice;
	private JLabel accomPrice;
	private JLabel flightPrice;
	private ButtonGroup g;
	private JRadioButton card;
	private JRadioButton cash;

	private PayVo vo;
	private int total;

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

		sumPrice = new JLabel();
		sumPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		sumPrice.setFont(new Font("굴림", Font.PLAIN, 18));
		sumPrice.setBounds(274, 526, 177, 41);
		add(sumPrice);

		havePoint = new JLabel();
		havePoint.setHorizontalAlignment(SwingConstants.RIGHT);
		havePoint.setFont(new Font("굴림", Font.PLAIN, 10));
		havePoint.setBounds(385, 441, 54, 18);
		add(havePoint);

		usePoint = new InvisiableTextField(null);
		usePoint.setHorizontalAlignment(SwingConstants.RIGHT);
		usePoint.setBounds(224, 467, 214, 35);
		usePoint.setDocument(new LengthRestrictedDocument(8));
		usePoint.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				int point = 0;
				if(usePoint.getText().equals("")) {
					
				}else {
					point = Integer.parseInt(usePoint.getText());
				}
				String sum = Integer.toString((total - point));
				sumPrice.setText(sum);
			}

			public void removeUpdate(DocumentEvent e) {
				int point = 0;
				if(usePoint.getText().equals("")) {
					
				}else {
					point = Integer.parseInt(usePoint.getText());
				}
				String sum = Integer.toString((total - point));
				sumPrice.setText(sum);
			}

			public void insertUpdate(DocumentEvent e) {
				int point = 0;
				if(usePoint.getText().equals("")) {
					
				}else {
					point = Integer.parseInt(usePoint.getText());
				}
				String sum = Integer.toString((total - point));
				sumPrice.setText(sum);
			}
		});
		usePoint.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(Integer.parseInt(usePoint.getText())>Integer.parseInt(havePoint.getText())) {
					usePoint.setText("0");
					int point = 0;
					if(usePoint.getText().equals("")) {
						
					}else {
						point = Integer.parseInt(usePoint.getText());
					}
					String sum = Integer.toString((total - point));
					sumPrice.setText(sum);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		add(usePoint);

		g = new ButtonGroup();

		card = new InvisiableRadio("카드");
		card.setText("1");
		card.setLocation(60, 685);
		g.add(card);
		add(card);

		cash = new InvisiableRadio("계좌이체");
		cash.setText("2");
		cash.setLocation(291, 685);
		g.add(cash);
		add(cash);

	}
	
	private void setUsePoint() {
		usePoint.setText("0");
	}

	public void reset() {
		total = Integer.parseInt(flightPrice.getText()) + Integer.parseInt(carPrice.getText())
				+ Integer.parseInt(accomPrice.getText());
		sumPrice.setText(Integer.toString(total));
		usePoint.setText(null);
		if (g != null)
			g.clearSelection();

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

	public int getUsePoint() {
		if(usePoint.getText().equals("")) {
			return 0;
		}
		return Integer.parseInt(usePoint.getText());
	}

	public void setSumPrice(int s) {
		this.sumPrice.setText(Integer.toString(s));
	}

	public void setHavePoint(int s) {
		this.havePoint.setText(Integer.toString(s));
	}

	public void setCarPrice(int s) {
		this.carPrice.setText(Integer.toString(s));
	}

	public void setAccomPrice(int s) {
		this.accomPrice.setText(Integer.toString(s));
	}

	public void setFlightPrice(int s) {
		this.flightPrice.setText(Integer.toString(s));
	}

	public int getSumPrice() {
		return Integer.parseInt(sumPrice.getText());
	}

	public int getHavePoint() {
		return Integer.parseInt(havePoint.getText());
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

	public PayVo getVo() {
		return vo;
	}

	public void setVo(PayVo vo) {
		this.vo = vo;
	}

}
