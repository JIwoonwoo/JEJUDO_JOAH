package main;

import accom.AccService;
import car.CarService;
import car.ReserveVo;
import flight3.Flight_Service;
import flight3.Flight_Vo_MyFlight;
import gui.GUI;
import gui.dialog.YesOrNo;
import payment.PayService;

public class Main {
	
	public static int loginNo;
	public static void cancle() {
		
		CarService cs = new CarService();
		AccService as = new AccService();
		PayService ps = new PayService();
		Flight_Service fs = new Flight_Service();
		
		YesOrNo d = new YesOrNo(GUI.frame, "예약취소", "함께 예약한 항목이 모두 취소됩니다.");
		if(d.run()==1) {
			
			int carRNo = 0;
			int AccRNo = 0;
			int FlightRNo = 0;
			int payNo = 0;
			
			ReserveVo cvo = new ReserveVo();
			Flight_Vo_MyFlight fvo = new Flight_Vo_MyFlight();
			
			cvo.setReserveNo(carRNo);
			
			
			cs.carCancel(cvo);
			as.reservCancel(loginNo);
			fs.delete(fvo);
			
			ps.
			
			
		}
	}
}
