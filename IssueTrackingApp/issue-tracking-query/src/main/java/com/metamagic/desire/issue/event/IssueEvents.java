package com.metamagic.desire.issue.event;

import java.io.Serializable;

/**
 * @author Mahesh Pardeshi
 *
 */
public interface IssueEvents extends Serializable {

	public final class IssueRaised implements IssueEvents {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4018134625718284855L;

		private final String issueId;

		private final String title;

		private final String description;

		private final Integer severity;

		private final String priority;

		public IssueRaised(String issueId, String title, String description, Integer severity, String priority) {
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

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public Integer getSeverity() {
			return severity;
		}

		public String getPriority() {
			return priority;
		}

	}

	public final class IssueUpdated implements IssueEvents {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5747889152699455494L;

		private final String issueId;

		private final String title;

		private final String description;

		private final Integer severity;

		private final String priority;

		public IssueUpdated(String issueId, String title, String description, Integer severity, String priority) {
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

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public Integer getSeverity() {
			return severity;
		}

		public String getPriority() {
			return priority;
		}
	}
}
