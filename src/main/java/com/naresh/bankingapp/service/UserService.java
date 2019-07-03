package com.naresh.bankingapp.service;

import java.util.List;

import com.naresh.bankingapp.dao.UserDAO;
import com.naresh.bankingapp.dao.impl.UserDAOImpl;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.exception.ServiceException;
import com.naresh.bankingapp.exception.ValidatorException;
import com.naresh.bankingapp.model.User;
import com.naresh.bankingapp.validator.UserValidator;

public class UserService {

	private UserDAO userDAO = new UserDAOImpl();

	public void addUser(String name, String email, String password) throws ServiceException {

		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);

		try {

			UserValidator.validateBeforeInsert(user);
			userDAO.insert(user);

		} catch (ValidatorException e) {
			System.out.println("Exception:" + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		} catch (DBException e) {
			System.out.println("Exception:" + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public User login(String email, String password) throws ServiceException {

		User user = null;
		try {
			UserValidator.validateBeforeLogin(email, password);
			user = userDAO.login(email, password);

		} catch (ValidatorException e) {
			System.out.println("Exception:" + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		} catch (DBException e) {
			System.out.println("Exception:" + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return user;
	}

	public List<User> list() throws ServiceException {
		List<User> list = null;
		try {
			list = userDAO.list();
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return list;
	}

}
