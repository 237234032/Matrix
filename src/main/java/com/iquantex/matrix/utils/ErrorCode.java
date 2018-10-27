package com.iquantex.matrix.utils;

import com.diqiumofang.tool.StringEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description :错误代码类
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 上午9:59
 */

public class ErrorCode {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static String ERRORCODE_FILE_NAME = "application.properties";
	private static String ERRORCODE_FILE_PATH = "/";

	private Properties properties;


	
	private static ErrorCode instance;
	
	public static synchronized ErrorCode getInstance()
	{
		if(instance == null) instance = new ErrorCode(); 
		return instance;
	}
	
	private ErrorCode()
	{
		log.info("read error code file");
		this.loadErrorCode();
	}

	public void loadErrorCode()
	{

         properties = getProperties(ERRORCODE_FILE_PATH,ERRORCODE_FILE_NAME);
	}
	
	public String getCode(String key)
	{
		return properties.getProperty(key);
	}


	private Properties getProperties(String filepath, String filename)
	{
       try
       {
           Resource resource = new ClassPathResource(filepath+filename);
           return PropertiesLoaderUtils.loadProperties(resource);
       }catch (IOException e)
       {
           log.error("error : {}",e);
            return new Properties();
       }
	}
	

	public boolean writeErrorCode(String errorcodeContent)
	{
        if(StringEx.isEmpty(errorcodeContent)) return true;
        String[] split = StringEx.split(errorcodeContent, "=");
        String after = "";
        if(split.length == 0) return true;
        String preix = split[0];
        if(split.length>1) after = split[1];
        properties.setProperty(preix,after);
        return true;
	}
	
	public Properties getErrorCode()
	{
		return properties;
	}

}