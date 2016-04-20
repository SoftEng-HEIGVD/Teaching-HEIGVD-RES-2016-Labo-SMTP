package ch.heigvd.res.mailpranker.model;

import ch.heigvd.res.mailpranker.smtp.ISmtpMessage;

import java.util.List;
import java.util.LinkedList;

/**
 * Class representing a prank
 *
 * Implements the ISmtpMessage interface to be directly used
 * by the SMTP client.
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class Prank implements ISmtpMessage {

    /**
     * The attributes
     */
    private Email from;
    private List<Email> to = new LinkedList<Email>();
    private List<Email> cc = new LinkedList<Email>();
    private List<Email> bcc = new LinkedList<Email>();
    private Message message;

    /**
     * Set the from field
     *
     * @param email the email to use
     */
    public void setFrom(Email email) {
        from = email;
    }

    /**
     * Get the from field
     *
     * @return the email
     */
    public String getFrom() {
        return from.getEmail();
    }

    /**
     * Add a new email to the recipients list
     *
     * @param email the email to add
     */
    public void addTo(Email email) {
        to.add(email);
    }

    /**
     * Get the list of recipients
     *
     * @return the emails
     */
    public List<String> getTo() {
        List<String> list = new LinkedList<String>();
        for (Email email : to) {
            list.add(email.getEmail());
        }
        return list;
    }

    /**
     * Add a new email to the carbon copy recipients' list
     *
     * @param email the email to add
     */
    public void addCC(Email email) {
        cc.add(email);
    }

    /**
     * Get the list of carbon copy recipients
     *
     * @return the emails
     */
    public List<String> getCC() {
        List<String> list = new LinkedList<String>();
        for (Email email : cc) {
            list.add(email.getEmail());
        }
        return list;
    }

    /**
     * Add a new email to the blinded carbon copy recipients list
     *
     * @param email the email to add
     */
    public void addBCC(Email email) {
        bcc.add(email);
    }

    /**
     * Get the list of blinded carbon copy recipients
     *
     * @return the emails
     */
    public List<String> getBCC() {
        List<String> list = new LinkedList<String>();
        for (Email email : bcc) {
            list.add(email.getEmail());
        }
        return list;
    }

    /**
     * Set the message for this prank
     *
     * @param message the message to use
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Get the subject of the message
     *
     * @return the subject
     */
    public String getSubject() {
        return message.getSubject();
    }

    /**
     * Get the body of the message
     *
     * @return the body
     */
    public String getBody() {
        return message.getBody();
    }
}
