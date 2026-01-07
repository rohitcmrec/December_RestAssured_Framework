package com.qa.gorest.configuration;

import com.qa.gorest.frameworkException.APIFrameworkException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private Properties properties;
    private FileInputStream ip;

    public Properties initProp() {
        String env = System.getProperty("env","qa");
        properties = new Properties();
        try {
            switch (env) {
                case "qa":
                    ip = new FileInputStream("./src/test/resources/config/qaConfig.properties");
                    System.out.println("Running on evn " + env);
                    break;
                case "dev":
                    ip = new FileInputStream("./src/test/resources/config/devConfig.properties");
                    System.out.println("Running on evn " + env);
                    break;
                case "uat":
                    ip = new FileInputStream("./src/test/resources/config/uatConfig.properties");
                    System.out.println("Running on evn " + env);
                    break;
                default:
                    System.out.println("Please pass correct env");
                    throw new APIFrameworkException("Invalid environment");
            }
            properties.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;

    }
}
