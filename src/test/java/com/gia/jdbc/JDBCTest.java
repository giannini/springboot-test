package com.gia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://172.16.1.23:1433;DatabaseName=hishop_app1";// "jdbc:mysql://172.16.1.50:3306/test2";
			String username = "dtstack";
			String password = "dtstack201610";

			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			// 插入数据
			// String sql = "insert into t_message(name, content) values('jack',
			// 'abcdefghijklm')";
			// int result = stmt.executeUpdate(sql);
			//
			// sql = "insert into t_message(name, content) values('rose',
			// 'dfkdsjfiuewytjkldjslak')";
			// result = stmt.executeUpdate(sql);

			String sql = "select * from Hishop_Orders";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// System.out.println(rs.getLong(1) + ":" + rs.getString(2) +
				// "," + rs.getString(3));
				System.out.println(rs.getString(1) + ":" + rs.getDate(4) + "," + rs.getString(7));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
