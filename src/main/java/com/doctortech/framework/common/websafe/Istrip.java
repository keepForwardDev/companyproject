package com.doctortech.framework.common.websafe;

/**
 * 防止攻击过滤父类
 * <p>
 * @author   hsh
 */
public interface Istrip {

	/**
	 * @Description 脚本内容剥离
	 * @param value
	 * 				待处理内容
	 * @return
	 */
	public String strip(String value);
}
