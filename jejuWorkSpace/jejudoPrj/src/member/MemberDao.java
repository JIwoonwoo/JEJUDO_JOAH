package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCTemplate;

public class MemberDao {
	
	public String login(MemberVo vo) throws Exception {
		
		String id = vo.getId();
		String pwd = vo.getPwd();
			
		Connection conn = JDBCTemplate.getConnection();
		
		String sql = "SELECT MEMBER_NO, PWD FROM MEMBER WHERE ID = ? AND QUIT_YN = 'N'";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		String userNo = null;
		if(rs.next()) {
			if(pwd.equals(rs.getString("PWD"))) {
				userNo = rs.getString("MEMBER_NO");
			}else {
				userNo = "";
			}
		}
		
		return userNo;
	}
	
	public boolean join(MemberVo vo) throws Exception {
		
		String name = vo.getMemberName();
		String id = vo.getId();
		String pwd= vo.getPwd();
		String nick = vo.getMemberNick();
		String phone = vo.getPhone();
		String email = vo.getEmail();
		
		Connection conn = JDBCTemplate.getConnection();
		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_MEMBER.NEXTVAL, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		pstmt.setString(3, name);
		pstmt.setString(4, nick);
		pstmt.setString(5, phone);
		pstmt.setString(6, email);
		
		int result = pstmt.executeUpdate();
		return (result == 1) ? true : false;
	}

}
