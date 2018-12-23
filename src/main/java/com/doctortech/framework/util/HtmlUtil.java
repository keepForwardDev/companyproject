package com.doctortech.framework.util;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
public class HtmlUtil
{

		private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	    
	    /**
	     * @param htmlStr
	     * @return
	     *  删除Html标签
	     */
	    public static String delHTMLTag(String htmlStr) {
	    	
	    	if (StringUtils.isBlank(htmlStr)) {
				
	    		return "";
			}
	        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	        Matcher m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll(""); // 过滤script标签

	        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	        Matcher m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll(""); // 过滤style标签

	        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        Matcher m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll(""); // 过滤html标签

	        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
	        Matcher m_space = p_space.matcher(htmlStr);
	        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
	        return htmlStr.trim(); // 返回文本字符串
	    }
	    
	    
	    public static String delOnlyHTMLTag(String htmlStr) {
	 
	    	if (StringUtils.isBlank(htmlStr)) {
				
	    		return "";
			}
 	
	    	Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	    	Matcher m_html = p_html.matcher(htmlStr);
	    	htmlStr = m_html.replaceAll(""); // 过滤html标签
	   
	    	return htmlStr.trim(); // 返回文本字符串
	    }
	    
		public static String getTextFromHtml(String htmlStr){
			htmlStr = delHTMLTag(htmlStr);
			htmlStr = htmlStr.replaceAll("&nbsp;", "");
			htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
			return htmlStr;
		}

	
	
	/**
	 * 去掉html标签
	 * @param html
	 * @return
	 */
	public static String trimHtml(String html){
		
		if( StringUtils.isBlank(html) ){
			return "";
		}
		return html.replaceAll("<([^>]*)>","").replace("&nbsp;", " ").replaceAll("\\s*|\t|\r|\n", "");
	}
	
	/**
	 * 去掉除了<font style=\"color:red\"></font>的html标签并高亮
	 * <p>系列化的反恐弹药<font style="color:red">技术</font>，是利用烟火<font style="color:red">技术</font>原理研制出的防御</p>
	 * @param html
	 * @return
	 */
	public static String trimHtmlAndHightLight(String html){
		
		if( StringUtils.isBlank(html) ){
			return "";
		}
	//	System.out.println("html="+html);
		String msg= html.replaceAll("<(?!font|/font).*?>", "").replaceAll("\t|\r|\n", "");
//		System.out.println("msg=="+msg);
		return msg;
	//	return html.replaceAll("/style=\"(.*)\"/","").replaceAll("\t|\r|\n", "");
	}
	


	
	  public static String changeUrlAddress(String fromUrl){
		  
		  //SSOProperties prop = SSOConfig.getSSOProperties();
		  //String localDomain=prop.get("local.domain"); //local域名
		  String localDomain="";
		 try {
			   fromUrl=URLDecoder.decode(fromUrl, "utf-8");
			
			  if(fromUrl.contains("/jyhigh_zone/artificialGroup")){
				  return localDomain+"/jyhigh_zone/logoutArtificialGroup";
			  }
			  
			  else if(fromUrl.contains("/jyhigh_zone/invitation/")){
				  String[] str= fromUrl.split("/");
				  return  localDomain+"/jyhigh_zone/invitationeNoLogin/"+str[str.length-1];
			   }
			  
			  else if(fromUrl.contains("/jyhigh_zone/zone/")){
				  String[] str= fromUrl.split("/");
				  return  localDomain+"/jyhigh_zone/zoneNoLogin/"+str[str.length-1];
			   }
			  
			  else if(fromUrl.contains("/jyhigh_zone/fillInformation/")){
				  String[] str= fromUrl.split("/");
				  return  localDomain+"/jyhigh_zone/fillInformationNoLogin/"+str[str.length-1];
			   }else{
				   return localDomain+"/jyhigh_zone/logoutArtificialGroup";
			   }
		} catch (Exception e) {
			e.printStackTrace();
			 return localDomain+"/jyhigh_zone/logoutArtificialGroup";
			// TODO Auto-generated catch block
			
		}
		  
		
	  }
	  
	  public static void main(String[] args) {
		String fromUrl="http%3a%2f%2fwww.ifuhua.com.cn%2fjyhigh_zone%2finvitation%2f%2f2323R239FAF";
		String s=HtmlUtil.changeUrlAddress(fromUrl); 
		System.out.println(s);
	}
}
