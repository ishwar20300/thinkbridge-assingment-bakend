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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = -6350362369328216620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "parent", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean parent;

	@Column(name = "sequence_no", nullable = true)
	private Integer sequenceNo;

	@Column(name = "parent_id", nullable = true)
	private Long parentId;

	@Column(name = "image")
	private String image;

	@Column(name = "status", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean status;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "category")
	private Collection<CategoryImages> images = new ArrayList<CategoryImages>();

	@Transient
	private String base64;
	
	@Transient
	private Long productCount;
	
	@Transient
	private Long subCatCount;
	
	@Transient
	private String imageName;

	@Transient
	private List<Category> subCategory;

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

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}


	public List<Category> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

	public Long getSubCatCount() {
		return subCatCount;
	}

	public void setSubCatCount(Long subCatCount) {
		this.subCatCount = subCatCount;
	}

	public Collection<CategoryImages> getImages() {
		return images;
	}

	public void setImages(Collection<CategoryImages> images) {
		this.images = images;
	}


	
	

}
