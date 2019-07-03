package com.naresh.bankingapp.validator;

import javax.xml.bind.ValidationException;

import com.naresh.bankingapp.dao.AccountDAO;
import com.naresh.bankingapp.dao.impl.AccountDAOImpl;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.Account;

public class AccountValidator {

	private static AccountDAO accountDAO = new AccountDAOImpl();

	public static boolean isAccountExists(int accountId) throws ValidationException {

		boolean isValid = false;
		Account findOne;
		try {
			findOne = accountDAO.findOne(accountId);
			if (findOne != null) {
				isValid = true;
			}
		} catch (DBException e) {
			e.printStackTrace();
			throw new ValidationException("Unable to find AccountId in db" + accountId);
		}
		return isValid;

	}

	public static void validateAccounts(Account sourceAccount, Account destinationAccount) throws ValidationException {
		if (sourceAccount ==null) {
			throw new ValidationException("Source AccountId is invalid " + sourceAccount);
		}
		if (destinationAccount == null) {
			throw new ValidationException("Destination AccountId is invalid " + destinationAccount);
		}
	}

	public static void validateAccount(Account account) throws ValidationException {
		if (account == null) {
			throw new ValidationException("Account is invalid " + account);
		}
	}
	
	public static void validateBeforeWithdraw(Account account, int amount) throws ValidationException {
		if ( account.getBalance() < amount) {
			throw new ValidationException("Insufficient balance in account-" + account.getId());
		}
	}
	
	
	public static void validateBeforeCreatingAccount(Account account) throws ValidationException {
		if ( account.getUser().getId() == null) {
			throw new ValidationException("Invalid UserId " + account);
		}
	}
}
