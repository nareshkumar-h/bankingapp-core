package com.naresh.bankingapp.dao;

import java.util.List;

import com.naresh.bankingapp.exception.DBException;
import com.naresh.bankingapp.model.User;

public interface UserDAO {

	void insert(User user) throws DBException;
	public User login(String email,String password) throws DBException;
	public List<User> list() throws DBException;

}