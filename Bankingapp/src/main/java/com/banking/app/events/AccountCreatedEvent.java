package com.banking.app.events;

public class AccountCreatedEvent {

	private final String accountNo;

	public AccountCreatedEvent(String accountNo){
		this.accountNo = accountNo;
	}
	
	public String getAccountNo(){
		return this.accountNo;
	}
}
