package com.ecomm.app.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.app.inventory.document.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

	public Item findByItemId(String itemId);
}
