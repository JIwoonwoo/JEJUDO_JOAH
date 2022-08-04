package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static int loginNo;
	public static void main(String[] args) throws Exception {
		System.out.println("제주도 좋아~");
		System.out.println("여기가 메인입니다~");
		
//		vo.getPrice(); // 1박요금
//		vo.getCheckin();
		String Checkout = "2022-08-05 654684311684";
		String Checkin = "2022/08/02 654684311684";
		
		Checkout = Checkout.substring(0,10); // 2022/05/05
		Checkout = Checkout.replace("/", "");
		Checkout = Checkout.replace("-", "");
		
		Checkin = Checkin.substring(0,10); // 2202/05/05
		Checkin = Checkin.replace("/", "");
		Checkin = Checkin.replace("-", "");
		
		Date date=new SimpleDateFormat("yyMMdd").parse(Checkout);  
		Date date2=new SimpleDateFormat("yyMMdd").parse(Checkin);  
		
		Integer.parseInt(Checkout);
		
		long Sec = (date.getTime() - date2.getTime()) / 1000; // 초
		int Days = (int)(Sec / (24*60*60)); // 일자수
		
		System.out.println(Days);
		
	}

}
