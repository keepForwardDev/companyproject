
package com.doctortech.framework.consts;

/**
 * 
 * 访问前台登录页面还是后台登录页面
 */
public enum LoginAction {
	
	FORNT("0", "前台"),
	
	/** 跳过 */
	BACK("1", "后台");

	/** 主键 */
	private final String key;

	/** 描述 */
	private final String desc;

	LoginAction(final String key, final String desc) {
		this.key = key;
		this.desc = desc;
	}

	public String getKey() {
		return this.key;
	}

	public String getDesc() {
		return this.desc;
	}

}
