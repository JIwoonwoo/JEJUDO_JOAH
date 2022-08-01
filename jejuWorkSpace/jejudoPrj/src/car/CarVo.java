package car;

import java.security.Timestamp;

public class CarVo {
	public CarVo() {
		
	}
	
	private String memberNO;
	private String money;
	private String carNo;
	private String rentalNo;
	private String returnDate;
	private String reserveDate;
	private String insurance;
	private Timestamp cancelDate;
	private String carUq;
	private String dayPrice;
	private String open_yn;
	private String carName;
	private String carSize;
	private	String carPerson;
	private String carFuel;
	
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
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
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
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
		return "CarVo [carNo=" + carNo + ", rentalNo=" + rentalNo + ", returnDate=" + returnDate + ", reserveDate="
				+ reserveDate + ", insurance=" + insurance + ", cancelDate=" + cancelDate + ", carUq=" + carUq
				+ ", dayPrice=" + dayPrice + ", open_yn=" + open_yn + ", carName=" + carName + ", carSize=" + carSize
				+ ", carPerson=" + carPerson + "]";
	}
	
	
}
