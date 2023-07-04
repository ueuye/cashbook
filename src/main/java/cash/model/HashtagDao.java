package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cash.vo.Hashtag;

public class HashtagDao {
	// 해시태그별 출력
	public List<Map<String, Object>> selectHashtag(String word){
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			String sql = "SELECT c.member_id memberId, c.category category, c.cashbook_date cashbookDate, c.memo memo, h.word word "
					+ "FROM cashbook c INNER JOIN hashtag h "
					+ "ON c.cashbook_no = h.cashbook_no "
					+ "WHERE h.word = ? "
					+ "ORDER BY c.createdate DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("memberId", rs.getString("memberId"));
				m.put("category", rs.getString("category"));
				m.put("cashbookDate", rs.getString("cashbookDate"));
				m.put("memo", rs.getString("memo"));
				m.put("word", rs.getString("word"));
				list.add(m);
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
		
		return list;
	}
	
	// 월별 해시태그 출력 
	public List<Map<String, Object>> selectWordCountByMonth(String memberId, int targetYear, int targetMonth){
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			String sql = "SELECT word, COUNT(*) cnt "
					+ "FROM hashtag h INNER JOIN cashbook c "
					+ "ON h.cashbook_no = c.cashbook_no "
					+ "WHERE c.member_id = ? AND year(c.cashbook_date) = ? AND month(c.cashbook_date) = ? "
					+ "GROUP BY word "
					+ "ORDER BY COUNT(*) DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("word", rs.getString("word"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
		return list;
	}
	
	// 해시태그 추가(입력)
	public void insertHashtag(Hashtag hashtag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			String sql = "INSERT INTO hashtag(cashbook_no, word, updatedate, createdate) "
					+ "VALUES(?,?,NOW(),NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, hashtag.getCashbookNo());
			stmt.setString(2, hashtag.getWord());
			stmt.executeUpdate();
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
		
		return;
	}
}
