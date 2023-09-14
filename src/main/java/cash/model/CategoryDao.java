package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDao {
	//서브카테고리목록 조회
	public ArrayList<String> selectSubcateList(String category) throws Exception {
	      ArrayList<String> list = new ArrayList<>();
	      Class.forName("org.mariadb.jdbc.Driver");
	      Connection conn = DriverManager.getConnection("jdbc:mariadb://43.202.102.1:3306/cash","root","java1234");
	      String sql = "SELECT subcategory FROM category WHERE category = ?";
	      PreparedStatement stmt = conn.prepareStatement(sql);
	      stmt.setString(1, category);
	      ResultSet rs = stmt.executeQuery();
	      while(rs.next()) {
	         list.add(rs.getString("subcategory"));
	      }
	      return list;
	   }
}
