package com.ecomm.app.rest;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.GenericEventMessage;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.app.command.DeliverShipmentCommand;

@RestController
@RequestMapping("/order")
public class RequestController {
	
	@Autowired
	private CommandGateway commandGateway;
	
	/*@Autowired
	private EventStore eventStore; 

	@PostMapping("/shipmentDelivered")
	public void shipmentDelivered(String shipmentId, String orderId, String custId){
		eventStore.publish(GenericEventMessage.asEventMessage(new ShipmentDeliveredEvent(shipmentId, orderId, custId)));
	}*/
	
	@PostMapping("/shipmentDelivered")
	public void shipmentDelivered(@RequestParam String shipmentId,@RequestParam String orderId,@RequestParam String custId){
		commandGateway.send(new DeliverShipmentCommand(shipmentId, orderId, custId, true));
	}
}
