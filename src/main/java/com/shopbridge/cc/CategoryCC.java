package com.shopbridge.cc;

import java.util.List;

import com.shopbridge.bean.Category;


public class CategoryCC {

	private Long categoryId;

	private String name;

	private boolean parent;

	private Integer sequenceNo;

	private Long parentId;

	private Long productCategoryId;
	
	private String image;

	private boolean status;

	private List<Category> parentCategoryList;

	private List<Category> subCategoryList;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



	public List<Category> getParentCategoryList() {
		return parentCategoryList;
	}

	public void setParentCategoryList(List<Category> parentCategoryList) {
		this.parentCategoryList = parentCategoryList;
	}

	public List<Category> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<Category> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	
}
