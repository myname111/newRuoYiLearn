package com.ruoyi.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class ScheduleConfig {
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);

        Properties properties = new Properties();
        InputStream resourceAsStream=null;
        try {
            resourceAsStream = ScheduleConfig.class.getClassLoader().getResourceAsStream("schedule.properties");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (resourceAsStream!=null){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        schedulerFactoryBean.setQuartzProperties(properties);
        schedulerFactoryBean.setSchedulerName("RuoyiScheduler");
        //延长时间
        schedulerFactoryBean.setStartupDelay(1);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        //启动时更新已存在的job,这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //设置自动启动,默认为true
        schedulerFactoryBean.setAutoStartup(true);
        return schedulerFactoryBean;
    }
}
