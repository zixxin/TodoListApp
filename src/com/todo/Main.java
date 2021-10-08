package com.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			//SQLite 데이터베이 파일에 연결
			String dbFile = "todolist.db";
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			TodoMain.start();
			/*
			//데이터 조회
			System.out.println("\n*** 데이터 조회 ***");
			Statement stat1 = conn.createStatement();
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);
			
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				String regdate = rs1.getString("regdate");
				System.out.println(id + " " + name + " " + regdate);
			}
			stat1.close();
			
			//데이터 추가
			System.out.println("\n*** 새 데이터 추가 ***");
			String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values (?, ?, ?, ?, datetime('now', 'localtime'));";
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "서강준");
			pstmt.setString(2, "남성");
			pstmt.setString(3, "2010년대");
			pstmt.setString(4, "2013년");
			int cnt = pstmt.executeUpdate(sql2);
			if (cnt>0) System.out.println("새로운 데이터가 추가되었습니다!");
			else System.out.println("[Error] 데이터 추가 오류!");
			pstmt.close();
			
			//데이터 수정
			System.out.println("\n*** 데이터 수정 ***");
			Statement stat3 = conn.createStatement();
			String sql3 = "update g_artists set name = 'IU' " 
					+ "where id=1 ;";
			int cnt3 = stat3.executeUpdate(sql3);
			if (cnt3>0) System.out.println("데이터가 수정되었습니다!");
			else System.out.println("[Error] 데이터 수정 오류!");
			stat3.close();
			
			//데이터 삭제
			System.out.println("\n*** 데이터 삭제 ***");
			Statement stat4 = conn.createStatement();
			String sql4 = "delete from g_artists where id=7 ;";
			int cnt4 = stat4.executeUpdate(sql4);
			if (cnt4>0) System.out.println("데이터가 삭제되었습니다!");
			else System.out.println("[Error] 데이터 삭제 오류!");
			stat4.close();
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	}

}