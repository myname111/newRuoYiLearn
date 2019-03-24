package com.ruoyi.framework.web.base;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.page.PageDomain;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.page.TableSupport;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

//web层通用数据处理
public class BaseController {
    //将前台传递过来的日期格式的字符串,自动转换成为Date类型
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
             webDataBinder.registerCustomEditor(Date.class,new PropertyEditorSupport(){
                 @Override
                 public void setAsText(String text){
                    setValue(DateUtils.parseDate(text));
                 }
             });
    }
    //操作结果返回
    protected AjaxResult toAjax(int row){
        return row>0?success():error();
    }
    //操作成功提醒
    protected AjaxResult success(){
        return AjaxResult.success();
    }
    //操作失败提醒
    protected AjaxResult error(){
        return AjaxResult.error();
    }
    protected  AjaxResult error(String msg){
        return AjaxResult.error(msg);
    }
    protected AjaxResult error(int code,String msg){
        return AjaxResult.error(code,msg);
    }
    //分页设置
    protected void  startPage(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum=pageDomain.getPageNum();
        Integer pageSize=pageDomain.getPageSize();
        if(StringUtils.isNotNull(pageNum)&&StringUtils.isNotNull(pageSize)){
            String orderBy = pageDomain.getOrderBy();
            PageHelper.startPage(pageNum,pageSize,orderBy);
        }
    }
    protected TableDataInfo getDataTable(List<?> list){
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setRows(list);
        tableDataInfo.setTotal(new PageInfo(list).getTotal());
        tableDataInfo.setCode(0);
        return tableDataInfo;
    }

    protected String redirect(String url) {
        return StringUtils.format("redirect:{}",url);
    }
}
