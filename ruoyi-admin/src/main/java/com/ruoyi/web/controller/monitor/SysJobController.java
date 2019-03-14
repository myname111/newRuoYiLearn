package com.ruoyi.web.controller.monitor;

import java.util.List;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
//import com.ruoyi.common.annotation.Log;
//import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.quartz.domain.Job;
import com.ruoyi.quartz.service.IJobService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
//import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 定时任务调度 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-03-10
 */
@Controller
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController
{
    private String prefix = "monitor/job";
	
	@Autowired
	private IJobService jobService;
	
	//@RequiresPermissions("quartz:job:view")
	@GetMapping()
	public String job()
	{
	    return prefix + "/job";
	}
	
	/**
	 * 查询定时任务调度列表
	 */
	//@RequiresPermissions("quartz:job:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Job job)
	{
		startPage();
        List<Job> list = jobService.selectJobList(job);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出定时任务调度列表
	 */
	//@RequiresPermissions("quartz:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Job job)
    {
    	List<Job> list = jobService.selectJobList(job);
       // ExcelUtil<Job> util = new ExcelUtil<Job>(Job.class);
        //return util.exportExcel(list, "job");
		return success();
    }
	
	/**
	 * 新增定时任务调度
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存定时任务调度
	 */
	//@RequiresPermissions("quartz:job:add")
	//@Log(title = "定时任务调度", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Job job)
	{		
		return toAjax(jobService.insertJobCron(job));
	}

	/**
	 * 修改定时任务调度
	 */
	@GetMapping("/edit/{jobId}")
	public String edit(@PathVariable("jobId") Integer jobId, ModelMap mmap)
	{
		Job job = jobService.selectJobById(jobId);
		mmap.put("job", job);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存定时任务调度
	 */
	//@RequiresPermissions("quartz:job:edit")
	//@Log(title = "定时任务调度", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Job job)
	{		
		return toAjax(jobService.updateJob(job));
	}
	
	/**
	 * 删除定时任务调度
	 */
	//@RequiresPermissions("quartz:job:remove")
	//@Log(title = "定时任务调度", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(jobService.deleteJobByIds(ids));
	}

	/**
	 * 检查cron表达式是否正确
	 * @param job
	 * @return
	 */
	@PostMapping("/checkCronExpressionIsValid")
	@ResponseBody
	public boolean checkCronExpressionIsValid(Job job){
       return jobService.checkCronExpressionIsValid(job.getCronExpression());
	}
}
