package com.iquantex.matrix.cache.cachevalue;

import java.util.Date;

/**
 * 临时元素接口类
 * 
 * @author 9995857
 *
 * 如有问题请关注 微信:  diqiumofang (地球魔方) 留言给我
 */
public class TempNode implements Cacheable {

	private String key;
	
	/**
	 * 数据过期时间
	 */
	private long expiredtime = Cacheable.TEMPNODE_EXPIRED_TIME;
	
	/**
	 * 创建时间
	 */
	private long start = -1;
	
	/**
	 * 元素数据
	 */
	private Object data = null;
	
	/**
	 * 构造器
	 * @param
	 * @param
	 */
	public TempNode()
	{
		this.start = new Date().getTime();
	}
	
	/**
	 * 构造器
	 * @param key
	 * @param data
	 */
	public TempNode(String key,Object data)
	{
		this.key = key;
		this.data = data;
		this.start = System.currentTimeMillis();
	}
	
	/**
	 * 构造器
	 * @param key 自定义key
	 * @param data 数据
	 * @param expiredtime 数据过期时间 -1为永不过期，0为系统默认过期时间(有默认过期时间)，大于0为过期时间
	 */
	public TempNode(String key,Object data,long expiredtime)
	{
		this.key = key;
		this.data = data;
		if(expiredtime != 0)this.expiredtime = expiredtime;
		this.start = System.currentTimeMillis();
	}
	
	/**
	 * 得到元素key
	 * @return
	 */
	public String getKey()
	{
		return this.key;
	}
	
	/**
     * 取得元素的数据
     * @return
     */
	public Object getData() {
		return data;
	}

	/**
     * 判断元素是否过期
     * @return  
     */
	public boolean isExpired() {
		long end = System.currentTimeMillis();
		long time = end - this.start;
		//是否超过数据过期时间
		if(expiredtime * 1000 < 0) return false;
		return expiredtime * 1000 < time;
	}

	/**
     * 判断元素是否需要更新
     * @return   
     */
	public boolean isRefresh() {
		return false;
	}

	/**
     * 执行更新
     *
     */
	public void refresh() {
		return;
	}

	/**
     * 得到类型
     * @return 0 为固定元素  1 为临时元素
     */
	public int getType() {
		return Cacheable.TEMP;
	}

	public long getExpiredtime() {
		return expiredtime;
	}

	public void setExpiredtime(long expiredtime) {
		this.expiredtime = expiredtime;
	}
}
