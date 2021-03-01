package com.shopbridge.cc;

import java.util.ArrayList;
import java.util.Collection;

import com.shopbridge.bean.Brand;
import com.shopbridge.bean.ProductImages;
import com.shopbridge.bean.ProductInformation;
import com.shopbridge.bean.ProductUnit;

public class ProductCC {

	private Long productId;

	private String name;

	private boolean inStock;

	private boolean status;

	private Brand brand;

	private String image;

	private String brandName;

	private double soldQty;

	private Long brandId;

	private Long pramotionaProductId;

	private Long userFavouriteId;

	private boolean favourite;

	private boolean offerable;

	private boolean recommendation;

	private String productNo;

	private String sku;

	private ProductUnit attribute;
	
	private Collection<ProductImages> productImages = new ArrayList<ProductImages>();

	private Collection<ProductInformation> productInformation = new ArrayList<ProductInformation>();

	private Collection<ProductUnit> productUnit = new ArrayList<ProductUnit>();

	private Collection<Long> categoryIds = new ArrayList<Long>();

	private Collection<CategoryCC> category = new ArrayList<CategoryCC>();

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Collection<ProductImages> getProductImages() {
		return productImages;
	}

	public void setProductImages(Collection<ProductImages> productImages) {
		this.productImages = productImages;
	}

	public Collection<ProductInformation> getProductInformation() {
		return productInformation;
	}

	public void setProductInformation(Collection<ProductInformation> productInformation) {
		this.productInformation = productInformation;
	}

	public Collection<ProductUnit> getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(Collection<ProductUnit> productUnit) {
		this.productUnit = productUnit;
	}

	public Collection<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(Collection<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Collection<CategoryCC> getCategory() {
		return category;
	}

	public void setCategory(Collection<CategoryCC> category) {
		this.category = category;
	}


	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getPramotionaProductId() {
		return pramotionaProductId;
	}

	public void setPramotionaProductId(Long pramotionaProductId) {
		this.pramotionaProductId = pramotionaProductId;
	}

	public Long getUserFavouriteId() {
		return userFavouriteId;
	}

	public void setUserFavouriteId(Long userFavouriteId) {
		this.userFavouriteId = userFavouriteId;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public boolean isOfferable() {
		return offerable;
	}

	public void setOfferable(boolean offerable) {
		this.offerable = offerable;
	}

	public boolean isRecommendation() {
		return recommendation;
	}

	public void setRecommendation(boolean recommendation) {
		this.recommendation = recommendation;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the attribute
	 */
	public ProductUnit getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(ProductUnit attribute) {
		this.attribute = attribute;
	}

	public double getSoldQty() {
		return soldQty;
	}

	public void setSoldQty(double soldQty) {
		this.soldQty = soldQty;
	}
	
	
	

}
