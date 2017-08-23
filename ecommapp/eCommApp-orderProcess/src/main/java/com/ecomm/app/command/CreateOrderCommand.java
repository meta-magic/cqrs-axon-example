package com.ecomm.app.command;

import java.util.Date;
import java.util.List;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.ecomm.app.aggregate.Items;

public class CreateOrderCommand {

	@TargetAggregateIdentifier
	private String orderId;
	
	private String custId;
	
	private Date orderDate;
	
	private boolean orderComplete;
	
	private double totalAmount;
	
	private List<Items> items;

	public CreateOrderCommand(String orderId, String custId, Date orderDate, boolean orderComplete, double totalAmount, List<Items> items) {
		super();
		this.orderId = orderId;
		this.custId = custId;
		this.orderDate = orderDate;
		this.orderComplete = orderComplete;
		this.items = items;
		this.totalAmount = totalAmount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date date) {
		this.orderDate = date;
	}

	public boolean isOrderComplete() {
		return orderComplete;
	}

	public void setOrderComplete(boolean orderComplete) {
		this.orderComplete = orderComplete;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
