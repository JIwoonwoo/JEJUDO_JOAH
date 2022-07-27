package gui.panel;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import gui.button.BackBtn;
import gui.button.ButtonGroupController;
import gui.button.HomeBtn;
import gui.button.InvisiableCheck;
import gui.button.NextBtn;
import gui.document.MyIntFilter;
import gui.field.InvisiableTextField;

public class ReservedAccomPanel extends ImgPanel {
	
	private ButtonGroupController bgc = new ButtonGroupController();

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JSpinner contPerson;
	private InvisiableTextField goDay;
	private InvisiableTextField backDay;
	private InvisiableTextField minMoney;
	private InvisiableTextField maxMoney;
	private JCheckBox hotelBtn;
	private JCheckBox gestBtn;
	private ButtonGroup g;

	public ReservedAccomPanel() {
		super("reservedAccom");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);

		contPerson = new JSpinner();
		contPerson.setFont(new Font("굴림", Font.PLAIN, 15));
		contPerson.setBounds(159, 420, 324, 41);
		contPerson.setOpaque(false);
		contPerson.setBorder(null);
		add(contPerson);

		goDay = new InvisiableTextField("YYYY-MM-DD");
		goDay.setBounds(171, 199, 312, 41);
		add(goDay);

		backDay = new InvisiableTextField("YYYY-MM-DD");
		backDay.setBounds(171, 291, 312, 41);
		add(backDay);

		minMoney = new InvisiableTextField("0");
		minMoney.setBounds(152, 707, 136, 41);
		minMoney.setHorizontalAlignment(JTextField.RIGHT);

		PlainDocument doc = (PlainDocument) minMoney.getDocument();
		doc.setDocumentFilter(new MyIntFilter());

		add(minMoney);

		maxMoney = new InvisiableTextField("0");
		maxMoney.setBounds(336, 707, 128, 41);
		maxMoney.setHorizontalAlignment(JTextField.RIGHT);

		PlainDocument doc2 = (PlainDocument) maxMoney.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());

		add(maxMoney);

		g = new ButtonGroup();

		hotelBtn = new InvisiableCheck("호텔");
		hotelBtn.setLocation(55, 528);
		g.add(hotelBtn);
		add(hotelBtn);

		gestBtn = new InvisiableCheck("게하");
		gestBtn.setLocation(287, 528);
		g.add(gestBtn);
		add(gestBtn);

	}

	public void reset() {
		contPerson.setValue(Integer.valueOf(0));
		goDay.setText("YYYY-MM-DD");
		backDay.setText("YYYY-MM-DD");
		minMoney.setText("0");
		maxMoney.setText("0");
		if(g!=null)g.clearSelection();
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

	public String getGoDay() {
		return goDay.getText();
	}

	public String getBackDay() {
		return backDay.getText();
	}

	public int getMinMoney() {
		return Integer.parseInt(minMoney.getText());
	}

	public int getMaxMoney() {
		return Integer.parseInt(maxMoney.getText());
	}

	public int getContPerson() {
		return (Integer) contPerson.getValue();
	}

	public JCheckBox getHotelBtn() {
		return hotelBtn;
	}

	public JCheckBox getGestBtn() {
		return gestBtn;
	}

	public String getHotel() {
		return bgc.getText(g);
	}

}
