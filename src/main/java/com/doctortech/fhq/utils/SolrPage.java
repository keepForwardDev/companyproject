package com.doctortech.fhq.utils;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;



/**
 * 分页导航条
 *
 *
 */
public class SolrPage implements Serializable{

	protected String _actionUrl; //url

	protected int _totalCount; //总记录条数

	protected int _pageCount; //总页数

	protected int _currentPage; //当前页

	protected int _pageSize; //每页显示记录数

	protected HttpServletRequest _req;
	
	protected String _htmls;

	protected List<?> list;
	
	public SolrPage() {

	}
	
	

	/**
	 * 分页
	 * @param req
	 * @param totalCount 总记录数
	 * @param List 记录集
	 * @param currentPage 当前页
	 * @param pageSize 每页显示调试
	 */
	public SolrPage(HttpServletRequest req,List<?> list,int totalCount,int currentPage,int pageSize,boolean get) {
       
		_req=req;
		
		_actionUrl=req.getRequestURL().toString();  //url地址
		
		_htmls=renderHtml(req,totalCount,currentPage,pageSize,get);
		
	    this.list=list;
		
	}

	public SolrPage(HttpServletRequest req,Object list,int totalCount,int currentPage,int pageSize,boolean get) {
	       
		_req=req;
		
		_actionUrl=req.getRequestURL().toString();  //url地址
		
		_htmls=renderHtml(req,totalCount,currentPage,pageSize,get);
		
	    this.list=(List<?>) list;
		
	}

	
	
	/**
	 * 显示总记录数
	 */
	public String renderHtml(HttpServletRequest req,int totalCount,int currentPage,int pageSize,boolean get) {
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("<ul class=\"pagings\">");
		
		//计算总页数
		int pageCount = (int) (totalCount / pageSize);
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		
		_totalCount= totalCount;
		_pageCount=pageCount;
		_currentPage=currentPage;
		_pageSize=pageSize;
		
		if (getTotalCount()==0) {
			
			
			//sb.append("无数据");
			
		}else {
			
			
			if (getPageCount()!=1) {
				
				if (getCurrentPage()!=1) {
					sb.append(generateLink(new MapBuilder<String ,Object>().put("pageIndex", 1).put("text", "首页").build(), false));
					sb.append(generateLink(new MapBuilder<String,Object>().put("pageIndex", getCurrentPage()-1).put("text","上一页").build(), false));
					
				}
				
				
				if (getCurrentPage()-3>1) {
					sb.append(generateEllipsisLink());
					sb.append(generateLink(new MapBuilder<String,Object>().put("pageIndex", getCurrentPage()-3).put("text",getCurrentPage()-3).build(), false));
					
				}
				if (getCurrentPage()-2>1) {
					sb.append(generateLink(new MapBuilder<String,Object>().put("pageIndex", getCurrentPage()-2).put("text",getCurrentPage()-2).build(), false));
					
				}
				if (getCurrentPage()-1>1) {
					sb.append(generateLink(new MapBuilder<String,Object>().put("pageIndex", getCurrentPage()-1).put("text",getCurrentPage()-1).build(), false));
					
				}
				/**
				 * 当前第几页
				 */
				
				sb.append(generateLink(new MapBuilder<String,Object>().put("pageIndex","javascript:").put("css", "cur").put("text",getCurrentPage()).build(), true));
				
				
				if (getCurrentPage()+1<getPageCount()) {
					
					sb.append(generateLink(new MapBuilder<String,Object>().put("pageIndex", getCurrentPage()+1).put("text",getCurrentPage()+1).build(), false));
					
					
				}
				if (getCurrentPage()+2<getPageCount()) {
					
					sb.append(generateLink(new MapBuilder<String,Object>().put("pageIndex", getCurrentPage()+2).put("text",getCurrentPage()+2).build(), false));
					
					
				}
				if (getCurrentPage()+3<getPageCount()) {
					
					sb.append(generateLink(new MapBuilder<String,Object>().put("pageIndex", getCurrentPage()+3).put("text",getCurrentPage()+3).build(), false));
					sb.append(generateEllipsisLink());
					
				}
				
				
				if (getCurrentPage()!=getPageCount()) {
					
					
					sb.append(generateLink(new MapBuilder<String ,Object>().put("pageIndex",getCurrentPage()+1).put("text", "下一页").build(), false));
					sb.append(generateLink(new MapBuilder<String ,Object>().put("pageIndex",getPageCount()).put("text", "末页").build(), false));
					
				}
				
			}else {

			}

		}
		if(_totalCount>0){
			sb.append("<li><a href=\"javascript:void(0)\" >共"+_totalCount+"条记录</a></li>");
		}
	
		sb.append(generateForm(req, getActionUrl(), get));
		sb.append(generateJavaScript());
		sb.append("</ul>");
		
		
		return sb.toString();
		
		
	}
	
	


	private String generateLink(Map<String, Object> valueMap,boolean css,boolean ellipsis){
		
		
		String template="<li onclick=\"submitPageNavigation(${pageIndex})\"><a href=\"javascript:void(0)\" >${text}</a></li>";
		
		if (css) {
			
			template="<li class=\"${css}\" onclick=\"submitPageNavigation(${pageIndex})\"><a href=\"javascript:void(0)\">${text}</a></li>";
			
		}
		
		if (ellipsis) {
			
			return template="<li><a href=\"javascript:void(0)\">...</a></li>";
			
		}
		
		StrSubstitutor strSubstitutor= new StrSubstitutor(valueMap);
		
		return strSubstitutor.replace(template);
		
	}
	//正常link
	private String generateLink(Map<String, Object> valueMap,boolean css){
		
		
		return this.generateLink(valueMap, css, false);
		
	}
	
	//生成省略号link
	private String generateEllipsisLink(){
		
		
		return this.generateLink(null, false, true);
		
	}
	
	/**
	 * 生成参数form
	 * @return
	 */
	private String generateForm(HttpServletRequest req,String acitonUrl,boolean get){
		
		StringBuffer sb=new StringBuffer();
		
		sb.append(String.format("<form id=\"pageNavigationFormId\" style=\"display:none\" action=\"%s\" method=\"%s\">", acitonUrl,get?"get":"post"));
		
		
		Enumeration paramNames = req.getParameterNames();
		
		while ((paramNames != null) && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
	           if(StringUtils.equals("page", paramName)||StringUtils.equals("pageSize", paramName)) {
				
				continue;
			    }
				String[] values = req.getParameterValues(paramName);
				if ((values == null) || (values.length == 0)) {
					
				} else if (values.length > 1) {
					sb.append(String.format("<input type=\"hidden\" name=\"%s\" value=\"%s\"/>", paramName,values));
				} else {

					sb.append(String.format("<input type=\"hidden\" name=\"%s\" value=\"%s\"/>", paramName,values[0]));
				}
			
		}

		//默认首页
		sb.append(String.format("<input type=\"hidden\" name=\"page\" value=\"%s\"/>",getCurrentPage()));
		sb.append(String.format("<input type=\"hidden\" name=\"pageSize\" value=\"%s\"/>",getPageSize()));
		
		sb.append("</form>");
		
		return sb.toString();
	}
	/**
	 * 生成js
	 * @return
	 */
	protected String generateJavaScript(){
		
		StringBuffer sb=new StringBuffer();
		
		sb.append("<script type=\"text/javascript\">");
		
		sb.append("function submitPageNavigation(page){$(\"form input[name='page']\").val(page);$(\"#pageNavigationFormId\").submit();}");
		
		
		sb.append("</script>");
		
		return sb.toString();
	}
	

	public String getActionUrl() {
		return _actionUrl;
	}


	public void setActionUrl(String actionUrl) {
		_actionUrl = actionUrl;
	}


	public int getTotalCount() {
		return _totalCount;
	}


	public void setTotalCount(int totalCount) {
		_totalCount = totalCount;
	}


	public int getPageCount() {
		return _pageCount;
	}


	public void setPageCount(int pageCount) {
		_pageCount = pageCount;
	}


	public int getCurrentPage() {
		return _currentPage;
	}


	public void setCurrentPage(int currentPage) {
		_currentPage = currentPage;
	}


	public int getPageSize() {
		return _pageSize;
	}


	public void setPageSize(int pageSize) {
		_pageSize = pageSize;
	}


	public HttpServletRequest getReq() {
		return _req;
	}


	public void setReq(HttpServletRequest req) {
		_req = req;
	}


	public String get_htmls() {
		return _htmls;
	}


	public void set_htmls(String _htmls) {
		this._htmls = _htmls;
	}



	public List<?> getList() {
		return list;
	}



	public void setList(List<?> list) {
		this.list = list;
	}



	
	
	


	


	
}
