package com.ruoyi.quartz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import java.util.Date;

/**
 * 定时任务调度日志表 sys_job_log
 * 
 * @author ruoyi
 * @date 2019-03-12
 */
public class JobLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 任务日志ID */
	private Integer jobLogId;
	/** 任务名称 */
	private String jobName;
	/** 任务组名 */
	private String jobGroup;
	/** 任务方法 */
	private String methodName;
	/** 方法参数 */
	private String methodParams;
	/** 日志信息 */
	private String jobMessage;
	/** 执行状态（0正常 1失败） */
	private String status;
	/** 异常信息 */
	private String exceptionInfo;

	public void setJobLogId(Integer jobLogId) 
	{
		this.jobLogId = jobLogId;
	}

	public Integer getJobLogId() 
	{
		return jobLogId;
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
	public void setJobMessage(String jobMessage) 
	{
		this.jobMessage = jobMessage;
	}

	public String getJobMessage() 
	{
		return jobMessage;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setExceptionInfo(String exceptionInfo) 
	{
		this.exceptionInfo = exceptionInfo;
	}

	public String getExceptionInfo() 
	{
		return exceptionInfo;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jobLogId", getJobLogId())
            .append("jobName", getJobName())
            .append("jobGroup", getJobGroup())
            .append("methodName", getMethodName())
            .append("methodParams", getMethodParams())
            .append("jobMessage", getJobMessage())
            .append("status", getStatus())
            .append("exceptionInfo", getExceptionInfo())
            .append("createTime", getCreateTime())
            .toString();
    }
}
