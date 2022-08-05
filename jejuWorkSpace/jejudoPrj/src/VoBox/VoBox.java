package VoBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import accom.AccDto;
import car.ReserveVo;
import flight3.Flight_Vo;
import flight3.Flight_Vo_MyFlight;
import payment.PayDao;
import payment.PayVo;

public class VoBox {
	
	private PayVo vogf = new PayVo(); // 가는비행기
	private PayVo vocf = new PayVo(); // 오는비행기
	private PayVo vor = new PayVo(); // 숙소
	private PayVo voc = new PayVo(); // 자동차
	
	private ReserveVo rvo;
	private AccDto avo;
	private Flight_Vo_MyFlight fvo;

	public void setF(List<Flight_Vo> list) {
		
		fvo = new Flight_Vo_MyFlight();
		fvo.setMyDepartureFlightNo(list.get(0).getFlightNo());
		fvo.setMyReturnFlightNo(list.get(1).getFlightNo());
		
		vogf.setFlightGoPay(Integer.parseInt(list.get(0).getFlightPrice()));
		vocf.setFlightComePay(Integer.parseInt(list.get(1).getFlightPrice()));
		
	}
	
	public void setA(AccDto vo) {
		
		avo = vo;
		
		String Checkout = vo.getCheckout();
		String Checkin = vo.getCheckin();
		System.out.println(Checkout);
		System.out.println(Checkin);
//		Checkout = Checkout.substring(0,10); // 2022/05/05
		Checkout = Checkout.replace("/", "");
		Checkout = Checkout.replace("-", "");
		
//		Checkin = Checkin.substring(0,10); // 2202/05/05
		Checkin = Checkin.replace("/", "");
		Checkin = Checkin.replace("-", "");
		
		int Days = 0;
		
		try {
			Date date=new SimpleDateFormat("yyMMdd").parse(Checkout);  
			Date date2 = new SimpleDateFormat("yyMMdd").parse(Checkin);
			
			Integer.parseInt(Checkout);
			
			long Sec = (date.getTime() - date2.getTime()) / 1000; // 초
			Days = (int) (Sec / (24*60*60)); // 일자수
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		vor.setAccomPay(vo.getPrice()*Days);
	}
	
	public void setC(ReserveVo vo) {
		
		rvo = vo;
		
		String Checkout = vo.getReturnDate();
		String Checkin = vo.getRentalDate();
		
		System.out.println(Checkout);
		System.out.println(Checkin);
		
//		Checkout = Checkout.substring(0,10); // 2022/05/05
		Checkout = Checkout.replace("/", "");
		Checkout = Checkout.replace("-", "");
		
//		Checkin = Checkin.substring(0,10); // 2202/05/05
		Checkin = Checkin.replace("/", "");
		Checkin = Checkin.replace("-", "");
		
		int Days = 0;
		
		try {
			Date date=new SimpleDateFormat("yyMMdd").parse(Checkout);  
			Date date2 = new SimpleDateFormat("yyMMdd").parse(Checkin);
			
			Integer.parseInt(Checkout);
			
			long Sec = (date.getTime() - date2.getTime()) / 1000; // 초
			Days = (int) (Sec / (24*60*60)); // 일자수
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		int rentPay = new PayDao().rentPayCount(Integer.parseInt(vo.getDayPrice()),Days,Integer.parseInt(vo.getInsurance()));
		voc.setCarPay(rentPay);
		
	}

	public PayVo getVogf() {
		return vogf;
	}

	public void setVogf(PayVo vogf) {
		this.vogf = vogf;
	}

	public PayVo getVocf() {
		return vocf;
	}

	public void setVocf(PayVo vocf) {
		this.vocf = vocf;
	}

	public PayVo getVor() {
		return vor;
	}

	public void setVor(PayVo vor) {
		this.vor = vor;
	}

	public PayVo getVoc() {
		return voc;
	}

	public void setVoc(PayVo voc) {
		this.voc = voc;
	}

	public ReserveVo getRvo() {
		return rvo;
	}

	public void setRvo(ReserveVo rvo) {
		this.rvo = rvo;
	}

	public AccDto getAvo() {
		return avo;
	}

	public void setAvo(AccDto avo) {
		this.avo = avo;
	}

	public Flight_Vo_MyFlight getFvo() {
		return fvo;
	}

	public void setFvo(Flight_Vo_MyFlight fvo) {
		this.fvo = fvo;
	}





	
}
