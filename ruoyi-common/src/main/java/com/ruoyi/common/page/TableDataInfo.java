package com.ruoyi.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 */
public class TableDataInfo implements Serializable{
    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    private List<?> rows;
    /**
     * 消息状态码
     */
    private int code;

    public TableDataInfo() {
    }

    public TableDataInfo(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
