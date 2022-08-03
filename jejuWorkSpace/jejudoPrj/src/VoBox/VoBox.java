package VoBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import accom.AccDto;
import car.ReserveVo;
import flight3.Flight_Vo;
import payment.PayVo;

public class VoBox {
	
	private PayVo vogf; // 가는비행기
	private PayVo vocf; // 오는비행기
	private PayVo vor; // 숙소
	private PayVo voc; // 자동차
	
	private ReserveVo rvo;

	public void setF(Flight_Vo vo) {
		
		vogf.setFlightGoPay(vo.get가는비행기가격);
		vocf.setFlightComePay(vo.get가는비행기가격);
		
	}
	
	public void setA(AccDto vo) {
		
		String Checkout = vo.getCheckout();
		String Checkin = vo.getCheckin();
		
		Checkout = Checkout.substring(0,10); // 2022/05/05
		Checkout = Checkout.replace("/", "");
		Checkout = Checkout.replace("-", "");
		
		Checkin = Checkin.substring(0,10); // 2202/05/05
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
		
		Checkout = Checkout.substring(0,10); // 2022/05/05
		Checkout = Checkout.replace("/", "");
		Checkout = Checkout.replace("-", "");
		
		Checkin = Checkin.substring(0,10); // 2202/05/05
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
		
		voc.setCarPay(Integer.parseInt(vo.getDayPrice())*Days);
		
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

	
}
