package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cash.vo.Cashbook;

public class CashbookDao {
	// 가계부 추가 - 반환값 : cashbook_no 키값
	public int insertCashbook(Cashbook cashbook) {
		int cashbookNo = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력후 생성된 키값을 반환
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			String sql = "INSERT INTO "
					+ "cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate)"
					+ "VALUES(?,?,?,?,?,NOW(),NOW())";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,cashbook.getMemberId());
			stmt.setString(2, cashbook.getCategory());
			stmt.setString(3, cashbook.getCashbookDate());
			stmt.setInt(4, cashbook.getPrice());
			stmt.setString(5, cashbook.getMemo());
			
			stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys(); // cashbook_no 값이 들어가 있음
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cashbookNo;
	}
	
	// 월간 수입, 지출 출력
	public List<Cashbook> selectCashbookListByMonth(
			String memberId, int targetYear, int targetMonth){
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT cashbook_no cashbookNo, category, price, cashbook_date cashbookDate "
				+ "FROM cashbook "
				+ "WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? "
				+ "ORDER BY cashbook_date ASC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,targetYear);
			stmt.setInt(3,targetMonth);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				list.add(c);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		return list;
	}
	
	// 일간 수입/지출 출력
	public List<Cashbook> selectCashbookListByDate(
			String memberId, int targetYear, int targetMonth, int targetDate){
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT cashbook_no cashbookNo, category, price, memo "
				+ "FROM cashbook "
				+ "WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ? "
				+ "ORDER BY cashbook_date ASC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,targetYear);
			stmt.setInt(3,targetMonth);
			stmt.setInt(4,targetDate);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				list.add(c);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		return list;
	}
}
