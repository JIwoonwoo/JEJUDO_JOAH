package accom;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;

import car.Parsing;

public class Practice {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("체크인 날짜를 입력하세요.");
		String checkin = sc.nextLine();

		// 날짜형식지정
				SimpleDateFormat f = new SimpleDateFormat("yyMMdd");

				// 오늘날짜 yyyy-MM-dd로 생성
				String todayfm = f.format(new Date(System.currentTimeMillis()));

				// 비교할 date와 today를데이터 포맷으로 변경
				Date date;
				Date today;
				int result;
				try {
					date = new Date(f.parse(checkin).getTime());
					today = new Date(f.parse(todayfm).getTime());
					// compareTo메서드를 통한 날짜비교
					result = today.compareTo(date);

					if (result > 0) {// 예약불가
						System.out.println("과거");
					} else if (result < 0) {// 예약가능
						System.out.println("미래");
					} else {// 예약가능
						System.out.println("지금");
					}
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("[error] 에러낫음!");
				}
		// 조건문

	}
}
