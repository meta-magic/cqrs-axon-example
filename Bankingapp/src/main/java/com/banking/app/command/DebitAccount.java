package com.banking.app.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class DebitAccount {

	@TargetAggregateIdentifier
	private final String accountNo;
	private final Double amount;

	public DebitAccount(String account, Double amount) {
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