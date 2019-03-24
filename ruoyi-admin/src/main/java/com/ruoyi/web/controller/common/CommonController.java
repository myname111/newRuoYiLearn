package com.ruoyi.web.controller.common;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 公共请求处理
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    /**
     * 通用下载请求
     * @param fileName
     * @param delete
     * @param response
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, boolean delete, HttpServletResponse response, HttpServletRequest request){
            String realFileName = System.currentTimeMillis()+fileName.substring(fileName.indexOf("_")+1);
            String filePath = Global.getDownloadPath()+fileName;
            try{
                response.setCharacterEncoding("utf-8");
                response.setContentType("multipart/form-data");
                response.setHeader("Content-Disposition","attachment;fileName="+setFileDownloadHeader(request,realFileName));
                FileUtils.writeBytes(filePath,response.getOutputStream());
                if (delete){
                    FileUtils.deleteFile(filePath);
                }
            } catch(Exception e){
                log.error("文件下载失败{}",e.getMessage());
            }



    }

    /**
     * 解决文件下载各大浏览器文件名乱码问题
     * @param request
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    private String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE")){
             filename= URLEncoder.encode(filename,"UTF-8");
             filename = filename.replace("+","");
        }else if (agent.contains("Firefox")){
            filename=new String(filename.getBytes(),"ISO8859-1");
        }else if (agent.contains("Chrome")){
            filename = URLEncoder.encode(filename,"utf-8");
        }else{
            filename = URLEncoder.encode(filename,"utf-8");
        }
        return filename;
    }
}
