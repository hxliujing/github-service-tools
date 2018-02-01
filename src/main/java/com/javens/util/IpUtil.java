package com.javens.util;

import java.net.InetAddress;


public class IpUtil {
    public static String getLocalIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            return "";
        }
    }

}
