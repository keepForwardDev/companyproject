package com.doctortech.framework.orm;
public interface IPagedCollection<T> extends Iterable<T> {
	/**
	 * 取得当前页码
	 * 
	 * @return
	 */
	int getCurrentPage();

	/**
	 * 设置当前页码
	 * 
	 * @param currentPage
	 */
	void setCurrentPage(int currentPage);

	/**
	 * 取得每页记录数
	 * 
	 * @return
	 */
	int getPageSize();

	/**
	 * 设置每页记录数
	 * 
	 * @return
	 */
	void setPageSize(int pageSize);

	/**
	 * 取得总页数
	 * 
	 * @return
	 */
	int getPageCount();

	/**
	 * 取得当前页码记录数
	 * 
	 * @return
	 */
	int getCount();

	/**
	 * 取得总记录数
	 * 
	 * @return
	 */
	int getVirtualCount();
}
