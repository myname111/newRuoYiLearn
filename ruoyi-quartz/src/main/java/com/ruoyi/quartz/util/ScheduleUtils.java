package com.ruoyi.quartz.util;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.quartz.domain.Job;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务工具类
 */
public class ScheduleUtils {
    private static final Logger log = LoggerFactory.getLogger(ScheduleUtils.class);
    /**
     * 创建定时任务
     * @param scheduler
     * @param job
     */
    public static void createScheduleJob(Scheduler scheduler, Job job) {
        try{
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(job.getJobId())).build();
            //表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(job,cronScheduleBuilder);
            //按照新的cronExpression构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(job.getJobId())).withSchedule(cronScheduleBuilder).build();
            //放入参数,运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES,job);
            scheduler.scheduleJob(jobDetail,trigger);
            //暂停任务
            if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())){
                pauseJob(scheduler,job.getJobId());
            }
        }catch(TaskException e){
            log.error("TaskException 异常:",e);
        } catch (SchedulerException e) {
            log.error("SchedulerException 异常:",e);
        }

    }

    /**
     * 暂停任务
     * @param scheduler
     * @param jobId
     */
    public static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
           log.error("pauseJob 异常:",e);
        }
    }

    /**
     * 获取表达式触发器
     * @param scheduler
     * @param jobId
     * @return
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
          log.error("getCronTrigger 异常:", e);
        }
        return null;
    }

    /**
     * 获取触发器key
     * @param jobId
     * @return
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME+jobId);
    }

    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(Job job, CronScheduleBuilder cronScheduleBuilder) throws TaskException {
        switch (job.getMisfirePolicy()){
            case ScheduleConstants.MISFIRE_DEFAULT:
                return cronScheduleBuilder;
            case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
                return cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
                return cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleConstants.MISFIRE_DO_NOTHING:
                return cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
            default:
                throw new TaskException("The task misfire policy '" + job.getMisfirePolicy() + "' cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
        }
    }

    /**
     * 获取jobkey
     * @param jobId
     * @return
     */
    private static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME+jobId);
    }
}
