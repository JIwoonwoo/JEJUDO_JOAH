package car;


import java.sql.Timestamp;

public class ReserveVo {
	private int reserveNo;
	private int rentalNo;
	private String memberNo;
	private String rentalDate;
	private String returnDate;
	private String reserveDate;
	private String insurance;
	private Timestamp cancelDate;
	private String cancleYn;
	
	
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
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
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
}
