package com.shopbridge.dao;

import java.util.List;

import com.shopbridge.bean.ProductImages;
import com.shopbridge.bean.ProductInformation;
import com.shopbridge.bean.ProductUnit;
import com.shopbridge.cc.CategoryCC;
import com.shopbridge.cc.ProductCC;
import com.shopbridge.constants.Filter;

public interface ProductDao {

	public List<CategoryCC> productCategories(Long productId) throws Exception;
	
	public List<ProductCC> getAllProduct(Filter filter) throws Exception;
	
	public List<ProductCC> getAllActiveProduct(Filter filter) throws Exception;
	
	public List<ProductCC> relatedProduct(Long categoryId) throws Exception;
	
	public List<ProductCC> productByCategory(Filter filter) throws Exception;
	
	public List<ProductUnit> productUnit(Long prodId, boolean status) throws Exception;
	
	public List<ProductInformation> productInformation(Long prodId, boolean status) throws Exception;
	
	public List<ProductImages> productImages(Long prodId) throws Exception;
	
	public List<ProductCC> favouriteList(Filter filter) throws Exception;
	
	public List<ProductCC> recommandedProduct(Filter filter) throws Exception;
	
	public Long allProductCount(Filter filter) throws Exception;
	
	
	public List<ProductCC> productReport(Filter filter) throws Exception;
	
	
	public Long productReportCount(Filter filter) throws Exception;
	
	
}
