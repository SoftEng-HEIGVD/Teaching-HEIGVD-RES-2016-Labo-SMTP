package ch.heigvd.res.mailpranker;

import ch.heigvd.res.mailpranker.config.ConfigLoader;
import ch.heigvd.res.mailpranker.config.Config;
import ch.heigvd.res.mailpranker.config.EmailsLoader;
import ch.heigvd.res.mailpranker.config.MessagesLoader;
import ch.heigvd.res.mailpranker.model.Email;
import ch.heigvd.res.mailpranker.model.PrankGenerator;
import ch.heigvd.res.mailpranker.smtp.SmtpClient;

/**
 * Main class of MailPranker
 *
 * Load config files, init the generator and the STMP client,
 * and send all the pranks.
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class MailPranker {

    /**
     * Config files.
     */
    private static final String configFile = "src/main/resources/config.properties";
    private static final String emailsFile = "src/main/resources/emails.utf8";
    private static final String messagesFile = "src/main/resources/messages.utf8";

    /**
     * Constructor the program
     */
    private MailPranker() {

        // Init ConfigLoader
        Config config = ConfigLoader.load(configFile);

        // Generate pranks
        PrankGenerator prankGenerator = new PrankGenerator(
                EmailsLoader.load(emailsFile),
                MessagesLoader.load(messagesFile),
                Integer.parseInt(config.get("numberOfGroups"))
        );
        prankGenerator.setBCC(new Email(config.get("sendBCCTo")));

        // Create and configure the SMTP client
        SmtpClient smtpClient = new SmtpClient(
                config.get("smtpServerAddress"),
                Integer.parseInt(config.get("smtpServerPort"))
        );
        prankGenerator.setSmtpClient(smtpClient);

        // Send pranks
        prankGenerator.sendAll();
    }

    /**
     * Main method of the program
     *
     * @param args arguments of the program
     */
    public static void main(String ... args) {
        new MailPranker();
    }
}
