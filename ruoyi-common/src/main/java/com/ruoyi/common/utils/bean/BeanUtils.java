package com.ruoyi.common.utils.bean;

import com.ruoyi.common.utils.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean工具类
 */
public class BeanUtils {
    /**
     * bean方法中属性名开始的下标
     */
    private static final int BEAN_METHOD_PROP_INDEX=3;
    /**
     * 匹配get方法的正则表达式
     */
    private static final Pattern GET_PATTERN=Pattern.compile("get(\\p{javaUpperCase}\\w*)");
    /**
     * 匹配set方法的正则表达式
     */
    private static final Pattern SET_PATTERN=Pattern.compile("set(\\p{javaUpperCase}\\w*)");
    /**
     * bean属性复制工具方法
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        List<Method> destSetters = getSetterMethods(dest);
        List<Method> srcGetters = getGetterMethods(src);
        try{
            for (Method set:destSetters){
                for (Method get:srcGetters){
                    if(get.getReturnType().equals(set.getParameterTypes()[0])&&isMethodPropEquals(set.getName(),get.getName())){
                        set.invoke(dest,get.invoke(src));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static List<Method> getSetterMethods(Object obj) {
        List<Method> setterMethods = new ArrayList<>();
        Method[] methods = obj.getClass().getMethods();
        if (StringUtils.isNotNull(methods)){
            for (Method method:methods){
                Matcher matcher = SET_PATTERN.matcher(method.getName());
                if (matcher.matches()&&method.getParameterTypes().length==1){
                    setterMethods.add(method);
                }
            }
        }
        return setterMethods;
    }

    public static List<Method> getGetterMethods(Object obj) {
        List<Method> getterMethods = new ArrayList<>();
        Method[] methods = obj.getClass().getMethods();
        if (StringUtils.isNotNull(methods)){
            for (Method method:methods){
                Matcher matcher = GET_PATTERN.matcher(method.getName());
                if (matcher.matches()&&method.getParameterTypes().length==0){
                    getterMethods.add(method);
                }
            }
        }
        return getterMethods;
    }

    /**
     * 检验bean方法名中属性名是否相等
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isMethodPropEquals(String str1,String str2){
         return str1.substring(BEAN_METHOD_PROP_INDEX).equals(str2.substring(BEAN_METHOD_PROP_INDEX));
    }
}
