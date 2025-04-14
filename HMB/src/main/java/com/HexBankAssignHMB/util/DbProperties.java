package com.HexBankAssignHMB.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbProperties {
    private static Properties properties = new Properties();

    static {
        try {
            //System.out.println("Loading DB properties...");
            InputStream in = DbProperties.class.getClassLoader().getResourceAsStream("HMBdb.properties");

            if (in == null) {
                throw new RuntimeException("Resource file not found!");
            }

            properties.load(in);
            //System.out.println("Properties loaded successfully!");
        } catch (IOException e) {
            throw new RuntimeException("Database Connection Failed!", e);
        }
    }

    public static String getDriver() {
        return properties.getProperty(HMBConstants.DB_DRIVER);
    }

    public static String getUrl() {
        return properties.getProperty(HMBConstants.DB_URL);
    }

    public static String getUser() {
        return properties.getProperty(HMBConstants.DB_USER);
    }

    public static String getPassword() {
        return properties.getProperty(HMBConstants.DB_PASSWORD);
    }

    public static Properties getProperties() {
        Properties connProperties = new Properties();
        connProperties.put("user", getUser());
        connProperties.put("password", getPassword());
        return connProperties;
    }
}
