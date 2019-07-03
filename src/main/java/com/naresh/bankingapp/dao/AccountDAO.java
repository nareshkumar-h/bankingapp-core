package com.naresh.bankingapp.dao;

import java.util.List;

import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.Account;

public interface AccountDAO {
	public void insert(Account account) throws DBException;

	public void deposit(Account account, int amount) throws DBException;

	public void withdraw(Account account, int amount) throws DBException;

	public void fundTransfer(Account account1, Account account2, int amount) throws DBException;

	public Account getUserAccount(int userId) throws DBException;

	public Account findOne(int accountId) throws DBException;

	public List<Account> list() throws DBException;
}
