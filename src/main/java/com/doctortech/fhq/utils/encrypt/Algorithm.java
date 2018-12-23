
package com.doctortech.fhq.utils.encrypt;

/**
 * <p>
 * 算法类型枚举类
 * </p>
 * 
 */
public enum Algorithm {
	MD5("MD5", "md5 encrypt"),
	SHA("SHA", "has encrypt"),
	AES("AES", "aes encrypt");

	/** 主键 */
	private final String key;

	/** 描述 */
	private final String desc;


	Algorithm( final String key, final String desc ) {
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
