package com.shopbridge.service;
import com.shopbridge.bean.Category;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;

public interface CategoryService {

	public Response addnew(Category category) throws Exception;

	public Response update(Category category) throws Exception;
	
	public Response findById(Long id) throws Exception;

	public Response activeParentCategories(Filter filter) throws Exception;


	

	

}
