package com.ruoyi.web.controller.system;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//部门管理
@Controller
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController{
    private String prefix="system/dept";
    @Autowired
    private ISysDeptService sysDeptService;
    //部门管理
    @GetMapping()
    public String dept(){
       return prefix+"/dept";
   }
   //部门列表
   @GetMapping("/list")
   @ResponseBody
   public List<SysDept> list(SysDept sysDept){
       List<SysDept> deptList = sysDeptService.selectDeptList(sysDept);
       return deptList;
   }

   //添加部门
    @GetMapping("/add/{parentId}")
    public String addDept(@PathVariable  Long parentId, ModelMap map){
       SysDept parentDept = sysDeptService.selectDeptById(parentId);
       map.put("dept",parentDept);
       return prefix+"/add";
    }
    //部门树
    @GetMapping("/selectDeptTree/{parentId}")
    public String selectDeptTree(@PathVariable Long parentId,ModelMap map){
        SysDept parentDept = sysDeptService.selectDeptById(parentId);
        map.put("dept",parentDept);
        return prefix +"/tree";
    }
    //加载部门列表树
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String,Object>> treeData(){
        List<Map<String,Object>> deptTree = sysDeptService.selectDeptTree(new SysDept());
        return deptTree;
    }
    //验证部门名称的唯一性
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(SysDept sysDept){
       return sysDeptService.checkDeptNameUnique(sysDept);
    }

    /**
     * 添加保存部门
     * @param sysDept
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysDept sysDept){
        return toAjax(sysDeptService.insertDept(sysDept));
    }

    //修改部门
    @GetMapping("/edit/{deptId}")
    public String editDept(@PathVariable Long deptId,ModelMap map){
       SysDept sysDept =  sysDeptService.selectDeptById(deptId);
       if (StringUtils.isNotNull(sysDept)&&Long.valueOf(0).equals(sysDept.getParentId())){
           sysDept.setParentName("无");
       }
       map.put("dept",sysDept);
       return prefix+"/edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult saveEdit(SysDept sysDept){
        //TODO:设置更新者
        return toAjax(sysDeptService.updateDept(sysDept));
    }
    @PostMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult deleteDept(@PathVariable Long deptId){
           if(sysDeptService.selectDeptCount(deptId)>0){
              return  error(1,"存在子部门,无法删除该部门");
           }
           //TODO:部门存在员工,无法删除
           return toAjax(sysDeptService.deleteDept(deptId));
    }
}
