package com.naresh.bankingapp.exception;

public class ServiceException extends Exception{

	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(String message, Throwable e) {
		super(message,e);
	}
}
