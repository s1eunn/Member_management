package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	public static Connection getConnection() {
		Connection conn = null;
		
		String url = "jdbc:mysql://localhost:3306/thisisjava";
		String user="root";
		String pass = "una0502a@@";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pass);	
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public static void close(PreparedStatement pstmt,Connection conn) {
		try {
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt,Connection conn) {
		try {
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, Statement stmt,Connection conn) {
		try {
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
