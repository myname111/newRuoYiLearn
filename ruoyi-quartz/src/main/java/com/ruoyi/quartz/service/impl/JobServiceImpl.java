package com.ruoyi.quartz.service.impl;

import java.util.List;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.quartz.util.CronUtils;
import com.ruoyi.quartz.util.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.quartz.mapper.JobMapper;
import com.ruoyi.quartz.domain.SysJob;
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
	public SysJob selectJobById(Long jobId)
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
	public List<SysJob> selectJobList(SysJob job)
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
	public int insertJob(SysJob job)
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
	public int updateJob(SysJob job)
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
	public void  deleteJobByIds(String ids)
	{
		Long[] idarr = Convert.toLongArray(ids);
		if (idarr!=null){
			for (Long id: idarr) {
				SysJob sysJob = jobMapper.selectJobById(id);
				deleteJob(sysJob);
			}
		}

	}

	/**
	 * 删除定时任务
	 * @param sysJob
	 * @return
	 */
	@Override
	public  int deleteJob(SysJob sysJob) {
		int rows = jobMapper.deleteJobById(sysJob.getJobId());
		if (rows>0){
			ScheduleUtils.deleteScheduleJob(scheduler,sysJob.getJobId());
		}
		return rows;
	}

	/**
	 * 检查表达式的正确性
	 * @param cronExpression
	 * @return
	 */
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
	public int insertJobCron(SysJob job) {
		job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		int rows = jobMapper.insertJob(job);
		if (rows>0){
			ScheduleUtils.createScheduleJob(scheduler,job);
		}
		return rows;
	}

	/**
	 * 更新任务
	 * @param job
	 * @return
	 */
	@Override
	public int updateJobCron(SysJob job) {
		int row = jobMapper.updateJob(job);
		if (row>0){
			ScheduleUtils.updateScheduleJob(scheduler,job);
		}
		return row;
	}

	/**
	 * 修改任务状态
	 * @param job
	 * @return
	 */
	@Override
	public int changeStatus(SysJob job) {
		String status = job.getStatus();
		int rows = 0;
		if (ScheduleConstants.Status.NORMAL.getValue().equals(status)){
			rows=resumeJob(job);
		}else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)){
			rows = pauseJob(job);
		}
		return rows;
	}

	/**
	 * 暂停定时任务
	 * @param job
	 * @return
	 */
	@Override
	public int pauseJob(SysJob job) {
		job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		int rows = jobMapper.updateJob(job);
		if (rows>0){
			ScheduleUtils.pauseJob(scheduler,job.getJobId());
		}
		return rows;
	}

	/**
	 * 立即运行任务
	 * @param sysJob
	 * @return
	 */
	@Override
	public int run(SysJob sysJob) {
		return ScheduleUtils.run(scheduler,selectJobById(sysJob.getJobId()));
	}

	/**
	 * 重启定时任务
	 * @param job
	 * @return
	 */
    @Override
	public int resumeJob(SysJob job){
		job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
		int rows = jobMapper.updateJob(job);
		if (rows>0){
			ScheduleUtils.remuseJob(scheduler,job.getJobId());
		}
		return rows;
	}
}
