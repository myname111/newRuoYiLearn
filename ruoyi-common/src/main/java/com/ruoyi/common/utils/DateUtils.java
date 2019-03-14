package com.ruoyi.common.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
    private static String[] parsePatterns={
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"
    };
    public static String YYYY_MM_DD="yyyy-MM-dd";
    public static String YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
    //日期型字符串转化为日期格式
    public static Date parseDate(Object str) {
        if (str==null){
            return null;
        }
        try{
            return parseDate(str.toString(),parsePatterns);
        }catch(ParseException e){
            return null;
        }
    }

    /**
     * 获取当前日期,默认格式为yyyy-MM-dd
     * @return
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String dateTimeNow(String format) {
        return parseDateToStr(format,new Date());
    }

    public static String parseDateToStr(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取服务器启动时间
     * @return
     */
    public static Date getServerStartDate() {
        return new Date(ManagementFactory.getRuntimeMXBean().getStartTime());
    }

    /**
     * 获取当前date型日期
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 计算两个时间的差值
     * @param endDate
     * @param beginDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date beginDate) {
             long dayOfMs = 1000*60*60*24;
             long houOfMs = 1000*60*60;
             long minOfms = 1000*60;

             long diff = endDate.getTime()-beginDate.getTime();

             long day = diff/dayOfMs;
             long hour = diff%dayOfMs/houOfMs;
             long minute = diff%dayOfMs%houOfMs/minOfms;

             return day+"天"+hour+"小时"+minute+"分钟";
    }
}
