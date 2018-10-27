package com.iquantex.matrix.controller.base;

import com.alibaba.fastjson.JSON;
import com.diqiumofang.tool.CalendarUtil;
import com.diqiumofang.tool.ClassInfo;
import com.diqiumofang.tool.StringEx;
import com.iquantex.matrix.utils.Results;

import java.util.UUID;

/**
 * @Description :Controller基类
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/3/20 10:51
 */
public class BaseController
{

    protected Exception getBodyNullErrorResult(String errorDesc) {
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position, errorDesc);
    }

    protected Exception getBodyNoJsonErrorResult(String errorDesc) {
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position, errorDesc);
    }

    protected Exception getParamsErrorResult(String errorDesc) {
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position, errorDesc);
    }

    protected Exception getParamsDataErrorResult(String errorDesc) {
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position, errorDesc);
    }

    protected Exception getCacheOperateErrorResult(String errorDesc) {
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position, errorDesc);
    }

    protected Exception getSystemConfigNameNullErrorResult(String errorDesc){
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position,errorDesc);
    }

    protected Exception getSystemConfigNameNoExistedErrorResult(String errorDesc){
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position,errorDesc);
    }

    protected Exception getParamsPrimaryErrorResult(String errorDesc){
        String position = ClassInfo.getPackageClassMethodName();
        return Results.getErrorByPosition(position,errorDesc);
    }

    protected  void sysoBean(Object o)
    {

        System.out.println(StringEx.isEmpty(o)?o: JSON.toJSONString(o));
    }

    public Integer getStartIndex(Integer startindex , int defaultValue)
    {
        return StringEx.isEmpty(startindex)?defaultValue:startindex;
    }

    public Integer getStartIndex(Integer startindex)
    {
        return this.getStartIndex(startindex,0);
    }



    public String surroundLike(String value)
    {
        return StringEx.isEmpty(value) ? "": "%"+value+"%";
    }


    public boolean atLeastOneHasValue(Object ... o)
    {
        if(StringEx.isEmpty(o) || o.length == 0) return false;
        for (int i = 0; i < o.length; i++) { if(!StringEx.isEmpty(o[i])) return true; }
        return false;
    }

    public Integer getPageSize(Integer pagesize, int defaultValue)
    {
        return StringEx.isEmpty(pagesize)?defaultValue:pagesize;
    }

    public Integer getPageSize(Integer pagesize)
    {
        return this.getPageSize(pagesize,10);
    }

    public String UUID ()
    {
        String res = UUID.randomUUID().toString();
        return res.replaceAll("-","");
    }


    protected String fillCurrentDateStringIfEmpty(String datetime)
    {
        return StringEx.isEmpty(datetime) ? CalendarUtil.getLongString() : datetime;
    }
}
