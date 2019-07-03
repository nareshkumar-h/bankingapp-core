package com.naresh.bankingapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.naresh.bankingapp.dao.AccountDAO;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.Account;
import com.naresh.bankingapp.model.User;
import com.naresh.bankingapp.util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	public void insert(Account account) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into accounts(user_id,balance) values ( ?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, account.getUser().getId());
			pst.setInt(2, account.getBalance());
			int rows = pst.executeUpdate();
			System.out.println("AccountDAO->insert-> rows updated:" + rows);
		} catch (SQLException e) {
			throw new DBException("Unable to insert Account", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}

	
	public void deposit(Account account, int amount) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "update accounts set balance = balance + ? where id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, amount);
			pst.setInt(2, account.getId());	
			
			int rows = pst.executeUpdate();
			System.out.println("AccountDAO->deposit-> rows updated:" + rows);
		} catch (SQLException e) {
			throw new DBException("Unable to perform deposit", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}
	
	public void withdraw(Account account, int amount) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "update accounts set balance = balance - ? where id = ?";	
			pst = con.prepareStatement(sql);
			pst.setInt(1, amount);
			pst.setInt(2, account.getId());		
			
			int rows = pst.executeUpdate();
			System.out.println("AccountDAO->withdraw-> rows updated:" + rows);
		} catch (SQLException e) {
			throw new DBException("Unable to perform withdraw", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}
	
	public void fundTransfer(Account account1, Account account2, int amount) throws DBException {
		withdraw(account1, amount);
		deposit(account2, amount);
	}
	
	public Account getUserAccount(int userId) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		Account account = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select a.id as account_id,u.id as user_id, a.balance from accounts a, users u where a.user_id = u.id and a.user_id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			if (rs.next()) {

				User user = new User();
				user.setId(rs.getInt("user_id"));

				account = new Account();
				account.setId(rs.getInt("account_id"));
				account.setBalance(rs.getInt("balance"));
				account.setUser(user);
			}
		} catch (SQLException e) {
			throw new DBException("Unable to login Account", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return account;
	}

	public Account findOne(int accountId) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		Account account = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select a.id as account_id,u.id as user_id, a.balance from accounts a, users u where a.user_id = u.id and a.id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, accountId);
			rs = pst.executeQuery();
			if (rs.next()) {

				User user = new User();
				user.setId(rs.getInt("user_id"));

				account = new Account();
				account.setId(rs.getInt("account_id"));
				account.setBalance(rs.getInt("balance"));
				account.setUser(user);
			}
		} catch (SQLException e) {
			throw new DBException("Unable to login Account", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return account;
	}

	
	public List<Account> list() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Account> accounts = new ArrayList<Account>();
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select a.id as account_id,u.id as user_id, a.balance from accounts a, users u where a.user_id = u.id";
			pst = con.prepareStatement(sql);

			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));

				Account account = new Account();
				account.setId(rs.getInt("account_id"));
				account.setBalance(rs.getInt("balance"));
				account.setUser(user);

				accounts.add(account);
			}
		} catch (SQLException e) {
			throw new DBException("Unable to get all accounts", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return accounts;
	}
}
