package com.banking.app.events;

import java.io.Serializable;

public class AccountCreditedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String accountNo;
	private final Double amountCredited;
	private final Double balance;

	public AccountCreditedEvent(String accountNo, Double amountCredited, Double balance) {
		this.accountNo = accountNo;
		this.amountCredited = amountCredited;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public Double getAmountCredited() {
		return amountCredited;
	}

	public Double getBalance() {
		return balance;
	}
}
