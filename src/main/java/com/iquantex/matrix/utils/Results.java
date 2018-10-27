package com.iquantex.matrix.utils;

import com.alibaba.fastjson.JSONObject;
import com.diqiumofang.tool.PropertiesUtil;
import com.diqiumofang.tool.StringEx;

/**
 * @Description :统一结果类
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 上午10:00
 */
public class Results {
	
	private static PropertiesUtil properties = new PropertiesUtil().loadResource("errorcode.properties");
	
	public static final String SUCCESS = "000000";
	
	public static final String SystemError = "100100";
	public static final String UnknowError = "100200";
	
	private String statusCode;
	
	private Object result;
	
	private String error;
	
	public String getStatusCode() {
		return statusCode;
	}

	public Object getResult() {
		return result;
	}
	
	public String getError() {
		return error;
	}
	
	/**
	 * 创建成功结果
	 * @param result 结果
	 * @return Results
	 */
	public static Results createSuccessResult(Object result)
	{
		Results results = new Results();
		results.statusCode = SUCCESS;
		results.result = result;
		results.error = "";
		
		return results;
	}
	
	/**
	 * 通过位置得到错误返回
	 * @param position 位置
	 * @param error 错误内容
	 * @return Results
	 */
	public static Exception getErrorByPosition(String position,String error)
	{
		String name = position.substring(position.lastIndexOf(".") + 1);
		String code = properties.getValue(name);
		
		return new ResultException(code, position,error);
	}
	
	/**
	 * 创建错误结果
	 * @param code 错误码
	 * @param error 错误描述
	 * @return Results
	 */
	public static Results createErrorResult(String code,String error)
	{
		Results results = new Results();
		results.statusCode = code;
		results.error = error;
		results.result = "";
		
		return results;
	}
	
	/**
	 * 创建系统异常结果
	 * @param ex 异常
	 * @return Results
	 */
	public static Results createSystemErrorResult(Exception ex)
	{	
		String code = SystemError;
		String error = ex.getMessage();
        if(ex instanceof ResultException)
		{
			ResultException re = (ResultException)ex;
			code = re.getCode();
			error = re.getError();
		}	
		return createErrorResult(code,error);
	}
	
	/**
	 * 创建未知错误结果
	 * @return Results
	 */
	public static Results createUnknowErrorResult()
	{
		Results results = new Results();
		results.statusCode = UnknowError;
		results.error = "";
		results.result = "";
		
		return results;
	}
	
	/**
	 * 是否成功返回
	 * @param result
	 * @return
	 */
	public static boolean isSuccessResult(String result)
	{
		boolean ret = false;
		if(StringEx.isEmpty(result)) return ret;
		try
		{
			JSONObject resultJson = JSONObject.parseObject(result);
            if(!StringEx.isEmpty(resultJson))
			{
				if(Results.SUCCESS.equals(resultJson.getString("statusCode")))ret = true;
			}				
		}catch(Exception e)
		{
			ret = false;
		}
		return ret;
	}
}


class ResultException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String position;
	
	private String error;
	
	public ResultException(String code,String position,String error)
	{
		super();
		this.code = code;
		this.position = position;
		this.error = error;
	}

	public String getCode() {
		return code;
	}

	public String getPosition() {
		return position;
	}

	public String getError() {
		return error;
	}
}
