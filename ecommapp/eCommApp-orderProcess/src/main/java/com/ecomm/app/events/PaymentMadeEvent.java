package com.ecomm.app.events;

public class PaymentMadeEvent {

	private String paymentId;

	private String orderId;

	private String custId;

	private double amount;

	public PaymentMadeEvent(String paymentId, String orderId, String custId, double amount) {
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
