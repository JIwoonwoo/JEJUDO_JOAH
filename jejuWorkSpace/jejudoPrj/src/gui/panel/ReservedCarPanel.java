package gui.panel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import gui.button.BackBtn;
import gui.button.ButtonGroupController;
import gui.button.HomeBtn;
import gui.button.InvisiableRadio;
import gui.button.NextBtn;
import gui.document.MyIntFilter;
import gui.field.InvisiableTextField;

public class ReservedCarPanel extends ImgPanel {
	
	private ButtonGroupController bgc = new ButtonGroupController();

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JSpinner contPerson;
	private InvisiableTextField goDay;
	private InvisiableTextField backDay;
	private InvisiableTextField minMoney;
	private InvisiableTextField maxMoney;
	private ButtonGroup sizeG;
	private InvisiableRadio carSize1;
	private InvisiableRadio carSize2;
	private InvisiableRadio carSize3;
	private ButtonGroup energyG;
	private InvisiableRadio carEnergy1;
	private InvisiableRadio carEnergy2;
	private InvisiableRadio carEnergy3;

	public ReservedCarPanel() {
		super("reservedCar");

		homeBtn = new HomeBtn();
		add(homeBtn);

		nextBtn = new NextBtn();
		add(nextBtn);

		backBtn = new BackBtn();
		add(backBtn);
		
		contPerson = new JSpinner();
		contPerson.setFont(new Font("굴림", Font.PLAIN, 15));
		contPerson.setBounds(159, 407, 324, 41);
		contPerson.setOpaque(false);
		contPerson.setBorder(null);
		add(contPerson);

		goDay = new InvisiableTextField("YYYY-MM-DD");
		goDay.setBounds(171, 196, 312, 41);
		add(goDay);

		backDay = new InvisiableTextField("YYYY-MM-DD");
		backDay.setBounds(171, 290, 312, 41);
		add(backDay);

		minMoney = new InvisiableTextField("0");
		minMoney.setBounds(159, 724, 136, 41);
		minMoney.setHorizontalAlignment(JTextField.RIGHT);

		PlainDocument doc = (PlainDocument) minMoney.getDocument();
		doc.setDocumentFilter(new MyIntFilter());

		add(minMoney);

		maxMoney = new InvisiableTextField("0");
		maxMoney.setBounds(335, 724, 128, 41);
		maxMoney.setHorizontalAlignment(JTextField.RIGHT);

		PlainDocument doc2 = (PlainDocument) maxMoney.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());

		add(maxMoney);
		
		sizeG = new ButtonGroup();
		
		carSize1 = new InvisiableRadio("소형");
		sizeG.add(carSize1);
		add(carSize1);
		
		carSize2 = new InvisiableRadio("중형");
		sizeG.add(carSize2);
		add(carSize2);
		
		carSize3 = new InvisiableRadio("대형");
		sizeG.add(carSize3);
		add(carSize3);
		
		energyG = new ButtonGroup();
		
		carEnergy1 = new InvisiableRadio("휘발유");
		energyG.add(carEnergy1);
		add(carEnergy1);
		
		carEnergy2 = new InvisiableRadio("경유");
		energyG.add(carEnergy2);
		add(carEnergy2);
		
		carEnergy3 = new InvisiableRadio("전기");
		energyG.add(carEnergy3);
		add(carEnergy3);
		
		carEnergy3.setLocation(361, 598);
		carEnergy2.setLocation(198, 598);
		carEnergy1.setLocation(34, 598);
		carSize3.setLocation(361, 509);
		carSize2.setLocation(198, 509);
		carSize1.setLocation(34, 509);
			
	}
	
	public void reset() {
		contPerson.setValue(Integer.valueOf(0));
		goDay.setText("YYYY-MM-DD");
		backDay.setText("YYYY-MM-DD");
		minMoney.setText("0");
		maxMoney.setText("0");
		if(energyG!=null)energyG.clearSelection();
		if(sizeG!=null)sizeG.clearSelection();
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

	public int getContPerson() {
		return (Integer) contPerson.getValue();
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
	
	public String getCarSize() {
		return bgc.getText(sizeG);
	}
	
	public String getEnergy() {
		return bgc.getText(energyG);
	}

}
