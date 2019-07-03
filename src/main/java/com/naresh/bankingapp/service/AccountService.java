package com.naresh.bankingapp.service;

import javax.xml.bind.ValidationException;

import com.naresh.bankingapp.dao.AccountDAO;
import com.naresh.bankingapp.dao.impl.AccountDAOImpl;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.exception.ServiceException;
import com.naresh.bankingapp.model.Account;
import com.naresh.bankingapp.model.User;
import com.naresh.bankingapp.validator.AccountValidator;

public class AccountService {

	private AccountDAO accountDAO = new AccountDAOImpl();

	public void addAccount(int userId) throws ServiceException {

		User user = new User();
		user.setId(userId);

		Account account = new Account();
		account.setUser(user);
		account.setBalance(0);

		try {
			AccountValidator.validateBeforeCreatingAccount(account);
			accountDAO.insert(account);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void deposit(int accountId, int amount) throws ServiceException {

		try {
			Account findOne = accountDAO.findOne(accountId);
			AccountValidator.validateAccount(findOne);

			accountDAO.deposit(findOne, amount);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void withdraw(int accountId, int amount) throws ServiceException {

		try {
			Account findOne = accountDAO.findOne(accountId);
			AccountValidator.validateAccount(findOne);
			accountDAO.withdraw(findOne, amount);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void fundTransfer(int sourceAccountId, int destinationAccountId, int amount) throws ServiceException {

		try {
			Account sourceAccount = accountDAO.findOne(sourceAccountId);
			Account destinationAccount = accountDAO.findOne(destinationAccountId);

			AccountValidator.validateAccounts(sourceAccount, destinationAccount);
			accountDAO.withdraw(sourceAccount, amount);
			accountDAO.deposit(destinationAccount, amount);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
