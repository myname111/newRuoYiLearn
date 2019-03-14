package com.ruoyi.common.support;

import com.ruoyi.common.utils.StringUtils;

/**
 * 字符串格式化
 */
public class StrFormatter {
    private static final String CURLY_BRACE="{}";
    private static final char SLASH='\\';
    private static final char LFET_BRACE='{';
    public static String format(final String strPattern, final Object... argArray) {
       if (StringUtils.isEmpty(strPattern)||StringUtils.isEmpty(argArray)){
           return strPattern;
       }
       int pointerPosition=0;
       int leftbraceIndex=0;
       int strLen=strPattern.length();
       StringBuilder builder = new StringBuilder(strLen+30);
       for (int arrIndex=0;arrIndex<argArray.length;arrIndex++){
            leftbraceIndex = strPattern.indexOf(CURLY_BRACE,pointerPosition);
            //不包含{}
            if (leftbraceIndex==-1){
                //整个字符串都不包含{}
                if (pointerPosition==0){
                    return strPattern;
                }else{
                    //剩余的内容不包含{}
                    builder.append(strPattern,pointerPosition,strLen);
                    return builder.toString();
                }
            }else{
                //{}的左侧有\\
                if (leftbraceIndex>0&&strPattern.charAt(leftbraceIndex-1)==SLASH){
                    //\\的左侧还有\\,表示\被转义了,占位符{}还起作用
                    if (leftbraceIndex>1&&strPattern.charAt(leftbraceIndex-2)==SLASH){
                        builder.append(strPattern,pointerPosition,leftbraceIndex-1);
                        builder.append(Convert.utf8Str(argArray[arrIndex]));
                        pointerPosition=leftbraceIndex+2;
                    }else{
                        //表示占位符被转义了
                        arrIndex--;
                        builder.append(strPattern,pointerPosition,leftbraceIndex-1);
                        builder.append(LFET_BRACE);
                        pointerPosition=leftbraceIndex+1;
                    }
                }else{
                    builder.append(strPattern,pointerPosition,leftbraceIndex);
                    builder.append(Convert.utf8Str(argArray[arrIndex]));
                    pointerPosition=leftbraceIndex+2;
                }
            }
       }
       builder.append(strPattern,pointerPosition,strLen);
        return builder.toString();
    }
}
