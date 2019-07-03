package com.naresh.bankingapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.naresh.bankingapp.dao.UserDAO;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.User;
import com.naresh.bankingapp.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	public void insert(User user) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into users(name,email,password) values ( ?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPassword());
			int rows = pst.executeUpdate();
			System.out.println("UserDAO->insert-> rows updated:" + rows);
		} catch (SQLException e) {
			throw new DBException("Unable to insert user", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}
	
	public User login(String email,String password) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,name,email from users where email=? and password =?";
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			throw new DBException("Unable to login user", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return user;
	}
	
	public List<User> list() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		List<User> users = new ArrayList<User>();
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,name,email from users";
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new DBException("Unable to retrieve all  user", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return users;
	}

}
