/**
 * 
 */
package com.metamagic.desire.issue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metamagic.desire.issue.entity.Issue;

/**
 * @author Mahesh Pardeshi
 *
 */
public interface IssueRepository extends JpaRepository<Issue, String> {

}