package flight2;

public class ReservationVo {
	
	//기본 생성자
	public ReservationVo() {
		
	}

	//매개변수 있는 생성자
	public ReservationVo(String departure_date, String arrival_date, String flight_person, String dep_airport,
			String flight_price) {
		super();
		this.departure_date = departure_date;
		this.arrival_date = arrival_date;
		this.flight_person = flight_person;
		this.dep_airport = dep_airport;
		this.flight_price = flight_price;
	}
	
	private String departure_date;
	private String arrival_date;
	private String flight_person;
	private String dep_airport;
	private String flight_price;
	
	//getter, setter
	public String getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getFlight_person() {
		return flight_person;
	}

	public void setFlight_person(String flight_person) {
		this.flight_person = flight_person;
	}

	public String getDep_airport() {
		return dep_airport;
	}

	public void setDep_airport(String dep_airport) {
		this.dep_airport = dep_airport;
	}

	public String getFlight_price() {
		return flight_price;
	}

	public void setFlight_price(String flight_price) {
		this.flight_price = flight_price;
	}

	
	//toString
	@Override
	public String toString() {
		return "ReservationVo [departure_date=" + departure_date + ", arrival_date=" + arrival_date + ", flight_person="
				+ flight_person + ", dep_airport=" + dep_airport + ", flight_price=" + flight_price + "]";
	}
	
	
	
	
}
