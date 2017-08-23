package com.ecomm.app.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class PlaceOrderCommand {
	
	@TargetAggregateIdentifier
	private String cartId;
	
	private String customerId;

	public PlaceOrderCommand(String cartId, String customerId) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
