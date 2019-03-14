package com.ruoyi.quartz.service;

import com.ruoyi.quartz.domain.Job;
import java.util.List;

/**
 * 定时任务调度 服务层
 * 
 * @author ruoyi
 * @date 2019-03-10
 */
public interface IJobService 
{
	/**
     * 查询定时任务调度信息
     * 
     * @param jobId 定时任务调度ID
     * @return 定时任务调度信息
     */
	public Job selectJobById(Integer jobId);
	
	/**
     * 查询定时任务调度列表
     * 
     * @param job 定时任务调度信息
     * @return 定时任务调度集合
     */
	public List<Job> selectJobList(Job job);
	
	/**
     * 新增定时任务调度
     * 
     * @param job 定时任务调度信息
     * @return 结果
     */
	public int insertJob(Job job);
	
	/**
     * 修改定时任务调度
     * 
     * @param job 定时任务调度信息
     * @return 结果
     */
	public int updateJob(Job job);
		
	/**
     * 删除定时任务调度信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJobByIds(String ids);

	/**
	 * 检验cron表达式是否有效
	 * @param cronExpression
	 * @return
	 */
	public boolean checkCronExpressionIsValid(String cronExpression);

	/**
	 * 新增任务表达式
	 * @param job
	 * @return
	 */
	public int insertJobCron(Job job);
}
