package travel;

import java.sql.Timestamp;

public class TravelVo {

	private int trable_no;
	private int recommend_type;
	private int theme;
	private int category;
	private String travel_name;
	private String travel_address;
	private String activity_yn;
	private int travel_price;
	private String animal_yn;
	private String travel_info;
	private String travel_phone;
	private String open;
	private String closed;
	private int like_cnt;
	private String closed_day;
	private Timestamp delete_date; // 타입 이게 맞나요..?
	
	// 기본생성자
	public void TravelVo() {
		
	}
	
	// 생성자 (파라미터)
	public TravelVo() {
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
	}

	// getter setter
	public int getTrable_no() {
		return trable_no;
	}


	public void setTrable_no(int trable_no) {
		this.trable_no = trable_no;
	}


	public int getRecommend_type() {
		return recommend_type;
	}


	public void setRecommend_type(int recommend_type) {
		this.recommend_type = recommend_type;
	}


	public int getTheme() {
		return theme;
	}


	public void setTheme(int theme) {
		this.theme = theme;
	}


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
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


	public int getTravel_price() {
		return travel_price;
	}


	public void setTravel_price(int travel_price) {
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


	public int getLike_cnt() {
		return like_cnt;
	}


	public void setLike_cnt(int like_cnt) {
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


	// toString
	@Override
	public String toString() {
		return "TravelVo [trable_no=" + trable_no + ", recommend_type=" + recommend_type + ", theme=" + theme
				+ ", category=" + category + ", travel_name=" + travel_name + ", travel_address=" + travel_address
				+ ", activity_yn=" + activity_yn + ", travel_price=" + travel_price + ", animal_yn=" + animal_yn
				+ ", travel_info=" + travel_info + ", travel_phone=" + travel_phone + ", open=" + open + ", closed="
				+ closed + ", like_cnt=" + like_cnt + ", closed_day=" + closed_day + ", delete_date=" + delete_date
				+ "]";
	}
	
	
	
	
}
