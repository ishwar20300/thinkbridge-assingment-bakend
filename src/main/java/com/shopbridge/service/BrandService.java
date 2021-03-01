/**
 * 
 */
package com.shopbridge.service;

import com.shopbridge.bean.Brand;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;

public interface BrandService {
	
	public Response addnew(Brand brand) throws Exception;

	public Response update(Brand brand) throws Exception;

	public Response findById(Long id) throws Exception;

	public Response allActiveBrand(Filter filter) throws Exception;

	
}
