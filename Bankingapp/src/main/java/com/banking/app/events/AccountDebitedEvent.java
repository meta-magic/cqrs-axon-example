package com.banking.app.events;

import java.io.Serializable;

public class AccountDebitedEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String accountNo;
	private final Double amountDebited;
	private final Double balance;

	public AccountDebitedEvent(String accountNo, Double amountDebited, Double balance) {
		this.accountNo = accountNo;
		this.amountDebited = amountDebited;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public Double getAmountDebited() {
		return amountDebited;
	}

	public Double getBalance() {
		return balance;
	}
}