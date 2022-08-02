package travel;

import java.sql.Timestamp;

public class TravelVo {

	private String travel_no;
	private String recommend_type;

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
	private int like_cnt;
	private String closed_day;

	private String purpose;

	// 기본생성자
	public TravelVo() {

	}


	// 생성자 (파라미터)
	public TravelVo(String travel_no, String recommend_type, String category, String travel_name,
			String travel_address, String activity_yn, String travel_price, String animal_yn, String travel_info,
			String travel_phone, String open, String closed, int like_cnt, String closed_day,
			String purpose) {
		super();
		this.travel_no = travel_no;
		this.recommend_type = recommend_type;

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

		this.purpose = purpose;
	}

public String getTravel_no() {
		return travel_no;
	}

	public void setTravel_no(String travel_no) {
		this.travel_no = travel_no;
	}

	public String getRecommend_type() {
		return recommend_type;
	}

	public void setRecommend_type(String recommend_type) {
		this.recommend_type = recommend_type;
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


	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Override
	public String toString() {
		return 
		" 여행지 NO = " + travel_no + ", 카테고리 = " + category + ", 테마 = " + purpose + ", 인원 수 = " + recommend_type 
		 + "\n 여행지 이름 : " + travel_name + ", 주소 = " + travel_address + ", 여행지 정보 = " + travel_info 
		 + "\n 가격 = " + travel_price
		+ ", 전화번호 = " + travel_phone + ", 좋아요 = " + like_cnt 
		+ ", 여는시간 = " + open + ", 닫는시간 = " + closed + ", 휴무요일 = " + closed_day  
		+ "\n 액티비티 여부= " + activity_yn + ", 동물동반 가능 여부 = " + animal_yn
		+  "\n -------------------------------------------------------------------------------------------------";
	}

}
