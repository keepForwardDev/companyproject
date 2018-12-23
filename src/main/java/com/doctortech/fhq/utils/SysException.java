
package com.doctortech.fhq.utils;

/**
 * <p>
 * 系统异常
 * </p>
 * 
 * 
 */
public class SysException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误编码
	 */
	private int code;
	
	public final static int OK = 0;
	public final static int ERROR_VALIDATE_SIGNATURE = -10001;
	public final static int ERROR_PARSE_XML = -10002;
	public final static int ERROR_PARSE_JSON = -10003;
	public final static int ERROR_COMPUTE_SIGNATURE = -10004;
	public final static int ERROR_ILLEGAL_AESKEY = -10005;
	public final static int ERROR_VALIDATE_APPID = -10006;
	public final static int ERROR_ENCRYPT_AES = -10007;
	public final static int ERROR_DECRYPT_AES = -10008;
	public final static int ERROR_ILLEGAL_BUFFER = -10009;


	public SysException(String message) {
		super(message);
	}

	public SysException(Throwable throwable) {
		super(throwable);
	}

	public SysException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public SysException(int code) {
		super(getMessage(code));
		this.code = code;
	}

	public SysException(int code, Throwable cause) {
		super(getMessage(code), cause);
		this.code = code;
	}

	private static String getMessage(int code) {
		switch (code) {
		case ERROR_VALIDATE_SIGNATURE:
			return "签名验证错误";
		case ERROR_PARSE_XML:
			return "xml解析失败";
		case ERROR_PARSE_JSON:
			return "json解析失败";
		case ERROR_COMPUTE_SIGNATURE:
			return "sha加密生成签名失败";
		case ERROR_ILLEGAL_AESKEY:
			return "SymmetricKey非法";
		case ERROR_VALIDATE_APPID:
			return "appid校验失败";
		case ERROR_ENCRYPT_AES:
			return "aes加密失败";
		case ERROR_DECRYPT_AES:
			return "aes解密失败";
		case ERROR_ILLEGAL_BUFFER:
			return "解密后得到的buffer非法";
		default:
			/*
			 * cannot be
			 */
			return null;
		}
	}

	public int getCode() {
		return code;
	}

	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked(Throwable ex) {
		if (ex instanceof RuntimeException) {
			return (RuntimeException) ex;
		} else {
			return new RuntimeException(ex);
		}
	}
	
	
}