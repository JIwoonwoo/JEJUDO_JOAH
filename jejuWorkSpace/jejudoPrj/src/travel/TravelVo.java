package travel;

import java.sql.Timestamp;

public class TravelVo {

	private String trable_no;
	private String recommend_type;
	private String theme;
	private String category;
	private String travel_name;
	private String travel_address;
	private String activity_yn;
	private String travel_price;
	private String animal_yn;
	private String travel_info;
	private String travel_phone;
	private String open;
	private String closed;
	private String like_cnt;
	private String closed_day;
	private Timestamp delete_date; // 타입 이게 맞나요..?
	private String purpose;
	
	// 기본생성자
	public void TravelVo() {
		
	}

	// 생성자 (파라미터)
	public TravelVo(String trable_no, String recommend_type, String theme, String category, String travel_name,
			String travel_address, String activity_yn, String travel_price, String animal_yn, String travel_info,
			String travel_phone, String open, String closed, String like_cnt, String closed_day,
			Timestamp delete_date, String purpose) {
		super();
		this.trable_no = trable_no;
		this.recommend_type = recommend_type;
		this.theme = theme;
		this.category = category;
		this.travel_name = travel_name;
		this.travel_address = travel_address;
		this.activity_yn = activity_yn;
		this.travel_price = travel_price;
		this.animal_yn = animal_yn;
		this.travel_info = travel_info;
		this.travel_phone = travel_phone;
		this.open = open;
		this.closed = closed;
		this.like_cnt = like_cnt;
		this.closed_day = closed_day;
		this.delete_date = delete_date;
		this.purpose = purpose;
	}

	public String getTrable_no() {
		return trable_no;
	}

	public void setTrable_no(String trable_no) {
		this.trable_no = trable_no;
	}

	public String getRecommend_type() {
		return recommend_type;
	}

	public void setRecommend_type(String recommend_type) {
		this.recommend_type = recommend_type;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTravel_name() {
		return travel_name;
	}

	public void setTravel_name(String travel_name) {
		this.travel_name = travel_name;
	}

	public String getTravel_address() {
		return travel_address;
	}

	public void setTravel_address(String travel_address) {
		this.travel_address = travel_address;
	}

	public String getActivity_yn() {
		return activity_yn;
	}

	public void setActivity_yn(String activity_yn) {
		this.activity_yn = activity_yn;
	}

	public String getTravel_price() {
		return travel_price;
	}

	public void setTravel_price(String travel_price) {
		this.travel_price = travel_price;
	}

	public String getAnimal_yn() {
		return animal_yn;
	}

	public void setAnimal_yn(String animal_yn) {
		this.animal_yn = animal_yn;
	}

	public String getTravel_info() {
		return travel_info;
	}

	public void setTravel_info(String travel_info) {
		this.travel_info = travel_info;
	}

	public String getTravel_phone() {
		return travel_phone;
	}

	public void setTravel_phone(String travel_phone) {
		this.travel_phone = travel_phone;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public String getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(String like_cnt) {
		this.like_cnt = like_cnt;
	}

	public String getClosed_day() {
		return closed_day;
	}

	public void setClosed_day(String closed_day) {
		this.closed_day = closed_day;
	}

	public Timestamp getDelete_date() {
		return delete_date;
	}

	public void setDelete_date(Timestamp delete_date) {
		this.delete_date = delete_date;
	}
	
	

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Override
	public String toString() {
		return "TravelVo [trable_no=" + trable_no + ", recommend_type=" + recommend_type + ", theme=" + theme
				+ ", category=" + category + ", travel_name=" + travel_name + ", travel_address=" + travel_address
				+ ", activity_yn=" + activity_yn + ", travel_price=" + travel_price + ", animal_yn=" + animal_yn
				+ ", travel_info=" + travel_info + ", travel_phone=" + travel_phone + ", open=" + open + ", closed="
				+ closed + ", like_cnt=" + like_cnt + ", closed_day=" + closed_day + ", delete_date=" + delete_date
				+ ", purpose=" + purpose + "]";
	}

	
	

	
	
	
	
	
}
