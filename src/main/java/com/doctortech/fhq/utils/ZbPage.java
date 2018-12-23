package com.doctortech.fhq.utils;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;



/**
 * 众包分页导航条
 *
 *
 */
public class ZbPage implements Serializable{

	protected String _actionUrl; //url

	protected int _totalCount; //总记录条数

	protected int _pageCount; //总页数

	protected int _currentPage; //当前页

	protected int _pageSize; //每页显示记录数

	protected HttpServletRequest _req;
	
	protected String _htmls;

	protected List<?> list;
	
	
	
	public ZbPage() {

	}
	
	

	/**
	 * 分页
	 * @param req
	 * @param totalCount 总记录数
	 * @param List 记录集
	 * @param currentPage 当前页
	 * @param pageSize 每页显示调试
	 */
	public ZbPage(HttpServletRequest req,List<?> list,int totalCount,int currentPage,int pageSize,boolean get) {
       
		_req=req;
		_actionUrl=req.getRequestURL().toString();  //url地址
		_htmls=renderHtml(req,totalCount,currentPage,pageSize,get);
		
	    this.list=list;
		
	}

	public ZbPage(HttpServletRequest req,Object list,int totalCount,int currentPage,int pageSize,boolean get) {
	       
		_req=req;
		
		_actionUrl=req.getRequestURL().toString();  //url地址
		
		_htmls=renderHtml(req,totalCount,currentPage,pageSize,get);
		
	    this.list=(List<?>) list;
		
	}
	
  public ZbPage(HttpServletRequest req,Page page) {
		
	     _req=req;
		
	     _actionUrl=req.getRequestURL().toString();  //url地址
		
		_htmls=renderHtml(req,(int)page.getTotalElements(),page.getNumber()+1,page.getSize(),true);
		
	    this.list=(List<?>) page.getContent();
	
	}
	
	public ZbPage(HttpServletRequest req, Page page, Object list, boolean get) {
	       
		_req=req;
		
		_actionUrl=req.getRequestURL().toString();  //url地址
		
		_htmls=renderHtml(req,(int)page.getTotalElements(),page.getNumber()+1,page.getSize(),get);
		
	    this.list=(List<?>) list;
		
	}

	
	public String renderHtml(HttpServletRequest req,int totalCount,int currentPage,int pageSize,boolean get){
		
		/**
		 * /**
		 *               <div class="pag">
                        <nav class="navs">
                            <ul class="pagingp">
                                <li><a href="javascript:void(0)"><i class="arrow arrow_right">&lt;</i>上一页 </a></li>
                                <li><a href="javascript:void(0)" class="numbers">01</a></li>
                                <li><a href="javascript:void(0)" class="dian">...</a></li>
                                <li><a href="javascript:void(0)" class="numbers bgCol">02</a></li>
                                <li><a href="javascript:void(0)" class="numbers">03</a></li>
                                <li><a href="javascript:void(0)" class="numbers">04</a></li>
                                <li><a href="javascript:void(0)" class="numbers">05</a></li>
                                <li><a href="javascript:void(0)" class="numbers">06</a></li>
                                <li><a href="javascript:void(0)" class="dian">...</a></li>
                                <li><a href="javascript:void(0)" class="numbers">220</a></li>  
                                <li><a href="javascript:void(0)">下一页<i class="arrow">&gt;</i></a></li>
                            </ul>
                        </nav>
                        <div class="jumping">
                            <span class="jumping_whole">共<em>226</em>页<i>跳转至</i></span>
                            <input type="text" class="pages_more" />
                            <span class="pages_tips">页</span>
                            <a href="#" class="confire_btn">确定</a>
                        </div>
                    </div>
		 */
			//计算总页数
			int pageCount = (int) (totalCount / pageSize);
			if (totalCount % pageSize > 0) {
				pageCount++;
			}
			
			_totalCount= totalCount;
			_pageCount=pageCount;
			_currentPage=currentPage;
			_pageSize=pageSize;
			
			StringBuffer sb=new StringBuffer();
			if(_totalCount==0){ //无记录
				return "";
			}
			sb.append("<div class=\"pag\">\n");
			sb.append("<nav class=\"navs\">\n");
			sb.append("<ul class=\"pagingp\">\n");
			//if(this._currentPage>1){
			 sb.append("<li onclick=\"javascript:zbPreviousPage("+(this._currentPage-1)+")\"><a href=\"javascript:void(0)\" class=\"first\">上一页 </a></li>\n");
		//	}
		/*	if(_pageCount<=9){//页数小于9页全部显示
				for(int i=1; i<=_pageCount;i++){
					if(getCurrentPage()==i)
						sb.append("<li><a href=\"javascript:void(0)\" class=\"numbers bgCol\">"+formatPageNo(getCurrentPage())+"</a></li>\n");
					 else 
						 sb.append("<li onclick=\"zbGotoPage("+i+")\"><a href=\"javascript:void(0)\" class=\"numbers\">"+formatPageNo(i)+"</a></li>\n");
				}
            }else{*///共100页，当前11页
            	int showTag = 5; //分页标签显示数量
            	int startTag = 1;
				if(getCurrentPage()>=showTag){
					startTag = getCurrentPage()-2; 
				}
				int endTag = startTag+showTag-1;
				
				//当前页大于5，如果页数不够，开始补页
				if(startTag>1&&getPageCount()-getCurrentPage()<2){ //当前7, start:5,endTag:9,pageCount:8
					 int minuxPageNum=getPageCount()-getCurrentPage()<=0?2:getPageCount()-getCurrentPage();
					 
					startTag=startTag-minuxPageNum;
				}
				
				if(startTag>1){
					sb.append("<li onclick=\"zbGotoPage(1)\"><a href=\"javascript:void(0)\" class=\"numbers\">01</a></li>\n");
					sb.append("<li><a href=\"javascript:void(0)\" class=\"dian\">...</a></li>\n");
				}
				
				for(int i=startTag; i<=getPageCount() && i<=endTag; i++){
					if(getCurrentPage()==i)
						sb.append("<li><a href=\"javascript:void(0)\" class=\"numbers bgCol\">"+formatPageNo(getCurrentPage())+"</a></li>\n");
					 else
						sb.append("<li  onclick=\"zbGotoPage("+i+")\"><a class=\"numbers\" href=\"javascript:void(0)\">"+formatPageNo(i)+"</a></li>\n");	
				}
			
				if(endTag<this._pageCount){
					sb.append("<li><a href=\"javascript:void(0)\" class=\"dian\">...</a></li>\n");
					sb.append("<li onclick=\"zbGotoPage("+this._pageCount+");\"><a href=\"javascript:void(0)\" class=\"numbers\">"+formatPageNo(this._pageCount)+"</a></li>\n");
				}
				
         //   }   
			//if(this._currentPage<this._pageCount){
				sb.append("<li onclick=\"javascript:zbNextPage("+(this._currentPage+1)+");\"><a href=\"javascript:void(0)\" class=\"last\">下一页</a></li>\n");
					
		//	}
			sb.append("</ul>\n");
			sb.append("</nav>\n");
		    
           
			sb.append("<div class=\"jumping\">\n");
			sb.append("<span class=\"jumping_whole\">共<em>"+this._pageCount+"</em>页<i>跳转至</i></span>\n");
			sb.append("<input type=\"text\" id=\"zbCommonGoPageNoNum\"   class=\"pages_more\"/>");
			sb.append("<span class=\"pages_tips\">页</span>");
			sb.append("<button class=\"confire_btn\" onclick=\"javascript:zbSubmitBtn();\">确定</button>");
			sb.append("</div>\n");
            
			
			sb.append(generateForm(req, getActionUrl(), false));
			sb.append(generateCommonPageScript());
				
			sb.append("</div>\n");
			return sb.toString();
			
	
		
		
	}
	
	/**
	 * 格式化页码
	 * @param curPage
	 * @return
	 */
	public String formatPageNo(int curPage){
		if(curPage<10){
			return "0"+curPage;
		}else{
			return String.valueOf(curPage);
		}
	}
	
	/**
	 * 分页的js
	 */
	public String generateCommonPageScript(){
        StringBuffer sb=new StringBuffer();
		 
		sb.append("<script type=\"text/javascript\">");
		
		//前一页
		sb.append("function zbPreviousPage(page){\n"
				+ "if("+this._currentPage+"<=1){return false;}\n"
				+ "if(page<1){page=1;}\n"
				+ "$(\"#pageNavigationFormId input[name='currentPage']\").val(page);\n"
				+ "$(\"#pageNavigationFormId\").submit();}\n");
		
		//点击页
		sb.append("function zbGotoPage(page){$(\"#pageNavigationFormId input[name='currentPage']\").val(page);$(\"#pageNavigationFormId\").submit();}");
		
		//后一页
		sb.append("function zbNextPage(page){\n"
				+ "if("+this._currentPage+">="+this._pageCount+"){return false;}\n"
				+ " if(page >"+this._pageCount+"){page="+this._pageCount+";}\n"
				+ "$(\"#pageNavigationFormId input[name='currentPage']\").val(page);\n"
				+ "$(\"#pageNavigationFormId\").submit();}\n");
		
		//跳转
		sb.append("function zbSubmitBtn(){\n"
				+ "var page=$(\"#zbCommonGoPageNoNum\").val();if(page>="+this._pageCount+"){page="+this._pageCount+"};var reg = /^\\+?[1-9]\\d*$/;"
				+ "if(page==''){alert(\"页码错误！\");return false;}"
				+ "if(!reg.test(page)){alert(\"页码错误！\");return false;}"
				+ "$(\"#pageNavigationFormId input[name='currentPage']\").val(page);\n"
				+ "$(\"#pageNavigationFormId\").submit();}\n");
		
		sb.append("</script>");
		
		return sb.toString();
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
	           if(StringUtils.equals("currentPage", paramName)||StringUtils.equals("pageSize", paramName)) {
				
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
		sb.append(String.format("<input type=\"hidden\" name=\"currentPage\" value=\"%s\"/>",getCurrentPage()));
		sb.append(String.format("<input type=\"hidden\" name=\"pageSize\" value=\"%s\"/>",getPageSize()));
		
		sb.append("</form>");
		
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



	@Override
	public String toString() {
		return "ZbPage [_actionUrl=" + _actionUrl + ", _totalCount=" + _totalCount + ", _pageCount=" + _pageCount
				+ ", _currentPage=" + _currentPage + ", _pageSize=" + _pageSize + ", _req=" + _req + ", _htmls="
				+ _htmls + ", list=" + list + "]";
	}



	
	
	


	


	
}
