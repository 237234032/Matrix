package com.iquantex.matrix.constant;

/**
 * @Description :正则表达式
 * @author : zn-Cabigail(1163727353@qq.com)
 * @date :  2018/4/9 上午9:57
 */
public interface RegexpConstant {

	public final static String UUID = "^\\w{32,36}$";
	
	public final static String STATUS = "^\\d{1,3}$";
	
	public final static String TYPE = "^\\d{1,3}$";
	
	public final static String STATUSES = "^(\\d{1,3},)*\\d{1,3}$";
	
	public final static String MOBILE = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\\d{8}$";

	/**邮件**/
	public final static String EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";

	public final static String NICKNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{1,30}$";


	/**年月日**/
    public final static String DATE = "^\\d{4}\\-\\d{2}\\-\\d{2}$";

    /**年月日时分秒**/
    public final static String DATETIME = "^\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$";

	/**性别**/
	public final static String SEX = "^\\d{1}$";

	/**图片**/
	public final static String IMG = "^.*$";

	/**排序**/
	public final static String ORDERBY = "^(\\w+-(asc|desc),)*\\w+-(asc|desc)$";

	/**纯数字3位**/
    public final static String NUMBER3 = "^[0-9]{1,3}$";

    /**纯数字6位**/
    public final static String NUMBER6 = "^[0-9]{1,6}$";

    /**纯数字36位**/
    public final static String NUMBER36 = "^[0-9]{1,36}$";

    /**区号**/
    public final static String TELCODE = "^0[1-9]{2,3}$";

    /**11位纯数字保留2位小数**/
    public final static String DOUBLE_11_2 = "^\\d{1,11}.\\d{1,2}|\\d{1,11}$";

    /**11位纯数字保留2位小数,逗号隔开**/
    public final static String DOUBLES_11_2 = "^(\\d{1,11}.\\d{1,2}|\\d{1,11},)*\\d{1,11}.\\d{1,2}|\\d{1,11}$";

    public final static String ORGANIZATIONTYPE = "^\\w+-(top|middle|bottom)$";

    public final static String FILTER_PASS_URL = "^(/_getSigutature|/_logout|/hello)\\S*$";
	
}