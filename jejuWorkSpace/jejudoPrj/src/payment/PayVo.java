package payment;

import java.sql.Timestamp;

public class PayVo{
	
	//기본생성자
	public PayVo() {
		
	}
	//테이블 데이터
	private int flightNo;
	private int accomNo;
	private int carNo;
	private int totalPay;
	private int pointUsed;
	private int mypoint;
	private String payMethod;
	private Timestamp payDate;
	private char cancelYN;
	private Timestamp cancelDate;
	
	//개별 가격 데이터
	private int flightGoPay;
	private int flightComePay;
	private int accomPay;
	private int carPay;
	
	
	
	// 전체 using 필드
	public PayVo(int flightNo, int accomNo, int carNo, int flightGoPay, int flightComePay, int accomPay, int carPay,
			int totalPay, int pointUsed, int mypoint, String payMethod, Timestamp payDate, char cancelYN,
			Timestamp cancelDate) {
		this.flightNo = flightNo;
		this.accomNo = accomNo;
		this.carNo = carNo;
		this.flightGoPay = flightGoPay;
		this.flightComePay = flightComePay;
		this.accomPay = accomPay;
		this.carPay = carPay;
		this.totalPay = totalPay;
		this.pointUsed = pointUsed;
		this.mypoint = mypoint;
		this.payMethod = payMethod;
		this.payDate = payDate;
		this.cancelYN = cancelYN;
		this.cancelDate = cancelDate;
	}


	@Override
	public String toString() {
		return "PayVo [flightNo=" + flightNo + ", accomNo=" + accomNo + ", carNo=" + carNo + ", flightGoPay="
				+ flightGoPay + ", flightComePay=" + flightComePay + ", accomPay=" + accomPay + ", carPay=" + carPay
				+ ", totalPay=" + totalPay + ", pointUsed=" + pointUsed + ", mypoint=" + mypoint + ", payMethod="
				+ payMethod + ", payDate=" + payDate + ", cancelYN=" + cancelYN + ", cancelDate=" + cancelDate + "]";
	}
	

	public int getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}
	public int getAccomNo() {
		return accomNo;
	}
	public void setAccomNo(int accomNo) {
		this.accomNo = accomNo;
	}
	public int getCarNo() {
		return carNo;
	}
	public void setCarNo(int carNo) {
		this.carNo = carNo;
	}
	public int getFlightGoPay() {
		return flightGoPay;
	}
	public void setFlightGoPay(int flightGoPay) {
		this.flightGoPay = flightGoPay;
	}
	public int getFlightComePay() {
		return flightComePay;
	}
	public void setFlightComePay(int flightComePay) {
		this.flightComePay = flightComePay;
	}
	public int getAccomPay() {
		return accomPay;
	}
	public void setAccomPay(int accomPay) {
		this.accomPay = accomPay;
	}
	public int getCarPay() {
		return carPay;
	}
	public void setCarPay(int carPay) {
		this.carPay = carPay;
	}
	public int getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}
	public int getPointUsed() {
		return pointUsed;
	}
	public void setPointUsed(int pointUsed) {
		this.pointUsed = pointUsed;
	}
	public int getMypoint() {
		return mypoint;
	}
	public void setMypoint(int mypoint) {
		this.mypoint = mypoint;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public Timestamp getPayDate() {
		return payDate;
	}
	public void setPayDate(Timestamp payDate) {
		this.payDate = payDate;
	}
	public char getCancelYN() {
		return cancelYN;
	}
	public void setCancelYN(char cancelYN) {
		this.cancelYN = cancelYN;
	}
	public Timestamp getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}
	
	
	
}	