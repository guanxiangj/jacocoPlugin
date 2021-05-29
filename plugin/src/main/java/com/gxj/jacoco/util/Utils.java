package com.gxj.jacoco.util;

public class Utils {
    
    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toLowerCase().contains("windows");
    }
}
