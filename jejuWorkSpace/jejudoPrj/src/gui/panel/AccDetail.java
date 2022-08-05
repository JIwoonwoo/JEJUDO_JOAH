package gui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import accom.AccDto;
import gui.button.BackBtn;
import gui.button.HomeBtn;
import gui.button.ImgButton;
import gui.button.InvisiableCheck;

public class AccDetail extends ImgPanel {
	
	private int no;

	private HomeBtn homeBtn;
	private BackBtn backBtn;
//	private JButton updateBtn;
	private JButton deleteBtn;

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1_1_1_1_1_1_1_1;
	private JLabel lblNewLabel_1_1_1_1_1_1_1;
	private JLabel lblNewLabel_1_1_1_1_1_1;
	private JLabel lblNewLabel_1_1_1_1_1;
	private JLabel lblNewLabel_1_1_1_1;
	private JLabel lblNewLabel_1_1_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1;
	private JCheckBox pool;
	private JCheckBox animal;

	public AccDetail() {
		super("accDetail");

		homeBtn = new HomeBtn();
		add(homeBtn);

		backBtn = new BackBtn();
		add(backBtn);

		pool = new InvisiableCheck("pool");
		pool.setLocation(61, 481);
		pool.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pool.isSelected()) {
					pool.setSelected(false);
					return;
				} else {
					pool.setSelected(true);
				}
			}
		});
		add(pool);

		animal = new InvisiableCheck("animal");
		animal.setLocation(265, 481);
		animal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (animal.isSelected()) {
					animal.setSelected(false);
					return;
				} else {
					animal.setSelected(true);
				}
			}
		});
		add(animal);

//		updateBtn = new ImgButton("예약변경");
//		add(updateBtn);
//		
		deleteBtn = new ImgButton("예약취소");
		deleteBtn.setLocation(0, 818);
		deleteBtn.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		add(deleteBtn);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(49, 136, 352, 40);
		add(lblNewLabel);

		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(115, 196, 352, 40);
		add(lblNewLabel_1);

		lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(142, 246, 109, 40);
		add(lblNewLabel_1_1);

		lblNewLabel_1_1_1 = new JLabel();
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(327, 246, 109, 40);
		add(lblNewLabel_1_1_1);

		lblNewLabel_1_1_1_1 = new JLabel();
		lblNewLabel_1_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(104, 291, 109, 40);
		add(lblNewLabel_1_1_1_1);

		lblNewLabel_1_1_1_1_1 = new JLabel();
		lblNewLabel_1_1_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1.setBounds(357, 291, 109, 40);
		add(lblNewLabel_1_1_1_1_1);

		lblNewLabel_1_1_1_1_1_1 = new JLabel();
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1_1.setBounds(142, 341, 166, 40);
		add(lblNewLabel_1_1_1_1_1_1);

		lblNewLabel_1_1_1_1_1_1_1 = new JLabel();
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(129, 409, 127, 40);
		add(lblNewLabel_1_1_1_1_1_1_1);

		lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel();
		lblNewLabel_1_1_1_1_1_1_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(340, 409, 127, 40);
		add(lblNewLabel_1_1_1_1_1_1_1_1);

	}

	public void set(AccDto vo) {
		no = vo.getReserveNo();
		System.out.println(no);
		lblNewLabel.setText(vo.getAccomname());
		lblNewLabel_1.setText(vo.getAddress());
		lblNewLabel_1_1.setText(vo.getRoomname());
		lblNewLabel_1_1_1.setText(Integer.toString(vo.getCapacity()));
		lblNewLabel_1_1_1_1.setText(vo.getRoomview());
		lblNewLabel_1_1_1_1_1.setText(vo.getAround());
		lblNewLabel_1_1_1_1_1_1.setText(Integer.toString(vo.getPrice()));
		lblNewLabel_1_1_1_1_1_1_1.setText(vo.getCheckin().substring(0, 10));
		lblNewLabel_1_1_1_1_1_1_1_1.setText(vo.getCheckout().substring(0, 10));

		System.out.println(vo.getAnimalYN());

		if (vo.getAnimalYN().equals("Y")) {
			animal.setSelected(true);

		}

		if (vo.getPoolableYN().equals("Y")) {
			pool.setSelected(true);

		}
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
