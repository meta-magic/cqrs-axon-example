package com.ecomm.app.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecomm.app.inventory.document.Item;
import com.ecomm.app.inventory.repository.ItemRepository;

@Component
public class ItemInventory {

	//private Set<Items> items = new HashSet<Items>();
	
	/*private static ItemInventory itemStore = new ItemInventory();
	
	public static ItemInventory getInstance(){
		if(itemStore==null){
			itemStore = new ItemInventory();
		}
		return itemStore;
	}*/
	
	/*private ItemInventory(){
		items.add(new Items("1", "Watch", 10));
		items.add(new Items("2", "shirt", 12));
		items.add(new Items("3", "shoes", 8));
		items.add(new Items("4", "t-shirt", 20));
	}*/

	@Autowired
	private ItemRepository itemRepository;
	
	public Item getItem(String itemId){
		return itemRepository.findByItemId(itemId);
	}
	
	public void updateItem(Item item){
		itemRepository.save(item);
	}

	@Override
	public String toString() {
		List<Item> list = itemRepository.findAll();
		return "ItemStore [items=" + list + "]";
	}
	
}
