/**
 * 
 */
package com.metamagic.desire.issue.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Mahesh Pardeshi
 *
 */
@Entity
public final class Issue {

	@Id
	private String issueId;

	private String title;

	private String description;

	private Integer severity;

	private String priority;

	private Date createdDate;

	private Date updatedDate;

	public Issue() {
		super();
	}

	public Issue(String issueId, String title, String description, Integer severity, String priority) {
		super();
		this.issueId = issueId;
		this.title = title;
		this.description = description;
		this.severity = severity;
		this.priority = priority;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}