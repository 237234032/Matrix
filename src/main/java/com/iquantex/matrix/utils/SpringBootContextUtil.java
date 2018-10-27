package com.iquantex.matrix.utils;

import com.diqiumofang.tool.StringEx;
import org.springframework.context.ApplicationContext;

import java.util.Properties;

/**
 * @Description : spring boot 的上下文工具类，在Applcation中注入app，可以在listener，filter，intercepter中注入bean
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/10 上午10:22
 */
public class SpringBootContextUtil
{
    private static ApplicationContext configurableApplicationContext;

    private static Properties properties;


    /**
     * 注入ApplicationContext，在Application中
     * @param app
     */
    public static void setConfigurableApplicationContext(ApplicationContext app)
    {
        SpringBootContextUtil.configurableApplicationContext = app;
    }


    public static void setProperties(Properties properties)
    {
        SpringBootContextUtil.properties = properties;
    }
    /**
     * 通过名字获取上下文中的bean
      * @param name
     * @return
     */
    public static Object getBean(String name){
        if(StringEx.isEmpty(configurableApplicationContext)) return null;
        return configurableApplicationContext.getBean(name);
    }

    /**
     * 通过类型获取上下文中的bean
      * @param requiredType
     * @return
     */
    public static Object getBean(Class<?> requiredType){
        if(StringEx.isEmpty(configurableApplicationContext)) return null;
        return configurableApplicationContext.getBean(requiredType);
    }

    public static Properties getProperties()
    {
        return properties;
    }

}
