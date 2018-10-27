package com.iquantex.matrix.service.base;

import java.io.Serializable;
import java.util.List;

/**
 * @Description :service基类
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/3/22 14:25
 */
public interface BaseService<T,V> extends Serializable
{
    T selectByPrimaryKey(String id);

    int insert(T t);

    int insertSelective(T t);

    long countByExample(V t);

    int deleteByExample(V t);

    int deleteByPrimaryKey(String id);

    List<T> selectByExample(V example);

    int updateByExampleSelective(T bean,V example);

    int updateByExample( T bean, V example);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
}
