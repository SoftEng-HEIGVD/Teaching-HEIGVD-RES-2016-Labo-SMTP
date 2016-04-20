package ch.heigvd.res.mailpranker.model;

import ch.heigvd.res.mailpranker.smtp.ISmtpMessage;

import java.util.List;
import java.util.LinkedList;

/**
 * Class representing a prank
 *
 * Implement the ISmtpMessage interface to be directly used
 * by the SMTP client.
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class Prank implements ISmtpMessage {

    /**
     * The attributes
     */
    private String from;
    private List<String> to = new LinkedList<String>();
    private List<String> cc = new LinkedList<String>();
    private List<String> bcc = new LinkedList<String>();
    private String subject;
    private String body;

    /**
     * Set the from field
     *
     * @param email the email to use
     */
    public void setFrom(Email email) {
        from = email.getEmail();
    }

    /**
     * Get the from field
     *
     * @return the email
     */
    public String getFrom() {
        return from;
    }

    /**
     * Add a new email to the recipients list
     *
     * @param email the email to add
     */
    public void addTo(Email email) {
        to.add(email.getEmail());
    }

    /**
     * Get the list of recipients
     *
     * @return the emails
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * Add a new email to the carbon copy recipients' list
     *
     * @param email the email to add
     */
    public void addCC(Email email) {
        cc.add(email.getEmail());
    }

    /**
     * Get the list of carbon copy recipients
     *
     * @return the emails
     */
    public List<String> getCC() {
        return cc;
    }

    /**
     * Add a new email to the blinded carbon copy recipients list
     *
     * @param email the email to add
     */
    public void addBCC(Email email) {
        bcc.add(email.getEmail());
    }

    /**
     * Get the list of blinded carbon copy recipients
     *
     * @return the emails
     */
    public List<String> getBCC() {
        return bcc;
    }

    /**
     * Set the subject and the body for this prank
     * from a message object
     *
     * @param message the message to use
     */
    public void setMessage(Message message) {
        subject = message.getSubject();
        body = message.getBody();
    }

    /**
     * Get the subject of the message
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the body of the message
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }
}
