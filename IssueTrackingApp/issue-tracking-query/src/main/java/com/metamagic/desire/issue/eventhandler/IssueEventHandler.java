/**
 * 
 */
package com.metamagic.desire.issue.eventhandler;

import java.util.Date;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.metamagic.desire.issue.entity.Issue;
import com.metamagic.desire.issue.event.IssueEvents;
import com.metamagic.desire.issue.repository.IssueRepository;

/**
 * @author Mahesh Pardeshi
 *
 */
@ProcessingGroup("amqpEvents")
@Component
public class IssueEventHandler {

	private final static Logger logger = LoggerFactory.getLogger(IssueEventHandler.class);

	private final IssueRepository repository;

	public IssueEventHandler(IssueRepository repository) {
		super();
		this.repository = repository;
	}

	@EventHandler
	private Issue onIssueRaised(IssueEvents.IssueRaised event) {
		logger.info("IssueRaised event received at query model and processing started");
		Issue issue = new Issue(event.getIssueId(), event.getTitle(), event.getDescription(), event.getSeverity(), event.getPriority());
		issue.setCreatedDate(new Date(System.currentTimeMillis()));
		return repository.save(issue);
	}

	@EventHandler
	private void onIssueUpdated(IssueEvents.IssueUpdated event) {
		logger.info("IssueUpdated event received at query model and processing started");
		if (event.getIssueId() != null) {
			Issue issue = repository.findOne(event.getIssueId());
			if (event.getTitle() != null)
				issue.setTitle(event.getTitle());
			if (event.getDescription() != null)
				issue.setDescription(event.getDescription());
			if (event.getSeverity() != null)
				issue.setSeverity(event.getSeverity());
			if (event.getPriority() != null)
				issue.setPriority(event.getPriority());
			issue.setUpdatedDate(new Date(System.currentTimeMillis()));
			repository.save(issue);
			logger.info("IssueUpdated event processing completed");
		}
	}
}
