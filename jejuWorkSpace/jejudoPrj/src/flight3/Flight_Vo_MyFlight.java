package flight3;

import java.sql.Date;
import java.sql.Timestamp;

public class Flight_Vo_MyFlight { //사용자가 선택한 비행기 예약
	
	//기본 생성자
	public Flight_Vo_MyFlight() {
		
	}

	
	//매개변수 있는 생성자
	public Flight_Vo_MyFlight(String myDepartureFlightNo, String myReturnFlightNo, String myMemberNo) {
		super();
		this.myDepartureFlightNo = myDepartureFlightNo;
		this.myReturnFlightNo = myReturnFlightNo;
		this.myMemberNo = myMemberNo;
	}
	
	
	//필드 생성
	private String myDepartureFlightNo;
	private String myReturnFlightNo;
	private String myMemberNo;
	
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



	//toString
	@Override
	public String toString() {
		return "Flight_Vo_MyFlight [myDepartureFlightNo=" + myDepartureFlightNo + ", myReturnFlightNo="
				+ myReturnFlightNo + ", myMemberNo=" + myMemberNo + "]";
	}
	
	
	
}
