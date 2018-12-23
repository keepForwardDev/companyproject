package com.doctortech.framework.orm;

import java.util.Iterator;
import java.util.List;


public abstract class AbstractPagedCollection<T> implements IPagedCollection<T> {
	/**
	 * 当前页码
	 */
	private int	_currentPage = 1;
	/**
	 * 每页记录数
	 */
	private int	_pageSize	= 1;

	public abstract int getVirtualCount();

	public int getCount() {
		if ((getCurrentPage() + 1) * getPageSize() > getVirtualCount()) {
			return getPageSize() - (((getCurrentPage() + 1) * getPageSize()) - getVirtualCount());
		} else {
			return getPageSize();
		}
	}

	public int getPageCount() {
		if (getVirtualCount() <= 0) {
			return 0;
		} else {
			return getPageSize() >= getVirtualCount() ? 1
			        : (getVirtualCount() % getPageSize()) > 0 ? (getVirtualCount() / getPageSize() + 1)
			                : (getVirtualCount() / getPageSize());
		}
	}

	/**
	 * 不发查询数量的SQL出来
	 * 2011-9-29 11:31:22
	 */
	public int getPageSize() {
		return _pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		}

		_pageSize = pageSize;
	}

	public int getCurrentPage() {
		return _currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0) {
			currentPage = 1;
		} else if (currentPage > getPageCount()) {
			currentPage = getPageCount()==0?1:getPageCount();
		}
		_currentPage = currentPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 * 
	 * @return 当前页的数据集的迭代
	 */
	public abstract Iterator<T> iterator();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @return list
	 */
	public abstract List<T> dataList();
	
}
