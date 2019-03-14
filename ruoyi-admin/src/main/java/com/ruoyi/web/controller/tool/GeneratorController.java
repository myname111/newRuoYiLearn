package com.ruoyi.web.controller.tool;

import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.gen.domain.ColumnInfo;
import com.ruoyi.gen.domain.TableInfo;
import com.ruoyi.gen.service.IGenService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/tool/gen")
public class GeneratorController extends BaseController{
    @Autowired
    private IGenService genService;
    private String prefix="tool/gen";
    @GetMapping()
    public String gen(){
        return prefix+"/gen";
    }

    /**
     * 查询表数据并进行分页
     * @param tableInfo
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TableInfo tableInfo){
            startPage();
            List<TableInfo> list = genService.selectTableList(tableInfo);
            return getDataTable(list);
    }
    /**
     * 生成代码
     */
    @GetMapping("/genCode/{tableName}")
    public void genCode(@PathVariable("tableName") String tableName, HttpServletResponse response)throws IOException{
        byte[] data = genService.generatorCode(tableName);
        response.reset();
        response.setHeader("Content-Disposition","attachment;filename=\""+tableName+".zip\"");
        response.addHeader("Content-Length",""+data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        IOUtils.write(data,response.getOutputStream());
    }
    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(String tables,HttpServletResponse response)throws IOException{
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition","attachment;filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length",""+data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        IOUtils.write(data,response.getOutputStream());
    }
}
