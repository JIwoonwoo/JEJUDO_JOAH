package member;

import java.sql.Connection;
import java.util.regex.Pattern;

import main.Main;
import util.JDBCTemplate;

public class MemberService {

	private MemberDao md = new MemberDao();

	/**
	 * join
	 * 
	 * @param vo (id, pwd, name, nick, [phone], email)
	 * @return true / false
	 */
	public boolean join(MemberVo vo) {

		boolean result = false;
		Connection conn = null;
		
		if(vo.getEmail()==null || vo.getMemberName()==null || vo.getEmail().equals("") || vo.getMemberName().equals("")) {
			return false;
		}
		
		if(vo.getMemberNick()==null || vo.getMemberNick().equals("")) {
			vo.setMemberNick(vo.getMemberName());
		}

		try {

			conn = JDBCTemplate.getConnection();
			if (md.join(conn, vo)) {
				System.out.println("[SUCCESS] 회원가입 성공");
				JDBCTemplate.commit(conn);
				result = true;
			} else {
				System.out.println("[FAILURE] 회원가입 실패");
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 회원가입 실패");
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}

	/**
	 * login
	 * 
	 * @param vo (id, pwd)
	 * @return true false
	 */
	public boolean login(MemberVo vo) {

		boolean result = false;
		Connection conn = null;

		try {

			conn = JDBCTemplate.getConnection();
			int userNo = md.login(conn, vo);

			if (userNo != -1 && userNo != 0) {
				System.out.println("[SUCCESS] 로그인 성공");
				Main.loginNo = userNo;
				result = true;
			} else {
				System.out.println("[FAILURE] 로그인 실패");
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 로그인 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;
	}

	/**
	 * checkDubleId
	 * 
	 * @param String (id)
	 * @return true / false
	 */
	public boolean checkDubleId(String id) {

		boolean result = false;
		Connection conn = null;

		MemberVo vo = new MemberVo();
		vo.setId(id);

		try {
			conn = JDBCTemplate.getConnection();

			if (md.login(conn, vo) == -1) {
				System.out.println("[SUCCESS] 아이디 생성가능");
				result = true;
			} else {
				System.out.println("[FAILURE] 아이디 중복");
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 아이디 중복");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;
	}

	/**
	 * search
	 * 
	 * @return vo(id, pwd, name, nick, phone, email, point)
	 */
	public MemberVo search() {

		MemberVo vo = new MemberVo();
		Connection conn = null;

		try {

			conn = JDBCTemplate.getConnection();
			MemberVo result = md.search(conn);

			if (result != null) {
				System.out.println("[SUCCESS] 로그인 정보");
				vo = result;
			} else {
				System.out.println("[FAILURE] 로그인 정보");
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 로그인 정보");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return vo;

	}

	/**
	 * update
	 * 
	 * @param vo (pwd, nick, phone, email)
	 * @return true / false
	 */
	public boolean update(MemberVo vo) {

		boolean result = false;
		Connection conn = null;

		try {

			conn = JDBCTemplate.getConnection();

			if (md.update(conn, vo)) {
				System.out.println("[SUCCESS] 회원정보 수정");
				result = true;
				JDBCTemplate.commit(conn);
			} else {
				System.out.println("[FAILURE] 회원정보 수정");
				JDBCTemplate.rollback(conn);
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 회원정보 수정");
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}

	/**
	 * quit
	 * 
	 * @return true / false
	 */
	public boolean quit() {

		boolean result = false;
		Connection conn = null;

		try {

			conn = JDBCTemplate.getConnection();

			if (md.quit(conn)) {
				System.out.println("[SUCCESS] 회원 탈퇴");
				result = true;
				JDBCTemplate.commit(conn);
			} else {
				System.out.println("[FAILURE] 회원 탈퇴");
				JDBCTemplate.rollback(conn);
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 회원 탈퇴");
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;
	}

	/**
	 * hasPoint
	 * 
	 * @return int(Point)
	 */
	public int hasPoint() {

		int result = -1;
		Connection conn = null;

		try {

			conn = JDBCTemplate.getConnection();
			MemberVo point = md.search(conn);
			if (point != null) {
				System.out.println("[SUCCESS] 포인트 조회");
				result = point.getPoint();
			} else {
				System.out.println("[FAILURE] 포인트 조회");
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 포인트 조회");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}

	/**
	 * findId
	 * 
	 * @param vo (name, email)
	 * @return String(id)
	 */
	public String findId(MemberVo vo) {

		String result = null;
		Connection conn = null;

		try {

			conn = JDBCTemplate.getConnection();
			String id = md.findId(conn, vo);
			if (id != null) {
				System.out.println("[SUCCESS] 아이디 찾기");
				result = id;
			} else {
				System.out.println("[FAILURE] 아이디 찾기");
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 아이디 찾기");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}

	/**
	 * findPwd
	 * 
	 * @param vo (name, id, email)
	 * @return String (pwd)
	 */
	public String findPwd(MemberVo vo) {

		String result = null;
		Connection conn = null;

		try {

			conn = JDBCTemplate.getConnection();
			String pwd = md.findPwd(conn, vo);
			if (pwd != null) {
				System.out.println("[SUCCESS] 비밀번호 찾기");
				result = pwd;
			} else {
				System.out.println("[FAILURE] 비밀번호 찾기");
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 비밀번호 찾기");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}

	public boolean checkId(String id) {
		// 아이디 4글자 이상 특수문자 X 영어만 숫자만 소문자만
		String pattern = "^[a-z0-9]{4,20}$";
		return Pattern.matches(pattern, id);
	}

	public boolean checkPwd(String pwd) {
		// 비밀번호 4글자 이상
		return pwd.length() >= 4;
	}

	public boolean checkNick(String nick) {
		// 특문 불가능 2-8글자
		String pattern = "^[가-힣a-z0-9]{2,8}$";
		return Pattern.matches(pattern, nick);
	}

	public boolean checkPhone(String phone) {
		String pattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		return Pattern.matches(pattern, phone);
	}

	public boolean checkEmail(String email) {
		String pattern = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		return Pattern.matches(pattern, email);
	}
}
