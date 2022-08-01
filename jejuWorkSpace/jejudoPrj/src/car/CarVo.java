package car;

import java.security.Timestamp;

public class CarVo {
	public CarVo() {
		
	}
	
	private String memberNO;
	private String money;
	private int reserveNo;
	private String rentalNo;
	private String returnDate;
	private String rentalDate;
	private String insurance;
	private Timestamp cancelDate;
	private String carUq;
	private String dayPrice;
	private String open_yn;
	private String carName;
	private String carSize;
	private	String carPerson;
	private String carFuel;
	
	public int getReserveNo() {
		return reserveNo;
	}
	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}

	public String getRentalNo() {
		return rentalNo;
	}
	public void setRentalNo(String rentalNo) {
		this.rentalNo = rentalNo;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String reserveDate) {
		this.rentalDate = reserveDate;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public Timestamp getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getCarUq() {
		return carUq;
	}
	public void setCarUq(String carUq) {
		this.carUq = carUq;
	}
	public String getDayPrice() {
		return dayPrice;
	}
	public void setDayPrice(String dayPrice) {
		this.dayPrice = dayPrice;
	}
	public String getOpen_yn() {
		return open_yn;
	}
	public void setOpen_yn(String open_yn) {
		this.open_yn = open_yn;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	

	public String getMemberNO() {
		return memberNO;
	}
	public void setMemberNO(String memberNO) {
		this.memberNO = memberNO;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCarSize() {
		return carSize;
	}
	public void setCarSize(String carSize) {
		this.carSize = carSize;
	}
	public String getCarPerson() {
		return carPerson;
	}
	public void setCarPerson(String carPerson) {
		this.carPerson = carPerson;
	}
	public String getCarFuel() {
		return carFuel;
	}
	public void setCarFuel(String carFuel) {
		this.carFuel = carFuel;
	}
	
	@Override
	public String toString() {
		return "CarVo [memberNO=" + memberNO + ", money=" + money + ", reserveNo=" + reserveNo + ", rentalNo="
				+ rentalNo + ", returnDate=" + returnDate + ", rentalDate=" + rentalDate + ", insurance=" + insurance
				+ ", cancelDate=" + cancelDate + ", carUq=" + carUq + ", dayPrice=" + dayPrice + ", open_yn=" + open_yn
				+ ", carName=" + carName + ", carSize=" + carSize + ", carPerson=" + carPerson + ", carFuel=" + carFuel
				+ "]";
	}
	
	public String test() {
		return "고유 번호 : "+rentalNo+" / 시작일 : "+rentalDate+" / 반납일 : "+returnDate+" / 자동차 : "+carName+" / 사이즈 : "+carSize+" / 1박 금액 : "+dayPrice+ " / 연료"+carFuel;
	}
	
	
	
	
}
