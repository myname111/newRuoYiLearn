package com.ruoyi.quartz.util;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.domain.SysJobLog;
import com.ruoyi.quartz.service.IJobLogService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * 定时任务处理
 */
@DisallowConcurrentExecution
public class ScheduleJob extends QuartzJobBean{
    private static final Logger log = LoggerFactory.getLogger(ScheduleJob.class);
    private ThreadPoolTaskExecutor executor = SpringUtils.getBean("threadPoolTaskExecutor");
    private final static IJobLogService jobLogService = SpringUtils.getBean(IJobLogService.class);
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SysJob job = new SysJob();
        BeanUtils.copyBeanProp(job,jobExecutionContext.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        //定时任务日志记录
        SysJobLog jobLog = new SysJobLog();
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setMethodName(job.getMethodName());
        jobLog.setMethodParams(job.getMethodParams());
        jobLog.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();
        try{
            log.info("任务开始执行 - 名称:{} 方法:{}",job.getJobName(),job.getMethodName());
            ScheduleRunnable task = new ScheduleRunnable(job.getJobName(),job.getMethodName(),job.getMethodParams());
            Future<?> future = executor.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;

            jobLog.setStatus(Constants.SUCCESS);
            jobLog.setJobMessage(job.getJobName()+" 总共耗时:"+times+"毫秒");

            log.info("任务执行结束 - 名称:{} 耗时:{} 毫秒",job.getJobName(),times);
        }catch(Exception e){
            log.info("任务执行失败 - 名称:{} 方法:{}",job.getJobName(),job.getMethodName());
            log.error("任务执行异常 - :",e);
            long times = System.currentTimeMillis() - startTime;

            jobLog.setStatus(Constants.FAIL);
            jobLog.setExceptionInfo(StringUtils.substring(e.getMessage(),0,2000));
        }finally {
            jobLogService.insertJobLog(jobLog);
        }



    }
}
