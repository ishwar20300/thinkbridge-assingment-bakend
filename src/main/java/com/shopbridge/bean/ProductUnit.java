/**
 * 
 */
package com.shopbridge.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "product_unit")
public class ProductUnit implements Serializable {

	private static final long serialVersionUID = -6350362369328216620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_unit_id")
	private Long productUnitId;

	@Column(name = "unit", length = 255)
	private String unit;

	@Column(name = "size", columnDefinition = "DECIMAL(10,2) DEFAULT '0'")
	private double size;

	@Column(name = "available_in")
	private String availableIn;

	@Column(name = "mrp", columnDefinition = "DECIMAL(10,2) DEFAULT '0.00'")
	private BigDecimal mrp = new BigDecimal(0);

	@Column(name = "selling_price", columnDefinition = "DECIMAL(10,2) DEFAULT '0.00'")
	private BigDecimal sellingPrice = new BigDecimal(0);

	@Column(name = "final_price", columnDefinition = "DECIMAL(10,2) DEFAULT '0.00'")
	private BigDecimal finalPrice = new BigDecimal(0);

	@Column(name = "taxinclusive", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean taxinclusive;

	@Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean status;

	@Column(name = "in_stock", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean inStock;

	@Column(name = "discount_value", columnDefinition = "DECIMAL(10,2) DEFAULT '0.00'")
	private BigDecimal discountValue;

	@Column(name = "discount_type")
	private Integer discountType;

	@Column(name = "stock_quantity")
	private Long stockQuantity;

	@Column(name = "sold_qty", columnDefinition = "DECIMAL(10,2) DEFAULT '0'")
	private double soldQty;

	@Column(name = "weight_in_gm")
	private Long weightInGm;

	@Column(name = "offerable", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean offerable;

	@Column(name = "tax_percent")
	private Integer taxPercent;
	

	@Column(name = "sku", length = 255)
	private String sku;


	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public Long getProductUnitId() {
		return productUnitId;
	}

	public void setProductUnitId(Long productUnitId) {
		this.productUnitId = productUnitId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getAvailableIn() {
		return availableIn;
	}

	public void setAvailableIn(String availableIn) {
		this.availableIn = availableIn;
	}

	public BigDecimal getMrp() {
		return mrp;
	}

	public void setMrp(BigDecimal mrp) {
		this.mrp = mrp;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public boolean isTaxinclusive() {
		return taxinclusive;
	}

	public void setTaxinclusive(boolean taxinclusive) {
		this.taxinclusive = taxinclusive;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}

	public Long getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public boolean isOfferable() {
		return offerable;
	}

	public void setOfferable(boolean offerable) {
		this.offerable = offerable;
	}

	public Integer getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Integer taxPercent) {
		this.taxPercent = taxPercent;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}



	public double getSoldQty() {
		return soldQty;
	}

	public void setSoldQty(double soldQty) {
		this.soldQty = soldQty;
	}

	public Long getWeightInGm() {
		return weightInGm;
	}

	public void setWeightInGm(Long weightInGm) {
		this.weightInGm = weightInGm;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	
	
}
