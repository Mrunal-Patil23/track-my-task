package com.tmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public Connection getConnection() throws ClassNotFoundException {
		Connection conn = null;

		//Class.forName("com.mysql.cj.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/track_my_task", "root", "");
		} catch (SQLException e) {
			System.out.println("Db connection error");
			e.printStackTrace();
		}
		return conn;
	}
}
