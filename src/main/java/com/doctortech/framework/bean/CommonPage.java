package com.doctortech.framework.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.doctortech.fhq.utils.SolrPage;
import com.doctortech.framework.orm.JpaSqlPagedCollection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
@ApiModel(value="分页对象",description="数据分页")
public class CommonPage {

	/**
	 * 总条数
	 */
	@ApiParam(hidden = true) 
	private long totalCount=0 ;
	
	/**
	 *总页数
	 */
	@ApiParam(hidden = true) 
	private int pageCount=0;
	
	/**
	 *每页条数
	 */
	@ApiModelProperty(value="每页条数",name="pageSize",example="20")
	private int pageSize=20;
	
	/**
	 * 当前页数 从第一页开始
	 */
	@ApiModelProperty(value="当前页数 ",name="currentPage",example="1")
	private int currentPage=1;
	
	/**
	 * 数据
	 */
	@ApiParam(hidden = true) 
	private Object list=new String() ;

	public CommonPage() {
		
		
		
	}
	public CommonPage(Page page) {
		super();
		this.totalCount = page.getTotalElements();
		this.pageCount=page.getTotalPages();
		this.pageSize = page.getSize();
		this.currentPage = page.getNumber()+1;
		this.list = page.getContent();

	}
	
	
	   public void update(Page page , Function mapper) {
	        this.totalCount=page.getTotalElements();
	        this.pageCount=page.getTotalPages();
	        this.list=page.getContent().parallelStream().map(mapper).collect(Collectors.toList());
	    }
	
	public CommonPage(Page page ,Transformer transformer) {
		super();
		this.totalCount = page.getTotalElements();
		this.pageCount=page.getTotalPages();
		this.pageSize = page.getSize();
		this.currentPage = page.getNumber()+1;
		
		List temp=new ArrayList();
		
		for (Object object : page.getContent()) {
			
			Object obj=transformer.transform(object);
			
			if(obj!=null){
				temp.add(obj);
			}
			
		}
		
		this.list=temp;		
		
	}

	public CommonPage(Page page ,Object list) {
		super();
		this.totalCount = page.getTotalElements();
		this.pageCount=page.getTotalPages();
		this.pageSize = page.getSize();
		this.currentPage = page.getNumber()+1;
		this.list=list;		
		
	}
	
	public CommonPage(JpaSqlPagedCollection jpaSqlPagedCollection ,Object list) {
		super();
		this.totalCount = (int) jpaSqlPagedCollection.getVirtualCount();
		this.pageCount=jpaSqlPagedCollection.getPageCount();
		this.pageSize =jpaSqlPagedCollection.getPageSize();
		this.currentPage = jpaSqlPagedCollection.getCurrentPage();
		this.list=list;		
		
	}
	
	public CommonPage(SolrPage page) {
		super();
		this.totalCount = (int) page.getTotalCount();
		this.pageCount=page.getPageCount();
		this.pageSize =page.getPageSize();
		this.currentPage = page.getCurrentPage();
		this.list=page.getList();		
		
	}
	
	
	public CommonPage(long totalCount, int pageCount, int pageSize, int currentPage, Object list) {
		super();
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.list = list;
	}

	public CommonPage(long totalCount,int currentPage,int pageSize,Object list){
		super();
		this.totalCount=totalCount;
		//计算总页数
		int pageCount = (int) (totalCount / pageSize);
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		this.pageCount=pageCount;
		this.pageSize=pageSize;
		this.currentPage=currentPage;
		this.list=list;
		
	}
	
	public long getTotalCount() {
		if(this.totalCount<0){
			return 0;
		}
		if(this.totalCount>Long.MAX_VALUE){
			return Long.MAX_VALUE;
		}
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
	       //计算总页数
        int pageCount = (int) (totalCount / pageSize);
        if (totalCount % pageSize > 0) {
            pageCount++;
        }
        this.pageCount=pageCount;
		this.totalCount = totalCount;
		
	}

	public int getPageCount() {
		if(this.pageCount<0){
			this.pageCount=0;
		}
		if(this.pageCount>Integer.MAX_VALUE){
			this.pageCount=Integer.MAX_VALUE;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		if(this.pageSize<0){
			this.pageSize=0;
		}
		if(this.pageSize>Integer.MAX_VALUE){
			this.pageSize=Integer.MAX_VALUE;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		/*if(this.pageCount>0&&this.currentPage>this.pageCount){
			return this.pageCount;
		}*/
		if(this.currentPage<=0||this.currentPage>Integer.MAX_VALUE){
			this.currentPage=1;
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "CommonPage [totalCount=" + totalCount + ", pageCount=" + pageCount + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", list=" + list + "]";
	}
	
	
	
	
}
