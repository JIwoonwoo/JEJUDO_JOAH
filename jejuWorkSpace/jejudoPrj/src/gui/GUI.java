package gui;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;

import gui.dialog.PopUpDialog;
import gui.dialog.TextFieldDialog;
import gui.panel.FaveratePanel;
import gui.panel.FindIdPanel;
import gui.panel.FindPwdPanel;
import gui.panel.JoinPanel;
import gui.panel.LoginPanel;
import gui.panel.MainPanel;
import gui.panel.PayInforPanel;
import gui.panel.ReservInforPanel;
import gui.panel.ReservedAccomPanel;
import gui.panel.ReservedCar2Panel;
import gui.panel.ReservedCarPanel;
import gui.panel.ReservedFlightPanel;
import gui.panel.ReservedPanel;
import gui.panel.SugTripPanel;
import main.Main;
import member.MemberService;
import member.MemberVo;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("JEJUDO JOAH");
//		frame.setIconImage(new ImageIcon("D:\\miniPrj\\jejuWorkSpace\\jejudoPrj\\image\\icon.png").getImage());
		frame.setIconImage(new ImageIcon(".\\.\\image\\icon.png").getImage());

		LoginPanel loginPanel = new LoginPanel();
		FaveratePanel faveratePanel = new FaveratePanel();
		MainPanel mainPanel = new MainPanel();
		JoinPanel joinPanel = new JoinPanel();
		FindIdPanel findIdPanel = new FindIdPanel();
		FindPwdPanel findPwdPanel = new FindPwdPanel();
		ReservedPanel reservedPanel = new ReservedPanel();
		ReservedFlightPanel reservedFlightPanel = new ReservedFlightPanel();
		ReservedAccomPanel reservedAccomPanel = new ReservedAccomPanel();
		ReservedCarPanel reservedCarPanel = new ReservedCarPanel();
		ReservedCar2Panel reservedCar2Panel = new ReservedCar2Panel();
		PayInforPanel payInforPanel = new PayInforPanel();
		ReservInforPanel reservInforPanel = new ReservInforPanel();
		SugTripPanel sugTripPanel = new SugTripPanel();

		MemberService ms = new MemberService();

		/** 추천관광지 **/
		frame.getContentPane().add(sugTripPanel);
		sugTripPanel.setVisible(false);

		// 홈
		sugTripPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sugTripPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		// 뒤로가기
		sugTripPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sugTripPanel.setVisible(false);
				reservInforPanel.setVisible(true);
			}
		});
		// 다음
		sugTripPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sugTripPanel.setVisible(false);
				mainPanel.setVisible(true);
				// 결제 완료!
			}
		});

		JTable tourism = sugTripPanel.getTourism();
		tourism.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					System.out.println(tourism.getValueAt(tourism.getSelectedRow(), 0).toString());
				}
			}
		});

		JTable activity = sugTripPanel.getActivity();
		activity.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					System.out.println(activity.getValueAt(activity.getSelectedRow(), 0).toString());
				}
			}
		});

		JTable restaurant = sugTripPanel.getRestaurant();
		restaurant.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					System.out.println(restaurant.getValueAt(restaurant.getSelectedRow(), 0).toString());
				}
			}
		});

		/** 예약정보 **/
		frame.getContentPane().add(reservInforPanel);
		reservInforPanel.setVisible(false);

		// 홈
		reservInforPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservInforPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		// 다음
		reservInforPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservInforPanel.setVisible(false);
				// 테이블 데이터 셋
				sugTripPanel.setVisible(true);
			}
		});

		/** 결제정보 **/
		frame.getContentPane().add(payInforPanel);
		payInforPanel.setVisible(false);

		// 홈
		payInforPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				payInforPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		// 뒤로가기
		payInforPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				payInforPanel.setVisible(false);
				reservedCar2Panel.setVisible(true);
			}
		});
		// 다음
		payInforPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				payInforPanel.setVisible(false);
				reservInforPanel.reset();
				reservInforPanel.setVisible(true);
				// 결제 완료!
			}
		});

		/** 예약페이지 랜트카2 **/// 체크박스 만들어야함..
		frame.getContentPane().add(reservedCar2Panel);
		reservedCar2Panel.setVisible(false);

		// 홈
		reservedCar2Panel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedCar2Panel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		// 뒤로가기
		reservedCar2Panel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedCar2Panel.setVisible(false);
				reservedCarPanel.setVisible(true);
			}
		});
		// 다음
		reservedCar2Panel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedCar2Panel.setVisible(false);

				payInforPanel.reset();
				payInforPanel.setFlightPrice(null);
				payInforPanel.setAccomPrice(null);
				payInforPanel.setCarPrice(null);
				payInforPanel.setHavePoint(null);
				payInforPanel.setSumPrice(null);

				payInforPanel.setVisible(true);
			}
		});

		/** 예약페이지 랜트카 **/
		frame.getContentPane().add(reservedCarPanel);
		reservedCarPanel.setVisible(false);

		// 홈
		reservedCarPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedCarPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		// 뒤로가기
		reservedCarPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedCarPanel.setVisible(false);
				reservedAccomPanel.setVisible(true);
			}
		});
		// 다음
		reservedCarPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedCarPanel.getGoDay();
				reservedCarPanel.getBackDay();
				reservedCarPanel.getContPerson();
				reservedCarPanel.getMinMoney();
				reservedCarPanel.getMaxMoney();
				reservedCarPanel.getCarSize();
				reservedCarPanel.getEnergy();

				reservedCarPanel.setVisible(false);
				reservedCar2Panel.reset();
				reservedCar2Panel.setVisible(true);
			}
		});

		/** 예약페이지 숙박 **/
		frame.getContentPane().add(reservedAccomPanel);
		reservedAccomPanel.setVisible(false);

		// 홈
		reservedAccomPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedAccomPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		// 뒤로가기
		reservedAccomPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedAccomPanel.setVisible(false);
				reservedFlightPanel.setVisible(true);
			}
		});
		// 다움
		reservedAccomPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedAccomPanel.getGoDay();
				reservedAccomPanel.getBackDay();
				reservedAccomPanel.getContPerson();
				reservedAccomPanel.getMinMoney();
				reservedAccomPanel.getMaxMoney();
				reservedAccomPanel.getHotel();

				reservedAccomPanel.setVisible(false);
				reservedCarPanel.reset();
				reservedCarPanel.setVisible(true);
			}
		});

		/** 예약페이지 비행기 **/
		frame.getContentPane().add(reservedFlightPanel);
		reservedFlightPanel.setVisible(false);

		reservedFlightPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedFlightPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});

		reservedFlightPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedFlightPanel.setVisible(false);
				reservedPanel.setVisible(true);
			}
		});

		reservedFlightPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedFlightPanel.getGoDay();
				reservedFlightPanel.getBackDay();
				reservedFlightPanel.getContPerson();
				reservedFlightPanel.getComboBox();
				reservedFlightPanel.getMinMoney();
				reservedFlightPanel.getMaxMoney();

				reservedFlightPanel.setVisible(false);
				reservedAccomPanel.reset();
				reservedAccomPanel.setVisible(true);
			}
		});

		/** 예약페이지 1 **/
		frame.getContentPane().add(reservedPanel);
		reservedPanel.setVisible(false);

		// 뒤로가기
		reservedPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedPanel.setVisible(false);
				faveratePanel.setVisible(true);
			}
		});
		// 홈으로
		reservedPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		// 일반예약
		reservedPanel.getNomalBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reservedPanel.setVisible(false);
				reservedFlightPanel.reset();
				reservedFlightPanel.setVisible(true);

			}
		});
		// 패키지 예약
		reservedPanel.getPackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		/** 설문페이지 **/
		frame.getContentPane().add(faveratePanel);
		faveratePanel.setVisible(false);
		// 뒤로가기
		faveratePanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				faveratePanel.setVisible(false);
				mainPanel.setVisible(true);

			}
		});
		// 홈으로
		faveratePanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				faveratePanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		// 예약하기
		faveratePanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(faveratePanel.getQ1());
				System.out.println(faveratePanel.getQ2());
				System.out.println(faveratePanel.getQ3());
				System.out.println(faveratePanel.getQ4());
				System.out.println(faveratePanel.getQ5());

				faveratePanel.setVisible(false);
				reservedPanel.setVisible(true);

			}
		});

		/** 메인페이지 **/
		frame.getContentPane().add(mainPanel);
		mainPanel.setVisible(false);

		// 예약하기 페이지 이동
		mainPanel.getGotoReserveBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				faveratePanel.reset();
				faveratePanel.setVisible(true);

			}
		});
		// 여행정보 이동
		mainPanel.getGotoInfoBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);

			}
		});
		// 마이페이지 이동
		mainPanel.getGotoMyPageBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);

			}
		});
		// 고객센터 이동
		mainPanel.getGotoServiceBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);

			}
		});
		// 로그아웃
		mainPanel.getLogoutBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.loginNo = 0;
				mainPanel.setVisible(false);
				loginPanel.reset();
				loginPanel.setVisible(true);

			}
		});

		/** 회원가입페이지 **/
		frame.getContentPane().add(joinPanel);
		joinPanel.setVisible(false);

		// 아이디중복확인
		joinPanel.getCheckIdBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (ms.checkId(joinPanel.getId())) {
					
					if (ms.checkDubleId(joinPanel.getId())) {
						PopUpDialog dialog = new PopUpDialog(frame, "아이디 중복확인", "생성가능한 아이디 입니다.");
						dialog.run();
						joinPanel.getTextFieldId().setEnabled(false);
						joinPanel.getCheckIdBtn().setSelected(true);
					} else {
						PopUpDialog dialog = new PopUpDialog(frame, "아이디 중복확인", "사용 불가능한 아이디 입니다.");
						dialog.run();
						joinPanel.getCheckIdBtn().setSelected(false);
					}
					
				} else {
					PopUpDialog dialog = new PopUpDialog(frame, "아이디 중복확인", "형식을 맞춰주시기 바랍니다.");
					dialog.run();
					joinPanel.getCheckIdBtn().setSelected(false);
				}
			}
		});
		// 비밀확인
		joinPanel.getCheckPwdBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (ms.checkPwd(joinPanel.getPwd())) {
					TextFieldDialog dialog = new TextFieldDialog(frame, "비밀번호 확인", "한번 더 입력해주세요");
					
					if (joinPanel.getPwd().equals(dialog.run())) {
						PopUpDialog dialog2 = new PopUpDialog(frame, "비밀번호 확인", "확인되었습니다.");
						dialog2.run();
						joinPanel.getTextFieldPwd().setEnabled(false);
						joinPanel.getCheckPwdBtn().setSelected(true);
					} else {
						PopUpDialog dialog2 = new PopUpDialog(frame, "비밀번호 확인", "비밀번호가 동일하지 않습니다.");
						dialog2.run();
						joinPanel.getCheckPwdBtn().setSelected(false);
					}
					
				} else {
					PopUpDialog dialog2 = new PopUpDialog(frame, "비밀번호 확인", "비밀번호는 4자리 이상입니다.");
					dialog2.run();
					joinPanel.getCheckPwdBtn().setSelected(false);
				}
			}
		});
		// 뒤로가기
		joinPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.reset();
				loginPanel.setVisible(true);
				joinPanel.setVisible(false);
			}
		});
		// 홈으로
		joinPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.reset();
				loginPanel.setVisible(true);
				joinPanel.setVisible(false);
			}
		});
		// 회원가입
		joinPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (joinPanel.getCheckIdBtn().isSelected() 	&& joinPanel.getCheckPwdBtn().isSelected()){

					MemberVo vo = new MemberVo();
					vo.setEmail(joinPanel.getTextFieldEmail());
					vo.setId(joinPanel.getId());
					vo.setMemberName(joinPanel.getTextFieldName());
					vo.setMemberNick(joinPanel.getTextFieldNick());
					vo.setPhone(joinPanel.getTextFieldPhone());
					vo.setPwd(joinPanel.getPwd());

					if (ms.join(vo)) {
						PopUpDialog dialog = new PopUpDialog(frame, "회원가입", "회원가입 성공! 환영합니다.");
						dialog.run();
						loginPanel.reset();
						loginPanel.setVisible(true);
						joinPanel.setVisible(false);
					} else {
						PopUpDialog dialog = new PopUpDialog(frame, "회원가입", "회원가입 실패.. 다시 시도해주세요.");
						dialog.run();
					}

				} else {
					PopUpDialog dialog = new PopUpDialog(frame, "회원가입", "아이디, 비밀번호 및 필수항목을 체크해주세요.");
					dialog.run();
				}
			}
		});

		/** 아이디찾기페이지 **/
		frame.getContentPane().add(findIdPanel);
		findIdPanel.setVisible(false);
		// 뒤로가기
		findIdPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.reset();
				loginPanel.setVisible(true);
				findIdPanel.setVisible(false);
			}
		});
		// 홈
		findIdPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.reset();
				loginPanel.setVisible(true);
				findIdPanel.setVisible(false);
			}
		});
		// 아이디 찾기
		findIdPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MemberVo vo = new MemberVo();
				vo.setEmail(findIdPanel.getTextFieldEmail());
				vo.setMemberName(findIdPanel.getTextFieldName());

				String id = ms.findId(vo);
				
				if (id != null) {
					PopUpDialog dialog = new PopUpDialog(frame, "아이디 찾기", "아이디는 " + id + " 입니다.");
					dialog.run();
					loginPanel.reset();
					loginPanel.setVisible(true);
					findIdPanel.setVisible(false);
				} else {
					PopUpDialog dialog = new PopUpDialog(frame, "아이디 찾기", "입력하신 정보가 없습니다.");
					dialog.run();
				}
			}
		});

		/** 비밀번호찾기페이지 **/
		frame.getContentPane().add(findPwdPanel);
		findPwdPanel.setVisible(false);
		// 뒤로가기
		findPwdPanel.getBackBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.reset();
				loginPanel.setVisible(true);
				findPwdPanel.setVisible(false);

			}
		});
		// 홈
		findPwdPanel.getHomeBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.reset();
				loginPanel.setVisible(true);
				findPwdPanel.setVisible(false);

			}
		});
		// 비밀번호 찾기
		findPwdPanel.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MemberVo vo = new MemberVo();
				vo.setId(findPwdPanel.getTextFieldId());
				vo.setEmail(findPwdPanel.getTextFieldEmail());
				vo.setMemberName(findPwdPanel.getTextFieldName());
				System.out.println(vo);
				String pwd = ms.findPwd(vo);
				
				if (pwd != null) {
					PopUpDialog dialog = new PopUpDialog(frame, "비밀번호 찾기", "비밀번호는 " + pwd + " 입니다.");
					dialog.run();
					loginPanel.reset();
					loginPanel.setVisible(true);
					findPwdPanel.setVisible(false);
				} else {
					PopUpDialog dialog = new PopUpDialog(frame, "비밀번호 찾기", "입력하신 정보가 없습니다.");
					dialog.run();
				}
			}
		});

		/** 로그인페이지 **/
		frame.getContentPane().add(loginPanel);
		loginPanel.setVisible(true);

		// 로그인 버튼
		loginPanel.getLoginBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MemberVo vo = new MemberVo();
				vo.setId(loginPanel.getIdTextField());
				vo.setPwd(loginPanel.getPasswordField());

				if (ms.login(vo)) {
					PopUpDialog dialog = new PopUpDialog(frame, "로그인성공", "로그인 되었습니다.");
					dialog.run();
					mainPanel.setVisible(true);
					loginPanel.setVisible(false);
				} else {
					PopUpDialog dialog = new PopUpDialog(frame, "로그인실패", "아이디, 비밀번호를 확인하십시오.");
					dialog.run();
				}

			}
		});
		// 아이디 찾기
		loginPanel.getFindIdBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				findIdPanel.reset();
				findIdPanel.setVisible(true);
				loginPanel.setVisible(false);
			}
		});
		// 패스워드 찾기
		loginPanel.getFindPwdBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				findPwdPanel.reset();
				findPwdPanel.setVisible(true);
				loginPanel.setVisible(false);
			}
		});
		// 회원가입
		loginPanel.getJoinBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				joinPanel.reset();
				joinPanel.setVisible(true);
				loginPanel.setVisible(false);
			}
		});

		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
