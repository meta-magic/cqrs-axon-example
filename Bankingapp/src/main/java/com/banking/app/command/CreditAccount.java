package com.banking.app.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreditAccount {

	@TargetAggregateIdentifier
	private final String accountNo;
	private final Double amount;

	public CreditAccount(String account, Double amount) {
		this.accountNo = account;
		this.amount = amount;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public Double getAmount() {
		return amount;
	}
}