package com.ruoyi.web.controller.system;

import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.service.ISysDeptService;
import com.sun.corba.se.impl.logging.InterceptorsSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SysIndexController extends BaseController{
    @Autowired
    private ISysDeptService sysDeptService;
    @GetMapping("/index")
    public String index(ModelMap map,Long deptId){
        return "index";
    }
    @GetMapping("/system/main")
    public String main(){
        return "main";
    }
}
