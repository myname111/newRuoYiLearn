package com.ruoyi.web.controller.monitor;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController{
    //TODO:重定向和转发关于决定路径和相对路径的问题
    private String prefix="/monitor/druid";
    @GetMapping()
    public String data(){
        return redirect(prefix+"/index");
    }
}
