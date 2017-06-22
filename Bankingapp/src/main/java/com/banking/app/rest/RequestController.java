package com.banking.app.rest;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.command.CreateAccount;
import com.banking.app.command.CreditAccount;
import com.banking.app.command.DebitAccount;

@RestController
public class RequestController {
	
	@Autowired
	private CommandGateway commandGateway;
	
	@PostMapping("/createAccount")
	public void createAccount(@RequestParam String accountNo){
		commandGateway.send(new CreateAccount(accountNo));
	}
	
	@PostMapping("/credit")
	public void credit(@RequestParam String accountNo, @RequestParam Double amount){
		commandGateway.send(new CreditAccount(accountNo, amount));
	}
	
	@PostMapping("/debit")
	public void debit(@RequestParam String accountNo, @RequestParam Double amount){
		commandGateway.send(new DebitAccount(accountNo, amount));
	}
}
