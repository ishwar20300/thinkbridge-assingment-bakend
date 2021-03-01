/**
 * 
 */
package com.shopbridge.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.shopbridge.cc.ProductCC;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -6350362369328216620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "in_stock", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean inStock;

	@Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean status;

	@Column(name = "offerable", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean offerable;

	@Column(name = "recommendation", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean recommendation;

	@Column(name = "product_no", length = 255)
	private String productNo;

	@Column(name = "sold_qty", columnDefinition = "DECIMAL(10,2) DEFAULT '0'")
	private double soldQty;

	@OneToOne(targetEntity = Brand.class)
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "product")
	private Collection<ProductImages> productImages = new ArrayList<ProductImages>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "product")
	private Collection<ProductInformation> productInformation = new ArrayList<ProductInformation>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "product")
	private Collection<ProductUnit> productUnit = new ArrayList<ProductUnit>();

	@Transient
	private Collection<Long> categoryIds = new ArrayList<Long>();

	@Transient
	private Long brandId;

	@Transient
	private List<ProductCC> relatedProduct;

	@Transient
	private String brandName;

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

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<ProductCC> getRelatedProduct() {
		return relatedProduct;
	}

	public void setRelatedProduct(List<ProductCC> relatedProduct) {
		this.relatedProduct = relatedProduct;
	}

	public boolean isOfferable() {
		return offerable;
	}

	public void setOfferable(boolean offerable) {
		this.offerable = offerable;
	}

	/**
	 * @return the recommendation
	 */
	public boolean isRecommendation() {
		return recommendation;
	}

	/**
	 * @param recommendation
	 *            the recommendation to set
	 */
	public void setRecommendation(boolean recommendation) {
		this.recommendation = recommendation;
	}

	/**
	 * @return the productNo
	 */
	public String getProductNo() {
		return productNo;
	}

	/**
	 * @param productNo the productNo to set
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public double getSoldQty() {
		return soldQty;
	}

	public void setSoldQty(double soldQty) {
		this.soldQty = soldQty;
	}


	
	
	
}
