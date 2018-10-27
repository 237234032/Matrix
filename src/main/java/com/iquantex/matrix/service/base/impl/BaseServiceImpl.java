package com.iquantex.matrix.service.base.impl;

import com.iquantex.matrix.mapper.base.BaseMapper;
import com.iquantex.matrix.service.base.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * @Description :实现类基类
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/3/22 14:31
 */
public abstract  class BaseServiceImpl<T,V>  implements Serializable, BaseService<T,V>
{
    public abstract BaseMapper getMapper();

    @Override
    public T selectByPrimaryKey(String id)
    {
    return (T)getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int insert(T t)
    {
    return getMapper().insert(t);
    }

    @Override
    public int insertSelective(T t)
    {
        return getMapper().insertSelective(t);
    }

    @Override
    public long countByExample(V t) { return getMapper().countByExample(t); }

    @Override
    public int deleteByExample(V t) { return getMapper().deleteByExample(t); }

    @Override
    public int deleteByPrimaryKey(String id) { return getMapper().deleteByPrimaryKey(id); }

    @Override
    public List<T> selectByExample(V t) { return getMapper().selectByExample(t); }

    @Override
    public int updateByExampleSelective(T t1, V t2) { return getMapper().updateByExampleSelective(t1,t2); }

    @Override
    public int updateByExample(T t1, V t2) { return getMapper().updateByExample(t1,t2); }

    @Override
    public int updateByPrimaryKeySelective(T t) { return getMapper().updateByPrimaryKeySelective(t); }

    @Override
    public int updateByPrimaryKey(T t) { return getMapper().updateByPrimaryKey(t); }



}
