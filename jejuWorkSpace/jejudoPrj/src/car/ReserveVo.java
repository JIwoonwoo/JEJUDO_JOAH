package car;


import java.sql.Timestamp;

public class ReserveVo {
	
	private int memberNo;
	private int reserveNo;
	private int rentalNo;
	private String rentalDate;
	private String returnDate;
	private String reserveDate;
	private String insurance;
	private Timestamp cancelDate;
	private String cancleYn;
	private String dayPrice;
	private String size;
	private String name;
	private String carUq;
	private String fuel;
	
	
	
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public int getReserveNo() {
		return reserveNo;
	}
	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}
	public int getRentalNo() {
		return rentalNo;
	}
	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}
	
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarUq() {
		return carUq;
	}
	public void setCarUq(String carUq) {
		this.carUq = carUq;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
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
	public String getCancleYn() {
		return cancleYn;
	}
	public void setCancleYn(String cancleYn) {
		this.cancleYn = cancleYn;
	}
	
	public String getDayPrice() {
		return dayPrice;
	}
	public void setDayPrice(String dayPrice) {
		this.dayPrice = dayPrice;
	}
	
	@Override
	public String toString() {
		return "ReserveVo [reserveNo=" + reserveNo + ", rentalNo=" + rentalNo + ", memberNo=" + memberNo
				+ ", rentalDate=" + rentalDate + ", returnDate=" + returnDate + ", reserveDate=" + reserveDate
				+ ", insurance=" + insurance + ", cancelDate=" + cancelDate + ", cancleYn=" + cancleYn + "]";
	}
	
}
