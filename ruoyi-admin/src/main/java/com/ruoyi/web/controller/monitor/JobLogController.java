package com.ruoyi.web.controller.monitor;

import java.util.List;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import com.ruoyi.common.annotation.Log;
//import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.quartz.domain.JobLog;
import com.ruoyi.quartz.service.IJobLogService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
//import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 定时任务调度日志 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class JobLogController extends BaseController
{
    private String prefix = "quartz/jobLog";
	
	@Autowired
	private IJobLogService jobLogService;
	
	//@RequiresPermissions("quartz:jobLog:view")
	@GetMapping()
	public String jobLog()
	{
	    return prefix + "/jobLog";
	}
	
	/**
	 * 查询定时任务调度日志列表
	 */
	//@RequiresPermissions("quartz:jobLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(JobLog jobLog)
	{
		startPage();
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出定时任务调度日志列表
	 */
	//@RequiresPermissions("quartz:jobLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JobLog jobLog)
    {
    	List<JobLog> list = jobLogService.selectJobLogList(jobLog);
       // ExcelUtil<JobLog> util = new ExcelUtil<JobLog>(JobLog.class);
        //return util.exportExcel(list, "jobLog");
		return success();
    }
	
	/**
	 * 新增定时任务调度日志
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存定时任务调度日志
	 */
	//@RequiresPermissions("quartz:jobLog:add")
	//@Log(title = "定时任务调度日志", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(JobLog jobLog)
	{		
		return toAjax(jobLogService.insertJobLog(jobLog));
	}

	/**
	 * 修改定时任务调度日志
	 */
	@GetMapping("/edit/{jobLogId}")
	public String edit(@PathVariable("jobLogId") Integer jobLogId, ModelMap mmap)
	{
		JobLog jobLog = jobLogService.selectJobLogById(jobLogId);
		mmap.put("jobLog", jobLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存定时任务调度日志
	 */
	//@RequiresPermissions("quartz:jobLog:edit")
	//@Log(title = "定时任务调度日志", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(JobLog jobLog)
	{		
		return toAjax(jobLogService.updateJobLog(jobLog));
	}
	
	/**
	 * 删除定时任务调度日志
	 */
	//@RequiresPermissions("quartz:jobLog:remove")
	//@Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(jobLogService.deleteJobLogByIds(ids));
	}
	
}
