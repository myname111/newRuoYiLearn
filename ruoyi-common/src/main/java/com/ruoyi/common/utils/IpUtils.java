package com.ruoyi.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ip工具类
 */
public class IpUtils {
    /**
     * 获取主机名
     * @return
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "未知";
    }

    /**
     * 获取主机ip
     * @return
     */
    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }
}
