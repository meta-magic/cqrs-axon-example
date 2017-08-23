package com.ecomm.app.event;

public class ShipmentDeliveredEvent {
	
	private String shipmentId;

	private String orderId;

	private String custId;
	
	private boolean delivered;
	
	public ShipmentDeliveredEvent(String shipmentId, String orderId, String custId, boolean delivered){
		this.shipmentId = shipmentId;
		this.orderId = orderId;
		this.custId = custId;
		this.delivered = delivered;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getCustId() {
		return custId;
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public boolean isDelivered() {
		return delivered;
	}
	
}
