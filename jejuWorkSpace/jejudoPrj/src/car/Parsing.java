package car;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import java.sql.Date;

public class Parsing {
	
	public static int getInt(String input) {
		int parse = 0;
		try {
			parse = Integer.parseInt(input);
			
		}catch (Exception e) {
			System.out.println("모두 입력해주세요 !");
			e.getStackTrace();
		}
		return parse;
	}
	
	public static int getInt() {
		int parse = -1;
		Scanner sc = new Scanner(System.in);
		try {
			parse = Integer.parseInt(sc.nextLine());
		}catch(Exception e) {
			System.out.println("제대로 입력해주세요");
		}
		return parse;
	}
	
	public static int getDate(String rentalDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyMMdd");
	      
	      //오늘날짜 yyyy-MM-dd로 생성
	      String todayfm = f.format(new Date(System.currentTimeMillis()));
	       
	      //비교할 date와 today를데이터 포맷으로 변경
	      Date rental;
	      int result = 0;
		try {
			 rental = new Date(f.parse(rentalDate).getTime());
			 Date today = new Date(f.parse(todayfm).getTime());
		       
		      //compareTo메서드를 통한 날짜비교
		      result = today.compareTo(rental); 

		} catch (Exception e) {
			System.out.println("날짜 비교 중 예외 발생 ! ");
			e.printStackTrace();
		}
		return result;
	     
	}
}
