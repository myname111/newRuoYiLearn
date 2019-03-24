package com.ruoyi.web.controller.monitor;

import java.util.List;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ruoyi.common.utils.poi.ExcelUtils;
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
import com.ruoyi.quartz.domain.SysJobLog;
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
    private String prefix = "monitor/job";
	
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
	public TableDataInfo list(SysJobLog jobLog)
	{
		startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出定时任务调度日志列表
	 */
	//@RequiresPermissions("quartz:jobLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJobLog jobLog)
    {
    	List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        ExcelUtils<SysJobLog> util = new ExcelUtils<>(SysJobLog.class);
        return util.exportExcel(list, "定时任务日志");
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
	public AjaxResult addSave(SysJobLog jobLog)
	{		
		return toAjax(jobLogService.insertJobLog(jobLog));
	}

	/**
	 * 修改定时任务调度日志
	 */
	@GetMapping("/edit/{jobLogId}")
	public String edit(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap)
	{
		SysJobLog jobLog = jobLogService.selectJobLogById(jobLogId);
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
	public AjaxResult editSave(SysJobLog jobLog)
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

	/**
	 * 日志详情
	 * @param logId
	 * @param map
	 * @return
	 */
	@GetMapping("/detail/{logId}")
	public String detail(@PathVariable("logId")Long logId,ModelMap map){
		SysJobLog sysJobLog = jobLogService.selectJobLogById(logId);
		map.put("name","jobLog");
		map.put("jobLog",sysJobLog);
		return prefix+"/detail";
	}
	/**
	 * 清空日志
	 */
	@PostMapping("/clean")
	@ResponseBody
	public AjaxResult clean(){
        jobLogService.cleanJobLog();
		return success();
	}
}
