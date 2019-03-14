package com.ruoyi.quartz.util;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * cron表达式工具类
 */
public class CronUtils {
    /**
     * 返回下一个执行时间,根据给定的cron表达式
     * @param cronExpression
     * @return
     */
    public static Date getNextExecution(String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * cron表达式是否有效
     * @param cronExpression
     * @return
     */
    public static boolean isValid(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }
}
