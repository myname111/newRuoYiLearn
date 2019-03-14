package com.ruoyi.web.controller.tool;

import com.ruoyi.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * build表单构建
 */
@Controller
@RequestMapping("/tool/build")
public class BuildController extends BaseController{
    private static String prefix="tool/build";

    @GetMapping()
    public String build(){
        return prefix+"/build";
    }
}
