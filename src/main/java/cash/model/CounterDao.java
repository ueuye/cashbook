package cash.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CounterDao {
	// 오늘 날짜 첫번째 접속 -> insert
	public int insertCounter(Connection conn) {
		int row = 0;
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO counter VALUES(CURDATE(),1)";
			stmt = conn.prepareStatement(sql);
			row = stmt.executeUpdate();
		}catch(Exception  e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return row;
	}
	// 오늘 날짜 첫번째가 아니면 -> update
	public int updateCounter(Connection conn) {
		int row = 0;
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE counter SET counter_num = counter_num+1 WHERE counter_date = CURDATE()";
			stmt = conn.prepareStatement(sql);
			row = stmt.executeUpdate();
		}catch(Exception  e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return row;
	}
	// 오늘 날짜 카운터 -> select
	public int selectCounterCurdate(Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int counter = 0;
		try {
			String sql = "SELECT counter_num FROM counter WHERE counter_date = CURDATE()";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				counter = rs.getInt(1);
			}
		}catch(Exception  e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return counter;
	}
	// 누적 카운터 -> select
	public int selectCounterAll(Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		try {
			String sql = "SELECT SUM(counter_num) FROM counter";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		}catch(Exception  e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return totalCount;
	}
}
