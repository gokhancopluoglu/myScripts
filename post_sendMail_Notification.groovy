import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.user.ApplicationUser

import com.atlassian.jira.issue.MutableIssue
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.IssueManager

import com.atlassian.mail.Email;
import com.atlassian.mail.server.MailServerManager;
import com.atlassian.mail.server.SMTPMailServer;

import org.apache.log4j.Logger

def log = Logger.getLogger("com.acme.CreateSubtask")

ComponentAccessor componentAccessor = new ComponentAccessor();
IssueManager issueManager = componentAccessor.getIssueManager();

Issue issue = issue
ApplicationUser assigneeUser = issue.getAssignee()
ApplicationUser reporterUser = issue.getReporter()

String subject = “İzin İsteği”
String body = "Merhaba\n"+reporterUser.getName()+" İzin kullanmak istiyor"
String emailAddr = assigneeUser.getEmailAddress()


sendEmail (emailAddr, subject, body)
log.error(emailAddr)
def sendEmail(String emailAddr, String subject, String body)
{
    def mailServer = ComponentAccessor.getMailServerManager().getDefaultSMTPMailServer()
    
    if (mailServer) 
    {
        Email email = new Email(emailAddr);
        email.setSubject(subject);
        email.setBody(body);
        mailServer.send(email);
	}
    
    else
    {
        // Problem getting the mail server from JIRA configuration, log this error
    }
}
