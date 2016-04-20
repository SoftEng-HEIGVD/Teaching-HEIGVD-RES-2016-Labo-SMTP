package ch.heigvd.res.mailpranker.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Static class loading a configuration file
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class ConfigLoader {

    /**
     * Private constructor, no instance of this class
     */
    private ConfigLoader() {}

    /**
     * Load a file and init config object with a properties object
     *
     * @param file the path of the file to load
     * @return the config object corresponding to the properties file
     */
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
