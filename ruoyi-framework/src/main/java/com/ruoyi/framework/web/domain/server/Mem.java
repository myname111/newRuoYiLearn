package com.ruoyi.framework.web.domain.server;

import com.ruoyi.common.utils.Arith;

/**
 * 内存相关信息
 */
public class Mem {
    /**
     * 内存容量
     */
    private double total;
    /**
     * 使用内存
     */
    private double used;
    /**
     * 空闲内存
     */
    private double free;

    public double getTotal() {
        return Arith.div(total,(1024*1024*1024),2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUsed() {
        return Arith.div(used,(1024*1024*1024),2);
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public double getFree() {
        return Arith.div(free,(1024*1024*1024),2);
    }

    public void setFree(double free) {
        this.free = free;
    }

    /**
     * 内存使用率
     * @return
     */
    public double getUsage(){
        return Arith.mul(Arith.div(used,total,4),100);
    }
}
