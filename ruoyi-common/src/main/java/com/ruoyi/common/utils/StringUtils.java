package com.ruoyi.common.utils;


import com.ruoyi.common.support.StrFormatter;

public class StringUtils extends org.apache.commons.lang3.StringUtils{
    /**
     * 下划线
     */
    private static final String SEPARATOR="_";
    /*
       空字符串
     */
    private static final String NULLLSTR="";
    /**
     * 判断一个对象是否为空
     * @param object
     * @return
     */
    public static boolean isNull(Object object){
        return object==null;
    }

    /**
     * 判断一个对象是否非空
     * @param object
     * @return
     */
    public static boolean isNotNull(Object object){
        return !isNull(object);
    }
    /**
     *判断一个字符串是否为空串 为null或者是""
     */
    public static boolean isEmpty(String str){
            return isNull(str)||NULLLSTR.equals(str.trim());
    }
    /**
     * 判断一个字符串是否非空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断数组是否为空,为null或者长度为0
     * @param arr
     * @return
     */
    public static boolean isEmpty(Object[] arr){
        return isNull(arr)||arr.length==0;
    }
    /**
     *判断参数value是否为空,不为空则返回原参数value,为空则返回默认值defaultValue
     * @param value
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value!=null?value:defaultValue;
    }
    /**
     * 驼峰命名转下划线
     * @param str
     * @return
     */
    public static String toUnderScoreCase(String str) {
           if(str==null){
               return null;
           }
           StringBuilder sb = new StringBuilder();
           boolean preCharIsUpperCase=true;//前置字符是否大写
           boolean curCharIsUpperCase=true;//当前字符是否大写
           boolean nextCharIsUpperCase=true;//下一个字符是否大写
           //TODO:驼峰命名转下划线
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (i > 0)
            {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            }
            else
            {
                preCharIsUpperCase = false;
            }

            curCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1))
            {
                nextCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curCharIsUpperCase && !nextCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            else if ((i != 0 && !preCharIsUpperCase) && curCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }
           return sb.toString();
    }



    /**
     * 将下划线形式的字符串转换成驼峰形式的字符串.例如:hello_world=>HelloWorld
     * @param name
     * @return
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        //字符串为空,不需要转换,直接返回""
        if(isEmpty(name)){
            return "";
        }else if (!name.contains("_")){
            //不包含下划线,只需要首字母大写
            return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
        }
        String[] camels = name.split("_");
        for (String camel:camels) {
            if (isEmpty(camel)){
                continue;
            }
            result.append(camel.substring(0,1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }
    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params 参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params) {
        if(isEmpty(template)||isEmpty(params)){
            return template;
        }
        return StrFormatter.format(template,params);
    }
}
