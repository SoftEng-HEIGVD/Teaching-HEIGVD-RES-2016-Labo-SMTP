package ch.heigvd.res.mailpranker.model;

import ch.heigvd.res.mailpranker.smtp.ISmtpClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class generating the pranks from emails and messages.
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class PrankGenerator {

    /**
     * The list of pranks
     */
    private List<Prank> pranks = new ArrayList<Prank>();

    /**
     * The SMTP client to use to send the pranks
     */
    private ISmtpClient smtpClient;

    /**
     * An optional email that will receive a copy of all
     * the pranks
     */
    private Email bcc;

    /**
     * Constructor of the object
     *
     * This method shuffles the two lists before generating the pranks.
     * There will probably not have the same number of victims in each pranks,
     * but this method ensure to send a joke to everybody.
     *
     * @param emails to use
     * @param messages to use
     * @param numberOfGroups the number of pranks to send
     */
    public PrankGenerator(List<Email> emails, List<Message> messages, int numberOfGroups) {
        Collections.shuffle(emails);
        Collections.shuffle(messages);

        // Check the number of victims
        if (3 * numberOfGroups > emails.size()) {
            throw new RuntimeException("Not enough emails.");
        }

        // Define the number of emails per prank (didn't consider the sender)
        // Upper round the division to not forget an email
        int groupSize = (int) Math.ceil(emails.size() / (double) numberOfGroups) - 1;

        // Generate the pranks
        int emailIndex = 0;
        int messageIndex = 0;
        for (int i = 0; i < numberOfGroups; ++i) {
            Prank prank = new Prank();

            // Add "from" email
            prank.setFrom(emails.get(emailIndex++));

            // Add "to" emails
            for (int j = 0; j < groupSize; ++j) {

                // Stop if there is no more email (for the last prank)
                if (emailIndex >= emails.size()) {
                    break;
                }

                prank.addTo(emails.get(emailIndex++));
            }

            // Add "bcc" email
            if (bcc != null) {
                prank.addBCC(bcc);
            }

            // Add message
            // Loop through the messages' list if there is no more message
            prank.setMessage(messages.get(messageIndex++ % messages.size()));

            // Save prank
            pranks.add(prank);
        }
    }

    /**
     * Set the SMTP client used to send the pranks
     *
     * @param smtpClient the client
     */
    public void setSmtpClient(ISmtpClient smtpClient) {
        this.smtpClient = smtpClient;
    }

    /**
     * Send all the pranks
     */
    public void sendAll() {
        if (smtpClient == null) {
            throw new RuntimeException("SMTP client not provided.");
        }
        for (Prank prank : pranks) {
            smtpClient.send(prank);
        }
    }

    /**
     * Set the optional email who will receive a copy
     * of all the pranks
     *
     * @param bcc the email
     */
    public void setBCC(Email bcc) {
        this.bcc = bcc;
    }
}
