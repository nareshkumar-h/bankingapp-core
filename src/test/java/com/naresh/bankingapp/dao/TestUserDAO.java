package com.naresh.bankingapp.dao;

import java.util.List;

import com.naresh.bankingapp.dao.impl.UserDAOImpl;
import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.User;

public class TestUserDAO {

	static UserDAO userDAO = new UserDAOImpl();
	
	public static void main(String[] args)  {
		
		//registerUser();
		//login();
		
		listUsers();
	}

	private static void listUsers() {
		try {
			List<User> list = userDAO.list();
			for (User user : list) {
				System.out.println(user);
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
	
	public static void login() {
		try {
			String email = "john@gmail.com";
			String password  ="pass123";
			User user = userDAO.login(email, password);
			System.out.println(user);
		} catch (DBException e) {
			e.printStackTrace();
		}
		
	}

	private static void registerUser() {
		String name = "naresh";
		String email = "naresh@gmail.com";
		String password = "pass123";
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		
		try {
			userDAO.insert(user);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
}
