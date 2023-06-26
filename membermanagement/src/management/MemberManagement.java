package management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MemberManagement {
	
	private static Scanner sc = new Scanner(System.in);
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("-----------------------------------------------------");
			System.out.println("1.회원등록 | 2.전체조회 | 3.회원조회 | 4.수정 | 5.삭제 | 0.종료");
			System.out.println("-----------------------------------------------------");
			System.out.print("선택: ");
			int select = sc.nextInt();
			
			if(select == 1) {
				insert();
			} else if(select == 2) {
				researchAll();
			} else if (select == 3) {
				researchMember();
			} else if (select == 4) {
				update();
			} else if (select == 5) {
				delete();
			} else if (select == 0) {
				break;
			}
		}
		System.out.println("프로그램 종료");
	}
	
	private static void insert() {
		System.out.println("---------");
		System.out.println("[회원 등록]");
		System.out.println("---------");
		System.out.print("아이디: ");
		String id = sc.next();
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("비밀번호: ");
		String pass = sc.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "insert into t_member(id,username,pwd) values (?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			
			System.out.println("회원 등록 완료");
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	private static void researchAll() {
		System.out.println("------------");
		System.out.println("[전체 회원 조회]");
		System.out.println("------------");
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from t_member";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String username = rs.getString("username");
				String pwd = rs.getString("pwd");
				
				System.out.println("id: "+id+" ,username: "+username+" ,pwd: "+pwd);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
	}
	
	private static void researchMember() {
		System.out.println("--------------");
		System.out.println("[회원 조회]");
		System.out.println("--------------");
		System.out.print("이름: ");
		String name = sc.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select id, pwd from t_member where username=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("id: "+rs.getString("id")+" ,pwd: "+rs.getString("pwd"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
	}
	
	private static void update() {
		System.out.println("-------------");
		System.out.println("[회원 정보 수정]");
		System.out.println("-------------");
		System.out.print("수정할 회원 이름: ");
		String name = sc.next();
		System.out.print("아이디: ");
		String id = sc.next();
		System.out.print("비밀번호: ");
		String pwd = sc.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "update t_member set id=?, pwd =? where username=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			
			pstmt.executeUpdate();
			System.out.println("수정 완료");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	private static void delete() {
		System.out.println("------------");
		System.out.println("[회원 삭제]");
		System.out.println("------------");
		System.out.print("삭제할 아이디: ");
		String id = sc.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from t_member where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			System.out.println("삭제 완료");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
}
