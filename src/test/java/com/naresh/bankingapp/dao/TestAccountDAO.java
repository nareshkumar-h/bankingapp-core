package com.naresh.bankingapp.dao;

import com.naresh.bankingapp.dao.impl.AccountDAOImpl;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.Account;
import com.naresh.bankingapp.model.User;

public class TestAccountDAO {

	private static AccountDAO accountDAO = new AccountDAOImpl();

	public static void main(String[] args) {

		//testAddAccount();
		//testDeposit();
		//testWithdraw();
		//testFundTransfer();

	}
	
	private static void testFundTransfer() {
		int sourceAccountId = 1;
		int destinationAccountId = 2;
		int amount = 100;
		try {
			Account sourceAccount = accountDAO.findOne(sourceAccountId);
			Account destinationAccount = accountDAO.findOne(destinationAccountId);
			if (sourceAccount != null && destinationAccount !=null) {
				accountDAO.fundTransfer(sourceAccount, destinationAccount, amount);
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	private static void testDeposit() {
		int accountId = 1;
		int amount = 100;
		try {
			Account account = accountDAO.findOne(accountId);
			if (account != null) {
				accountDAO.deposit(account, amount);
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
	

	private static void testWithdraw() {
		int accountId = 1;
		int amount = 100;
		try {
			Account account = accountDAO.findOne(accountId);
			if (account != null) {
				accountDAO.withdraw(account, amount);
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	private static void testAddAccount() {
		int userId = 2;
		Account account = new Account();

		User user = new User();
		user.setId(userId);
		account.setUser(user);
		account.setBalance(0);

		try {
			accountDAO.insert(account);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}
