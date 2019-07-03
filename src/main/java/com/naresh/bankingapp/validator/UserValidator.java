package com.naresh.bankingapp.validator;

import com.naresh.bankingapp.exception.ValidatorException;
import com.naresh.bankingapp.model.User;

public class UserValidator {

	public static void validateBeforeInsert(User user) throws ValidatorException {
		if (user.getName() == null) {
			throw new ValidatorException("Invalid Name");
		}
		if (user.getEmail() == null) {
			throw new ValidatorException("Invalid Email");
		}
		if (user.getPassword() == null) {
			throw new ValidatorException("Invalid Password");
		}
	}
	
	public static void validateBeforeLogin(String email, String password) throws ValidatorException {
		
		if (email == null) {
			throw new ValidatorException("Invalid Email");
		}
		if (password == null) {
			throw new ValidatorException("Invalid Password");
		}
	}
}
