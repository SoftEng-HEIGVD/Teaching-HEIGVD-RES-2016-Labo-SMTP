package ch.heigvd.res.mailpranker.smtp;

/**
 * Interface to implement to create a SMTP client
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public interface ISmtpClient {

    /**
     * The objects must implement the send() method,
     * that will take one or more messages to send.
     *
     * @param messages the messages to send
     */
    void send(ISmtpMessage ... messages);
}
