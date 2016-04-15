package ch.heigvd.res.mailpranker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrankGenerator {

    private List<Prank> pranks = new ArrayList<Prank>();

    public PrankGenerator(List<Email> emails, List<Message> messages, int groupSize) {
        Collections.shuffle(emails);
        Collections.shuffle(messages);

        // Generate the groups
        int numberOfGroups = (int) Math.ceil(emails.size() / (double) groupSize);
        int emailIndex = 0;
        int messageIndex = 0;
        for (int g = 0; g < numberOfGroups; ++g) {

            // Create a group
            // Stop if there is no more email
            Group group = new Group();
            for (int e = 0; e < groupSize && emailIndex < emails.size(); ++e) {
                group.addEmail(emails.get(emailIndex++));
            }

            // Create the pranks
            // Loop if there is no more message
            pranks.add(new Prank(group, messages.get(messageIndex++ % messages.size())));
        }

    }
}
