package com.ruoyi.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义导出Excel数据注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {
    /**
     * 字段类型(0:导入导出,1:只导出,2:只导入)
     * @return
     */
    public Type type() default Type.ALL;

    /**
     * 字段导出到excel中的名称
     * @return
     */
    public String name();

    /**
     * 导出时在excel中每列的宽度 单位是字符
     * @return
     */
    public double width() default 16;

    /**
     * 导出时在excel中每列的高度  单位是字符
     * @return
     */
    public double height() default 14;

    /**
     * 提示信息
     * @return
     */
    public String prompt() default "";

    /**
     * 设置只能选择不能输入的列的内容
     * @return
     */
    public String[] combo()default{};

    /**
     * 是否导出数据
     * @return
     */
    public boolean isExport()default true;

    /**
     * 日期格式
     * @return
     */
    public String dateFormat()default "";

    /**
     * 读取内容转换表达式 (如0=男,1=女,2=未知)
     * @return
     */
    public String readConvertExp()default "";

    /**
     * 当值为空时,字段的默认值
     * @return
     */
    public String defaultValue()default "";

    /**
     * 文字后缀 ,如%,使得90变成90%
     * @return
     */
    public String suffix() default "";

    /**
     * 当前类某个属性是另外的一个类,但是导出的时候该字段只取另一个类的某个字段
     * 该方法的作用就是取另外一个类中某个字段的值.
     * 多级获取用小数点隔开
     * @return
     */
    public String targetAttr() default "";

    public enum Type{
        ALL(0),EXPORT(1),IMPORT(2);
        private int value;
        Type(int value){
            this.value=value;
        }
        public int getValue(){
            return this.value;
        }
    }
}
