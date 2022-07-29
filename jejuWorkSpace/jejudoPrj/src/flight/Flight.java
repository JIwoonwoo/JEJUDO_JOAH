package flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import util.InputUtil;
import util.JDBCTemplate;

public class Flight {
	
	public static int flightMain() throws Exception {
		
			//비행기 메인 메뉴
			//비행기 예약(fr), 수정(fm), 삭제(fd), 조회(fs)
			System.out.println("----- 비행기 -----");
			System.out.println("비행기 예약은 1번 ");
			System.out.println("예약 수정은 2번");
			System.out.println("예약 삭제는 3번");
			System.out.println("예약 조회는 4번");
			
			int flightAnswer = InputUtil.sc.nextInt();
			switch(flightAnswer) {
			case 1 : System.out.println("비행기 예약으로 넘어갑니다."); break;
			case 2 : System.out.println("비행기 예약 수정으로 넘어갑니다."); break;
			case 3 : System.out.println("비행기 예약 삭제로 넘어갑니다."); break;
			case 4 : System.out.println("비행기 예약 조회로 넘어갑니다."); break;
			}
			return flightAnswer;
		
	}

	
	

}
