package com.ruoyi.common.utils;

import java.math.BigDecimal;

/**
 * 用于算数运算(浮点数精确运算)
 */
public class Arith {
    /**
     * 该类不能实例化
     */
    private Arith(){}

    /**
     *乘法运算
     * @param v1
     * @param v2
     * @return
     */
    public static double mul(double v1,double v2){
        BigDecimal value1 = new BigDecimal(String.valueOf(v1));
        BigDecimal value2 = new BigDecimal(String.valueOf(v2));
        return value1.multiply(value2).doubleValue();
    }

    /**
     * 四舍五入
     * @param value
     * @param scale 需要保留的小数位数
     * @return
     */
    public static double round(double value,int scale){
        if (scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        BigDecimal bigDecimal2 = new BigDecimal("1");
        return bigDecimal.divide(bigDecimal2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法运算
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double div(double v1, double v2, int scale) {
        if (scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal value1 = new BigDecimal(String.valueOf(v1));
        BigDecimal value2 = new BigDecimal(String.valueOf(v2));
        if (value1.compareTo(BigDecimal.ZERO)==0){
            return BigDecimal.ZERO.doubleValue();
        }
        return value1.divide(value2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
