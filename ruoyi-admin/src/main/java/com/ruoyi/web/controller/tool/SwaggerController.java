package com.ruoyi.web.controller.tool;

import com.ruoyi.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController{
    @GetMapping()
    public String swagger(){
       return redirect("/swagger-ui.html");
    }


}
