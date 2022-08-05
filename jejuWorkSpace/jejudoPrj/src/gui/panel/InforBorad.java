package gui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.InvisiableCheck;
import travel.TravelDao;
import travel.TravelService;
import travel.TravelVo;

public class InforBorad extends ImgPanel {
	
	private String no;

	private HomeBtn homeBtn;
	private BackBtn backBtn;
	private JLabel txtAsdfasdfads;
	private JLabel textField_1;
	private JLabel textField;
	private JLabel textField_2;
	private JLabel textField_3;
	private JLabel textField_4;
	private JLabel textField_5;
	private JLabel textField_6;
	private JLabel textField_7;
	private JLabel textField_8;
	private JLabel textField_9;
	private JLabel textField_10;
	private JTextArea textArea;
	private JCheckBox likeBtn;
	
	TravelService ts = new TravelService();
	TravelDao td = new TravelDao();

	public InforBorad() {
		super("inforBorad");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);

		txtAsdfasdfads = new JLabel();

		txtAsdfasdfads.setFont(new Font("굴림", Font.BOLD, 18));
		txtAsdfasdfads.setBounds(44, 120, 314, 40);
		add(txtAsdfasdfads);

		textField_1 = new JLabel();
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);

		textField_1.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_1.setBounds(399, 121, 63, 40);
		add(textField_1);

		textArea = new JTextArea();
		textArea.setFont(new Font("굴림", Font.PLAIN, 17));
		textArea.setBackground(new Color(250, 250, 250));
		textArea.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(null, "info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11)),
				javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)));

		textArea.setLineWrap(true);
		textArea.setEditable(false);

//		textArea.setBorder(new LineBorder(new Color(144, 168, 94), 1, true));
		textArea.setBounds(54, 183, 442, 143);
		add(textArea);

		textField = new JLabel();

		textField.setFont(new Font("굴림", Font.PLAIN, 15));
		textField.setBounds(126, 346, 348, 40);
		add(textField);

		textField_2 = new JLabel();

		textField_2.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_2.setBounds(126, 380, 348, 40);
		add(textField_2);

		textField_3 = new JLabel();

		textField_3.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_3.setBounds(143, 412, 331, 40);
		add(textField_3);

		textField_4 = new JLabel();

		textField_4.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_4.setBounds(143, 485, 80, 40);
		add(textField_4);

		textField_5 = new JLabel();

		textField_5.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_5.setBounds(324, 485, 80, 40);
		add(textField_5);

		textField_6 = new JLabel();

		textField_6.setFont(new Font("굴림", Font.PLAIN, 12));
		textField_6.setBounds(143, 564, 98, 40);
		add(textField_6);

		textField_7 = new JLabel();

		textField_7.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_7.setBounds(348, 564, 87, 40);
		add(textField_7);

		textField_8 = new JLabel();

		textField_8.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_8.setBounds(143, 633, 87, 40);
		add(textField_8);

		textField_9 = new JLabel();

		textField_9.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_9.setBounds(330, 633, 87, 40);
		add(textField_9);

		textField_10 = new JLabel();

		textField_10.setFont(new Font("굴림", Font.PLAIN, 15));
		textField_10.setBounds(126, 669, 268, 40);
		add(textField_10);
		
		likeBtn = new InvisiableCheck("좋아요");
		likeBtn.setLocation(26, 764);
		likeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!likeBtn.isSelected()) {
					likeBtn.setSelected(true);
					return;
				}else {
					ts.like_sum(no);
					textField_1.setText(Integer.toString(td.nowLike(no).getLike_cnt()));
					likeBtn.setSelected(true);
				}
			}
		});
		add(likeBtn);

	}

	public void set(TravelVo vo) {
		
		likeBtn.setSelected(false);
		
		no = vo.getTravel_no();
		
		txtAsdfasdfads.setText(vo.getTravel_name());
		textField_1.setText(Integer.toString(vo.getLike_cnt()));
		textArea.setText(vo.getTravel_info().replace("//.", "//./n"));
		textField.setText(vo.getPurpose());
		textField_2.setText(vo.getTravel_address());
		textField_3.setText(vo.getTravel_phone());
		textField_4.setText(vo.getActivity_yn());
		textField_5.setText(vo.getTravel_price());
		textField_6.setText(vo.getRecommend_type());

		String animal = "";
		if (vo.getAnimal_yn().equals("Y")) {
			animal = "가능";
		} else {
			animal = "불가능";
		}

		textField_7.setText(animal);
		textField_8.setText(vo.getOpen());
		textField_9.setText(vo.getClosed());
		textField_10.setText(vo.getClosed_day());

	}

	public HomeBtn getHomeBtn() {
		return homeBtn;
	}

	public BackBtn getBackBtn() {
		return backBtn;
	}

	public JLabel getTxtAsdfasdfads() {
		return txtAsdfasdfads;
	}

	public JLabel getTextField_1() {
		return textField_1;
	}

	public JLabel getTextField() {
		return textField;
	}

	public JLabel getTextField_2() {
		return textField_2;
	}

	public JLabel getTextField_3() {
		return textField_3;
	}

	public JLabel getTextField_4() {
		return textField_4;
	}

	public JLabel getTextField_5() {
		return textField_5;
	}

	public JLabel getTextField_6() {
		return textField_6;
	}

	public JLabel getTextField_7() {
		return textField_7;
	}

	public JLabel getTextField_8() {
		return textField_8;
	}

	public JLabel getTextField_9() {
		return textField_9;
	}

	public JLabel getTextField_10() {
		return textField_10;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JCheckBox getLikeBtn() {
		return likeBtn;
	}
	
	
}
