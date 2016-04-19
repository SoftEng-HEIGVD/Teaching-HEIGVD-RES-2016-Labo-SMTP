package ch.heigvd.res.mailpranker;

import ch.heigvd.res.mailpranker.config.ConfigLoader;
import ch.heigvd.res.mailpranker.config.Config;
import ch.heigvd.res.mailpranker.config.EmailsLoader;
import ch.heigvd.res.mailpranker.config.MessagesLoader;
import ch.heigvd.res.mailpranker.model.PrankGenerator;
import ch.heigvd.res.mailpranker.smtp.SmtpClient;

public class MailPranker {

    private static final String configFile = "src/main/resources/config.properties";
    private static final String emailsFile = "src/main/resources/emails.utf8";
    private static final String messagesFile = "src/main/resources/messages.utf8";

    private MailPranker() {

        // Init ConfigLoader
        Config config = ConfigLoader.load(configFile);

        // Create and configure the SMTP client
        SmtpClient smtpClient = new SmtpClient(
            config.get("smtpServerAddress"),
            Integer.parseInt(config.get("smtpServerPort"))
        );
        smtpClient.setCC(config.get("sendCopyTo"));

        // Generate and send pranks
        PrankGenerator prankGenerator = new PrankGenerator(
                EmailsLoader.load(emailsFile),
                MessagesLoader.load(messagesFile),
                Integer.parseInt(config.get("groupSize"))
        );
        prankGenerator.setSmtpClient(smtpClient);
        prankGenerator.sendAll();
    }

    public static void main(String ... args) {
        new MailPranker();
    }
}
