package com.ruoyi.common.utils;

import com.ruoyi.common.support.Convert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户端工具类
 */
public class ServletUtils {
  public static String getParameter(String name){
      return getRequest().getParameter(name);
  }
  public static Integer getParameterToInt(String name){
      return Convert.toInt(getParameter(name));
  }
  public static HttpServletRequest getRequest(){
      return  getRequestAttributes().getRequest();
  }
  public static ServletRequestAttributes getRequestAttributes(){
      RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
      return (ServletRequestAttributes) attributes;
  }
}
