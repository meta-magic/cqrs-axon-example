package com.banking.app.command;

public class CreateAccount {

	private String accountNo;
	
	public CreateAccount(String accountNo) {
		this.accountNo = accountNo; 
	}

	public String getAccountNo() {
		return accountNo;
	}
	
}
