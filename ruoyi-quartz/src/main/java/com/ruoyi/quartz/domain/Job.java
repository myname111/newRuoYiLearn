package com.ruoyi.quartz.domain;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.quartz.util.CronUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度表 sys_job
 * 
 * @author ruoyi
 * @date 2019-03-10
 */
public class Job extends BaseEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 任务ID */
	private Long jobId;
	/** 任务名称 */
	private String jobName;
	/** 任务组名 */
	private String jobGroup;
	/** 任务方法 */
	private String methodName;
	/** 方法参数 */
	private String methodParams;
	/** cron执行表达式 */
	private String cronExpression;
	/** 计划执行错误策略（1立即执行 2执行一次 3放弃执行） */
	private String misfirePolicy= ScheduleConstants.MISFIRE_DEFAULT;
	/** 状态（0正常 1暂停） */
	private String status;


	public void setJobId(Long jobId)
	{
		this.jobId = jobId;
	}

	public Long getJobId()
	{
		return jobId;
	}
	public void setJobName(String jobName) 
	{
		this.jobName = jobName;
	}

	public String getJobName() 
	{
		return jobName;
	}
	public void setJobGroup(String jobGroup) 
	{
		this.jobGroup = jobGroup;
	}

	public String getJobGroup() 
	{
		return jobGroup;
	}
	public void setMethodName(String methodName) 
	{
		this.methodName = methodName;
	}

	public String getMethodName() 
	{
		return methodName;
	}
	public void setMethodParams(String methodParams) 
	{
		this.methodParams = methodParams;
	}

	public String getMethodParams() 
	{
		return methodParams;
	}
	public void setCronExpression(String cronExpression) 
	{
		this.cronExpression = cronExpression;
	}

	public String getCronExpression() 
	{
		return cronExpression;
	}
	public void setMisfirePolicy(String misfirePolicy) 
	{
		this.misfirePolicy = misfirePolicy;
	}

	public String getMisfirePolicy() 
	{
		return misfirePolicy;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}

	/**
	 * 返回下一个执行时间
	 * @return
	 */
	public Date getNextValidTime(){
		if (StringUtils.isNotEmpty(cronExpression)){
			return CronUtils.getNextExecution(cronExpression);
		}
		return null;
	}
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jobId", getJobId())
            .append("jobName", getJobName())
            .append("jobGroup", getJobGroup())
            .append("methodName", getMethodName())
            .append("methodParams", getMethodParams())
            .append("cronExpression", getCronExpression())
            .append("misfirePolicy", getMisfirePolicy())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
