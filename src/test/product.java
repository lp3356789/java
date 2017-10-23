package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class product {
	public int getp(String product) {
		int cost = 0;
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root123");
			pstmt = conn.prepareStatement("select pro_price from product where pro_no='" + product + "'");
			rs = pstmt.executeQuery();
			if (rs.next())
				cost = rs.getInt(1);
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cost;
	}

	public String getn(String product) {
		String name = null;
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root123");
			pstmt = conn.prepareStatement("select pro_name from product where pro_no='" + product + "'");
			rs = pstmt.executeQuery();
			if (rs.next())
				name = rs.getString(1);
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
