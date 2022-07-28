package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Main;
import util.JDBCTemplate;

public class MemberDao {

	/**
	 * login
	 * @param conn
	 * @param vo (id,  pwd)
	 * @return -1 0 MemberNO
	 * @throws Exception
	 */
	public int login(Connection conn, MemberVo vo) throws Exception {

		int userNo = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT MEMBER_NO, PWD FROM MEMBER WHERE ID = ? AND QUIT_YN = 'N'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (vo.getPwd() != null && vo.getPwd().equals(rs.getString("PWD"))) {
					userNo = rs.getInt("MEMBER_NO");
				} else {
					userNo = 0;
				}
			}

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		return userNo;
	}

	/**
	 * join 메소드 input > Connection, MemberVo (id, pwd, name, nick, phone, email)
	 * output > true / false
	 * 
	 * 넘겨받은 데이터들로 insert 성공 결과에 따라 true / false 리턴
	 */
	public boolean join(Connection conn, MemberVo vo) throws Exception {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "INSERT INTO MEMBER VALUES(SEQ_MEMBER.NEXTVAL, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getMemberName());
			pstmt.setString(4, vo.getMemberNick());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getEmail());

			result = pstmt.executeUpdate();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return (result == 1) ? true : false;
	}

	/**
	 * search 메소드
	 * 
	 * @param conn
	 * @return MemberVo
	 * @throws Exception
	 */
	public MemberVo search(Connection conn) throws Exception {

		MemberVo vo = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "SELECT ID, PWD, MEMBER_NAME, MEMBER_NICK, PHONE, EMAIL, POINT "
					+ "FROM MEMBER WHERE MEMBER_NO = ? AND QUIT_YN = 'N'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setId(rs.getString("ID"));
				vo.setPwd(rs.getString("PWD"));
				vo.setMemberName(rs.getString("MEMBER_NAME"));
				vo.setMemberNick(rs.getString("MEMBER_NICK"));
				vo.setPhone(rs.getString("PHONE"));
				vo.setEmail(rs.getString("EMAIL"));
				vo.setPoint(rs.getInt("POINT"));
			}

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		return vo;

	}

	/**
	 * update
	 * 
	 * 
	 */
	public boolean update(Connection conn, MemberVo vo) throws Exception {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE MEMBER SET PWD = ? ,MEMBER_NICK = ? ,PHONE = ?, EMAIL = ? WHERE MEMBER_NO = ? AND QUIT_YN = 'N'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getMemberNick());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getEmail());
			pstmt.setInt(5, Main.loginNo);

			result = pstmt.executeUpdate();

		} finally {
			JDBCTemplate.close(pstmt);
		}

		return (result == 1) ? true : false;

	}

	/**
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public boolean quit(Connection conn) throws Exception {

		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE MEMBER SET QUIT_YN = 'Y' WHERE MEMBER_NO = ? AND QUIT_YN = 'N'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);

			result = pstmt.executeUpdate();

		} finally {
			JDBCTemplate.close(pstmt);
		}

		return (result == 1) ? true : false;
	}

	/**
	 * 
	 * @param conn
	 * @return point
	 * @throws Exception
	 */
	public int hasPoint(Connection conn) throws Exception {

		int result = -1;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "SELECT POINT FROM MEMBER WHERE ID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("POINT");
			}

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		return result;
	}

	/**
	 * find ID
	 * @param conn
	 * @param vo (name, email)
	 * @return id
	 * @throws Exception
	 */
	public String findId(Connection conn, MemberVo vo) throws Exception {
		
		String result = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "SELECT ID FROM MEMBER WHERE MEMBER_NAME = ? AND EMAIL = ? AND QUIT_YN = 'N'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberName());
			pstmt.setString(2, vo.getEmail());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("ID");
			}

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}

		return result;
	}

	/**
	 * findPwd
	 * @param conn
	 * @param vo (id, name, email)
	 * @return PWD
	 * @throws Exception
	 */
	public String findPwd(Connection conn, MemberVo vo) throws Exception {

		String result = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "SELECT PWD FROM MEMBER WHERE MEMBER_NAME = ? AND EMAIL = ? AND ID = ? AND QUIT_YN = 'N'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getId());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("PWD");
			}

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		return result;
	}
}
