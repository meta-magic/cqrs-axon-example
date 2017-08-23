package com.ecomm.app.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class MakePaymentCommand {

	@TargetAggregateIdentifier
	private String paymentId;

	private String orderId;

	private String custId;

	private double amount;

	public MakePaymentCommand(String paymentId, String orderId, String custId, double amount) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.custId = custId;
		this.amount = amount;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getCustId() {
		return custId;
	}

	public double getAmount() {
		return amount;
	}
	
}
