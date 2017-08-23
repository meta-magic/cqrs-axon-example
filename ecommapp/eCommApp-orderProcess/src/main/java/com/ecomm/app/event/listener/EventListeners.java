package com.ecomm.app.event.listener;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.ecomm.app.events.CartCreatedEvent;
import com.ecomm.app.events.ItemAddedEvent;
import com.ecomm.app.events.ItemRemovedEvent;
import com.ecomm.app.events.OrderCreatedEvent;
import com.ecomm.app.events.OrderPlacedEvent;

@Component
@ProcessingGroup("cartListeners")
public class EventListeners {

	@EventHandler
	public void handle(CartCreatedEvent cartCreatedEvent){
		System.out.println(" event received  ");
	}
	
	@EventHandler
	public void handle(ItemAddedEvent itemAddedEvent){
		System.out.println(" event received  ");
	}
	
	@EventHandler
	public void handle(ItemRemovedEvent itemRemovedEvent){
		System.out.println(" event received  ");
	}
	
	@EventHandler
	public void handle(OrderPlacedEvent orderPlacedEvent){
		System.out.println(" =============> order placed ");
	}
	
	@EventHandler
	public void handle(OrderCreatedEvent orderPlacedEvent){
		System.out.println(" =============> order created ");
	}
}
