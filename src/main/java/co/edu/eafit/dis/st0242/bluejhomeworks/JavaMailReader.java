package co.edu.eafit.dis.st0242.bluejhomeworks;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Calendar;

public class JavaMailReader {

  public static void main(String args[]) throws Exception {

    // mail server connection parameters
    String host = "outlook.office365.com";
    int port = 995;
    String user = "fcardona@eafit.edu.co";
    String password = "13;mayhumav";

    // connect to my pop3 inbox
    Properties properties = System.getProperties();
    properties.list(System.out);
    properties.put("mail.host", "outlook.office365.com");
    properties.put("mail.store.protocol", "pop3s");
    properties.put("mail.pop3s.auth", "true");
    properties.put("mail.pop3s.port", "995");
    Session session = Session.getDefaultInstance(properties, null);
    Store store = session.getStore(); // ("pop3");
    store.connect(user, password);
    Folder inbox = store.getFolder("Inbox");
    inbox.open(Folder.READ_ONLY);

    // get the list of inbox messages
    Message[] messages = inbox.getMessages();

    Calendar rightNow = Calendar.getInstance();

    rightNow.add(Calendar.WEEK_OF_YEAR, -1);

    if (messages.length == 0) System.out.println("No messages found.");

    System.out.println("Messages: " + messages.length);
    // boolean contReadMail = true;
    for (int i = messages.length - 1; ; i--) {
	// stop after listing ten messages
	// if (i > 10) {
	//   System.exit(0);
	//   inbox.close(true);
	//   store.close();
	// }

	// if (messages[i].getSubject().compareTo("ST0242035-2015-1/Taller 1") == 0) {
	    
	    System.out.println("Message " + (i + 1));
	    System.out.println("From : " + messages[i].getFrom()[0]);
	    System.out.println("Subject : " + messages[i].getSubject());
	    System.out.println("Sent Date : " + messages[i].getSentDate());
	    System.out.println();
	    // }
	
	if (rightNow.getTime().compareTo(messages[i].getSentDate()) > 0) 
	    break;

    }
    
    inbox.close(true);
    store.close();
  }
}
