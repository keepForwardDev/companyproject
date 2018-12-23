
package com.doctortech.fhq.utils.encrypt;

import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.doctortech.fhq.utils.SysException;
import com.doctortech.framework.consts.Const;


/**
 * <p>
 * AES 加解密工具
 * </p>
 */
public class AES implements BaseEncrypt {
	private static final Logger logger = Logger.getLogger("AES");
	private SecretKeySpec secretKey;
	private static AES aes;

	public static AES getInstance() {
		if (aes == null) {
			aes = new AES();
		}
		return aes;
	}

	public AES() {
		
	}

	public AES(String str) {
		setKey(str);// generate secret key
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	/**
	 * generate KEY
	 */
	public void setKey(String strKey) {
		try {
			byte[] bk = MD5.md5Raw(strKey.getBytes(Const.ENCODING));
			this.secretKey = new SecretKeySpec(bk, Algorithm.AES.getKey());
		} catch (Exception e) {
			logger.severe("Encrypt setKey is exception.");
			e.printStackTrace();
		}
	}

	/**
	 * @Description AES encrypt
	 * @param str
	 * @return
	 * @throws SysException 
	 */
	public String encryptAES(String str) throws SysException {
		byte[] encryptBytes = null;
		String encryptStr = null;
		try {
			Cipher cipher = Cipher.getInstance(Algorithm.AES.getKey());
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
			encryptBytes = cipher.doFinal(str.getBytes());
			if (encryptBytes != null) {
				encryptStr = Base64Util.encryptBASE64(encryptBytes);
			}
		} catch (Exception e) {
			throw new SysException(e);
		}
		return encryptStr;
	}

	/**
	 * @Description AES decrypt
	 * @param str
	 * @return
	 * @throws SysException 
	 */
	public String decryptAES(String str) throws SysException {
		byte[] decryptBytes = null;
		String decryptStr = null;
		try {
			Cipher cipher = Cipher.getInstance(Algorithm.AES.getKey());
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
			byte[] scrBytes = Base64Util.decryptBASE64(str);
			decryptBytes = cipher.doFinal(scrBytes);
		} catch (Exception e) {
			throw new SysException(e);
		}
		if (decryptBytes != null) {
			decryptStr = new String(decryptBytes);
		}
		return decryptStr;
	}

	/**
	 * AES encrypt
	 */
	public String encrypt(String value, String key) throws Exception {
		setKey(key);
		return encryptAES(value);
	}

	/**
	 * AES decrypt
	 */
	public String decrypt(String value, String key) throws Exception {
		setKey(key);
		return decryptAES(value);
	}

}