package flight3;

import java.sql.Date;
import java.sql.Timestamp;

public class Flight_Vo {
	
	//기본 생성자
	public Flight_Vo() {
		
	}
	//매개변수 있는 생성자
	


	private Number memberNo;
	private Number flightNo;
	private Number departureFlight;
	private String returnFlight;
	private Date reserveDate;
	private Timestamp cancleDate;
	private String flightName;
	private String departureTime;
	private String arrivalTime;
	private String depAirport;
	private String arrAirport;
	private String departureDate;
	private String returnDate;
	private String flightPerson;
	private String flightPrice;
	private String airline;

	
	//getter, setter
	public Number getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Number memberNo) {
		this.memberNo = memberNo;
	}
	public Number getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(Number flightNo) {
		this.flightNo = flightNo;
	}
	public Number getDepartureFlight() {
		return departureFlight;
	}
	public void setDepartureFlight(Number departureFlight) {
		this.departureFlight = departureFlight;
	}
	public String getReturnFlight() {
		return returnFlight;
	}
	public void setReturnFlight(String returnFlight) {
		this.returnFlight = returnFlight;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public Timestamp getCancleDate() {
		return cancleDate;
	}
	public void setCancleDate(Timestamp cancleDate) {
		this.cancleDate = cancleDate;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepAirport() {
		return depAirport;
	}
	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}
	public String getArrAirport() {
		return arrAirport;
	}
	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getFlightPerson() {
		return flightPerson;
	}
	public void setFlightPerson(String flightPerson) {
		this.flightPerson = flightPerson;
	}
	public String getFlightPrice() {
		return flightPrice;
	}
	public void setFlightPrice(String flightPrice) {
		this.flightPrice = flightPrice;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	

	//toString
	@Override
	public String toString() {
		return "Flight_Vo [memberNo=" + memberNo + ", flightNo=" + flightNo + ", departureFlight=" + departureFlight
				+ ", returnFlight=" + returnFlight + ", reserveDate=" + reserveDate + ", cancleDate=" + cancleDate
				+ ", flightName=" + flightName + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", depAirport=" + depAirport + ", arrAirport=" + arrAirport + ", departureDate=" + departureDate
				+ ", arrivalDate=" + returnDate + ", flightPerson=" + flightPerson + ", flightPrice=" + flightPrice
				+ ", airline=" + airline + "]";
	}
	
	
	
}
