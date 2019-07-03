package com.naresh.bankingapp.util;

import java.sql.Connection;

public class TestConnection {

	public static void main(String[] args) {
		Connection connection = ConnectionUtil.getConnection();
		System.out.println(connection);
	}
}
