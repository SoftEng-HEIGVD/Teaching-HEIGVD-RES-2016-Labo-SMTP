package ch.heigvd.res.mailpranker.model;

import ch.heigvd.res.mailpranker.smtp.ISmtpMessage;

import java.util.LinkedList;
import java.util.List;

public class Prank implements ISmtpMessage {

    private Message message;
    private String sender;
    private List<String> recipients = new LinkedList<String>();

    public Prank(Group group, Message message) {
        this.message = message;
        boolean first = true;
        for (Email email : group.getEmails()) {
            if (first) {
                sender = email.getEmail();
                first = false;
            }
            else {
                recipients.add(email.getEmail());
            }
        }
    }

    public String getSender() {
        return sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return message.getSubject();
    }

    public String getBody() {
        return message.getBody();
    }
}
