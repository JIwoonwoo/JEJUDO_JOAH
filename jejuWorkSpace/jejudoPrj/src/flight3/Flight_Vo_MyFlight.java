package flight3;

import java.sql.Date;
import java.sql.Timestamp;

public class Flight_Vo_MyFlight { //사용자가 선택한 비행기 예약
	
	//기본 생성자
	public Flight_Vo_MyFlight() {
		
	}

	
	//매개변수 있는 생성자
	public Flight_Vo_MyFlight(String myDepartureFlightNo, String myReturnFlightNo, String myMemberNo, String reservDate,
			String myDepartureNo, String departureDate, String flightName, String airline, String departureTime,
			String myReturnNo, String returnDate, String returnFlightName, String returnAirline, String returnTime) {
		super();
		this.myDepartureFlightNo = myDepartureFlightNo;
		this.myReturnFlightNo = myReturnFlightNo;
		this.myMemberNo = myMemberNo;
		this.reservDate = reservDate;
		this.myDepartureNo = myDepartureNo;
		this.departureDate = departureDate;
		this.flightName = flightName;
		this.airline = airline;
		this.departureTime = departureTime;
		this.myReturnNo = myReturnNo;
		this.returnDate = returnDate;
		this.returnFlightName = returnFlightName;
		this.returnAirline = returnAirline;
		this.returnTime = returnTime;
	}
	
	//필드 생성
	private String myDepartureFlightNo;
	private String myReturnFlightNo;
	private String myMemberNo;
	private int reserveNo;
	
	private String reservDate; 
	private String myDepartureNo; 
	private String departureDate;
	private String flightName;
	private String airline; 
	private String departureTime; 
	private String myReturnNo; 
	private String returnDate; 
	private String returnFlightName; 
	private String returnAirline; 
	private String returnTime; 
	
	//getter, setter
	public String getMyDepartureFlightNo() {
		return myDepartureFlightNo;
	}
	public void setMyDepartureFlightNo(String myDepartureFlightNo) {
		this.myDepartureFlightNo = myDepartureFlightNo;
	}
	public String getMyReturnFlightNo() {
		return myReturnFlightNo;
	}
	public void setMyReturnFlightNo(String myReturnFlightNo) {
		this.myReturnFlightNo = myReturnFlightNo;
	}
	
	public String getMyMemberNo() {
		return myMemberNo;
	}

	public void setMyMemberNO(String myMemberNo) {
		this.myMemberNo = myMemberNo;
	}



	public String getReservDate() {
		return reservDate;
	}


	public void setReservDate(String reservDate) {
		this.reservDate = reservDate;
	}


	public String getMyDepartureNo() {
		return myDepartureNo;
	}


	public void setMyDepartureNo(String myDepartureNo) {
		this.myDepartureNo = myDepartureNo;
	}


	public String getDepartureDate() {
		return departureDate;
	}


	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}


	public String getFlightName() {
		return flightName;
	}


	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}


	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public String getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}


	public String getMyReturnNo() {
		return myReturnNo;
	}


	public void setMyReturnNo(String myReturnNo) {
		this.myReturnNo = myReturnNo;
	}


	public String getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}


	public String getReturnFlightName() {
		return returnFlightName;
	}


	public void setReturnFlightName(String returnFlightName) {
		this.returnFlightName = returnFlightName;
	}


	public String getReturnAirline() {
		return returnAirline;
	}


	public void setReturnAirline(String returnAirline) {
		this.returnAirline = returnAirline;
	}


	public String getReturnTime() {
		return returnTime;
	}


	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}


	public void setMyMemberNo(String myMemberNo) {
		this.myMemberNo = myMemberNo;
	}





	public int getReserveNo() {
		return reserveNo;
	}


	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}


	//toString
	@Override
	public String toString() {
		return "Flight_Vo_MyFlight [myDepartureFlightNo=" + myDepartureFlightNo + ", myReturnFlightNo="
				+ myReturnFlightNo + ", myMemberNo=" + myMemberNo + ", reservDate=" + reservDate + ", myDepartureNo="
				+ myDepartureNo + ", departureDate=" + departureDate + ", flightName=" + flightName + ", airline="
				+ airline + ", departureTime=" + departureTime + ", myReturnNo=" + myReturnNo + ", returnDate="
				+ returnDate + ", returnFlightName=" + returnFlightName + ", returnAirline=" + returnAirline
				+ ", returnTime=" + returnTime + "]";
	}
	
	
	
}
