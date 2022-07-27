package qna;

import java.sql.Timestamp;

public class qnaVo {
	
	public qnaVo() {
		
	}
	
	public qnaVo(int questionNo, int memberNo, String questionTitle, char answerYN, String qContent, String aContent) {
		super();
		this.questionNo = questionNo;
		this.memberNo = memberNo;
		this.questionTitle = questionTitle;
		this.answerYN = answerYN;
		this.qContent = qContent;
		this.aContent = aContent;
	}
	private int questionNo;
	private int memberNo;
	private String questionTitle;
	private char answerYN;
	private String qContent;
	private String aContent;
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public char getAnswerYN() {
		return answerYN;
	}
	public void setAnswerYN(char answerYN) {
		this.answerYN = answerYN;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	@Override
	public String toString() {
		return "qnaVo [questionNo=" + questionNo + ", memberNo=" + memberNo + ", questionTitle=" + questionTitle
				+ ", answerYN=" + answerYN + ", qContent=" + qContent + ", aContent=" + aContent + "]";
	}
	
	

}
