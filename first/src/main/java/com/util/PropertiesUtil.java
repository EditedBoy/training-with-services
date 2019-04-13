package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties loadPropertiesFile(String fileName) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(inputStream);
        } catch (IOException io) {
            throw new IllegalArgumentException("file is not found!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
