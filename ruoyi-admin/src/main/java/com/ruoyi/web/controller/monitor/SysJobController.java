package com.ruoyi.web.controller.monitor;

import java.util.List;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ruoyi.common.utils.poi.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
//import com.ruoyi.common.annotation.Log;
//import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.quartz.domain.SysJob;
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
	public TableDataInfo list(SysJob job)
	{
		startPage();
        List<SysJob> list = jobService.selectJobList(job);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出定时任务调度列表
	 */
	//@RequiresPermissions("quartz:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJob job)
    {
    	List<SysJob> list = jobService.selectJobList(job);
        ExcelUtils<SysJob> util = new ExcelUtils<SysJob>(SysJob.class);
        return util.exportExcel(list, "定时任务");
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
	public AjaxResult addSave(SysJob job)
	{		
		return toAjax(jobService.insertJobCron(job));
	}

	/**
	 * 修改定时任务调度
	 */
	@GetMapping("/edit/{jobId}")
	public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap)
	{
		SysJob job = jobService.selectJobById(jobId);
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
	public AjaxResult editSave(SysJob job)
	{		
		return toAjax(jobService.updateJobCron(job));
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
        try{
			jobService.deleteJobByIds(ids);
			return success();
		}catch(Exception e){
        	e.printStackTrace();
        	return error(e.getMessage());
		}

	}

	/**
	 * 检查cron表达式是否正确
	 * @param job
	 * @return
	 */
	@PostMapping("/checkCronExpressionIsValid")
	@ResponseBody
	public boolean checkCronExpressionIsValid(SysJob job){
       return jobService.checkCronExpressionIsValid(job.getCronExpression());
	}

	/**
	 * 修改定时任务状态
	 * @param job
	 * @return
	 */
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(SysJob job){
          return toAjax(jobService.changeStatus(job));
	}

	/**
	 * 任务详情
	 * @param jobId
	 * @param map
	 * @return
	 */
	@GetMapping("/detail/{jobId}")
	public String detail(@PathVariable("jobId") Long jobId, ModelMap map){
		SysJob job = jobService.selectJobById(jobId);
		map.put("name","job");
        map.put("job",job);
        return prefix+"/detail";
	}

	/**
	 * 运行定时任务
	 * @param sysJob
	 * @return
	 */
	@PostMapping("/run")
	@ResponseBody
	public AjaxResult run(SysJob sysJob){
		return toAjax(jobService.run(sysJob));
	}

}
