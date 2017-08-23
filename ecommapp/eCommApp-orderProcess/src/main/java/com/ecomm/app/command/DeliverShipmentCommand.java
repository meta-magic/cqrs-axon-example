package com.ecomm.app.command;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class DeliverShipmentCommand {

	@TargetAggregateIdentifier
	private String shipmentId;
	
	private String custId;
	
	private String orderId;
	
	private boolean delivered;
	
	public DeliverShipmentCommand(String shipmentId, String custId, String orderId, boolean delivered){
		this.shipmentId = shipmentId;
		this.custId = custId;
		this.orderId = orderId;
		this.delivered = delivered;
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

}
