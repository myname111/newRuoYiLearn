package com.ruoyi.common.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * yaml配置处理类
 */
public class YamlUtil {
    //加载配置文件
    public static Map<?,?> loadYaml(String name) throws FileNotFoundException{
        InputStream inputStream =  YamlUtil.class.getClassLoader().getResourceAsStream(name);
        return StringUtils.isNotEmpty(name)?(LinkedHashMap<?,?>) new Yaml().load(inputStream):null;
    }
    public static Object getProperty(Map<?,?> yamlMap,Object key){
            if(yamlMap!=null&&!yamlMap.isEmpty()&&key!=null){
                String input = String.valueOf(key);
                if(!input.equals("")){
                    if (input.contains(".")){
                        int index = input.indexOf(".");
                        String left =  input.substring(0,index);
                        String right = input.substring(index+1,input.length());
                        return getProperty((Map<?,?>)yamlMap.get(left),right);
                    }else if(yamlMap.containsKey(input)){
                        return yamlMap.get(key);
                    }else{
                        return null;
                    }
                }
            }
            return null;
    }
}
