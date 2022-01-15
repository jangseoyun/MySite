package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao {

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

	// 메소드 g,s

	// 메소드 일반
	public void getConnection() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close() {
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
	
	// -----------views count--------------------------------------------------
	public int boardViews(int bNo) {
		
		this.getConnection();
		int count = 0;
		
		try {
			// 문자열
			String query = "";
			query += " update board ";
			query += " set hit = hit+1 ";
			query += " where board.no = ? ";
			
			//쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setInt(1, bNo);
			
			//실행
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return count;
	}
	
	// -----------list 출력--------------------------------------------------
	public List<BoardVo> boardList() {

		this.getConnection();

		List<BoardVo> bList = new ArrayList<BoardVo>();
		BoardVo boardVo = null;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열
			String query = "";
			query += " select  board.no bNo, ";
			query += "         users.no uNo, ";
			query += "         board.user_no user_no, ";
			query += "         board.title title, ";
			query += "         users.name name, ";
			query += "         board.hit hit, ";
			query += "         to_char(board.reg_date, 'yy-mm-dd HH24:MI') reg_date ";
			query += " from users , board ";
			query += " where users.no = board.user_no ";
			query += " order by board.reg_date desc ";

			// 쿼리문
			pstmt = conn.prepareStatement(query);

			// 바인딩 x

			// 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int bNo = rs.getInt("bNo");
				int uNo = rs.getInt("uNo");
				int userNo = rs.getInt("user_no");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");


				boardVo = new BoardVo(bNo, title, hit, regDate, uNo, name,userNo);
				bList.add(boardVo);

			}

			// 4.결과처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return bList;
	}

	// -----------insert 등록--------------------------------------------------
	public int boardInsert(String title, String content, int uNo) {

		this.getConnection();
		int count = 0;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열
			String query = "";
			query += " insert into board ";
			query += " values(seq_board_no.nextval,?,?,0,sysdate,?) ";

			// 쿼리문
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, uNo);

			// 실행
			rs = pstmt.executeQuery();

			System.out.println(count + "건을 등록하였습니다");

			// 4.결과처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return count;
	}

	// -----------delete 삭제--------------------------------------------------
	public int baordDelete(int bNo) {

		this.getConnection();
		int count = 0;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열
			String query = "";
			query += " delete from board ";
			query += " where no = ? ";
			
			//쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setInt(1, bNo);
			
			//실행
			count = pstmt.executeUpdate();
			
			System.out.println(count+"건이 삭제되었습니다");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return count;
	} 
	
	// -----------getBoard 선택 게시판 정보 불러오기-------------------------------------
	public BoardVo getBoard(int bNo) {
		
		BoardVo boardVo = null;
		
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열
			String query = "";
			query += " select  users.name name, ";
			query += "         board.hit hit, ";
			query += "         to_char(board.reg_date, 'yyyy-mm-dd') reg_date, ";
			query += "         board.title title, ";
			query += "         board.content content, ";
			query += "         board.no bNo";
			query += " from users, board ";
			query += " where users.no = board.user_no ";
			query += " and board.no = ? ";
			
			//쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setInt(1, bNo);

			//실행
			rs = pstmt.executeQuery();
			
			rs.next();
			int no = rs.getInt("bNo");
			String name = rs.getString("name");
			int hit = rs.getInt("hit");
			String regDate = rs.getString("reg_date");
			String title = rs.getString("title");
			String content = rs.getString("content");
			
			boardVo = new BoardVo(no,title,content,hit,regDate,name);
			
			System.out.println(boardVo);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return boardVo;
		
	}
	
	// -----------update 수정------------------------------------------------
	public int boardUpdate(BoardVo boardVo) {
		
		this.getConnection();
		int count = 0;
		
		try {
			
			//문자열
			String query = "";
			query += " update board ";
			query += " set title = ?, ";
			query += "     content = ? ";
			query += " where no = ? ";
			
			//쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getbNo());
			
			//실행
			count = pstmt.executeUpdate();
			System.out.println(count+"건이 수정되었습니다");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return count;
	}

}
