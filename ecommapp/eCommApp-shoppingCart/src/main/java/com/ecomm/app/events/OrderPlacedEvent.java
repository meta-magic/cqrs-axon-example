package com.ecomm.app.events;

import java.io.Serializable;
import java.util.List;

import com.ecomm.app.aggregate.Items;

public class OrderPlacedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885046483586380537L;

	private String cartId;
	
	private String customerId;
	
	private List<Items> items;
	
	public OrderPlacedEvent(){
		
	}

	public OrderPlacedEvent(String cartId, String customerId, List<Items> items) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.items = items;
	}

	public String getCartId() {
		return cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderPlacedEvent [cartId=" + cartId + ", customerId=" + customerId + ", items=" + items + "]";
	}
	
}
