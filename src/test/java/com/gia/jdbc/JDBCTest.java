package com.gia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://172.16.1.50:3306/test2";
			String username = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			// 插入数据
			String sql = "insert into t_message(name, content) values('jack', 'abcdefghijklm')";
			int result = stmt.executeUpdate(sql);

			sql = "insert into t_message(name, content) values('rose', 'dfkdsjfiuewytjkldjslak')";
			result = stmt.executeUpdate(sql);

			sql = "select * from t_message";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getLong(1) + ":" + rs.getString(2) + "," + rs.getString(3));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
