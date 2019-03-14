package com.ruoyi.quartz.service.impl;

import java.util.List;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.quartz.util.CronUtils;
import com.ruoyi.quartz.util.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.quartz.mapper.JobMapper;
import com.ruoyi.quartz.domain.Job;
import com.ruoyi.quartz.service.IJobService;
import com.ruoyi.common.support.Convert;

/**
 * 定时任务调度 服务层实现
 * 
 * @author ruoyi
 * @date 2019-03-10
 */
@Service
public class JobServiceImpl implements IJobService 
{
	@Autowired
	private JobMapper jobMapper;
    @Autowired
	private Scheduler scheduler;
	/**
     * 查询定时任务调度信息
     * 
     * @param jobId 定时任务调度ID
     * @return 定时任务调度信息
     */
    @Override
	public Job selectJobById(Integer jobId)
	{
	    return jobMapper.selectJobById(jobId);
	}
	
	/**
     * 查询定时任务调度列表
     * 
     * @param job 定时任务调度信息
     * @return 定时任务调度集合
     */
	@Override
	public List<Job> selectJobList(Job job)
	{
	    return jobMapper.selectJobList(job);
	}
	
    /**
     * 新增定时任务调度
     * 
     * @param job 定时任务调度信息
     * @return 结果
     */
	@Override
	public int insertJob(Job job)
	{
	    return jobMapper.insertJob(job);
	}
	
	/**
     * 修改定时任务调度
     * 
     * @param job 定时任务调度信息
     * @return 结果
     */
	@Override
	public int updateJob(Job job)
	{
	    return jobMapper.updateJob(job);
	}

	/**
     * 删除定时任务调度对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJobByIds(String ids)
	{
		return jobMapper.deleteJobByIds(Convert.toStrArray(ids));
	}

	@Override
	public boolean checkCronExpressionIsValid(String cronExpression) {
		return CronUtils.isValid(cronExpression);
	}

	/**
	 * 新增任务
	 * @param job
	 * @return
	 */
	@Override
	public int insertJobCron(Job job) {
		job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		int rows = jobMapper.insertJob(job);
		if (rows>0){
			ScheduleUtils.createScheduleJob(scheduler,job);
		}
		return rows;
	}

}
