package com.ecomm.app.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CompleteOrderCommand {

	@TargetAggregateIdentifier
	private String orderId;
	
	private boolean orderComplete;
	
	public CompleteOrderCommand(String orderId, boolean orderComplete){
		this.orderId = orderId;
		this.orderComplete = orderComplete;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isOrderComplete() {
		return orderComplete;
	}

	public void setOrderComplete(boolean orderComplete) {
		this.orderComplete = orderComplete;
	}
	
}
