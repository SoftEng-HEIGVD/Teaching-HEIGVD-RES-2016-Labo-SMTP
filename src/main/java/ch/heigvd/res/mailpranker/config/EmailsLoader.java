package ch.heigvd.res.mailpranker.config;

import ch.heigvd.res.mailpranker.model.Email;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class EmailsLoader {

    private EmailsLoader() {}

    public static List<Email> load(String file) {
        try {

            // Load file
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            // Load emails
            List<Email> emails = new ArrayList<Email>();
            String line;
            while ((line = input.readLine()) != null) {
                emails.add(new Email(line));
            }

            // Return the list of emails
            return emails;
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to load emails file.");
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to read emails file.");
        }
    }
}
