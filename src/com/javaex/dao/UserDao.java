package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	// 필드
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자

	// 메소드 gs

	// 메소드 일반
	
	// 연결하기
	private void getConnection() {

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		}

	}
	

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	//-------------insert 저장------------------------------
	//저장메소드 (회원가입)
	public int insert(UserVo userVo) {
		
		int count = 0;
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into users ";
			query += " values (seq_users_no.nextval,?,?,?,?) ";
			
			// 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);
			// 바인딩
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());
			
			//실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count+"건이 등록되었습니다(UserDao)");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return count;
		
	} 
	//-------------getUser------------------------------
	//1명의 회원정보 가져오기 (로그인용)
	public UserVo getUser(String id, String password) {
		
		UserVo userVo = null;
		
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  no, ";
			query += "         name ";
			query += " from users ";
			query += " where id = ? ";
			query += " and password = ? ";
			
			// 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);
			
			// 바인딩
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			//실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				
				int no = rs.getInt("no");
				String name = rs.getString("name");
				
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return userVo;
	}
	//-------------update------------------------------
	public int userUpdate(UserVo userVo) {
		
		int count = 0;
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update users ";
			query += " set password = ?, ";
			query += "     name = ?, ";
			query += "     gender = ? ";
			query += " where no = ? ";
			
			// 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);
			
			// 바인딩
			pstmt.setString(1, userVo.getPassword());
			pstmt.setString(2, userVo.getName());
			pstmt.setString(3, userVo.getGender());
			pstmt.setInt(4, userVo.getNo());
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count+"건이 수정되었습니다(UserDao)");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return count;
	}
	//-------------getPerson 회원정보수정------------------------------
	public UserVo getPerson(int no) {
		
		this.getConnection();
		
		UserVo userVo = null;
		
		try{
			
			//3. 쿼리문 문자열
			String query = "";
			query += " select  id, ";
			query += "         password, ";
			query += "         name, ";
			query += "         gender ";
			query += " from users ";
			query += " where no = ? ";
			
			//문자열 -> 쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setInt(1, no);
			
			//실행
			rs = pstmt.executeQuery();
			
			rs.next();
			String id = rs.getString("id");
			String password = rs.getString("password");
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			
			userVo = new UserVo(id,password,name,gender);

			System.out.println(userVo);
			
		}catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return userVo;
	}
	
	

}
