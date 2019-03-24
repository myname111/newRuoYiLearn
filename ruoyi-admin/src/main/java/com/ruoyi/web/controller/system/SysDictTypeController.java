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
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.service.ISysDictTypeService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtils;

/**
 * 字典类型 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-03-21
 */
@Controller
@RequestMapping("/system/dict")
public class SysDictTypeController extends BaseController
{
    private String prefix = "system/dict/type";
	
	@Autowired
	private ISysDictTypeService sysDictTypeService;
	
	//@RequiresPermissions("system:sysDictType:view")
	@GetMapping()
	public String sysDictType()
	{
	    return prefix + "/type";
	}
	
	/**
	 * 查询字典类型列表
	 */
	//@RequiresPermissions("system:sysDictType:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysDictType sysDictType)
	{
		startPage();
        List<SysDictType> list = sysDictTypeService.selectSysDictTypeList(sysDictType);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出字典类型列表
	 */
	//@RequiresPermissions("system:sysDictType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictType sysDictType)
    {
    	List<SysDictType> list = sysDictTypeService.selectSysDictTypeList(sysDictType);
        ExcelUtils<SysDictType> util = new ExcelUtils<SysDictType>(SysDictType.class);
        return util.exportExcel(list, "sysDictType");
    }
	
	/**
	 * 新增字典类型
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存字典类型
	 */
	//@RequiresPermissions("system:sysDictType:add")
	//@Log(title = "字典类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysDictType sysDictType)
	{		
		return toAjax(sysDictTypeService.insertSysDictType(sysDictType));
	}

	/**
	 * 修改字典类型
	 */
	@GetMapping("/edit/{dictId}")
	public String edit(@PathVariable("dictId") Long dictId, ModelMap mmap)
	{
		SysDictType sysDictType = sysDictTypeService.selectSysDictTypeById(dictId);
		mmap.put("sysDictType", sysDictType);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存字典类型
	 */
	//@RequiresPermissions("system:sysDictType:edit")
	//@Log(title = "字典类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysDictType sysDictType)
	{		
		return toAjax(sysDictTypeService.updateSysDictType(sysDictType));
	}
	
	/**
	 * 删除字典类型
	 */
	//@RequiresPermissions("system:sysDictType:remove")
	//@Log(title = "字典类型", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sysDictTypeService.deleteSysDictTypeByIds(ids));
	}
	
}
