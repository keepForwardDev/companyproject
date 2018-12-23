
package com.doctortech.fhq.utils.encrypt;

import java.util.Formatter;

/**
 * <p>
 * 字节 16进制字串转换工具类
 * </p>
 * 
 */
public class Byte2Hex {

	/**
	 * 
	 * 字节转换为 16 进制字符串
	 * 
	 * @param b
	 * 			字节
	 * @return
	 */
	public static String byte2Hex( byte b ) {
		String hex = Integer.toHexString(b);
		if ( hex.length() > 2 ) {
			hex = hex.substring(hex.length() - 2);
		}
		while ( hex.length() < 2 ) {
			hex = "0" + hex;
		}
		return hex;
	}


	/**
	 * 
	 * 字节数组转换为 16 进制字符串
	 * 
	 * @param bytes
	 * 			字节数组
	 * @return
	 */
	public static String byte2Hex( byte[] bytes ) {
		Formatter formatter = new Formatter();
		for ( byte b : bytes ) {
			formatter.format("%02x", b);
		}
		String hash = formatter.toString();
		formatter.close();
		return hash;
	}
}
