package com.ecomm.app.listeners;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecomm.app.events.CartCreatedEvent;
import com.ecomm.app.events.ItemAddedEvent;
import com.ecomm.app.events.ItemRemovedEvent;
import com.ecomm.app.inventory.document.Item;
import com.ecomm.app.store.ItemInventory;

@Component
public class ShoppingCartListener {

	@Autowired
	private ItemInventory itemStore;
	
	@EventHandler
	public void handle(CartCreatedEvent cartCreatedEvent){
		System.out.println("====== event received : cart created  "+ cartCreatedEvent);
	}
	
	@EventHandler
	public void handle(ItemAddedEvent itemAddedEvent){
		System.out.println("====== event received : item added  "+ itemAddedEvent);
		Item item = itemStore.getItem(itemAddedEvent.getItems().getItemId());
		item.setQuantity(item.getQuantity() - itemAddedEvent.getItems().getQuantity());
		itemStore.updateItem(item);
		System.out.println("====== Store status "+ itemStore);
	}
	
	@EventHandler
	public void handle(ItemRemovedEvent itemRemovedEvent){
		System.out.println("====== event received : itme removed  "+ itemRemovedEvent);
		Item item = itemStore.getItem(itemRemovedEvent.getItems().getItemId());
		item.setQuantity(item.getQuantity() + itemRemovedEvent.getItems().getQuantity());
		itemStore.updateItem(item);
		System.out.println("====== Store status "+ itemStore);
	}
	
}
