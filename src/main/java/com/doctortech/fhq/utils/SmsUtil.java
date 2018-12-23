package com.doctortech.fhq.utils;

import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloopen.rest.sdk.CCPRestSDK;



/**
 * 云讯通发送短信工具类
 * 
 */
public class SmsUtil {

	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);


	/**
	 * 测试模版id:1
	 * 生产环境id:4090
	 * {1},{2}...对应数组内容来替换
	 * @param phoneNumber
	 * @param content
	 */
	public static boolean send(String phoneNumber,String templateId,String[] content,boolean test){
		  boolean success = false;
		  CCPRestSDK restAPI=null;
		  try 
		  {
			
			HashMap<String, Object> ret = null;

	
			restAPI= new CCPRestSDK();
		
	
		/*
		 * 
		 * 登录云通信，帐号15626477417 密码：highjy123
		 * 
		 * 测试sanboxapp 
		 */
			if (false) {
				
				restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
				restAPI.setAccount("8a48b5514811a270014816412e730208", "15f5a545ee4b4c0d9b71fafae628b908");// 初始化主帐号名称和主帐号令牌
				restAPI.setAppId("8aaf070865e6b6eb0165f51e48100b1f");// 初始化应用ID
				ret = restAPI.sendTemplateSMS(phoneNumber,templateId ,content);
			}else {
				
				restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
				restAPI.setAccount("8a48b5514811a270014816412e730208", "15f5a545ee4b4c0d9b71fafae628b908");// 初始化主帐号名称和主帐号令牌
				restAPI.setAppId("8aaf070865e6b6eb0165f51e48100b1f");// 初始化应用ID  8a216da86339b5e801633db8a99e02f0
				
				ret = restAPI.sendTemplateSMS(phoneNumber,templateId,content);

				
			}	
			
		//	System.out.println("SDKTestGetSubAccounts result=" + ret);
			
			if("000000".equals(ret.get("statusCode"))){
				//正常返回输出data包体信息（map）
				HashMap<String,Object> data = (HashMap<String, Object>) ret.get("data");
				Set<String> keySet = data.keySet();
				for(String key:keySet){
					Object object = data.get(key);
					//System.out.println(key +" = "+object);
				}
				success=true;
			}else{
				//异常返回输出错误码和错误信息
				System.out.println("错误码=" + ret.get("statusCode") +" 错误信息= "+ret.get("statusMsg"));
				logger.error("发送短信失败","错误码=" + ret.get("statusCode") +" 错误信息= "+ret.get("statusMsg"));
			}	
		
		}catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("发送短信异常",e.getMessage());
		}finally{
			System.out.println("restAPI="+restAPI);
			return success;
		}
			

	}
	
	/**
	 * 测试模版id:1
	 * 生产环境id:4090
	 * {1},{2}...对应数组内容来替换
	 * @param phoneNumber
	 * @param content
	 */
	public static String sendSms(String phoneNumber,String templateId,String[] content,boolean test){
		  String str="";
		  CCPRestSDK restAPI=null;
		  try
		  {

			HashMap<String, Object> ret = null;


			restAPI= new CCPRestSDK();


		/*
		 *
		 * 登录云通信，帐号15626477417 密码：123456x
		 *
		 * 测试sanboxapp
		 */
			if (test) {

				restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
				restAPI.setAccount("8a48b5514811a270014816412e730208", "15f5a545ee4b4c0d9b71fafae628b908");// 初始化主帐号名称和主帐号令牌
				restAPI.setAppId("8a48b5514811a2700148165076dd0250");// 初始化应用ID
				ret = restAPI.sendTemplateSMS(phoneNumber,templateId ,content);
			}else {

				restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
				restAPI.setAccount("8a48b5514811a270014816412e730208", "15f5a545ee4b4c0d9b71fafae628b908");// 初始化主帐号名称和主帐号令牌
				restAPI.setAppId("8a48b5514811a2700148165076dd0250");// 初始化应用ID

				ret = restAPI.sendTemplateSMS(phoneNumber,templateId,content);


			}

		//	System.out.println("SDKTestGetSubAccounts result=" + ret);

			if("000000".equals(ret.get("statusCode"))){
				//正常返回输出data包体信息（map）
				HashMap<String,Object> data = (HashMap<String, Object>) ret.get("data");
				Set<String> keySet = data.keySet();
				for(String key:keySet){
					Object object = data.get(key);
					//System.out.println(key +" = "+object);
				}
				str="success";
			}else{
				//异常返回输出错误码和错误信息
				str="错误码=" + ret.get("statusCode") +" 错误信息= "+ret.get("statusMsg");
				logger.error("发送短信失败",str);
			}

		}catch (Exception e)
		{
			e.printStackTrace();
			logger.error("发送短信异常",e.getMessage());
			str = e.getMessage();
		}finally{
			System.out.println("str="+str);
			return str;
		}

		
	}
	
}
