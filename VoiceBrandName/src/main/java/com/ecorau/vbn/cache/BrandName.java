package com.ecorau.vbn.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class BrandName {
    private static HashMap<String, String> mapBrandName;

    private BrandName() {
    }

    static {
        mapBrandName = new HashMap<>();
        String brandNameDir = System.getProperty("user.dir") + File.separator + "config" + File.separator + "brandname.properties";
        System.out.println("configFileDir is: " + brandNameDir);
        try (InputStream input = new FileInputStream(brandNameDir)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            for (String phoneNumber : prop.stringPropertyNames()) {
                mapBrandName.put(phoneNumber, prop.getProperty(phoneNumber));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getBrandName(String phoneNumber) {
        if (mapBrandName.containsKey(phoneNumber))
            return mapBrandName.get(phoneNumber);
        else
            return "";
    }
}
