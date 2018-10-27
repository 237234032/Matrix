package com.iquantex.matrix.mapper.base;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @Description :mapper基类
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/3/22 14:23
 */
public interface BaseMapper<T,V>  extends Serializable
{
    /**
     * 根据指定主键获取一条记录
     * @param : uuid
     */
    T selectByPrimaryKey(String id);

    /**
     * 添加记录
     * @param : record
     */
    int insert(T t);

    /**
     * 动态添加记录
     * @param : record
     */
    int insertSelective(T t);

    /**
     * 根据指定的条件获取记录数
     * @param : example
     */
    long countByExample(V example);


    /**
     * 根据指定的条件删除符合条件的记录
     * @param : example
     */
    int deleteByExample(V example);

    /**
     * 根据主键删除记录
     * @param : uuid
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据条件查询,如果example中有startIndex和pageSize，那么可以进行分页查询
     * @param : example
     */
    List<T> selectByExample(V t);

    /**
     * 动态根据指定的条件来更新符合条件的记录
     * @param : record
     * @param : example
     */
    int updateByExampleSelective(@Param("record") T bean, @Param("example") V example);

    /**
     * 根据指定的条件来更新符合条件的记录
     * @param : record
     * @param : example
     */
    int updateByExample(@Param("record") T bean,@Param("example") V example);

    /**
     * 动态字段,根据主键来更新符合条件的记录
     * @param : record
     */
    int updateByPrimaryKeySelective(T t);

    /**
     * 根据主键来更新符合条件的记录
     * @param : record
     */
    int updateByPrimaryKey(T t);
}
