package com.sds.icto.guestbook3.dao;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.sds.icto.guestbook3.vo.guestbookvo;
@Repository

public class guestbookdao {
	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "webdb", "webdb");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return conn;
	}

	public void insert(guestbookvo vo) {
		try {
			Connection conn = getConnection();
			String sql = "insert into guestbook values (guestbook_seq.nextval, ?, ?, ?, SYSDATE)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());

			pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void delete(guestbookvo vo) {

		// DELETE FROM GUESTBOOK WHERE no=3 and password = 'ㄷㄷ' ;
		try {
			Connection conn = getConnection();
			String sql = "delete from guestbook where no = ? and password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

			pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<guestbookvo> fetchList() {
		List<guestbookvo> list = new ArrayList<guestbookvo>();

		try {
			// 1 connection 생성
			Connection conn = getConnection();

			Statement stmt = conn.createStatement();

			String sql = "select * from guestbook";
			ResultSet rs = stmt.executeQuery(sql);

			// 4 결과처리
			while (rs.next()) {
				int no = rs.getInt(1);
				String Name = rs.getString(2);
				String Password = rs.getString(3);
				String Message = rs.getString(4);
				String Date = rs.getString(5);

				guestbookvo vo = new guestbookvo();
				vo.setNo(no);
				vo.setName(Name);
				vo.setPassword(Password);
				vo.setMessage(Message);
				vo.setDate(Date);
				list.add(vo);
			}
			// 5 자원정리
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return list;

	}

}
