package com.iquantex.matrix.cache;

import com.iquantex.matrix.cache.cachepool.Cache;

import java.util.List;
import java.util.Map;

/**
 * 回调接口
 * 
 * @author 9995857
 *
 * 如有问题请关注 微信:  diqiumofang (地球魔方) 留言给我
 */
public abstract class CallBack {

	protected Cache cache;
	
	public CallBack(Cache cache) {
		this.cache = cache;
	}

	public abstract void call(List<Map<String,Object>> datas);
}
