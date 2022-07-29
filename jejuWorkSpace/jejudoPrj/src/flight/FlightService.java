package flight;

public class FlightService {
	
	public int flightReservation(FlightVo vo) {
		
		//비지니스 로직 (출발 날짜, 복귀 날짜, 출발 공항, 금액)
		if(vo.getDeparture_date().length() < 7) {
			//출발 날짜 이상함
			return -1;
		}
		
		int result = 0;
		return result;
	}

}
