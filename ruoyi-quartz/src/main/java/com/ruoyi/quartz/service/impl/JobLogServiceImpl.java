package com.ruoyi.quartz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.quartz.mapper.JobLogMapper;
import com.ruoyi.quartz.domain.SysJobLog;
import com.ruoyi.quartz.service.IJobLogService;
import com.ruoyi.common.support.Convert;

/**
 * 定时任务调度日志 服务层实现
 * 
 * @author ruoyi
 * @date 2019-03-12
 */
@Service
public class JobLogServiceImpl implements IJobLogService 
{
	@Autowired
	private JobLogMapper jobLogMapper;

	/**
     * 查询定时任务调度日志信息
     * 
     * @param jobLogId 定时任务调度日志ID
     * @return 定时任务调度日志信息
     */
    @Override
	public SysJobLog selectJobLogById(Long jobLogId)
	{
	    return jobLogMapper.selectJobLogById(jobLogId);
	}
	
	/**
     * 查询定时任务调度日志列表
     * 
     * @param jobLog 定时任务调度日志信息
     * @return 定时任务调度日志集合
     */
	@Override
	public List<SysJobLog> selectJobLogList(SysJobLog jobLog)
	{
	    return jobLogMapper.selectJobLogList(jobLog);
	}
	
    /**
     * 新增定时任务调度日志
     * 
     * @param jobLog 定时任务调度日志信息
     * @return 结果
     */
	@Override
	public int insertJobLog(SysJobLog jobLog)
	{
	    return jobLogMapper.insertJobLog(jobLog);
	}
	
	/**
     * 修改定时任务调度日志
     * 
     * @param jobLog 定时任务调度日志信息
     * @return 结果
     */
	@Override
	public int updateJobLog(SysJobLog jobLog)
	{
	    return jobLogMapper.updateJobLog(jobLog);
	}

	/**
     * 删除定时任务调度日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJobLogByIds(String ids)
	{
		return jobLogMapper.deleteJobLogByIds(Convert.toLongArray(ids));
	}

	@Override
	public void cleanJobLog() {
		jobLogMapper.cleanJobLog();
	}

}
