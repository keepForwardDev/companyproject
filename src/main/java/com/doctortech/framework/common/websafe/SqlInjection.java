
package com.doctortech.framework.common.websafe;

/**
 * SQL注入攻击过滤
 * @author hsh 
 */
public class SqlInjection implements Istrip {

	/**
	 * @Description SQL注入内容剥离
	 * @param value
	 * 				待处理内容
	 * @return
	 */
	public String strip(String value) {

		//剥离SQL注入部分代码
		return value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
	}
}
