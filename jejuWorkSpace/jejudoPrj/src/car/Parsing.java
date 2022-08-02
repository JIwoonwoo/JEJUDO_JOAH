package car;

import java.util.Scanner;

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
}
