package util;

import java.util.Scanner;

public class InputUtil {
	
	public static final Scanner sc = new Scanner(System.in);
	
	public static int getInt() /*throws Exception*/{
		return Integer.parseInt(sc.nextLine());
	}

}
