package com.ruoyi.web.controller.monitor;

import com.ruoyi.framework.web.domain.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务监控,获取服务器信息
 */
@Controller
@RequestMapping("/monitor/server")
public class ServerController {
    private String prefix="monitor/server";

    @GetMapping()
    public String server(ModelMap model){
        Server server = new Server();
        server.copyTo();
        model.put("server",server);
        return prefix+"/server";
    }
}
