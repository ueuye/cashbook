package cash.model;

import java.sql.*;

import cash.vo.Member;

public class MemberDao {
	// 회원정보수정
	public int modifyMember(Member member, String currentPw) {
		// 반환할 변수 생성
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "UPDATE member SET member_pw = PASSWORD(?) , updatedate = NOW() WHERE member_id = ? and member_pw = PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberPw());
			stmt.setString(2,member.getMemberId());
			stmt.setString(3,currentPw);
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
	
	// 회원탈퇴
	public int removeMember(Member member) {
		// 반환할 변수 생성
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberId());
			stmt.setString(2,member.getMemberPw());
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
	
	// 회원 상세정보
	public Member selectMemberOne(String memberId) {
		// 반환할 Member객체 생성
		Member member = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT member_id memberId, member_pw memberPw FROM member WHERE member_id =?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("memberId"));
				member.setMemberPw(rs.getString("memberPw"));
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
		
		return member;
	}
	
	// 회원가입 메서드
	public int insertMember(Member member) {
		// 반환할 변수 생성
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO member(member_id, member_pw, updatedate, createdate) VALUES(?, PASSWORD(?), NOW(), NOW())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberId());
			stmt.setString(2,member.getMemberPw());
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
	
	// 로그인 메서드
	public Member SelectMemberById(Member paramMember) {
		Member returnMember = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId FROM member WHERE member_id =? AND member_pw = PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,paramMember.getMemberId());
			stmt.setString(2,paramMember.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
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
		
		return returnMember;
	}
	
	//아이디 중복검사
	public int idCheck(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member WHERE member_id = ?";
		
		int idCount = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				idCount = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}

		return idCount;
	}
}
