package com.iquantex.matrix.cache.cachepool;


/**
 * 缓存基类
 * 
 * @author 9995857
 *
 * 如有问题请关注 微信:  diqiumofang (地球魔方) 留言给我
 */
public interface Cache {

	public void init();
	

	/**
	 * 向缓存中添加一个临时元素,并设置过期时间
	 * @param key 唯一编号
	 * @param value 数据
	 * @param expiredtime 数据过期时间 毫秒   -1为永不过期，0为系统默认过期时间(有默认过期时间)，大于0为过期时间 单位秒
	 */
	public void put(String key, Object value, long expiredtime);
	
	/**
	 * 从缓存中得到指定元素
	 * @param key
	 * @return 对象
	 */
	public Object get(String key);
	
	/**
	 * 从缓存中删除指定元素
	 * @param key
	 */
	public void delete(String key);
	
	/**
	 * 清空缓存所有元素
	 *
	 */
	public void clear();
	
	/**
	 * 得到缓存元素总数
	 * @return 总数
	 */
	public int size();
	
	/**
	 * 是否存在
	 * @param key
	 * @return 是否存在
	 */
	public boolean isExist(String key);
	
	/**
	 * 强制刷新
	 * @param key
	 */
	public boolean forceRefresh(String key);
}
