package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtils;
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
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtils;

/**
 * 字典数据 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-03-21
 */
@Controller
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController
{
    private String prefix = "system/dict/data";
	
	@Autowired
	private ISysDictDataService sysDictDataService;
	
	//@RequiresPermissions("system:sysDictData:view")
	@GetMapping()
	public String sysDictData()
	{
	    return prefix + "/sysDictData";
	}
	
	/**
	 * 查询字典数据列表
	 */
	//@RequiresPermissions("system:sysDictData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysDictData sysDictData)
	{
		startPage();
        List<SysDictData> list = sysDictDataService.selectSysDictDataList(sysDictData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出字典数据列表
	 */
	//@RequiresPermissions("system:sysDictData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictData sysDictData)
    {
    	List<SysDictData> list = sysDictDataService.selectSysDictDataList(sysDictData);
        ExcelUtils<SysDictData> util = new ExcelUtils<>(SysDictData.class);
        return util.exportExcel(list, "sysDictData");
    }
	
	/**
	 * 新增字典数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存字典数据
	 */
	//@RequiresPermissions("system:sysDictData:add")
	//@Log(title = "字典数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysDictData sysDictData)
	{		
		return toAjax(sysDictDataService.insertSysDictData(sysDictData));
	}

	/**
	 * 修改字典数据
	 */
	@GetMapping("/edit/{dictCode}")
	public String edit(@PathVariable("dictCode") Long dictCode, ModelMap mmap)
	{
		SysDictData sysDictData = sysDictDataService.selectSysDictDataById(dictCode);
		mmap.put("sysDictData", sysDictData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存字典数据
	 */
	//@RequiresPermissions("system:sysDictData:edit")
	//@Log(title = "字典数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysDictData sysDictData)
	{		
		return toAjax(sysDictDataService.updateSysDictData(sysDictData));
	}
	
	/**
	 * 删除字典数据
	 */
	//@RequiresPermissions("system:sysDictData:remove")
	//@Log(title = "字典数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sysDictDataService.deleteSysDictDataByIds(ids));
	}
	
}
