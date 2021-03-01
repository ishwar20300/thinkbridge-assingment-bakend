/**
 * 
 */
package com.shopbridge.bean;

import java.io.Serializable;

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
@Table(name = "product_information")
public class ProductInformation implements Serializable {

	private static final long serialVersionUID = -6350362369328216620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_information_id")
	private Long productInformationId;

	@Column(name = "title", length = 255)
	private String title;

	@Column(name = "description", columnDefinition = "LONGTEXT DEFAULT NULL")
	private String description;

	@Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean status;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public Long getProductInformationId() {
		return productInformationId;
	}

	public void setProductInformationId(Long productInformationId) {
		this.productInformationId = productInformationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	

}
