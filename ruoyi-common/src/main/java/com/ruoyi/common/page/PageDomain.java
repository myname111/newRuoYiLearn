package com.ruoyi.common.page;

import com.ruoyi.common.utils.StringUtils;

/**
 * 分页数据
 */
public class PageDomain {
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 每页数
     */
    private Integer pageSize;
    /**
     *排序列
     */
    private String orderByColumn;
    /**
     * 排序方向 desc或者asc
     */
    private String isAsc;
    //获取排序列和排序方向
    public String getOrderBy(){
        if(StringUtils.isEmpty(orderByColumn)){
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn)+" "+isAsc;
    }
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }
}
