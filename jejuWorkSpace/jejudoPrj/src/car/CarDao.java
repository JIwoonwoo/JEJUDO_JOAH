package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.Main;
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
		List<CarVo> carVoList = null;
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
			
			carVoList = new ArrayList<CarVo>();
			
			while(rs.next()) {
				CarVo vo2 = new CarVo();
				
				String no = rs.getString("RENTAL_NO");
				String name = rs.getString("CAR_NAME");
				String size = rs.getString("CAR_SIZE");
				String uq = rs.getString("CAR_UQ");
				String fuel = rs.getString("FUEL");
				String openable = rs.getString("OPENABLE");
				String price = rs.getString("DAY_PRICE");
				
				vo2.setRentalNo(no);
				vo2.setCarName(name);
				vo2.setCarSize(size);
				vo2.setCarUq(uq);
				vo2.setCarFuel(fuel);
				vo2.setOpen_yn(openable);
				vo2.setDayPrice(price);
				
				carVoList.add(vo2);
				
				
				
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
			pstmt.setInt(2, rVo.getMemberNo());
			pstmt.setString(3, rVo.getRentalDate());
			pstmt.setString(4, rVo.getReturnDate());
			pstmt.setString(5, rVo.getInsurance());
			
			result = pstmt.executeUpdate();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public List<ReserveVo> reserveInquiry(Connection conn, int input)throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReserveVo> reserveVoList = null;
		
		try {
			String sql = "SELECT 예약.CAR_NO,RENTAL_NO, RENTAL_DATE, RETURN_DATE, RESERVE_DATE, 보험.INSURANCE, DAY_PRICE \r\n"
					+ "FROM CAR_RESERVATION 예약 \r\n"
					+ "JOIN INSURANCE 보험 ON 예약.INSURANCE = 보험.INSURANCE_NO \r\n"
					+ "JOIN RENTAL_CAR 렌트 USING(RENTAL_NO)  \r\n"
					+ "WHERE CANCEL_YN = 'N'\r\n"
					+ "AND PAID_YN = 'Y'\r\n"
					+ "AND 예약.MEMBER_NO = ?\r\n"
					+ "ORDER BY CAR_NO";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			rs = pstmt.executeQuery();
			
			reserveVoList = new ArrayList<ReserveVo>();
			
			while(rs.next()) {
				
				ReserveVo rVo = new ReserveVo();
				
				int reserveNo = rs.getInt("CAR_NO");
				int rentalNo = rs.getInt("RENTAL_NO");
				String rentalDate = rs.getString("RENTAL_DATE");
				String returnDate = rs.getString("RETURN_DATE");
				String reserveDate = rs.getString("RESERVE_DATE");
				String insurance = rs.getString("INSURANCE");
				String dayPrice = rs.getString("DAY_PRICE");
				
				rVo.setReserveNo(reserveNo);
				rVo.setRentalNo(rentalNo);
				rVo.setRentalDate(rentalDate);
				rVo.setReturnDate(returnDate);
				rVo.setReserveDate(reserveDate);
				rVo.setInsurance(insurance);
				rVo.setDayPrice(dayPrice);
				
				reserveVoList.add(rVo);
			}
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		return reserveVoList;
	}
	public int carCancel(ReserveVo rVo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "UPDATE CAR_RESERVATION SET CANCEL_YN = 'Y' WHERE MEMBER_NO = ? AND CAR_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Main.loginNo);
			pstmt.setInt(2, rVo.getReserveNo());
			
//			Main.loginNo;
			
			result = pstmt.executeUpdate();
			
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return result;
	}
	public ReserveVo detailInquiry(int rentalNo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReserveVo vo = null;
		
		try {
			String sql = "SELECT C.CAR_NAME,T.CAR_SIZE,F.FUEL,R.CAR_UQ,R.OPENABLE FROM CAR C JOIN RENTAL_CAR R USING(CAR_NO) JOIN FUEL F ON C.FUEL = F.FUEL_NO JOIN TB_SIZE T ON C.CAR_SIZE = T.SIZE_NO WHERE R.RENTAL_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rentalNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				String size = (rs.getString("CAR_SIZE"));
				String uq = (rs.getString("CAR_UQ"));
				String name = (rs.getString("CAR_NAME"));
				String fuel = (rs.getString("FUEL"));
				String openable = (rs.getString("OPENABLE"));
				
				vo = new ReserveVo();
				vo.setCarUq(uq);
				vo.setSize(size);
				vo.setName(name);
				vo.setFuel(fuel);
				vo.setOpenable(openable);
			}
			
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}
		
		return vo;
	}
	public int insuranceEdit(ReserveVo rVo, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			
			String sql = "UPDATE CAR_RESERVATION SET INSURANCE = ? WHERE MEMBER_NO = ? AND CAR_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rVo.getInsurance());
			pstmt.setInt(2, rVo.getMemberNo());
			pstmt.setInt(3, rVo.getReserveNo());
			
			result = pstmt.executeUpdate();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	

}
