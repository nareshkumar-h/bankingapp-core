package com.naresh.bankingapp.service;

import java.util.List;

import com.naresh.bankingapp.exception.ServiceException;
import com.naresh.bankingapp.model.User;

public class TestUserService {

	private static UserService userService = new UserService();
	
	public static void main(String[] args)  {

		testAddUser();
		testListUsers();
		
	}

	private static void testAddUser() {
		String name = "Naresh";
		String email = "naresh@gmail.com";
		String password = "pass123";	
		
		try {
			userService.addUser(name, email, password);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private static void testListUsers() {		
		
		List<User> list;
		try {
			list = userService.list();
			System.out.println("List Users");
			for (User user : list) {
				System.out.println(user);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
