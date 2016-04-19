package ch.heigvd.res.mailpranker.config;

import ch.heigvd.res.mailpranker.model.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MessagesLoader {

    private MessagesLoader() {}

    public static List<Message> load(String file) {
        try {

            // Load file
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            // Load emails
            List<Message> messages = new ArrayList<Message>();
            String line;
            while ((line = input.readLine()) != null) {
                String subject;
                String content = "";

                // Read subject
                if (line.startsWith("Subject: ")) {
                    // extract from the next char after "Subject: " to the end of the line
                    subject = line.substring(9);
                }
                else {
                    throw new RuntimeException("Wrong format of messages file : no subject");
                }

                // Read the content until the end of message character
                line = input.readLine();
                while (line != null && !line.equals("==")) {
                    content += line + "\r\n";
                    line = input.readLine();
                }

                // Generate a message object
                // Remove enventually ending or begining whitespaces
                messages.add(new Message(subject.trim(), content.trim()));
            }

            // Return the list of emails
            return messages;
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to load messages file.");
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to read messages file.");
        }
    }
}
