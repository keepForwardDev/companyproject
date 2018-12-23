package com.doctortech.framework.orm;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.doctortech.framework.bean.PageData;
/**
 * sql 分页
 * @author gong
 * 2015年8月24日
 */
public class JpaSqlPagedCollection extends AbstractPagedCollection<Object[]> {
	/**
	 * 已经缓存的数据记录数
	 */
	private Integer cachedRecordCount;

	/**
	 * 已经缓存的数据记录集合
	 */
	private List<Object[]> cachedResult;

	
	private EntityManager entityManager;

	private String sql;
	
	private PageData pd;
	
	private List<Object> objs;
	private Map<String,Object> objsMap;
	
	public JpaSqlPagedCollection(EntityManager entityManager,String sql) {
		this.entityManager=entityManager;
		this.sql=sql;
	}
	public JpaSqlPagedCollection(EntityManager entityManager,String sql,List<Object> objs) {
		this.entityManager=entityManager;
		this.sql=sql;
		this.objs=objs;
	}
	public JpaSqlPagedCollection(EntityManager entityManager,String sql, Map<String,Object> objsMap) {
		this.entityManager=entityManager;
		this.sql=sql;
		this.objsMap=objsMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getVirtualCount() {
		if (cachedRecordCount == null) {
            Query query= entityManager.createNativeQuery("select count(1) from ( "+sql+" ) c ");
			if(objs!=null&&!objs.isEmpty()){
				for(int i=1;i<=objs.size();i++){
					query.setParameter(i, objs.get(i-1));
				}
			}
			if(objsMap!=null&&!objsMap.isEmpty()){
				for(Entry<String, Object> entry:objsMap.entrySet()){
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			cachedRecordCount = ((BigInteger)query.getSingleResult()).intValue();
		}

		return cachedRecordCount;
	}
	

	@Override
	public void setCurrentPage(int currentPage) {
		int oldValue = getCurrentPage();

		super.setCurrentPage(currentPage);

		if (oldValue != currentPage) {
			cachedResult = null;
		}
	}

	@Override
	public void setPageSize(int pageSize) {
		int oldValue = getPageSize();

		super.setPageSize(pageSize);

		if (oldValue != pageSize) {
			cachedResult = null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Object[]> iterator() {
		if (cachedResult == null) {
				
				int firstResult = (getCurrentPage()-1) * getPageSize();
				int maxResult = getPageSize();
				
				if ((firstResult + maxResult) > getVirtualCount()) {
					maxResult = getVirtualCount() - firstResult;
				}
				
				Query query=entityManager.createNativeQuery(sql);
				if(objs!=null&&!objs.isEmpty()){
					for(int i=1;i<=objs.size();i++){
						query.setParameter(i, objs.get(i-1));
					}
				}
				if(objsMap!=null&&!objsMap.isEmpty()){
					for(Entry<String, Object> entry:objsMap.entrySet()){
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResult);			
			
			cachedResult = (List<Object[]>)query.getResultList() ;
		}

		return cachedResult.iterator();
	}

	/**
	 * 刷新缓存
	 * <p />
	 * 因为该类只会查一次数据库，之后每次操作都会从缓存中拿取<br />
	 * 该操作可以消除缓存使其再次从数据库中拿数据
	 */
	public void refresh() {
		cachedRecordCount = null;
		cachedResult = null;

		if (getCurrentPage() >= getPageCount()) {
			setCurrentPage(getPageCount() > 0 ? getPageCount() - 1 : 0);
		}
	}


	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}


	@Override
	public List<Object[]> dataList() {
		if (cachedResult == null) {
			
			int firstResult = (getCurrentPage()-1) * getPageSize();
			int maxResult = getPageSize();
			
			if ((firstResult + maxResult) > getVirtualCount()) {
				maxResult = getVirtualCount() - firstResult>=0?( getVirtualCount() - firstResult):0;
			}
		
			Query query=entityManager.createNativeQuery(sql);
			if(objs!=null&&!objs.isEmpty()){
				for(int i=1;i<=objs.size();i++){
					query.setParameter(i, objs.get(i-1));
				}
			}
			if(objsMap!=null&&!objsMap.isEmpty()){
				for(Entry<String, Object> entry:objsMap.entrySet()){
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);			
		
		  cachedResult = (List<Object[]>)query.getResultList() ;
	   }

	   return cachedResult;
		
	}
	
	public PageData getPd() {
		return pd;
	}


	public void setPd(PageData pd) {
		this.pd = pd;
	}
}
