package com.ruoyi.quartz.mapper;

import com.ruoyi.quartz.domain.SysJob;
import java.util.List;	

/**
 * 定时任务调度 数据层
 * 
 * @author ruoyi
 * @date 2019-03-10
 */
public interface JobMapper 
{
	/**
     * 查询定时任务调度信息
     * 
     * @param jobId 定时任务调度ID
     * @return 定时任务调度信息
     */
	public SysJob selectJobById(Long jobId);
	
	/**
     * 查询定时任务调度列表
     * 
     * @param job 定时任务调度信息
     * @return 定时任务调度集合
     */
	public List<SysJob> selectJobList(SysJob job);
	
	/**
     * 新增定时任务调度
     * 
     * @param job 定时任务调度信息
     * @return 结果
     */
	public int insertJob(SysJob job);
	
	/**
     * 修改定时任务调度
     * 
     * @param job 定时任务调度信息
     * @return 结果
     */
	public int updateJob(SysJob job);
	
	/**
     * 删除定时任务调度
     * 
     * @param jobId 定时任务调度ID
     * @return 结果
     */
	public int deleteJobById(Long jobId);
	
	/**
     * 批量删除定时任务调度
     * 
     * @param jobIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteJobByIds(Long[] jobIds);

}