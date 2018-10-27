package com.iquantex.matrix.constant;

/**
 * @Description :缓存常量类
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 上午9:56
 */
public interface CacheConstant {
	
	 /**
     *  Cache 前缀
     */
    public static final String PREFIX = "cachekey_";

    /**
     * token链
     */
	public static final String CACHEKEY_TOKEN_LIST = PREFIX + "token_list";

    /**
     * user链
     */
	public static final String CACHEKEY_USER_LIST = PREFIX + "user_list";

    /**
     * 钉钉accessToken
     */
    public static String CACHEKEY_DING_ACCESS_TOKEN_NAME = "ding_access_token";
}