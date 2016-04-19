package ch.heigvd.res.mailpranker.config;

import java.util.Properties;

/**
 * Object representing a configuration file
 *
 * @author Damien Rochat (damien.rochat@heig-vd.ch)
 * @author Sébastien Richoz (sebastien.richoz1@heig-vd.ch)
 */
public class Config {

    /**
     * Properties object linked to the config file
     */
    private Properties properties;

    /**
     * Constructor taking a properties object
     *
     * @param properties the properties object to use
     */
    public Config(Properties properties) {
        this.properties = properties;
    }

    /**
     * Getter of a configuration
     *
     * @param key the key to look for
     * @return the value
     */
    public String get(String key) {
        return properties.getProperty(key);
    }
}
