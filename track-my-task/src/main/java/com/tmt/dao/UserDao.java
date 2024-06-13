package com.tmt.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tmt.db.DbConnection;
import com.tmt.model.User;

public class UserDao {

	DbConnection conn = new DbConnection();

	public User checkUserLogin(String username, String password) throws SQLException, ClassNotFoundException {

		String sql = "SELECT * FROM tbl_users WHERE username=? AND password=? AND status=1";
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);

		ResultSet rs = stmt.executeQuery();
		User dbUser = new User();
		if (rs.next()) {
			dbUser.setId(rs.getInt("id"));
			dbUser.setName(rs.getString("name"));
			dbUser.setUsername(rs.getString("username"));
		} else {
			dbUser = null;
		}
		rs.close();
		stmt.close();
		return dbUser;
	}

	public User getUser(int id) throws SQLException, ClassNotFoundException {
		User dbUser = new User();
		String sql = "SELECT * FROM tbl_users WHERE id= ? AND status=1";

		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			// int dbUserId = rs.getInt("id");
			String name = rs.getString("name");
			// dbUser.setId(dbUserId);
			dbUser.setId(id);
			dbUser.setName(name);
		} else {
			dbUser = null;
		}
		rs.close();
		stmt.close();
		return dbUser;
	}

	public boolean saveUser(User newUser) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO tbl_users(name, username, password) VALUES (?, ?, ?)";
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setString(1, newUser.getName());
		stmt.setString(2, newUser.getUsername());
		stmt.setString(3, newUser.getPassword());
		
		boolean rowInserted = stmt.executeUpdate() > 0;
		stmt.close();		
		return rowInserted;
		
	}
}
