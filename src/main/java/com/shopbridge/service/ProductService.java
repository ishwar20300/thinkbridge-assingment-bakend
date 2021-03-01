/**
 * 
 */
package com.shopbridge.service;

import com.shopbridge.bean.Product;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;

public interface ProductService {

	// Product
	public Response addnew(Product product) throws Exception;

	public Response update(Product product) throws Exception;

	public Response findById(Long id) throws Exception;

	public Response productDetailWithRelatedProd(Long productId, Long categoryId) throws Exception;

	public Response delete(Long id) throws Exception;

	public Response changeStatus(Long id) throws Exception;

	public Response changeStockStatus(Long id) throws Exception;

	public Response getAllProduct(Filter filter) throws Exception;

	public Response getAllActiveProduct(Filter filter) throws Exception;

	public Response productByCategory(Filter filter) throws Exception;

	// Image
	public Response deleteImage(Long id) throws Exception;

	// Information
	public Response deleteInformation(Long id) throws Exception;

	public Response changeInformationStatus(Long id) throws Exception;

	// Unit
	public Response deleteUnit(Long id) throws Exception;

	public Response changeUnitStatus(Long id) throws Exception;

	public Response changeUnitStockStatus(Long id) throws Exception;

	// Category
	public Response removeCategory(Long prodId, Long catId) throws Exception;

	public Response productReport(Filter filter) throws Exception;

}
