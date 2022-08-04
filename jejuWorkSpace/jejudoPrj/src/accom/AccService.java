package accom;

import java.sql.Connection;
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

		if ((checkoutdate - checkindate) <= 0) {
			return null;
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

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error 숙소예약 입력 실패");
			JDBCTemplate.rollback(conn);
		}

		return result;

	}// accSelect

	public List<AccDto> accReservCheck(AccDto dto) {

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

}
