package com.iquantex.matrix.service.base;

import java.util.Map;

/**
 * @Description :
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 下午2:34
 */
public interface CacheService
{

    /**
     * 通过username生成token(token就是sessionid)
     * @param username
     * {username,token}
     * @return
     */
    public void saveTokenByUsername(String username ,String token);

    /**
     * 通过token存储用户信息(token就是sessionid)
     * @param token
     * @param value
     * {token,object}
     * @return
     */
    public boolean saveUserByToken(String token,Object value);


    /**
     * 通过usernmae获取token(token就是sessionid)
     * @param username
     * @return
     */
    public String getTokenByUserName(String username);

    /**
     * 通过token获取user信息(token就是sessionid)
     * @param token
     * @return
     */
    public Object getUserByToken(String token);

    /**
     * 通过username清除token(token就是sessionid)
     * @param username
     * @return
     */
    public boolean removeTokenByUsername(String username);

    /**
     * 通过token清除user信息(token就是sessionid)
     * @param token
     * @return
     */
    public boolean removeUserByToken(String token);

    /**
     * 获取所有tokens(token就是sessionid)
     * @return
     */
    public Map<String,String> getTokens();

    /**
     * 获取所有users(token就是sessionid)
     * @return
     */
    public Map<String,Object> getUsers();


    /**
     * 加入临时元素
     *
     * @param key
     *            唯一编号
     * @param value
     *            数据
     * @param expiredSecond
     *            数据过期时间 -1为永不过期，0为系统默认过期时间(有默认过期时间)，大于0为过期时间 单位秒
     * @return
     */
    boolean put(String key , Object value , long expiredSecond);

    boolean put(String key , Object value );

    /**
     * 得到指定数据
     *
     * @param key
     *            唯一编号
     * @return
     */
    Object get(String key);

    /**
     * 删除指定元素
     *
     * @param key
     *            唯一编号
     */
    void delete(String key);

    /**
     * 是否存在
     *
     * @param key
     *            唯一编号
     * @return
     */
    boolean isExist(String key);

    String getDingAccessToken();

    boolean setDingAccessToken(String token);


}
