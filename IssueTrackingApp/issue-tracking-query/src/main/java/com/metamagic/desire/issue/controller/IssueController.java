/** 
 * 
 */
package com.metamagic.desire.issue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.desire.issue.entity.Issue;
import com.metamagic.desire.issue.repository.IssueRepository;

/**
 * @author Mahesh Pardeshi
 *
 */
@RestController
@RequestMapping("/Issue")
public class IssueController {

	private final static Logger logger = LoggerFactory.getLogger(IssueController.class);

	private final IssueRepository repository;

	public IssueController(IssueRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("/findAll")
	private List<Issue> findAll() {
		logger.info("Processing findAll operation on Issue");
		return repository.findAll();
	}

	@GetMapping("/findById/{id}")
	private Issue findById(@PathVariable String id) {
		logger.info("Processing findById operation on Issue by #Payload-issueid - " + id);
		return repository.findOne(id);
	}
}