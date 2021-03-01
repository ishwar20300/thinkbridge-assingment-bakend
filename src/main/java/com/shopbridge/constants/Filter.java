package com.shopbridge.constants;

import java.util.List;
public class Filter {
	
	private long page;
	
	private long size;
	
	private String searchBy;
	
	private String subSearchBy;
	
	private String fromDate;
	
	private String toDate;
	
	private Long parentId;
	
	private Long userId;
	
	private Long categoryId;
	
	private List<Long> ids;
	
	private boolean status;
	
	private int byNumStatus;
	
	private int stock;

	private Integer paymentType;
	
	private Integer paymentStatus;
	
	private int stockQuanity;
	
	
	private String orderBy;
	
	
	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}


	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getSubSearchBy() {
		return subSearchBy;
	}

	public void setSubSearchBy(String subSearchBy) {
		this.subSearchBy = subSearchBy;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the ids
	 */
	public List<Long> getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the byNumStatus
	 */
	public int getByNumStatus() {
		return byNumStatus;
	}

	/**
	 * @param byNumStatus the byNumStatus to set
	 */
	public void setByNumStatus(int byNumStatus) {
		this.byNumStatus = byNumStatus;
	}

	/**
	 * @return the paymentType
	 */
	public Integer getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the paymentStatus
	 */
	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the stockQuanity
	 */
	public int getStockQuanity() {
		return stockQuanity;
	}

	/**
	 * @param stockQuanity the stockQuanity to set
	 */
	public void setStockQuanity(int stockQuanity) {
		this.stockQuanity = stockQuanity;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	


}
