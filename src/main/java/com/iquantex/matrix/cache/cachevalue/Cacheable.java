package com.iquantex.matrix.cache.cachevalue;

/**
 * 缓存元素接口
 * 
 * @author 9995857
 *
 * 如有问题请关注 微信:  diqiumofang (地球魔方) 留言给我
 */
public interface Cacheable {

	/**
	 * 固定数据默认刷新时间
	 */
	public final long STONGNODE_REFRESH_TIME = 30 * 24 * 60 * 60;
	
	/**
	 * 临时数据过期时间
	 */
	public final long TEMPNODE_EXPIRED_TIME = 24 * 60 * 60;
	
	/**
	 * 需要定时更新的元素
	 */
	public int STRONG = 0;
	
	/**
	 * 临时元素
	 */
	public int TEMP = 1;
	
	/**
	 * 得到元素key
	 * @return
	 */
	public String getKey();
	
    /**
     * 取得元素的数据
     * @return
     */
    public Object getData();

    /**
     * 判断元素是否过期
     * @return  
     */
    public boolean isExpired();

    /**
     * 判断元素是否需要更新
     * @return  
     */
    public boolean isRefresh();

    /**
     * 更新
     */
    public void refresh();
    
    /**
     * 得到类型
     * @return 0 为固定元素  1 为临时元素
     */
    public int getType();
}
