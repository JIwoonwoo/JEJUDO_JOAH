package member;

import main.Main;
import util.InputUtil;

public class MemberController {
	
	private MemberDao md = new MemberDao();
	
	public void join(MemberVo vo) {
		try {
			if(md.join(vo)) System.out.println("회원가입 성공");
			else System.out.println("회원가입 실패");
		} catch (Exception e) {
			System.out.println("[err] 회원가입 실패");
			e.printStackTrace();
		}
	}
	
	public void viewLogin() {
		String id = InputUtil.sc.nextLine();
		String pwd = InputUtil.sc.nextLine();
		
		login(id, pwd);
	}
	
	public void login(String id, String pwd) {
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			String result = md.login(vo);
			if(result!=null && !result.equals("")) {
				System.out.println("로그인 성공");
				Main.userNo = result;
			}else {
				System.out.println("로그인 실패");
			}
		} catch (Exception e) {
			System.out.println("[err] 로그인 실패");
			e.printStackTrace();
		}
	}
	
	public boolean checkDubleId(String id) {
		
		boolean result = false;
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		
		try {
			if(md.login(vo)==null) result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
