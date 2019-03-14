package com.ruoyi.common.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * spring工具类
 * 方便在非spring管理环境中获取bean
 * 可以修改bean的定义信息
 */
@Component
public class SpringUtils implements BeanFactoryPostProcessor{
    /**
     * spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory configurableListableBeanFactory;
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
         this.configurableListableBeanFactory=configurableListableBeanFactory;
    }

    /**
     * 根据bean的名称获取bean对象
     * @param beanName
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName) {
        return (T)configurableListableBeanFactory.getBean(beanName);
    }

    /**
     * 根据bean的类名获取bean对象
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cla){
        return configurableListableBeanFactory.getBean(cla);
    }
}
