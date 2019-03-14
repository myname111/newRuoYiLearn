package com.ruoyi.common.config;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.YamlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 */
public class Global {
    private static final Logger log = LoggerFactory.getLogger(Global.class);
    //配置文件名称
    private static String NAME="application.yml";
    /**
     * 保存全局属性值
     */
    private static Map<String,String> map = new HashMap<>();

    /**
     * 获取配置文件中的配置
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value==null){
            Map<?,?> yamlMap = null;
            try {
                yamlMap = YamlUtil.loadYaml(NAME);
                value = String.valueOf(YamlUtil.getProperty(yamlMap,key));
                map.put(key,value!=null?value: StringUtils.EMPTY);
            } catch (FileNotFoundException e) {
               log.error("获取全剧配置异常 {}",key);
            }

        }
        return value;
    }
    /**
     * 是否自动去除表前缀
     * @return
     */
    public static String getAutoRemovePre() {
        return StringUtils.nvl(getConfig("gen.autoRemovePre"),"true");
    }



    /**
     * 表前缀(类名不会包含表前缀)
     * @return
     */
    public static String getTablePrefix() {
        return StringUtils.nvl(getConfig("gen.tablePrefix"),"sys_");
    }

    /**
     * 包名
     * @return
     */
    public static String getPackageName() {
        return StringUtils.nvl(getConfig("gen.packageName"),"com.ruoyi.project.module");
    }

    /**
     * 作者
     * @return
     */
    public static String getAuthor() {
        return StringUtils.nvl(getConfig("gen.author"),"ruoyi");
    }

    /**
     * 项目名称
     * @return
     */
    public static String getName() {
        return StringUtils.nvl(getConfig("ruoyi.name"),"RuoYiLearn");
    }
    /**
     *项目版本号
     */
    public static String getVersion(){
        return StringUtils.nvl(getConfig("ruoyi.version"),"1.0");
    }
}
