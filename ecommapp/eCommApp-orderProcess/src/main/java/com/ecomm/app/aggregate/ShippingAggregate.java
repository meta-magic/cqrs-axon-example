package com.ecomm.app.aggregate;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.ecomm.app.command.DeliverShipmentCommand;
import com.ecomm.app.command.InitiateShippingCommand;
import com.ecomm.app.event.ShipmentDeliveredEvent;
import com.ecomm.app.events.ShipmentInitiatedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

@Aggregate
public class ShippingAggregate {

	@AggregateIdentifier
	private String shipmentId;
	
	private String custId;
	
	private String orderId;
	
	private boolean delivered;
	
	private List<Items> items; 
	
	public ShippingAggregate(){
	}
	
	@CommandHandler
	public ShippingAggregate(InitiateShippingCommand initiateShippingCommand){
		apply(new ShipmentInitiatedEvent(initiateShippingCommand.getShipmentId(), initiateShippingCommand.getOrderId(), initiateShippingCommand.getCustId(), initiateShippingCommand.isDelivered(), initiateShippingCommand.getItems()));
	}
	
	@EventSourcingHandler
	public void handle(ShipmentInitiatedEvent shipmentInitiatedEvent){
		this.shipmentId = shipmentInitiatedEvent.getShipmentId();
		this.custId = shipmentInitiatedEvent.getCustId();
		this.orderId = shipmentInitiatedEvent.getOrderId();
		this.delivered = shipmentInitiatedEvent.isDelivered();
		this.items = shipmentInitiatedEvent.getItems();
	}
	
	@CommandHandler
	public void handle(DeliverShipmentCommand deliverShipmentCommand){
		apply(new ShipmentDeliveredEvent(deliverShipmentCommand.getShipmentId(), deliverShipmentCommand.getOrderId(), deliverShipmentCommand.getCustId(), deliverShipmentCommand.isDelivered()));
	}
	
	@EventSourcingHandler
	public void handle(ShipmentDeliveredEvent shipmentDeliveredEvent){
		this.shipmentId = shipmentDeliveredEvent.getShipmentId();
		this.custId = shipmentDeliveredEvent.getCustId();
		this.orderId = shipmentDeliveredEvent.getOrderId();
		this.delivered = shipmentDeliveredEvent.isDelivered();
		markDeleted();
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public String getCustId() {
		return custId;
	}

	public String getOrderId() {
		return orderId;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public List<Items> getItems() {
		return items;
	}
	
}
