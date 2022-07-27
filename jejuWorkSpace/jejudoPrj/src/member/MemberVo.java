package member;

import java.sql.Timestamp;

public class MemberVo {

	private int memberNo;
	private String id;
	private String pwd;
	private String memberName;
	private String memberNick;
	private String phone;
	private String email;
	private int point;

	public MemberVo() {

	}

	public MemberVo(int memberNo, String id, String pwd, String memberName, String memberNick, String phone,
			String email, int point) {
		super();
		this.memberNo = memberNo;
		this.id = id;
		this.pwd = pwd;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.phone = phone;
		this.email = email;
		this.point = point;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "MemberDto [memberNo=" + memberNo + ", id=" + id + ", pwd=" + pwd + ", memberName=" + memberName
				+ ", memberNick=" + memberNick + ", phone=" + phone + ", email=" + email + ", point=" + point + "]";
	}

}
