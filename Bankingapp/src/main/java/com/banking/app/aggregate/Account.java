package com.banking.app.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.banking.app.command.CreateAccount;
import com.banking.app.command.CreditAccount;
import com.banking.app.command.DebitAccount;
import com.banking.app.events.AccountCreatedEvent;
import com.banking.app.events.AccountCreditedEvent;
import com.banking.app.events.AccountDebitedEvent;

@Aggregate
public class Account {

	@AggregateIdentifier
	private String accountNo;

	private Double balance;
	
	public Account(){
		
	}

	@CommandHandler
	public Account(CreateAccount createAccount) {
		AggregateLifecycle.apply(new AccountCreatedEvent(createAccount.getAccountNo()));
	}
	
	@EventSourcingHandler
	private void createAccout(AccountCreatedEvent accountCreatedEvent){
		this.accountNo = accountCreatedEvent.getAccountNo();
		this.balance = 0.0d;
	}

	/**
	 * Business Logic Cannot debit with a negative amount Cannot debit with more
	 * than a million amount (You laundering money?)
	 * 
	 * @param debitAmount
	 */
	@CommandHandler
	public void handle(DebitAccount debitAccount) {

		if (Double.compare(debitAccount.getAmount(), 0.0d) > 0 && this.balance - debitAccount.getAmount() > -1) {
			/*
			 * this.balance -= debitAmount; AccountDebitedEvent
			 * accountDebitedEvent = new AccountDebitedEvent(this.id,
			 * debitAmount, this.balance); registerEvent(accountDebitedEvent);
			 */
			AccountDebitedEvent accountDebitedEvent = new AccountDebitedEvent(this.accountNo, debitAccount.getAmount(), this.balance);
			AggregateLifecycle.apply(accountDebitedEvent);
		} else {
			throw new IllegalArgumentException("Cannot debit with the amount");
		}

	}

	/**
	 * Business Logic Cannot credit with a negative amount Cannot credit with
	 * amount that leaves the account balance in a negative state
	 * 
	 * @param creditAmount
	 */
	@CommandHandler
	public void handle(CreditAccount creditAccount) {
		if (Double.compare(creditAccount.getAmount(), 0.0d) > 0 && Double.compare(creditAccount.getAmount(), 1000000) < 0) {
			/*
			 * this.balance += creditAmount; AccountCreditedEvent
			 * accountCreditedEvent = new AccountCreditedEvent(this.id,
			 * creditAmount, this.balance); registerEvent(accountCreditedEvent);
			 */
			AccountCreditedEvent accountCreditedEvent = new AccountCreditedEvent(this.accountNo, creditAccount.getAmount(), this.balance);
			AggregateLifecycle.apply(accountCreditedEvent);
		} else {
			throw new IllegalArgumentException("Cannot credit with the amount");
		}
	}

	@EventSourcingHandler
	private void applyDebit(AccountDebitedEvent event) {
		/**
		 * This method is called as a reflection of applying events stored in
		 * the event store. Consequent application of all the events in the
		 * event store will bring the Account to the most recent state.
		 */
		this.accountNo = event.getAccountNo();
		this.balance -= event.getAmountDebited();
	}

	@EventSourcingHandler
	private void applyCredit(AccountCreditedEvent event) {
		/**
		 * This method is called as a reflection of applying events stored in
		 * the event store. Consequent application of all the events in the
		 * event store will bring the Account to the most recent state.
		 */
		this.accountNo = event.getAccountNo();
		this.balance += event.getAmountCredited();
	}

	public Double getBalance() {
		return balance;
	}
}