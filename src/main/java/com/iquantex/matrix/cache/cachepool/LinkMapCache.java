package com.iquantex.matrix.cache.cachepool;

import com.iquantex.matrix.cache.cachevalue.Cacheable;
import com.iquantex.matrix.cache.cachevalue.TempNode;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 维护缓存更新
 * 
 * @author 9995857
 *
 * 如有问题请关注 微信:  diqiumofang (地球魔方) 留言给我
 */
public class LinkMapCache extends Thread implements Cache {

	/**
	 * 容期最大存放数
	 */
	private static final int MAXNUMBER = 100000;

	/**
	 * 线程运行间隔1秒
	 */
	private static final long THREADTIME = 1 * 1000L;

	/**
	 * 真实容器
	 */
	private LinkedHashMap<String, Cacheable> buffer = null;

	/**
	 * 线程开关
	 */
	private boolean isOnThread = false;
	
	/**
	 * 判断是否需要更新就直接更新
	 */
	private boolean validate = true;
	
	/**
	 * 不判断是否需要更新
	 */
	private boolean noValidate = false;
	
	/**
	 * 构造器
	 * 
	 */
	public LinkMapCache() {
		this.buffer = new LinkedHashMap<String, Cacheable>(LinkMapCache.MAXNUMBER);
	}
		
	/**
	 * 初始化
	 * 
	 */
    @Override
	public void init() {
		
		this.setName("Cache pool thread");
		//初始化
		this.refresh(this.noValidate);
		// 打开开关
		this.isOnThread = true;
		this.start();
	}

	/**
	 * 线程
	 */
	@Override
	public void run()
	{
		while (isOnThread) 
		{
			synchronized (this) 
			{
				this.refresh(this.validate);
				try {Thread.sleep(LinkMapCache.THREADTIME);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	/**
	 * 执行刷新
	 * @param flag 为不判断是否需要更新就直接更新   为判断是否需要更新
	 */
	private void refresh(Boolean flag)
	{
		Iterator<Cacheable> values = this.buffer.values().iterator();
		while (values.hasNext())
		{
			Cacheable cachevalue = values.next();
			//是否过期
			if (cachevalue.isExpired()) values.remove();
			
			//初始化
			if(!flag)
				cachevalue.refresh();
			//是否需要更新
			else if (cachevalue.isRefresh()) 
				cachevalue.refresh();
		}
	}
	
	/**
	 * 强制刷新
	 * @param key
	 */
    @Override
	public boolean forceRefresh(String key)
	{
		if (key == null && "".equals(key))return false;
		
		Cacheable cachevalue = this.buffer.get(key);
		cachevalue.refresh();
		
		return true; 
	}

	/**
	 * 向缓存中添加一个临时元素
	 * @param key    唯一编号
	 * @param value 元素值
	 * @param expiredtime 数据过期时间  
	 */
    @Override
	public void put(String key, Object value,long expiredtime) {
		if(this.buffer.size() > LinkMapCache.MAXNUMBER)return ;
		
		// 不允许更改TempNodeImpl 里面的值
		final Cacheable obj = new TempNode(key, value,expiredtime);
		this.buffer.put(key, obj);
	}

	/**
	 * 从缓存中得到指定元素
	 * @param key
	 * @return 数据
	 */
    @Override
	public Object get(String key) {
		
		if (key == null && "".equals(key))return null;
		if(!this.isExist(key)) return null;
		
		return this.buffer.get(key).getData();
	}

	/**
	 * 从缓存中删除指定元素
	 * @param key
	 */
    @Override
	public void delete(String key) {
		if (key != null && !"".equals(key)) 
		{
			this.buffer.remove(key);
		}
	}

	/**
	 * 清空缓存所有元素
	 * 
	 */
    @Override
	public void clear() {
		this.buffer.clear();
	}

	/**
	 * 得到缓存元素总数
	 * @return 总数
	 */
    @Override
	public int size() {
		return this.buffer.size();
	}
	
	/**
	 * 是否存在
	 * @param key
	 * @return 是否存在
	 */
    @Override
	public boolean isExist(String key)
	{
		return this.buffer.containsKey(key);
	}
}
