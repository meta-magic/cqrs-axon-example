package com.ecomm.app.inventory.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
public class Item {

	@Id
	private String id;
	
	@Indexed
	private String itemId;
	
	private String name;
	
	private int quantity;
	
	private double price;

	public Item(){
	}
	
	public Item(String itemId, String name, int quantity) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.quantity = quantity;
	}
	
	public Item(String itemId, String name, int quantity, double price) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", quantity=" + quantity + "]";
	}
	

}
