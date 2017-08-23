package com.ecomm.app.events;

public class OrderCompletedEvent {

	private String orderId;

	private boolean orderComplete;

	public OrderCompletedEvent(String orderId, boolean orderComplete) {
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
