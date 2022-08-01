package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import car_main.Main;
import util.JDBCTemplate;

public class CarDao {
	
	/**
	 * 렌터카 조회
	 * @param vo
	 * @param conn
	 * @throws Exception
	 */
	public List<CarVo> carInquiry(CarVo vo, Connection conn) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CarVo> carVoList = new ArrayList<>();
		try {
			String sql = "SELECT R.RENTAL_NO, T.CAR_SIZE, C.CAR_NAME, R.CAR_UQ , R.DAY_PRICE, C.CAR_PERSON,R.OPENABLE, F.FUEL\r\n"
					+ "FROM CAR C  \r\n"
					+ "JOIN RENTAL_CAR R ON C.CAR_NO = R.CAR_NO\r\n"
					+ "JOIN TB_SIZE T ON C.CAR_SIZE = T.SIZE_NO\r\n"
					+ "JOIN FUEL F ON C.FUEL = F.FUEL_NO\r\n"
					+ "WHERE R.RENTAL_NO NOT IN  \r\n"
					+ "(SELECT RENTAL_NO   \r\n"
					+ "FROM CAR_RESERVATION  \r\n"
					+ "WHERE CANCEL_YN = 'N' AND (RENTAL_DATE BETWEEN ? AND ? \r\n"
					+ "OR RETURN_DATE BETWEEN ? AND ?)) \r\n"
					+ "AND C.CAR_PERSON > ?\r\n"
					+ "AND C.CAR_SIZE = ?\r\n"
					+ "ORDER BY RENTAL_NO";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getRentalDate());
			pstmt.setString(2, vo.getReturnDate());
			pstmt.setString(3, vo.getRentalNo());
			pstmt.setString(4, vo.getReturnDate());
			pstmt.setString(5, vo.getCarPerson());
			pstmt.setString(6, vo.getCarSize());
//			pstmt.setString(7, vo.getCarFuel());
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				vo.setRentalNo(rs.getString("RENTAL_NO"));
				vo.setCarSize(rs.getString("CAR_SIZE"));
				vo.setCarName(rs.getString("CAR_NAME"));
				vo.setDayPrice(rs.getString("DAY_PRICE"));
				vo.setCarFuel(rs.getString("FUEL"));
				
				Main.inquiryCar = vo;
				
				carVoList.add(vo);		
				
			}
			
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		return carVoList;
	}
	/**
	 * 렌터카 예약
	 * @param rVo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int carReserve(ReserveVo rVo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			
			String sql = "INSERT INTO CAR_RESERVATION (CAR_NO, RENTAL_NO, MEMBER_NO, RENTAL_DATE, RETURN_DATE, RESERVE_DATE,INSURANCE)\r\n"
					+ "VALUES (SEQ_CAR_RESERVATION.NEXTVAL,?,?,?,?, SYSDATE,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rVo.getRentalNo());
			pstmt.setString(2, rVo.getMemberNo());
			pstmt.setString(3, rVo.getRentalDate());
			pstmt.setString(4, rVo.getReturnDate());
			pstmt.setString(5, rVo.getInsurance());
			
			result = pstmt.executeUpdate();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
