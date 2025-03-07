package com.andreasmlbngaol.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties prop = new Properties();

    static {
        try(InputStream in = Config.class.getClassLoader().getResourceAsStream("hibernate.properties")) {
            if(in == null) {
                throw new RuntimeException("Config properties file not found");
            }
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config file", e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
