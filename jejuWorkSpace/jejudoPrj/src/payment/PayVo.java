package payment;

import java.sql.Timestamp;

public class PayVo {

	// 기본생성자
	public PayVo() {

	}

	// 테이블 데이터
	private int payNo;
	private int flightNo;
	private int accomNo;
	private int carNo;
	private int totalPay;
	private int pointUsed;
	private int cutPrice;
	private int myPoint;
	private int payMethod;
	private Timestamp payDate;
	private char cancelYN;
	private Timestamp cancelDate;
	
	// 개별 가격 데이터
	private int flightGoPay;
	private int flightComePay;
	private int accomPay;
	private int carPay;
	
	
	public PayVo(int flightNo, int accomNo, int carNo, int totalPay, int flightGoPay, int flightComePay, int accomPay,
			int carPay) {
		this.flightNo = flightNo;
		this.accomNo = accomNo;
		this.carNo = carNo;
		this.totalPay = totalPay;
		this.flightGoPay = flightGoPay;
		this.flightComePay = flightComePay;
		this.accomPay = accomPay;
		this.carPay = carPay;
	}

	@Override
	public String toString() {
		return "PayVo [payNo=" + payNo + ", flightNo=" + flightNo + ", accomNo=" + accomNo + ", carNo=" + carNo
				+ ", totalPay=" + totalPay + ", pointUsed=" + pointUsed + ", cutPrice=" + cutPrice + ", myPoint="
				+ myPoint + ", payMethod=" + payMethod + ", payDate=" + payDate + ", cancelYN=" + cancelYN
				+ ", cancelDate=" + cancelDate + ", flightGoPay=" + flightGoPay + ", flightComePay=" + flightComePay
				+ ", accomPay=" + accomPay + ", carPay=" + carPay + "]";
	}

	// 전체 using 필드
	public PayVo(int payNo, int flightNo, int accomNo, int carNo, int totalPay, int pointUsed, int cutPrice,
			int myPoint, int payMethod, Timestamp payDate, char cancelYN, Timestamp cancelDate, int flightGoPay,
			int flightComePay, int accomPay, int carPay) {
		super();
		this.payNo = payNo;
		this.flightNo = flightNo;
		this.accomNo = accomNo;
		this.carNo = carNo;
		this.totalPay = totalPay;
		this.pointUsed = pointUsed;
		this.cutPrice = cutPrice;
		this.myPoint = myPoint;
		this.payMethod = payMethod;
		this.payDate = payDate;
		this.cancelYN = cancelYN;
		this.cancelDate = cancelDate;
		this.flightGoPay = flightGoPay;
		this.flightComePay = flightComePay;
		this.accomPay = accomPay;
		this.carPay = carPay;
	}

	public int getPayNo() {
		return payNo;
	}
	
	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}

	public int getMyPoint() {
		return myPoint;
	}

	public void setMyPoint(int myPoint) {
		this.myPoint = myPoint;
	}

	
	public int getCutPrice() {
		return cutPrice;
	}

	public void setCutPrice(int cutPrice) {
		this.cutPrice = cutPrice;
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
		return myPoint;
	}

	public void setMypoint(int myPoint) {
		this.myPoint = myPoint;
	}

	public int getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(int payMethod) {
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