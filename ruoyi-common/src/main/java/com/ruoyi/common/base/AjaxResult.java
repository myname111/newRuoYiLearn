package com.ruoyi.common.base;

import java.util.HashMap;

public class AjaxResult extends HashMap<String,Object>{
    public AjaxResult(){

    }

    public static AjaxResult success(){
        return AjaxResult.success("操作成功");
    }

    private static AjaxResult success(String msg) {
        AjaxResult result = new AjaxResult();
        result.put("msg",msg);
        result.put("code",0);
        return result;
    }
    public static AjaxResult error(){
        return AjaxResult.error(1,"操作失败");
    }
    public static AjaxResult error(String msg){
        return AjaxResult.error(500,msg);
    }
    public static AjaxResult error(int code,String msg){
        AjaxResult result = new AjaxResult();
        result.put("msg",msg);
        result.put("code",code);
        return result;
    }
    /**
     * 添加信息
     * @param key
     * @param value
     * @return
     */
    @Override
    public AjaxResult put(String key,Object value){
       super.put(key, value);
       return this;
    }
}
