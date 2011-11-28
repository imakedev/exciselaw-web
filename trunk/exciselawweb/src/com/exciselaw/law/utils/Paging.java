package com.exciselaw.law.utils;

import java.io.Serializable;


/**
 * <p>Title: RTA Web Project</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author Chatchai
 * @version 1.0
 */
public class Paging implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6912297229674351418L;
	private int pageNo = 1;
	//TODO
	private int pageSize = 30;
	private int pageAnswerSize = 3;
	private int totalRecord = 0;
	private String orderBy = "";
	private String slotBy = "";

	public Paging(int pageNo, int pageSize, String orderBy) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.orderBy = orderBy;
		this.slotBy = "DESC";
	}

	public Paging() {
		pageNo = 1;
		//pageSize = 10;
		//TODO
		pageSize = 30;
		totalRecord = 0;
		orderBy = "";
		pageAnswerSize = 3;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public String getSlotBy() {
		return slotBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public void setSlotBy(String slotBy) {
		this.slotBy = slotBy;
	}

	public int getPageAnswerSize() {
		return pageAnswerSize;
	}

	public void setPageAnswerSize(int pageAnswerSize) {
		this.pageAnswerSize = pageAnswerSize;
	}

}
