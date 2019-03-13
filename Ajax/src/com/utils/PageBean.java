package com.utils;

import java.util.List;

/*
 * ��װ��ҳ��Ϣ  rows
 */
public class PageBean {
	// ��ǰҪ��ʾҳ���
	private int pageNow;
	// ÿһҳ����ʾ������
	private int pageSize = 4;
	// ������
	private int rows;
	// �ܵ�ҳ����--�������
	private int pages;
	// ������Ҫ��ʾ������
	private List<?> list;

	public int getPageNow() {
		return pageNow;
	}

	/**
	 * 
	 * @param pageNow
	 *            Ҫ��ʾ��ҳ��� 0 -1 1000
	 */
	public void setPageNow(int pageNow) {
		if (pageNow < 1)
			this.pageNow = 1;
		   
		else if (pageNow > this.pages) {// Ҫ��ʾ��ҳ��Ŵ�������ҳ����
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
	 * ����������
	 * 
	 * @param rows
	 *            ����pages
	 */
	public void setRows(int rows) {
		this.rows = rows;
		if (this.rows % this.pageSize == 0) {// ����
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
