package com.utils;

import java.util.List;

/*
 * 封装分页信息  rows
 */
public class PageBean {
	// 当前要显示页面号
	private int pageNow;
	// 每一页面显示多少行
	private int pageSize = 4;
	// 总行数
	private int rows;
	// 总的页面数--需求计算
	private int pages;
	// 当面需要显示的数据
	private List<?> list;

	public int getPageNow() {
		return pageNow;
	}

	/**
	 * 
	 * @param pageNow
	 *            要显示的页面号 0 -1 1000
	 */
	public void setPageNow(int pageNow) {
		if (pageNow < 1)
			this.pageNow = 1;
		   
		else if (pageNow > this.pages) {// 要显示的页面号大于了总页面数
			this.pageNow = this.pages;
		} else {
			this.pageNow = pageNow;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRows() {
		return rows;
	}

	/**
	 * 设置总行数
	 * 
	 * @param rows
	 *            计算pages
	 */
	public void setRows(int rows) {
		this.rows = rows;
		if (this.rows % this.pageSize == 0) {// 整除
			this.pages = this.rows / this.pageSize;
		} else {
			this.pages = this.rows / this.pageSize + 1;
		}
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getPages() {
		return pages;
	}

}
