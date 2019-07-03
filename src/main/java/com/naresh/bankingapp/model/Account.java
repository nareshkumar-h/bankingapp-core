package com.naresh.bankingapp.model;

public class Account {

	private Integer id; //accountId
	private User user;
	private Integer balance;
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", user=" + user + ", balance=" + balance + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
}
