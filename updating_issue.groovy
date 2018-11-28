import com.atlassian.jira.issue.UpdateIssueRequest
import com.atlassian.jira.issue.UpdateIssueRequest.UpdateIssueRequestBuilder
import com.atlassian.jira.event.type.EventDispatchOption

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.user.ApplicationUser

import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.IssueManager

 MutableIssue issue =issue
// MutableIssue issue = issueManager.getIssueObject("AZY-130")

ApplicationUser currentUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser()
UpdateIssueRequest updateIssueRequest = UpdateIssueRequest.builder().eventDispatchOption(EventDispatchOption.DO_NOT_DISPATCH).sendMail(false).build();

issueManager.updateIssue( currentUser, issue,  updateIssueRequest)
