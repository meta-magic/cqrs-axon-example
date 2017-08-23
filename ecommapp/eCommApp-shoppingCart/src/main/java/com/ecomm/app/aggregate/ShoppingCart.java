package com.ecomm.app.aggregate;


import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.ecomm.app.commands.AddItemCommand;
import com.ecomm.app.commands.CreateCartCommand;
import com.ecomm.app.commands.EmptyCartCommand;
import com.ecomm.app.commands.PlaceOrderCommand;
import com.ecomm.app.commands.RemoveItemCommand;
import com.ecomm.app.events.CartCreatedEvent;
import com.ecomm.app.events.ItemAddedEvent;
import com.ecomm.app.events.ItemRemovedEvent;
import com.ecomm.app.events.OrderPlacedEvent;
import com.ecomm.app.inventory.document.Item;
import com.ecomm.app.store.ItemInventory;

@Aggregate
@AggregateRoot
public class ShoppingCart {

	@AggregateIdentifier
	private String cartId;
	
	private String customerId;
	
	private List<Items> items;
	
	public ShoppingCart(){
	}
	
	@CommandHandler
	public ShoppingCart(CreateCartCommand createCartCommand){
		apply(new CartCreatedEvent(createCartCommand.getCartId(), createCartCommand.getCustomerId()));
		System.out.println(" --------------> cart created "+createCartCommand);
	}
	
	@EventSourcingHandler
	public void handle(CartCreatedEvent cartCreatedEvent){
		this.cartId = cartCreatedEvent.getCartId();
		this.customerId = cartCreatedEvent.getCustomerId();
		System.out.println("++++++++++++++ Items in cart at source "+ cartCreatedEvent);
	}
	
	@CommandHandler
	public void handleCommand(AddItemCommand addItemCommand, ItemInventory itemInventory) throws Exception{
		Item itemsRef = itemInventory.getItem(addItemCommand.getItemId());
		if(itemsRef.getQuantity() - addItemCommand.getQuantity() < 0){
			throw new Exception("Not too many items are in stock");
		} else {
			Items items = new Items(addItemCommand.getItemId(), itemsRef.getName(), addItemCommand.getQuantity(), itemsRef.getPrice());
			ItemAddedEvent addedEvent = new ItemAddedEvent(this.cartId, this.customerId, items);
			System.out.println(" ----------->  Item added "+ addedEvent);
			apply(addedEvent);
		}
	}
	
	@EventSourcingHandler
	public void handle(ItemAddedEvent itemAddedEvent){
		this.cartId = itemAddedEvent.getCartId();
		this.customerId = itemAddedEvent.getCustomerId();
		Items items1 = null;
		if(items!=null){
			for (Items items2 : items) {
				if(items2.getItemId().equals(itemAddedEvent.getItems().getItemId())){
					items1 = items2;
				}
			}	
		} else {
			items = new ArrayList<Items>();
		}
		
		if(items1!=null){
			items1.setQuantity(items1.getQuantity() +  itemAddedEvent.getItems().getQuantity());
		} else {
			items1 = new Items(itemAddedEvent.getItems().getItemId(), itemAddedEvent.getItems().getName(), itemAddedEvent.getItems().getQuantity());
			items.add(items1);
		}
		System.out.println("+++++++++++++ Items in cart at source "+items);
	}
	
	@CommandHandler
	public void handleCommand(RemoveItemCommand removeItemCommand, ItemInventory itemInventory) {
		Item itemsRef = itemInventory.getItem(removeItemCommand.getItemId());
		Items items = new Items(removeItemCommand.getItemId(), itemsRef.getName(), removeItemCommand.getQuantity());
		ItemRemovedEvent event = new ItemRemovedEvent(this.cartId, this.customerId, items);
		System.out.println(" ----------->  Item removed "+ event);
		apply(event);
	}
	
	@EventSourcingHandler
	public void handle(ItemRemovedEvent itemRemovedEvent){
		this.cartId = itemRemovedEvent.getCartId();
		this.customerId = itemRemovedEvent.getCustomerId();
		Items items1 = null;
		if(items!=null){
			for (Items items2 : items) {
				if(items2.getItemId().equals(itemRemovedEvent.getItems().getItemId())){
					items1 = items2;
				}
			}	
		}	
		if(items1!=null){
			if(items1.getQuantity() -  itemRemovedEvent.getItems().getQuantity()<=0){
				items.remove(items1);
			} else {
				items1.setQuantity(items1.getQuantity() -  itemRemovedEvent.getItems().getQuantity());				
			}
		}
		System.out.println("+++++++++++++ Items in cart at source "+items);
	}
	
	@CommandHandler
	public void handle(PlaceOrderCommand placeOrderCommand){
		this.cartId = placeOrderCommand.getCartId();
		this.customerId = placeOrderCommand.getCustomerId();
		OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(cartId, customerId, items);
		apply(orderPlacedEvent);
	}
	
	@CommandHandler
	public void handle(EmptyCartCommand emptyCartCommand){
		System.out.println(" +++++++++++++>  cart emptied ");
		markDeleted();
	}
}
