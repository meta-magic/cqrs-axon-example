/**
 * 
 */
package com.metamagic.desire.issue.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.metamagic.desire.issue.command.IssueCommands;
import com.metamagic.desire.issue.event.IssueEvents;

/**
 * @author Mahesh Pardeshi
 *
 */
@Aggregate
public class Issue {

	private final static Logger logger = LoggerFactory.getLogger(Issue.class);

	@AggregateIdentifier
	private String issueId;

	public Issue() {
		super();
	}

	@CommandHandler
	public Issue(IssueCommands.RaiseIssue command) {
		logger.info("RaiseIssue commandhandler called");
		AggregateLifecycle.apply(
				new IssueEvents.IssueRaised(command.getIssueId(), command.getTitle(), command.getDescription(), command.getSeverity(), command.getPriority()));
		logger.info("RaiseIssue commandhandler processing completed");
	}

	@CommandHandler
	public void handleUpdateIssue(IssueCommands.UpdateIssue command) {
		logger.info("UpdateIssue commandhandler called");
		AggregateLifecycle.apply(
				new IssueEvents.IssueUpdated(command.getIssueId(), command.getTitle(), command.getDescription(), command.getSeverity(), command.getPriority()));
		logger.info("UpdateIssue commandhandler processing completed");
	}

	@EventSourcingHandler
	public void onIssueRaised(IssueEvents.IssueRaised event) {
		logger.info("IssueRaised event-sourcing handler called");
		this.issueId = event.getIssueId();
		logger.info("IssueRaised event-sourcing handler processing completed");
	}

	@EventSourcingHandler
	public void onIssueUpdated(IssueEvents.IssueUpdated event) {
		logger.info("IssueUpdated event-sourcing handler called");
		this.issueId = event.getIssueId();
		logger.info("IssueUpdated event-sourcing handler processing completed");
	}
}