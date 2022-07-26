package travel;

import java.sql.Timestamp;

public class TravelDto {

	private int travelNo;
	private String travelName;
	private String travelLocation;
	private boolean isActivity;
	private int travelPrice;
	private boolean withAnimal;
	private String travelInfo;
	private String travelPhone;
	private String category;
	private String theme;
	private String type;
	private Timestamp open;
	private Timestamp close;
	private String closedDay;
	private int like;
	
	public TravelDto() {
		
	}

	public TravelDto(int travelNo, String travelName, String travelLocation, boolean isActivity, int travelPrice,
			boolean withAnimal, String travelInfo, String travelPhone, String category, String theme, String type,
			Timestamp open, Timestamp close, String closedDay, int like) {
		super();
		this.travelNo = travelNo;
		this.travelName = travelName;
		this.travelLocation = travelLocation;
		this.isActivity = isActivity;
		this.travelPrice = travelPrice;
		this.withAnimal = withAnimal;
		this.travelInfo = travelInfo;
		this.travelPhone = travelPhone;
		this.category = category;
		this.theme = theme;
		this.type = type;
		this.open = open;
		this.close = close;
		this.closedDay = closedDay;
		this.like = like;
	}

	public int getTravelNo() {
		return travelNo;
	}

	public void setTravelNo(int travelNo) {
		this.travelNo = travelNo;
	}

	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}

	public String getTravelLocation() {
		return travelLocation;
	}

	public void setTravelLocation(String travelLocation) {
		this.travelLocation = travelLocation;
	}

	public boolean isActivity() {
		return isActivity;
	}

	public void setActivity(boolean isActivity) {
		this.isActivity = isActivity;
	}

	public int getTravelPrice() {
		return travelPrice;
	}

	public void setTravelPrice(int travelPrice) {
		this.travelPrice = travelPrice;
	}

	public boolean isWithAnimal() {
		return withAnimal;
	}

	public void setWithAnimal(boolean withAnimal) {
		this.withAnimal = withAnimal;
	}

	public String getTravelInfo() {
		return travelInfo;
	}

	public void setTravelInfo(String travelInfo) {
		this.travelInfo = travelInfo;
	}

	public String getTravelPhone() {
		return travelPhone;
	}

	public void setTravelPhone(String travelPhone) {
		this.travelPhone = travelPhone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getOpen() {
		return open;
	}

	public void setOpen(Timestamp open) {
		this.open = open;
	}

	public Timestamp getClose() {
		return close;
	}

	public void setClose(Timestamp close) {
		this.close = close;
	}

	public String getClosedDay() {
		return closedDay;
	}

	public void setClosedDay(String closedDay) {
		this.closedDay = closedDay;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

}
