package com.ecomm.app.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecomm.app.inventory.document.Item;
import com.ecomm.app.inventory.repository.ItemRepository;

@Component
public class ItemInventory {

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
