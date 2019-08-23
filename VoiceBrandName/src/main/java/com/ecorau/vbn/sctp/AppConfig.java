package com.ecorau.vbn.sctp;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Setter
public class AppConfig {
    private static AppConfig appConfig;
    private String remoteIp;
    private int remotePort;
    private String localIp;
    private int localPort;
    private int numberOfThread;

    private AppConfig() {
    }

    static {
        String configFileDir = System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.properties";
        System.out.println("configFileDir is: " + configFileDir);
        try (InputStream input = new FileInputStream(configFileDir)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);

            appConfig = new AppConfig();
            appConfig.setRemoteIp(prop.getProperty("server.ip"));
            appConfig.setRemotePort(Integer.parseInt(prop.getProperty("server.port")));
            appConfig.setLocalIp(prop.getProperty("local.ip"));
            appConfig.setLocalPort(Integer.parseInt(prop.getProperty("local.port")));

            appConfig.setNumberOfThread(Integer.parseInt(prop.getProperty("numberOfThread")));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static AppConfig getInstance() {
        return appConfig;
    }
}
