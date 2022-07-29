package qna;

import java.sql.Timestamp;

public class QnaVo {
	
	public QnaVo() {
		
	}
	
	public QnaVo(int questionNo, int memberNo, String questionTitle, boolean answerYN, String qContent, String aContent) {
		super();
		this.questionNo = questionNo;
		this.questionTitle = questionTitle;
		this.answerYN = answerYN;
		this.qContent = qContent;
		this.aContent = aContent;
	}
	private int questionNo;
	private String questionTitle;
	private boolean answerYN;
	private String qContent;
	private String aContent;
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public boolean getAnswerYN() {
		return answerYN;
	}
	public void setAnswerYN(boolean answerYN) {
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
		return "qnaVo [questionNo=" + questionNo + ", questionTitle=" + questionTitle
				+ ", answerYN=" + answerYN + ", qContent=" + qContent + ", aContent=" + aContent + "]";
	}
	
	

}
