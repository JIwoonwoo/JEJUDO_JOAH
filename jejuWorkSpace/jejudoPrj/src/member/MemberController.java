package member;

import main.Main;
import util.InputUtil;

public class MemberController {
	
	private MemberService ms = new MemberService();
	
	public void viewLogin() {
		String id = InputUtil.sc.nextLine();
		String pwd = InputUtil.sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		ms.login(vo);
	}
	
}
