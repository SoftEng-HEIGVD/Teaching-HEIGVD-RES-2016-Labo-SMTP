package ch.heigvd.res.mailpranker.config;

import java.util.Properties;

public class Config {

    private Properties properties;

    public Config(Properties properties) {
        this.properties = properties;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
