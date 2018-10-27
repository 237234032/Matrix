package com.iquantex.matrix.utils;

import com.diqiumofang.request.JettyHttpRequest;
import com.iquantex.matrix.constant.Constant;
import org.eclipse.jetty.client.api.ContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description :得到HTTP链接实例
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/8 下午2:46
 */
public class GetHttpClient {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static JettyHttpRequest request;


    private static String ERRORCODE_FILE_NAME = "application.properties";
    private static String ERRORCODE_FILE_PATH = "/";

    private Properties properties;

    private  final String HTTP_POOL_MAXCONNECTS = "HTTP_POOL_MAXCONNECTS";

    private  final String HTTP_POOL_WAIT_REQUEST_COUNT = "HTTP_POOL_WAIT_REQUEST_COUNT";

    private  final String HTTP_CONNECT_TIMEOUT = "HTTP_CONNECT_TIMEOUT";

    private  final String HTTP_READ_TIMEOUT = "HTTP_READ_TIMEOUT";

	public synchronized static JettyHttpRequest getInstance()
	{
		if(request == null)
			request = new GetHttpClient().getHttpRequest();
		return request;
	}

    public synchronized  static void closeConnection() throws  Exception
    {
        if(request != null) request.close();
    }
	 
	private JettyHttpRequest getHttpRequest()
	{
		JettyHttpRequest request = null;

		try
		{
			log.info("get http connect");
            properties = getProperties(ERRORCODE_FILE_PATH,ERRORCODE_FILE_NAME);

            request = new JettyHttpRequest(true,
				Integer.parseInt(properties.getProperty(HTTP_POOL_MAXCONNECTS,"500")),
				Integer.parseInt(properties.getProperty(HTTP_POOL_WAIT_REQUEST_COUNT,"10")));

			request.setConnectTimeout(Long.parseLong(properties.getProperty(HTTP_CONNECT_TIMEOUT,"3000")));
			request.setAddressResolutionTimeout(Long.parseLong(properties.getProperty(HTTP_READ_TIMEOUT,"3000")));

			log.info("get http connect finished");
			
		} catch (Exception e) {
			log.info("error : {}",e);
		}
		
		return request;
	}

    private Properties getProperties(String filepath, String filename) throws Exception
    {
            Resource resource = new ClassPathResource(filepath+filename);
            return PropertiesLoaderUtils.loadProperties(resource);
    }


    /**
     * sendGetDemo
     * @throws Exception
     */
    public static  void sendGet() throws  Exception
    {
        JettyHttpRequest instance = GetHttpClient.getInstance();
        ContentResponse cr = instance.sendGet("http://localhost:8080/avatar/test/list", HttpClientHeader.getHeader());
        System.out.println("UrlString : " + cr.getContentAsString());
        System.out.println("Reason : " + cr.getReason());
        System.out.println("Status : " + cr.getStatus());
        System.out.println("MediaType : " + cr.getMediaType());
        System.out.println("Encode : " + cr.getEncoding());

        /**
        *UrlString : {"statusCode":"000000","result":[{"uuid":"023BEE44901B4809892D968A581EEBD1","name":"zhangning","age":28,"createtime":"2018-03-22 17:21:32"},{"uuid":"CF0744F766C14E32A564A15FFD369BD7","name":"zhangning","age":28,"createtime":"2018-03-22 17:39:09"}],"error":""}
        *Reason : null
        *Status : 200
        *MediaType : application/json
        *Encode : UTF-8
         */
    }


    /**
     * sendPostDemo
     * @throws Exception
     */
    public static void sendPost() throws  Exception
    {
        JettyHttpRequest postInstance = GetHttpClient.getInstance();
        Map<String, Object> body = new HashMap<>();
        body.put("name","yuanqiang");
        body.put("age","28");
        ContentResponse postCr = postInstance.sendPost("http://localhost:8080/avatar/test", HttpClientHeader.getHeader(),body.toString().getBytes(Constant.ENCODING));
        System.out.println("UrlString : " + postCr.getContentAsString());
        System.out.println("Reason : " + postCr.getReason());
        System.out.println("Status : " + postCr.getStatus());
        System.out.println("MediaType : " + postCr.getMediaType());
        System.out.println("Encode : " + postCr.getEncoding());

        /**
         * UrlString : {"statusCode":"000000","result":"ok","error":""}
         *Reason : null
         *Status : 200
         *MediaType : application/json
         *Encode : UTF-8
         */
    }

    /**
     * sendPutDemo
     * @throws Exception
     */
    public static void sendPut() throws  Exception
    {
        JettyHttpRequest putInstance = GetHttpClient.getInstance();
        Map<String, Object> body = new HashMap<>();
        body.put("name","yuanqiang");
        body.put("age","28");
        ContentResponse putCr = putInstance.sendPost("http://localhost:8080/avatar/test", HttpClientHeader.getHeader(),body.toString().getBytes(Constant.ENCODING));
        System.out.println("UrlString : " + putCr.getContentAsString());
        System.out.println("Reason : " + putCr.getReason());
        System.out.println("Status : " + putCr.getStatus());
        System.out.println("MediaType : " + putCr.getMediaType());
        System.out.println("Encode : " + putCr.getEncoding());

        /**
         * UrlString : {"statusCode":"000000","result":"ok","error":""}
         *Reason : null
         *Status : 200
         *MediaType : application/json
         *Encode : UTF-8
         */
    }


}
