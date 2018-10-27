package com.iquantex.matrix.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :http请求头
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/3/22 17:31
 */
public class HttpClientHeader
{

    private static final String APPLICATION_JSON = "application/json";
    /**
     * 获得Http头,默认applecation/json
     * @return 头集合
     */
    public static Map<String, String> getHeader( )
    {
        String timestamp = Long.valueOf(System.currentTimeMillis()).toString();

        Map<String, String> propertys = new HashMap<>();
        propertys.put("Content-Type", APPLICATION_JSON);
        propertys.put("timestamp", timestamp);
        return propertys;
    }
}
