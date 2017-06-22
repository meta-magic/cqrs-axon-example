package com.banking.app.eventHandlers;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.banking.app.events.AccountCreatedEvent;
import com.banking.app.events.AccountCreditedEvent;
import com.banking.app.events.AccountDebitedEvent;

@Component
@ProcessingGroup("replayHandler")
public class AccountReplayEventHandler {

	private List<String> events = new ArrayList<String>();
	
	@EventHandler
	public void handle(AccountCreatedEvent createAccount) {
		System.out.println(" method invoked ");
		events.add(String.format("Account Created - Account Number: %s", createAccount.getAccountNo()));
	}
	
	@EventHandler
	public void handle(AccountCreditedEvent accountCreditedEvent) {
		System.out.println(" method invoked ");
		events.add(String.format("Account Credited for Account Number: %s, Amount credited %s, Balance %s", accountCreditedEvent.getAccountNo(), accountCreditedEvent.getAmountCredited(), (accountCreditedEvent.getBalance()+accountCreditedEvent.getAmountCredited())));
	}
	
	@EventHandler
	public void handle(AccountDebitedEvent accountDebitedEvent) {
		System.out.println(" method invoked ");
		events.add(String.format("Account Debited for Account Number: %s, Amount debited %s, Balance %s", accountDebitedEvent.getAccountNo(), accountDebitedEvent.getAmountDebited(), (accountDebitedEvent.getBalance()+accountDebitedEvent.getAmountDebited())));
	}

	public List<String> getEvents() {
		return events;
	}
	
}
