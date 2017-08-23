/**
 * 
 */
package com.metamagic.desire.issue.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.desire.issue.command.IssueCommands;

/**
 * @author Mahesh Pardeshi
 *
 */
@RestController
@RequestMapping("/Issue")
public class IssueController {

	private final static Logger logger = LoggerFactory.getLogger(IssueController.class);

	private final CommandGateway commandGateway;

	public IssueController(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}

	@PostMapping("/raiseIssue")
	public CompletableFuture<Object> raiseIssue(@RequestBody IssueCommands.RaiseIssue command) {
		logger.info("Call received to RaiseIssue #Payload : " + command);
		String issueId = UUID.randomUUID().toString();
		command.setIssueId(issueId);
		logger.info("*****Generated UUID for issue id : " + command.getIssueId());
		return commandGateway.send(command);
	}

	@PostMapping("/update")
	public void updateIssue(@RequestBody IssueCommands.UpdateIssue command) {
		logger.info("Call received to UpdateIssue #Payload : " + command);
		commandGateway.send(command);
	}
}