package payment;

public class PayVo{
	
	public PayVo() {
		
	}
	
	private int flightNo;
	private int accomNo;
	private int carNo;
	private int flightGoPay;
	private int flightComePay;
	private int accomPay;
	private int carPay;
	private int mypoint;
	
	
	
	
	public PayVo(int flightNo, int accomNo, int carNo, int flightGoPay, int flightComePay, int accomPay, int carPay,
			int mypoint) {
		super();
		this.flightNo = flightNo;
		this.accomNo = accomNo;
		this.carNo = carNo;
		this.flightGoPay = flightGoPay;
		this.flightComePay = flightComePay;
		this.accomPay = accomPay;
		this.carPay = carPay;
		this.mypoint = mypoint;
	}
	@Override
	public String toString() {
		return "PayVo [flightNo=" + flightNo + ", accomNo=" + accomNo + ", carNo=" + carNo + ", flightGoPay="
				+ flightGoPay + ", flightComePay=" + flightComePay + ", accomPay=" + accomPay + ", carPay=" + carPay
				+ ", mypoint=" + mypoint + "]";
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
	public int getMypoint() {
		return mypoint;
	}
	public void setMypoint(int mypoint) {
		this.mypoint = mypoint;
	}
	
	
	
	
}	