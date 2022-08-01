package survey;

import java.sql.Timestamp;

public class SurveyVo {

	private String survey_no;
	private String member_no ;
	private String purpose ;
	private String purpose2 ;
	private String location ;
	private String group ;
	private String budget ;
	private String animal_yn ;
	private Timestamp survey_date ;
	private String delete_yn ;
	
	public SurveyVo() {
		
	}
	
	public SurveyVo(String survey_no, String member_no, String purpose, String purpose2, String location, String group,
			String budget, String animal_yn, Timestamp survey_date, String delete_yn) {
		super();
		this.survey_no = survey_no;
		this.member_no = member_no;
		this.purpose = purpose;
		this.purpose2 = purpose2;
		this.location = location;
		this.group = group;
		this.budget = budget;
		this.animal_yn = animal_yn;
		this.survey_date = survey_date;
		this.delete_yn = delete_yn;
	}

	public String getSurvey_no() {
		return survey_no;
	}

	public void setSurvey_no(String survey_no) {
		this.survey_no = survey_no;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPurpose2() {
		return purpose2;
	}

	public void setPurpose2(String purpose2) {
		this.purpose2 = purpose2;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getAnimal_yn() {
		return animal_yn;
	}

	public void setAnimal_yn(String animal_yn) {
		this.animal_yn = animal_yn;
	}

	public Timestamp getSurvey_date() {
		return survey_date;
	}

	public void setSurvey_date(Timestamp survey_date) {
		this.survey_date = survey_date;
	}

	public String getDelete_yn() {
		return delete_yn;
	}

	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}

	@Override
	public String toString() {
		return "SurveyVo [survey_no=" + survey_no + ", member_no=" + member_no + ", purpose=" + purpose + ", purpose2="
				+ purpose2 + ", location=" + location + ", group=" + group + ", budget=" + budget + ", animal_yn="
				+ animal_yn + ", survey_date=" + survey_date + ", delete_yn=" + delete_yn + "]";
	}

	
	
	
}
