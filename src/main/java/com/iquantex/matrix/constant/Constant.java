package com.iquantex.matrix.constant;

import java.io.Serializable;

/**
 * @Description :公共常量定义接口类
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 上午9:57
 */
public interface Constant extends Serializable {
	
	/**
	 * databaseService 在spring里的名字
	 */
	public static final String BEAN_DATABASE_SERVICE = "databaseService";
	
	/**
	 * jdbcDao 在spring里的名字
	 */
	public static final String BEAN_JDBC_DAO = "jdbcDao";

	
	/**
	 * request response 的字符集
	 */
	public static final String ENCODING = "utf-8";
	
	/**
	 * json
	 */
	public static final String CONTENTTYPE_JSON = "application/json";
	
	
}
