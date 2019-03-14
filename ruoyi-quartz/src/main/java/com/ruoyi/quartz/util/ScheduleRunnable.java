package com.ruoyi.quartz.util;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 */
public class ScheduleRunnable implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(ScheduleRunnable.class);

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException {
        this.target = SpringUtils.getBean(beanName);
        this.params = params;
        if (StringUtils.isNotEmpty(params)){
            this.method=target.getClass().getDeclaredMethod(methodName,String.class);
        }else{
            this.method=target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try{
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotEmpty(params)){
                method.invoke(target,params);
            }else{
                method.invoke(target);
            }
        }catch(Exception e){
            log.error("执行定时任务出错 - :",e);
        }

    }
}
