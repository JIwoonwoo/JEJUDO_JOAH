package gui.panel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.NextBtn;
import gui.document.MyIntFilter;
import gui.field.InvisiableTextField;

public class ReservedFlightPanel extends ImgPanel {

	private HomeBtn homeBtn;
	private NextBtn nextBtn;
	private BackBtn backBtn;
	private JTextField goDay;
	private JTextField backDay;
	private JTextField minMoney;
	private JTextField maxMoney;
	private JSpinner contPerson;
	private JComboBox<String> comboBox;

	public ReservedFlightPanel() {
		super("reservedFlight");

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
		goDay.setBounds(171, 192, 312, 41);
		add(goDay);

		backDay = new InvisiableTextField("YYYY-MM-DD");
		backDay.setBounds(171, 286, 312, 41);
		add(backDay);

		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("굴림", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "인천공항", "김포공항" }));
		comboBox.setBounds(159, 517, 324, 41);
		comboBox.setOpaque(false);
		comboBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JComponent result = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				result.setOpaque(false);
				return result;
			}
		});
		add(comboBox);

		minMoney = new InvisiableTextField("0");
		minMoney.setBounds(152, 645, 136, 41);
		minMoney.setHorizontalAlignment(JTextField.RIGHT);

		PlainDocument doc = (PlainDocument) minMoney.getDocument();
		doc.setDocumentFilter(new MyIntFilter());

		add(minMoney);

		maxMoney = new InvisiableTextField("0");
		maxMoney.setBounds(336, 645, 128, 41);
		maxMoney.setHorizontalAlignment(JTextField.RIGHT);

		PlainDocument doc2 = (PlainDocument) maxMoney.getDocument();
		doc.setDocumentFilter(new MyIntFilter());

		add(maxMoney);

	}

	public void reset() {
		contPerson.setValue(Integer.valueOf(0));
		goDay.setText("YYYY-MM-DD");
		backDay.setText("YYYY-MM-DD");
		minMoney.setText("0");
		maxMoney.setText("0");
		comboBox.setSelectedItem("인천공항");
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

	public String getComboBox() {
		return (String) comboBox.getSelectedItem();
	}

}
