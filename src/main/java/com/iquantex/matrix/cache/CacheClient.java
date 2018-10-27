package com.iquantex.matrix.cache;

import com.iquantex.matrix.cache.cachepool.Cache;
import com.iquantex.matrix.cache.cachepool.LinkMapCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 缓存类
 * 
 * @author 9995857
 * 
 *         如有问题请关注 微信: diqiumofang (地球魔方) 留言给我
 */
public class CacheClient {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private Cache cache = null;


	private static CacheClient instance;

	public static synchronized CacheClient getInstance()
	{
		if(instance == null) {
			instance = new CacheClient();
		}
		return instance;
	}


	public void init()
	{
		this.cache = new LinkMapCache();
		
		this.cache.init();
	}


	/**
	 * 加入临时元素
	 * 
	 * @param value
	 *            数据
	 * @return
	 */
	public String put(Object value)
	{
		String key = UUID.randomUUID().toString();
		return this.put(key, value, -1);
	}

	/**
	 * 加入临时元素
	 * 
	 * @param value
	 *            数据
	 * @param expiredtime
	 *            数据过期时间 -1为永不过期，0为系统默认过期时间(有默认过期时间)，大于0为过期时间 单位秒
	 * @return
	 */
	public String put(Object value, long expiredtime)
	{
		String key = UUID.randomUUID().toString();
		return this.put(key, value, expiredtime);
	}

	/**
	 * 加入临时元素
	 * 
	 * @param key
	 *            唯一编号
	 * @param value
	 *            数据
	 * @return
	 */
	public String put(String key, Object value)
	{
		return this.put(key, value, -1);
	}

	/**
	 * 加入临时元素
	 * 
	 * @param key
	 *            唯一编号
	 * @param value
	 *            数据
	 * @param expiredtime
	 *            数据过期时间 -1为永不过期，0为系统默认过期时间(有默认过期时间)，大于0为过期时间 单位秒
	 * @return
	 */
	public String put(String key, Object value, long expiredtime)
	{
		this.cache.put(key, value, expiredtime);
		return key;
	}

	/**
	 * 得到指定数据
	 * 
	 * @param key
	 *            唯一编号
	 * @return
	 */
	public Object get(String key)
	{
		return this.cache.get(key);
	}

	/**
	 * 删除指定元素
	 * 
	 * @param key
	 *            唯一编号
	 */
	public void delete(String key)
	{
		this.cache.delete(key);
	}


	/**
	 * 是否存在
	 * 
	 * @param key
	 *            唯一编号
	 * @return
	 */
	public boolean isExist(String key)
	{
		return this.cache.isExist(key);
	}

}
