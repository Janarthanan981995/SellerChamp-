package com.sellerchamp.main;

public final class Framework {
    private Framework(){}

    private static final String CONFIG_PATH = System.getProperty("user.dir")+"/src/test/resources/config/config.properties";
    private static final int WAIT_TIME = 15;
    private static final String DOWNLOAD_FILE_PATH = System.getProperty("user.home")+"/Downloads/";;

    public static String getConfigPath(){
        return CONFIG_PATH;
    }

    public static int getWaitTime(){
        return WAIT_TIME;
    }

    public static String getDownloadDirectory(){
       return DOWNLOAD_FILE_PATH;
    }

    public static String getDownloadFilePath(){
        return getDownloadDirectory()+"inventory.csv";
    }
}
