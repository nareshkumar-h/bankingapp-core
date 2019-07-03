package com.naresh.bankingapp.validator;

import com.naresh.bankingapp.model.User;

public class TestUserValidator {

	public static void main(String[] args) {

		testValidateBeforeInsert();
	}

	private static void testValidateBeforeInsert() {
		String name = null;
		String email = "naresh@gmail.com";
		String password = "pass123";

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);

		try {
			UserValidator.validateBeforeInsert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
