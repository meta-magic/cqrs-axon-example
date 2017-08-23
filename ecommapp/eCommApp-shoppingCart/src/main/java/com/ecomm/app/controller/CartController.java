package com.ecomm.app.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.app.commands.AddItemCommand;
import com.ecomm.app.commands.CreateCartCommand;
import com.ecomm.app.commands.PlaceOrderCommand;
import com.ecomm.app.commands.RemoveItemCommand;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CommandGateway commandGateway;
	
	@PostMapping("/create")
	public void createCart(@RequestParam String cartId, @RequestParam String customerId){
		commandGateway.send(new CreateCartCommand(cartId, customerId));
	}
	
	@PostMapping("/addItem")
	public void addItem(@RequestParam String cartId, @RequestParam String itemId, @RequestParam int quantity){
		commandGateway.send(new AddItemCommand(cartId, itemId, quantity));
	}
	
	@PostMapping("/removeItem")
	public void removeItem(@RequestParam String cartId, @RequestParam String itemId, @RequestParam int quantity){
		commandGateway.send(new RemoveItemCommand(cartId, itemId, quantity));
	}
	
	@PostMapping("/placeOrder")
	public void placeOrder(@RequestParam String cartId, @RequestParam String customerId){
		commandGateway.send(new PlaceOrderCommand(cartId, customerId));
	}
}
