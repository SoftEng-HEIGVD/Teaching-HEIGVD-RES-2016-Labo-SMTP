package ch.heigvd.res.mailpranker.model;

/**
 * Class representing an email
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class Email {

    /**
     * The attributs
     */
    private String email;

    /**
     * Constructor initiating the email
     *
     * @param email the email to use
     */
    public Email(String email) {
        this.email = email;
    }

    /**
     * Getter for the email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}
