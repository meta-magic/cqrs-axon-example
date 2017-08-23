package com.ecomm.app.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class RemoveItemCommand {
	
	@TargetAggregateIdentifier
	private String cartId;
	
	private String itemId;

	private int quantity;
	
	public RemoveItemCommand(){
	}

	public RemoveItemCommand(String cartId, String itemId, int quantity) {
		super();
		this.cartId = cartId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
