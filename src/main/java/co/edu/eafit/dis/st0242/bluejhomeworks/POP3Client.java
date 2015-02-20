package co.edu.eafit.dis.st0242.bluejhomeworks;

import jodd.mail.Pop3Server;
import jodd.mail.ReceiveMailSession;
import jodd.mail.ReceivedEmail;
import jodd.mail.EmailMessage;
import jodd.mail.EmailAttachment;
import javax.mail.Authenticator;

public class POP3Client {
   
    public static void main(String[] args) {

        Pop3Server popServer = 
	    new Pop3Server("outlook.office365.com", 995,
			   new SimpleAuthenticator("fcardona", 
						   ""));
	ReceiveMailSession session = popServer.createSession();
	session.open();
	System.out.println(session.getMessageCount());
	ReceivedEmail[] emails = session.receiveEmail();
	if (emails != null) {
	    for (ReceivedEmail email : emails) {
		System.out.println("\n\n===[" + 
				   email.getMessageNumber() + "]===");
		
		// common info
		Printf.out("%0x", email.getFlags());
		System.out.println("FROM:" + email.getFrom());
		System.out.println("TO:" + email.getTo()[0]);
		System.out.println("SUBJECT:" + email.getSubject());
		System.out.println("PRIORITY:" + email.getPriority());
		System.out.println("SENT DATE:" + email.getSentDate());
		System.out.println("RECEIVED DATE: " 
				   + email.getReceiveDate());
		
		// process messages
		List messages = email.getAllMessages();
		for (EmailMessage msg : messages) {
		    System.out.println("------");
		    System.out.println(msg.getEncoding());
		    System.out.println(msg.getMimeType());
		    System.out.println(msg.getContent());
		}
		
		// process attachments
		List<EmailAttachment> attachments = email.getAttachments();
		if (attachments != null) {
		    System.out.println("+++++");
		    for (EmailAttachment attachment : attachments) {
			System.out.println("name: " + attachment.getName());
			System.out.println("cid: " 
					   + attachment.getContentId());
			System.out.println("size: " + attachment.getSize());
			// attachment.writeToFile(
			// new File("d:\\", attachment.getName()));
		    }
		}
	    }
	}
	session.close();
    }
}
