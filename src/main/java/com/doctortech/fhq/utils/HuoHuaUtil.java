package com.doctortech.fhq.utils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.doctortech.framework.bean.CommonRespon;
import com.doctortech.framework.consts.Const;
import com.doctortech.framework.util.UUIDUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HuoHuaUtil {
	
	//建敏账户
	//钱包地址
	//jK78ES1FUtfs9Xgrv6xRqbfZrWujmEyLtc
	//私钥
	//sshT4vi6abE3Kfr5iLApVZ8ra6Vhr
	
	//龚增辉
	//jpYDUfxqiMZdQbroqK7nx9P1YiUQoEZnwD
	//sp5SphJrGsHsAytahFimHj796a8d5
	
	public static final String TEST="https://dapi.sparkchain.cn";
	
	public static final String APPID="1065094582837977088";
	public static final String APPSECRET="11f66da6-4624-45d8-add9-c016b7998258";
	//19cfac59-ff3d-4d9c-b0d5-e7f7f3e2bf14
	public static  String ACCESSTOKEN="b8f41843-a3ba-4395-a2a0-621700ef463f" ;
	
	public static String NEW_USER="new_user" ;
	
	public static String ADD_EXPERT="add_expert" ;
	
	
	public static void main(String[] args) {
		
		//init();
//		getAccessToken();
//		selectChain();
		
		//{"code":"200","data":{"walletAddr":"c5013726-8310-42b1-8f09-3d2cdcd437fc","appId":"1065094582837977088","accounts":[{"balances":[{"tokenCode":"SWT","freeze":"0","balance":"0"}],"chainCode":"jingtum","account":"jGN7ckZ8mXyub4kp8nxTukp3mhSX65r2VC"}],"userId":"1"},"message":"","success":true}
//		/{"code":"200","data":{"walletAddr":"ab567af0-7021-4625-b75c-1979f7f54330","appId":"1065094582837977088","accounts":[{"balances":[{"tokenCode":"SWT","freeze":"0","balance":"0"}],"chainCode":"jingtum","account":"jNc4tdjXre5fdUtjZjq8W7QbjHvhbRu3mn"}],"userId":"2"},"message":"","success":true}
		
		//这个是建敏火花账号
		//605b687db95045f2a4e12c066ed98358
		//{"code":"200","data":{"walletAddr":"23d2c5b8-868d-4745-b8ca-8684aa298ef9","appId":"1065094582837977088","accounts":[{"balances":[{"tokenCode":"SWT","freeze":"0","balance":"0"}],"chainCode":"jingtum","account":"jpvL9cgicUP4LECwnGR36quo2WPeRsU69J"}],"userId":"605b687db95045f2a4e12c066ed98358"},"message":"","success":true}
		//5a50708f7da14a479e041c9a09f2a47e
		//{"code":"200","data":{"walletAddr":"0e8a7493-be17-40e7-90b9-7a9be6503dea","appId":"1065094582837977088","accounts":[{"balances":[{"tokenCode":"SWT","freeze":"0","balance":"0"}],"chainCode":"jingtum","account":"jB8vbrTZ4oQYyEMHjjcngLNSgTsJdjLWY4"}],"userId":"5a50708f7da14a479e041c9a09f2a47e"},"message":"","success":true}
		
		//这是振国火花账号
		//{"code":"200","data":{"walletAddr":"cce20e11-ccae-4741-8e7a-f7b6add88112","appId":"1065094582837977088","accounts":[{"balances":[{"tokenCode":"SWT","freeze":"0","balance":"0"}],"chainCode":"jingtum","account":"j9t8oei4oT59rE2Fo7BFfjo8hgmBgpkgr9"}],"userId":"76eb485155954328a06c1c0e4eef1f38"},"message":"","success":true}
		//createWallet("76eb485155954328a06c1c0e4eef1f38", "123456");
		
		//214.99000
		//35.00000
		
		//180
		//{"code":"200","data":{"walletAddr":"23d2c5b8-868d-4745-b8ca-8684aa298ef9","userId":"605b687db95045f2a4e12c066ed98358"},"message":"","success":true}
		//setPayPassword("23d2c5b8-868d-4745-b8ca-8684aa298ef9", "123456", "223456");
		//transfer("23d2c5b8-868d-4745-b8ca-8684aa298ef9","223456", "cce20e11-ccae-4741-8e7a-f7b6add88112", "60", UUIDUtils.genUUid());
		
		//{"code":"200","data":{"password":"1065094582837977088","walletAddr":"1184f2af-e9b1-4a4d-9ac3-6ce867209b67","accounts":[{"balances":[{"tokenCode":"SWT","freeze":"0","balance":"0"}],"chainCode":"jingtum","account":"j27wwjF2AS1nexBE36mEaK6ambz3w59aZ"}],"userId":"1065094582837977088"},"message":"","success":true}
		//createAppWallet();
		
//		charge("jK78ES1FUtfs9Xgrv6xRqbfZrWujmEyLtc", "sshT4vi6abE3Kfr5iLApVZ8ra6Vhr", "23d2c5b8-868d-4745-b8ca-8684aa298ef9", UUIDUtils.genUUid(), "1000");
		//charge("jK78ES1FUtfs9Xgrv6xRqbfZrWujmEyLtc", "sshT4vi6abE3Kfr5iLApVZ8ra6Vhr", "605b687db95045f2a4e12c066ed98358", UUIDUtils.genUUid(), "3000");
		
		//getBalances("76eb485155954328a06c1c0e4eef1f38");
		
		
	}
	
	//系统初始化
	public static void init() {
		System.out.println("init()");

		String uri=TEST+"/v1/app/init" ;
		
		
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("appcode","yunfu_test");			
			jsonObject1.put("appname","云孵_测试");			
			jsonObject1.put("transCallBackUrl","");			
			jsonObject1.put("msgCallBackUrl","");			
			jsonObject1.put("userid","");			
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
		          InputStream inputStream = method.getResponseBodyAsStream();
		          //logger.info("response:"+new String(responseBody));
		          ObjectMapper objectMapper=new ObjectMapper();
		          JsonNode readTree = objectMapper.readTree(inputStream);
		          System.out.println(readTree);
		          if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
		        	  String appid=readTree.get("data").get("appid").asText();
		        	  String appsecret=readTree.get("data").get("appsecret").asText();
		        	  
		          }	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//生成访问凭着
	public static void getAccessToken() {
		System.out.println("getAccessToken()");
		String uri=TEST+"/v1/app/access" ;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("appid",APPID);			//业务系统的 ID
			jsonObject1.put("appsecret",APPSECRET);		//业务系统的秘钥		
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
					String accessToken=readTree.get("data").get("accessToken").asText();
					ACCESSTOKEN=accessToken;
					
				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//选择区块链
	public static void selectChain() {
		System.out.println("selectChain()");
		String uri=TEST+"/v1/app/selectChain" ;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("accessToken",ACCESSTOKEN);			//访问凭证
			jsonObject1.put("chainCodes","jingtum");		//区块链编码（多个值时，用逗号分隔），比如：jingtum,moac,eth 等
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
					
				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//创建企业钱包
	public static void createAppWallet() {
		
		String uri=TEST+"/v1/app/createAppWallet" ;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("accessToken",ACCESSTOKEN);			//访问凭证
			jsonObject1.put("chainCodes","jingtum");		//【可选】区块链编码（多个值，用逗号分隔），比如：jingtum、moac 或 eth 等。如果已在 2.3 节中选择了区块链，此参数值可为空
			jsonObject1.put("onlyWallet",false);		//【可选】是否仅生成钱包（默认值为 false）如果为 true，那么只生成空钱包；如果为 false，会在已选的公链上自动创建账户
			
			
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
					String walletAddr=readTree.get("data").get("walletAddr").asText(); //钱包地址
					String userId=readTree.get("data").get("userId").asText(); //用户 Id
					String password=readTree.get("data").get("password").asText(); //查询密码
					Iterator<JsonNode> accounts=readTree.get("data").get("accounts").elements();
					while(accounts.hasNext()) {
						JsonNode acc=accounts.next();
						String chainCode=acc.get("chainCode").asText(); //区块链的编码
						String account=acc.get("account").asText(); //账户
						Iterator<JsonNode> balances=acc.get("balances").elements();
						while(balances.hasNext()){
							JsonNode bal=balances.next();
							String tokenCode=bal.get("tokenCode").asText();//通证编码
							String freeze=bal.get("freeze").asText(); //冻结金额
							String balance=bal.get("balance").asText(); //总金额
						}
						
					}
					
				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	//创建用户钱包
	/**
	 * 在接入的应用系统中,创建用户钱包，每个人仅可以创建一个用户钱包，以传
	 * 入的用户名作为应用系统中的唯一标识，密码可以用来设置支付密码。
	 */
	public static void createWallet(String userId,String password) {
		
		String uri=TEST+"/v1/app/createWallet" ;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("accessToken",ACCESSTOKEN);			//访问凭证
			jsonObject1.put("userId",userId);		//用户 Id
			jsonObject1.put("password",password);		//查询密码
			jsonObject1.put("onlyWallet",false);		//【可选】是否仅生成钱包（默认值为 false）如果为 true，那么只生成空钱包；如果为 false，会在已选的公链上自动创建账户
			
			
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
					
					String appId=readTree.get("data").get("appId").asText(); //应用 Id
					String userIdR=readTree.get("data").get("userId").asText(); //用户 Id
					String walletAddr=readTree.get("data").get("walletAddr").asText(); //钱包地址
					Iterator<JsonNode> accounts=readTree.get("data").get("accounts").elements();
					while(accounts.hasNext()) {
						JsonNode acc=accounts.next();
						String chainCode=acc.get("chainCode").asText(); //区块链的编码
						String account=acc.get("account").asText(); //账户
						Iterator<JsonNode> balances=acc.get("balances").elements();
						while(balances.hasNext()){
							JsonNode bal=balances.next();
							String tokenCode=bal.get("tokenCode").asText();//通证编码
							String freeze=bal.get("freeze").asText(); //冻结金额
							String balance=bal.get("balance").asText(); //总金额
						}
						
					}
					
				}else if(readTree.get("code").asInt()==400 && !readTree.get("success").asBoolean()) {
					//{"code":"400","success":false,"message":"3007:The input accessToken is not right or expired."}
					String  message=readTree.get("message").asText();
					System.out.println("refresh accessToken");
					getAccessToken();
					System.out.println(message+" retry request");
					createWallet(userId,password);

				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	//选择通证
	public static void selectToken() {
		
		String uri=TEST+"/v1/app/selectToken" ;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("accessToken","");			//访问凭证
			jsonObject1.put("chainCode","");		//区块链编码(比如：jingtum，moac 或 eth 等)
			jsonObject1.put("tokenCodes","");		//通证编码（多个值时，用逗号分隔）
			
			
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
					
					
				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//设置支付密码
	public static void setPayPassword(String walletAddr,String password ,String payPassword) {
		
		String uri=TEST+"/v1/wallet/setPayPassword" ;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("accessToken",ACCESSTOKEN);			//访问凭证
			jsonObject1.put("walletAddr",walletAddr);		//walletAddr 和 userId 二选一
			jsonObject1.put("userId","");			//
			jsonObject1.put("password",password);		    //查询密码
			jsonObject1.put("payPassword",payPassword);		//支付密码
			
			
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
					String walletAddrR=readTree.get("data").get("walletAddr").asText(); //钱包地址
					String userId=readTree.get("data").get("userId").asText(); //用户 Id

					
				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//钱包的支付请求
	/**
	 * 
	 * @param srcWalletAddr
	 * @param payPassword
	 * @param destWalletAddr
	 * @param amount
	 * @param bizId
	 */
	public static boolean transfer(String srcWalletAddr,String payPassword,String destUserId,String amount,String bizId) {
		
		String uri=TEST+"/v1/wallet/transfer" ;
		boolean flag=false ;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("accessToken",ACCESSTOKEN);			//访问凭证
			jsonObject1.put("srcWalletAddr",srcWalletAddr);		//【三选一】支付方的钱包地址
			jsonObject1.put("srcUserId","");			//
			jsonObject1.put("srcAccount","");		    //
			jsonObject1.put("payPassword",payPassword);		//支付方的支付密码
			jsonObject1.put("chainCode","jingtum");		//区块链编码（比如：jingtum 或 moac）
			jsonObject1.put("tokenCode","SWT");		//通证编码(比如：jingtum 的 SWT、moac 的 MOAC)
//			jsonObject1.put("destWalletAddr",destWalletAddr);		//【三选一】接受方的钱包地址
			jsonObject1.put("destUserId",destUserId);		//
			jsonObject1.put("destAccount","");		//
			jsonObject1.put("amount",amount);		//支付金额
			jsonObject1.put("bizId",bizId);		//业务 Id（每次操作都不能重复，保证事务）
//			jsonObject1.put("memo","");		//【可选】记录内容（提供了敏感词过滤功能，上链时敏感词会转换为*）
//			jsonObject1.put("gasLimit","");		//【可选】Gas 数的上限值，该 gas 设置对 jingtum公链不起效
//			jsonObject1.put("gasPrice","");		//【可选】Gas 单位价格，该 gas 设置对 jingtum 公链不起效
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
//					String hash=readTree.get("data").get("hash").asText(); //钱包地址
					flag=true ;
					
				}else if(readTree.get("code").asInt()==400 && !readTree.get("success").asBoolean()) {
					//{"code":"400","success":false,"message":"3007:The input accessToken is not right or expired."}
					String  message=readTree.get("message").asText();
					System.out.println("refresh accessToken");
					getAccessToken();
					System.out.println(message+" retry request");
					transfer(srcWalletAddr,payPassword,destUserId,amount,bizId);


				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag; 
		
	}
	
	
	//获取钱包余额
	public static Map<String,Object> getBalances(String userId) {
		
		String uri=TEST+"/v1/wallet/balances" ;
//		CommonRespon respon=new CommonRespon();
		Map<String,Object> retMap=new HashMap<>();
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("accessToken",ACCESSTOKEN);			//访问凭证
			jsonObject1.put("walletAddr","");		//【二选一】钱包地址
			jsonObject1.put("userId",userId);		//账户
			jsonObject1.put("chainCode","jingtum");		//区块链编码（比如：jingtum 或 moac）
			jsonObject1.put("tokenCode","SWT");		//通证编码(比如：jingtum 的 SWT、moac 的 MOAC)
			//jsonObject1.put("bizId",UUIDUtils.genUUid());		//业务 Id（每次操作都不能重复，保证事务）

			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505			
					String userIdR=readTree.get("data").get("userId").asText(); //用户 Id
					String walletAddr=readTree.get("data").get("walletAddr").asText(); //钱包地址
					Iterator<JsonNode> balances=readTree.get("data").get("balances").elements();
					while(balances.hasNext()) {
						JsonNode acc=balances.next();
						String tokenCode=acc.get("tokenCode").asText(); //通证编码
						String address=acc.get("address").asText(); //账户地址
						String balance=acc.get("balance").asText(); //余额
						String chainCode=acc.get("chainCode").asText(); //区块链编码
						String freezed=acc.get("freezed").asText(); //冻结的金额
			
						retMap.put("address", address);
						retMap.put("balance", balance);
						retMap.put("tokenCode",tokenCode);
						retMap.put("freezed",freezed);
					}
					

				}else if(readTree.get("code").asInt()==400 && !readTree.get("success").asBoolean()) {
					//{"code":"400","success":false,"message":"3007:The input accessToken is not right or expired."}
					String  message=readTree.get("message").asText();
					System.out.println("refresh accessToken");
					getAccessToken();
					System.out.println(message+" retry request");
					getBalances(userId);


				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retMap ;
		
	}
	
	
	//账户充值
	/**
	 * 外部账户向本平台的账户或平台的钱包进行充值，比如，创建 app 钱包之后，
     *就需要外部账户向其 app 钱包中充值，然后 app 钱包向用户钱包转账。
	 */
	public static void charge(String srcAccount,String srcPrivateKey,String destUserId,String bizId,String amount) {
		
		String uri=TEST+"/v1/account/charge" ;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(uri);
			method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("accessToken",ACCESSTOKEN);			//访问凭证
			jsonObject1.put("chainCode","jingtum");		//区块链编码
			jsonObject1.put("tokenCode","SWT");		//通证编码
			jsonObject1.put("srcAccount",srcAccount);		//充值方的账户
			jsonObject1.put("srcPrivateKey",srcPrivateKey);		//充值方的账户私钥
//			jsonObject1.put("destAccount","");		//【三选一】接受方的账户
//			jsonObject1.put("destWalletAddr",destWalletAddr);		//【三选一】接收方的钱包地址
			jsonObject1.put("appid",APPID);		//应用 Id，和 destUserId 结合在一起使用
			jsonObject1.put("destUserId",destUserId);		//【三选一】接受方的用户 Id
			jsonObject1.put("bizId",bizId);		//业务 ID（每次操作都不能重复，保证事务）
			jsonObject1.put("amount",amount);		//充值金额
//			jsonObject1.put("gasLimit","");		//【可选】Gas 数的上限值,该 gas 设置对jingtum 公链不起效
//			jsonObject1.put("gasPrice","");		//【可选】Gas 单位价格,该 gas 设置对jingtum 公链不起效

			
			
			for(String key :jsonObject1.keySet()){
				String value=jsonObject1.getString(key);
				if(value!=null) {
					method.addParameter(new NameValuePair(key,value));
				}
			}
			
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = method.getResponseBodyAsStream();
				//logger.info("response:"+new String(responseBody));
				ObjectMapper objectMapper=new ObjectMapper();
				JsonNode readTree = objectMapper.readTree(inputStream);
				System.out.println(readTree);
				if(readTree.get("code").asInt()==200) { //成功：200, 请求失败: 400，未认证:401, 服务器内部错误:505
					//String hash=readTree.get("data").get("hash").asText(); //钱包地址
					
					
				}else if(readTree.get("code").asInt()==400 && !readTree.get("success").asBoolean()) {
					//{"code":"400","success":false,"message":"3007:The input accessToken is not right or expired."}
					String  message=readTree.get("message").asText();
					System.out.println("refresh accessToken");
					getAccessToken();
					System.out.println(message+" retry request");
					charge(srcAccount, srcPrivateKey, destUserId, UUIDUtils.genUUid(), amount);

				}	 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	/**
	 * 总账户-》员工账户
	 */
	public static boolean tranferByCom(String destUserId,String amount) {
		
		//transfer("23d2c5b8-868d-4745-b8ca-8684aa298ef9","223456", "cce20e11-ccae-4741-8e7a-f7b6add88112", "60", UUIDUtils.genUUid());
		return transfer("23d2c5b8-868d-4745-b8ca-8684aa298ef9","223456",destUserId,amount, UUIDUtils.genUUid());

	}

	
	
	
	
	
	
	

}
