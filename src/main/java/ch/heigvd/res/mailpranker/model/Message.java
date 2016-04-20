package ch.heigvd.res.mailpranker.model;

/**
 * Class representing a message
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class Message {

    /**
     * The attributs
     */
    private String subject;
    private String body;

    /**
     * Constructor initiating the message
     * with a subject and a body
     *
     * @param subject the subject to use
     * @param body the body to use
     */
    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    /**
     * Getter for the subject
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Getter for the body
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }
}
