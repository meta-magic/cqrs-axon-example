package com.ecomm.app.aggregate;

import java.util.Date;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.ecomm.app.command.CompleteOrderCommand;
import com.ecomm.app.command.CreateOrderCommand;
import com.ecomm.app.events.OrderCompletedEvent;
import com.ecomm.app.events.OrderCreatedEvent;

@Aggregate
public class OrderAggregate {

	@AggregateIdentifier
	private String orderId;
	
	private String custId;
	
	private Date orderDate;
	
	private boolean orderComplete;
	
	private double totalAmount;
	
	private List<Items> items;
	
	public OrderAggregate(){
	}
	
	@CommandHandler 
	public OrderAggregate(CreateOrderCommand createOrderCommand){
		AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.getOrderId(), createOrderCommand.getCustId(), createOrderCommand.getOrderDate(), false, createOrderCommand.getTotalAmount(), createOrderCommand.getItems()));
	}
	
	@EventSourcingHandler
	public void handle(OrderCreatedEvent orderCreatedEvent){
		this.orderId = orderCreatedEvent.getOrderId();
		this.custId = orderCreatedEvent.getCustId();
		this.orderDate = orderCreatedEvent.getOrderDate();
		this.totalAmount = orderCreatedEvent.getTotalAmount();
		this.orderComplete = orderCreatedEvent.isOrderComplete();
		this.items = orderCreatedEvent.getItems();
	}
	
	@CommandHandler
	public void on(CompleteOrderCommand completeOrderCommand){
		AggregateLifecycle.apply(new OrderCompletedEvent(completeOrderCommand.getOrderId(), completeOrderCommand.isOrderComplete()));
	}
	
	@EventSourcingHandler 
	public void handle(OrderCompletedEvent orderCompletedEvent){
		this.orderId = orderCompletedEvent.getOrderId();
		this.orderComplete = orderCompletedEvent.isOrderComplete();
	}

	public String getOrderId() {
		return orderId;
	}

	public String getCustId() {
		return custId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public boolean isOrderComplete() {
		return orderComplete;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public List<Items> getItems() {
		return items;
	}
	
}
