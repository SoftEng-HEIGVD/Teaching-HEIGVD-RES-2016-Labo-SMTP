package ch.heigvd.res.mailpranker.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private ConfigLoader() {}

    public static Config load(String file) {
        try {

            // Load config file
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            // Load properties
            Properties properties = new Properties();
            properties.load(input);

            // Create and return
            return new Config(properties);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to load configuration file.");
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to read configuration file.");
        }
    }
}
