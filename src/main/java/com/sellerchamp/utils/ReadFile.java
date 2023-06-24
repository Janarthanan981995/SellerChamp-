package com.sellerchamp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import com.sellerchamp.main.Framework;

public final class ReadFile {

    private ReadFile(){}
    private static Properties properties = new Properties();

   
    
    static {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(Framework.getConfigPath());
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static String getValue(String key) throws Exception {
        if(Objects.isNull(properties.getProperty(key))){
            throw new Exception("property name "+key+" is not found, please check the properties");
        }
        return properties.getProperty(key);
    }
}
