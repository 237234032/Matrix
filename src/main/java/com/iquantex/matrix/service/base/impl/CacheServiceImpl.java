package com.iquantex.matrix.service.base.impl;

import com.diqiumofang.tool.StringEx;
import com.iquantex.matrix.cache.CacheClient;
import com.iquantex.matrix.constant.CacheConstant;
import com.iquantex.matrix.service.base.CacheService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 下午2:34
 */
@Service
public class CacheServiceImpl  implements CacheService
{

    private CacheClient cacheClient;

    public CacheServiceImpl()
    {
            cacheClient = CacheClient.getInstance();
    }



    /**
     * 通过username生成token(token就是sessionid)
     * @param username
     * {username,token}
     * @return
     */
    @Override
    public void saveTokenByUsername(String username ,String token) {

        Object data = cacheClient.get(CacheConstant.CACHEKEY_TOKEN_LIST);
        if (StringEx.isEmpty(username)) return ;
        if (StringEx.isEmpty(data)) data = new HashMap<String,String>();
        if (data instanceof Map) ((Map) data).put(username,token);
        cacheClient.put(CacheConstant.CACHEKEY_TOKEN_LIST, data);
    }

    /**
     * 通过token存储用户信息(token就是sessionid)
     * @param token
     * @param value
     * {token,object}
     * @return
     */
    @Override
    public boolean saveUserByToken(String token, Object value) {
        Object data = cacheClient.get(CacheConstant.CACHEKEY_USER_LIST);
        if (StringEx.isEmpty(value)) return false;
        if (StringEx.isEmpty(token)) return false;
        if (StringEx.isEmpty(data)) data = new HashMap<String,Object>();
        if (data instanceof Map) ((Map) data).put(token,value);
        cacheClient.put(CacheConstant.CACHEKEY_USER_LIST, data);
        return true;
    }

    /**
     * 通过usernmae获取token(token就是sessionid)
     * @param username
     * @return
     */
    @Override
    public String getTokenByUserName(String username) {
        Object data = cacheClient.get(CacheConstant.CACHEKEY_TOKEN_LIST);
        if (StringEx.isEmpty(username)) return null;
        if (StringEx.isEmpty(data)) return null;
        if (!(data instanceof Map)) return null;
        return ((Map<String,String>)data).get(username);
    }

    /**
     * 通过token获取user信息(token就是sessionid)
     * @param token
     * @return
     */
    @Override
    public Object getUserByToken(String token) {
        Object data = cacheClient.get(CacheConstant.CACHEKEY_USER_LIST);
        if (StringEx.isEmpty(token)) return null;
        if (StringEx.isEmpty(data)) return null;
        if (!(data instanceof Map)) return null;
        return ((Map<String,Object>)data).get(token);
    }

    /**
     * 通过username清除token(token就是sessionid)
     * @param username
     * @return
     */
    @Override
    public boolean removeTokenByUsername(String username) {
        Object data = cacheClient.get(CacheConstant.CACHEKEY_TOKEN_LIST);
        if (StringEx.isEmpty(data)) return true;
        if (!(data instanceof Map)) return true;
        ((Map<String,String>)data).remove(username);
        return true;
    }

    /**
     * 通过token清除user信息(token就是sessionid)
     * @param token
     * @return
     */
    @Override
    public boolean removeUserByToken(String token) {
        Object data = cacheClient.get(CacheConstant.CACHEKEY_USER_LIST);
        if (StringEx.isEmpty(data)) return true;
        if (!(data instanceof Map)) return true;
        ((Map<String,Object>)data).remove(token);
        return true;
    }

    /**
     * 获取所有tokens(token就是sessionid)
     * @return
     */
    @Override
    public Map<String, String> getTokens() {
        Object data = cacheClient.get(CacheConstant.CACHEKEY_TOKEN_LIST);
        return StringEx.isEmpty(data) ? null : (Map<String, String>) data;
    }

    /**
     * 获取所有users(token就是sessionid)
     * @return
     */
    @Override
    public Map<String, Object> getUsers() {
        Object data = cacheClient.get(CacheConstant.CACHEKEY_USER_LIST);
        return StringEx.isEmpty(data) ? null : (Map<String, Object>) data;
    }

    /**
     * 加入临时元素
     *
     * @param key
     *            唯一编号
     * @param val
     *            数据
     * @param expiredSecond
     *            数据过期时间 -1为永不过期，0为系统默认过期时间(有默认过期时间)，大于0为过期时间 单位秒
     * @return
     */
    @Override
    public boolean put(String key, Object val,long expiredSecond)
    {
       cacheClient.put(key,val,expiredSecond);
       return true;
    }


    @Override
    public boolean put(String key, Object val)
    {
        cacheClient.put(key,val);
        return true;
    }

    /**
     * 得到指定数据
     *
     * @param key
     *            唯一编号
     * @return
     */
    @Override
    public Object get(String key) {
        return cacheClient.get(key);
    }

    /**
     * 删除指定元素
     *
     * @param key
     *            唯一编号
     */
    @Override
    public void delete(String key) {
        cacheClient.delete(key);
    }

    /**
     * 是否存在
     *
     * @param key
     *            唯一编号
     * @return
     */
    @Override
    public boolean isExist(String key) {
        return cacheClient.isExist(key);
    }

    /**
     * 获取钉钉accessToken，没有则返回null
     * @return
     */
    @Override
    public String getDingAccessToken() {
        Object o = cacheClient.get(CacheConstant.CACHEKEY_DING_ACCESS_TOKEN_NAME);
        return StringEx.isEmpty(o) ? null : o.toString();

    }

    /**
     * 设置钉钉accessToken，设置失效时间为7200S
     * @return
     */
    @Override
    public boolean setDingAccessToken(String token) {
        this.cacheClient.put(CacheConstant.CACHEKEY_DING_ACCESS_TOKEN_NAME,token,7199);
        return true;
    }


}
