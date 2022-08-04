package accom;

import java.util.Scanner;

import car.Parsing;

public class Practice {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("체크인 날짜를 입력하세요.");
		String day = sc.nextLine();
		
		System.out.println("숫자로 변환");
		int rentalDate = Parsing.getInt(day);
		System.out.println(rentalDate-1);
	}
}
