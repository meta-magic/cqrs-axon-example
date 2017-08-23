package com.ecomm.app.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.ecomm.app.command.MakePaymentCommand;
import com.ecomm.app.events.PaymentMadeEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class PaymentAggregate {

	@AggregateIdentifier
	private String paymentId;
	
	private String orderId;
	
	private String custId;
	
	private double amount;
	
	public PaymentAggregate(){}
	
	@CommandHandler
	public PaymentAggregate(MakePaymentCommand makePaymentCommand){
		apply(new PaymentMadeEvent(makePaymentCommand.getPaymentId(), makePaymentCommand.getOrderId(), makePaymentCommand.getCustId(), makePaymentCommand.getAmount()));
	}
	
	@EventSourcingHandler
	public void handle(PaymentMadeEvent paymentMadeEvent){
		this.paymentId = paymentMadeEvent.getPaymentId();
		this.orderId = paymentMadeEvent.getOrderId();
		this.custId = paymentMadeEvent.getCustId();
		this.amount = paymentMadeEvent.getAmount();
	}
	
}
