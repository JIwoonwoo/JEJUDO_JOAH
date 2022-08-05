package accom;

import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import car.Parsing;
import main.Main;
import survey.SurveyService;
import survey.SurveyVo;
import util.JDBCTemplate;

public class AccService {

	public List<AccDto> accSearch(AccDto dto) {

		// 비지니스 로직
		if (dto.getPeople() < 1) {
			// 인원이 1명이 안되므로 진행 불가
			return null;
		}

		// 날짜예약 로직
		int checkindate = Parsing.getInt(dto.getCheckin());
		int checkoutdate = Parsing.getInt(dto.getCheckout());

		if (checkoutdate <= checkindate) {
			return null;
		}

		// 과거여행금지
		// 날짜형식지정
		SimpleDateFormat f = new SimpleDateFormat("yyMMdd");

		// 오늘날짜 yyyy-MM-dd로 생성
		String todayfm = f.format(new Date(System.currentTimeMillis()));

		// 비교할 date와 today를데이터 포맷으로 변경
		Date date;
		Date today;
		int timeresult;
		try {
			date = new Date(f.parse(dto.getCheckin()).getTime());
			today = new Date(f.parse(todayfm).getTime());
			// compareTo메서드를 통한 날짜비교
			timeresult = today.compareTo(date);

			if (timeresult > 0) {// 예약불가

				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("[error] 에러낫음!");
		}

		Connection conn = null;
		List<AccDto> list = null;

		try {
			conn = JDBCTemplate.getConnection();
			SurveyVo svo = new SurveyService().search(Main.loginNo);

			list = new AccDao().accSearch(dto, conn, svo);

			if (list != null) {
				System.out.println("성공");
			} else {
				System.out.println("숙소 조회 실패");
			}

		} catch (Exception e) {
			System.out.println("에러; 숙소 조회 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return list;

	}// accSearch

	public AccDto accSelect(AccDto dto) {

		Connection conn = null;
		AccDto result = null;
		try {
			conn = JDBCTemplate.getConnection();

			result = new AccDao().accSelect(dto, conn);
			if (result != null) {
				System.out.println("숙소 조회 1개 성공");
			} else {
				System.out.println("숙소 조회 1개 실패");
			}

		} catch (Exception e) {
			System.out.println("error 숙소 조회 1개 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;

	}// accSelect

	// 정한
	public int accReserve(AccDto dto) {

		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();

			result = new AccDao().accReserve(dto, conn);

			if (result == 1) {
				System.out.println("예약 성공");
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JDBCTemplate.rollback(conn);
			System.out.println("error 숙소예약 입력 실패");
		}

		return result;

	}// accSelect

	public List<AccDto> accReservCheck() {

		Connection conn = null;
		List<AccDto> list = null;
		try {
			conn = JDBCTemplate.getConnection();
			list = new AccDao().accReservCheck(conn);

			if (list == null) {
				System.out.println("조회 실패");
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return list;

	}// accRC

	public AccDto accReCheckDetail(AccDto inputDto) {

		AccDto dto = null;

		Connection conn = null;
		try {
			conn = JDBCTemplate.getConnection();
			dto = new AccDao().accReCheckDetail(inputDto, conn);

			if (dto == null) {
				System.out.println("조회 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return dto;

	}// accRC

	public void reservCancel(int cancelno) {
		
		Connection conn = null;
		int result = 0;
		AccDto dto = new AccDto();
		try {
			conn = JDBCTemplate.getConnection();

			result = new AccDao().reservCancel(dto, conn, cancelno);

			if (result == 1) {
				System.out.println("예약 취소됨");
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JDBCTemplate.rollback(conn);
			System.out.println("error 예약취소 실패");
		}

		
		
	}

}
