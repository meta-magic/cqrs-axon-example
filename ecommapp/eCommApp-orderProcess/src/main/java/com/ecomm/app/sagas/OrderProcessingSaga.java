package com.ecomm.app.sagas;

import java.util.Date;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecomm.app.aggregate.Items;
import com.ecomm.app.command.CreateOrderCommand;
import com.ecomm.app.command.InitiateShippingCommand;
import com.ecomm.app.command.MakePaymentCommand;
import com.ecomm.app.event.ShipmentDeliveredEvent;
import com.ecomm.app.events.OrderCreatedEvent;
import com.ecomm.app.events.OrderPlacedEvent;
import com.ecomm.app.events.PaymentMadeEvent;
import static org.axonframework.eventhandling.saga.SagaLifecycle.associateWith;
import static org.axonframework.eventhandling.saga.SagaLifecycle.end;
@Saga
public class OrderProcessingSaga {

	private boolean paid = false;

	private boolean delivered = false;
	
	@Autowired
	private transient CommandGateway commandGateway;
	
	@StartSaga
	@SagaEventHandler(associationProperty="cartId")
	public void handle(OrderPlacedEvent orderPlacedEvent){
		System.out.println(" +++++++++ order placed +++++++++ ");
		String orderId = IdentifierFactory.getInstance().generateIdentifier();
		associateWith("orderId", orderId);
		double amount = 0.0;
		if(orderPlacedEvent.getItems()!=null){
			for (Items items : orderPlacedEvent.getItems()) {
				amount += items.getPrice();
			}
		}
		commandGateway.send(new CreateOrderCommand(orderId, orderPlacedEvent.getCustomerId(), new Date(), false, amount, orderPlacedEvent.getItems()));
		
	}
	
	@SagaEventHandler(associationProperty="orderId")
	public void handle(OrderCreatedEvent orderCreatedEvent){
		System.out.println(" +++++++++++ order created ++++++++++++ ");
		String paymentId = IdentifierFactory.getInstance().generateIdentifier();
		String shipmentId = IdentifierFactory.getInstance().generateIdentifier();
		associateWith("paymentId", paymentId);
		associateWith("shipmentId", shipmentId);
		commandGateway.send(new MakePaymentCommand(paymentId, orderCreatedEvent.getOrderId(), orderCreatedEvent.getCustId(), orderCreatedEvent.getTotalAmount()));
		commandGateway.send(new InitiateShippingCommand(shipmentId, orderCreatedEvent.getOrderId(), orderCreatedEvent.getCustId(), false, orderCreatedEvent.getItems()));
	}
	
	@SagaEventHandler(associationProperty="paymentId")
	public void handle(PaymentMadeEvent paymentMadeEvent){
		System.out.println(" +++++++++++ payment done ++++++++++++ ");
		paid = true;
		if(delivered){
			end();
		}
	}
	
	@SagaEventHandler(associationProperty="shipmentId")
	public void handle(ShipmentDeliveredEvent shipmentDeliveredEvent){
		System.out.println(" +++++++++++ shipment delivered ++++++++++++ ");
		delivered = true;
		if(paid){
			end();
		}
	}
	
}
