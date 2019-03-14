package com.ruoyi.common.support;

import com.ruoyi.common.utils.StringUtils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Convert {
    /**
     *转成int
     * 转换失败(例如产生异常)或者value为空(null或者"")都会返回默认值null
     * @param value
     * @return
     */
    public static Integer toInt(Object value){
        return toInt(value,null);
    }

    /**
     *转成int
     * 转换失败(例如产生异常)或者value为空(null或者"")都会返回默认值defaultValue
     * @param value
     * @param defaultValue
     * @return
     */
    public static Integer toInt(Object value,Integer defaultValue){
        if(value==null){
            return defaultValue;
        }
        if (value instanceof Integer){
            return (Integer) value;
        }
        if(value instanceof Number){
            return ((Number)value).intValue();
        }
       final String valueStr =  toStr(value,null);
        if(StringUtils.isEmpty(valueStr)){
            return defaultValue;
        }
        try{
            return Integer.valueOf(valueStr.trim());
        }catch (Exception e){
            return defaultValue;
        }
    }
    public static String toStr(Object value){
        return toStr(value,null);
    }
    public static String toStr(Object value,String defaultValue){
        if(value==null){
            return defaultValue;
        }
        if(value instanceof String){
            return (String)value;
        }
        return value.toString();
    }

    public static String[] toStrArray(String str) {
        return toStrArray(",",str);
    }

    public static String[] toStrArray(String split, String str) {
        return str.split(split);
    }

    /**
     * 对象转字符串
     * 1.byte数组和byteBuffer会被转换成对应的字符串数组
     * 2.其他对象数组会调用Arrays.toString方法
     * @param obj
     * @return
     */
    public static String utf8Str(Object obj) {
          return str(obj,CharseKit.CHARSET_UTF_8);
    }

    /**
     * 对象转字符串
     * 1.byte数组和byteBuffer会被转换成对应的字符串数组
     * 2.其他对象数组会调用Arrays.toString方法
     * @param obj
     * @param charset
     * @return
     */
    public static String str(Object obj, Charset charset){
          if (obj==null){
              return null;
          }
          if (obj instanceof String){
              return (String)obj;
          }else if (obj instanceof byte[]||obj instanceof Byte[]){
              return str((byte[])obj,charset);
          }else if (obj instanceof ByteBuffer){
              return str((ByteBuffer)obj,charset);
          }
          return obj.toString();
    }

    /**
     * 将byte数组转换成字符串
     * @param bytes
     * @param charset
     * @return
     */
    public static String str(byte[] bytes,String charset){
        return str(bytes,StringUtils.isEmpty(charset)?Charset.defaultCharset():Charset.forName(charset));
    }

    /**
     * 把byte数组转换成字符串
     * @param bytes
     * @param charset
     * @return
     */
    public static String str(byte[] bytes,Charset charset){
        if (bytes==null){
            return null;
        }
        if (charset==null){
            return new String(bytes);
        }
        return new String(bytes,charset);
    }

    /**
     * 将编码的byteBuffer数据转换为字符串
     * @param byteBuffer
     * @param charset
     * @return
     */
    public static String str(ByteBuffer byteBuffer,String charset){
         if (byteBuffer==null){
             return null;
         }
         return str(byteBuffer,StringUtils.isEmpty(charset)?Charset.defaultCharset():Charset.forName(charset));
    }

    /**
     * 将编码的byteBuffer数据转换为字符串
     * @param byteBuffer
     * @param charset
     * @return
     */
    public static String str(ByteBuffer byteBuffer,Charset charset){
        if (byteBuffer==null){
            return null;
        }
        if (charset==null){
            charset=Charset.defaultCharset();
        }
        return charset.decode(byteBuffer).toString();
    }
}
