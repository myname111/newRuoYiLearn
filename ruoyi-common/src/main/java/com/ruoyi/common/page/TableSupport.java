package com.ruoyi.common.page;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.ServletUtils;

/**
 * 表格数据处理
 */
public class TableSupport {
   public static PageDomain buildPageRequest(){
       return getPageDomain();
   }

    /**
     * 封装分页对象
     * @return
     */
   public static PageDomain getPageDomain(){
         PageDomain pageDomain = new PageDomain();
         pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
         pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
         pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
         pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
         return pageDomain;
   }
}
