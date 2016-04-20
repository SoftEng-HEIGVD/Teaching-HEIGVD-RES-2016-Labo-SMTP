package ch.heigvd.res.mailpranker.smtp;

import java.util.List;

/**
 * Interface to implement to create a SMTP message
 * that will be sent by a ISmtpClient object.
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public interface ISmtpMessage {

    /**
     * Get the from field
     *
     * @return the email
     */
    String getFrom();

    /**
     * Get the list of recipients
     *
     * @return the emails
     */
    List<String> getTo();

    /**
     * Get the list of carbon copy recipients
     *
     * @return the emails
     */
    List<String> getCC();

    /**
     * Get the list of blinded carbon copy recipients
     *
     * @return the emails
     */
    List<String> getBCC();

    /**
     * Get the subject of the message
     *
     * @return the subject
     */
    String getSubject();

    /**
     * Get the body of the message
     *
     * @return the body
     */
    String getBody();
}
