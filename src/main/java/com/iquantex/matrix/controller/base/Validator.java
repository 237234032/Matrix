package com.iquantex.matrix.controller.base;


import com.diqiumofang.tool.StringEx;

import java.util.*;

/**
 * @Description :字段验证器
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 上午10:44
 */
public class Validator {

    private List<Validator> list = null;

    /**字段**/
    private String column;
    /**字段值**/
    private Object value;
    /**是否不为空**/
    private boolean canNotBeNull;
    /**正则**/
    private String regx = null;

    private final String VALIDATE_ZERO_NOTE  = "validate column size is zero";

    /**
     * 构造器
     */
    public Validator() {
    }

    /**
     * 构造器
     * @param value 值
     * @param canNotBeNull 是否不能为空
     */
    public Validator(String column ,Object value, boolean canNotBeNull) {
        this.column = column;
        this.value = value;
        this.canNotBeNull = canNotBeNull;
    }

    /**
     * 构造器
     * @param value 值
     * @param regx 正则
     * @param canNotBeNull 是否不能为空
     */
    public Validator(String column , Object value, String regx, boolean canNotBeNull) {
        this.column = column;
        this.value = value;
        this.canNotBeNull = canNotBeNull;
        this.regx = regx;
    }

    /**
     * 
     * @param validator
     */
    public void addValidator(Validator validator)
    {
        if(this.list == null) this.list = new ArrayList<>();
        this.list.add(validator);
    }


    public void init()
    {
        this.list = null;
    }

    /**
     * 验证
     * @throws Exception
     */
    public void validate() throws  Exception
    {
        Iterator<Validator> it = this.list.iterator();
        String column ;
        Object value ;
        Set<String> set = new HashSet<>();
        Validator validator ;
        while (it.hasNext()) {
            validator = it.next();
            value = validator.getValue();
            column = validator.getColumn();
            /**如果不能为空并且值为空，报错**/
            if(validator.isCanNotBeNull() && StringEx.isEmpty(value)) throw new Exception("validate column is empty : " + column);
            /**如果有正则并且正则验证失败，报错**/
            if(!this.validateColumn(validator,value)) throw new  Exception("validate column is invalid : " + column + " column value : " + value);
            if(StringEx.isEmpty(value)) continue;
            set.add(column);
        }
        if(set.size() == 0 )throw new Exception(VALIDATE_ZERO_NOTE);
    }

    /**
     * 正则验证
     * @param validator
     * @param value
     * @return
     */
    private boolean validateColumn(Validator validator, Object value)
    {
        boolean ret = true;
        if(StringEx.isEmpty(value)) return ret;
        String regx = validator.getRegx();
        if(StringEx.isEmpty(regx)) return ret;
        return StringEx.match(regx,value.toString());

    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isCanNotBeNull() {
        return canNotBeNull;
    }

    public void setCanNotBeNull(boolean canNotBeNull) {
        this.canNotBeNull = canNotBeNull;
    }

    public String getRegx() {
        return regx;
    }

    public void setRegx(String regx) {
        this.regx = regx;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

}
