package com.ruoyi.quartz.service;

import com.ruoyi.quartz.domain.JobLog;
import java.util.List;

/**
 * 定时任务调度日志 服务层
 * 
 * @author ruoyi
 * @date 2019-03-12
 */
public interface IJobLogService 
{
	/**
     * 查询定时任务调度日志信息
     * 
     * @param jobLogId 定时任务调度日志ID
     * @return 定时任务调度日志信息
     */
	public JobLog selectJobLogById(Integer jobLogId);
	
	/**
     * 查询定时任务调度日志列表
     * 
     * @param jobLog 定时任务调度日志信息
     * @return 定时任务调度日志集合
     */
	public List<JobLog> selectJobLogList(JobLog jobLog);
	
	/**
     * 新增定时任务调度日志
     * 
     * @param jobLog 定时任务调度日志信息
     * @return 结果
     */
	public int insertJobLog(JobLog jobLog);
	
	/**
     * 修改定时任务调度日志
     * 
     * @param jobLog 定时任务调度日志信息
     * @return 结果
     */
	public int updateJobLog(JobLog jobLog);
		
	/**
     * 删除定时任务调度日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJobLogByIds(String ids);
	
}
