package com.metamagic.desire.issue.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author Mahesh Pardeshi
 *
 */
public interface IssueCommands {

	public final class RaiseIssue implements IssueCommands {

		@TargetAggregateIdentifier
		private String issueId;

		private String title;

		private String description;

		private Integer severity;

		private String priority;

		public RaiseIssue() {
			super();
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

		@Override
		public String toString() {
			return "RaiseIssue [issueId=" + issueId + ", title=" + title + ", description=" + description + ", severity=" + severity + ", priority=" + priority
					+ "]";
		}
	}

	public final class UpdateIssue implements IssueCommands {

		@TargetAggregateIdentifier
		private String issueId;

		private String title;

		private String description;

		private Integer severity;

		private String priority;

		public UpdateIssue() {
			super();
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

		@Override
		public String toString() {
			return "UpdateIssue [issueId=" + issueId + ", title=" + title + ", description=" + description + ", severity=" + severity + ", priority=" + priority
					+ "]";
		}
	}
}
