package com.ruoyi.gen.util;

import com.ruoyi.common.constant.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * VelocityEngine工厂
 */
public class VelocityInitializer {
    public static void initVelocity() {
        Properties p = new Properties();
        try{
            /**
             * 加载classpath路径下的vm文件
             */
            p.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            /**
             * 设置字符集
             */
            p.setProperty(Velocity.ENCODING_DEFAULT, Constants.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, Constants.UTF8);
            /**
             * 初始化Velocity引擎,指定配置properties
             */
            Velocity.init(p);
        }catch (Exception e){
           throw new RuntimeException(e);
        }

    }
}
