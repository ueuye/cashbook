package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
					+ "cashbook(member_id, category, subcategory, cashbook_date, price, memo, updatedate, createdate)"
					+ "VALUES(?,?,?,?,?,?,NOW(),NOW())";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,cashbook.getMemberId());
			stmt.setString(2, cashbook.getCategory());
			stmt.setString(3, cashbook.getSubcategory());
			stmt.setString(4, cashbook.getCashbookDate());
			stmt.setInt(5, cashbook.getPrice());
			stmt.setString(6, cashbook.getMemo());
			
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
	
	// 태그별 가계부리스트 출력
	public List<Cashbook> selectCashbookListByTag(String memberId, String word, int beginRow, int rowPerPage){
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT c.cashbook_no cashbookNo, c.category category, c.subcategory subcategory, c.price price, c.cashbook_date cashbookDate, c.memo memo "
				+ "FROM cashbook c INNER JOIN hashtag h "
				+ "ON c.cashbook_no = h.cashbook_no "
				+ "WHERE c.member_id = ? AND h.word = ? "
				+ "ORDER BY c.cashbook_date DESC "
				+ "LIMIT ?,?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setString(2,word);
			stmt.setInt(3,beginRow);
			stmt.setInt(4,rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setSubcategory(rs.getString("subcategory"));
				c.setPrice(rs.getInt("price"));
				c.setCashbookDate(rs.getString("cashbookDate"));
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
	
	// 태그별 가계부 개수 출력
	public int cashbookByTagCnt(String memberId, String word){
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) "
				+ "FROM cashbook c INNER JOIN hashtag h "
				+ "ON c.cashbook_no = h.cashbook_no "
				+ "WHERE c.member_id = ? AND h.word = ? "
				+ "ORDER BY c.cashbook_date DESC ";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setString(2,word);
			rs = stmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
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
	
		return cnt;
	}
			
	// 월간 수입, 지출 출력
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth){
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT cashbook_no cashbookNo, category, subcategory, price, cashbook_date cashbookDate "
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
				c.setSubcategory(rs.getString("subcategory"));
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
	// 일간 수입, 지출 총액 출력
	public List<Cashbook> selectCashbookSum(String memberId, int targetYear, int targetMonth){
		DecimalFormat decFormat = new DecimalFormat("###,###");
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT category, sum(price) price, cashbook_date cashbookDate FROM cashbook "
				+ "WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? "
				+ "AND category = '수입' "
				+ "GROUP BY cashbook_date "
				+ "UNION "
				+ "SELECT category, sum(price) price, cashbook_date cashbookDate FROM cashbook "
				+ "WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? "
				+ "AND category = '지출' "
				+ "GROUP BY cashbook_date";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,targetYear);
			stmt.setInt(3,targetMonth);
			stmt.setString(4,memberId);
			stmt.setInt(5,targetYear);
			stmt.setInt(6,targetMonth);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCategory(rs.getString("category"));
				c.setSumPrice(decFormat.format(rs.getInt("price")));
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
	public List<Cashbook> selectCashbookListByDate(String memberId, int targetYear, int targetMonth, int targetDate){
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT cashbook_no cashbookNo, category, subcategory, price, memo "
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
				c.setSubcategory(rs.getString("subcategory"));
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
	
	// 가계부 상세정보 출력
	public Cashbook selectCashbookOne(int cashbookNo){
		
		Cashbook cashbook = new Cashbook();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT cashbook_no cashbookNo, category, subcategory, price, memo "
				+ "FROM cashbook "
				+ "WHERE cashbook_no = ? ";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,cashbookNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				cashbook.setCashbookNo(rs.getInt("cashbookNo"));
				cashbook.setCategory(rs.getString("category"));
				cashbook.setSubcategory(rs.getString("subcategory"));
				cashbook.setPrice(rs.getInt("price"));
				cashbook.setMemo(rs.getString("memo"));
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
		return cashbook;
	}
	
	// 가계부 상세정보 수정
	public int modifyCashbook(Cashbook cashbook) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE cashbook SET category = ?, subcategory=?, price = ?, memo = ? , updatedate=NOW() WHERE cashbook_no = ?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cashbook.getCategory());
			stmt.setString(2, cashbook.getSubcategory());
			stmt.setInt(3, cashbook.getPrice());
			stmt.setString(4, cashbook.getMemo());
			stmt.setInt(5, cashbook.getCashbookNo());
			
			row = stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 가계부 삭제
	public int removeCashbook(int cashbookNo) {
		// 반환할 변수 생성
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM cashbook WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			row = stmt.executeUpdate();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 월별 총 수입, 지출 출력
	public List<Map<String, Object>> monthSum(String memberId, int targetYear, int targetMonth){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT category , sum(price) sum FROM cashbook WHERE member_id=? AND YEAR(cashbook_date)=? and MONTH(cashbook_date)=? GROUP BY category";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,targetYear);
			stmt.setInt(3,targetMonth);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("category", rs.getString("category"));
				map.put("sum", rs.getInt("sum"));
				list.add(map);
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
	
	// 월, 카테고리별 소비현황
	public List<Map<String, Object>> monthCateSum(String memberId, int targetYear, int targetMonth){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT subcategory, sum(price) sum FROM cashbook WHERE category='지출' AND member_id=? AND YEAR(cashbook_date)=? AND MONTH(cashbook_date)=? GROUP BY subcategory";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,targetYear);
			stmt.setInt(3,targetMonth);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("subcategory", rs.getString("subcategory"));
				map.put("sum", rs.getInt("sum"));
				list.add(map);
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
	
	// 최근 3개월 지출 현황
	public List<Map<String, Object>> threeMonthSum(String memberId, int targetYear, int targetMonth){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT MONTH(cashbook_date) month, sum(price) sum FROM cashbook WHERE category='지출' AND member_id=? AND YEAR(cashbook_date)=? AND MONTH(cashbook_date)=? "
				+ "UNION "
				+ "SELECT MONTH(cashbook_date) month, sum(price) sum FROM cashbook WHERE category='지출' AND member_id=? AND YEAR(cashbook_date)=? AND MONTH(cashbook_date)=? "
				+ "UNION "
				+ "SELECT MONTH(cashbook_date) month, sum(price) sum FROM cashbook WHERE category='지출' AND member_id=? AND YEAR(cashbook_date)=? AND MONTH(cashbook_date)=?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,targetYear);
			stmt.setInt(3,targetMonth-2);
			stmt.setString(4,memberId);
			stmt.setInt(5,targetYear);
			stmt.setInt(6,targetMonth-1);
			stmt.setString(7,memberId);
			stmt.setInt(8,targetYear);
			stmt.setInt(9,targetMonth);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("month", rs.getString("month"));
				map.put("sum", rs.getInt("sum"));
				list.add(map);
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
