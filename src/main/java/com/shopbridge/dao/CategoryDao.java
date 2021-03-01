package com.shopbridge.dao;

import java.util.List;
import com.shopbridge.bean.Category;
import com.shopbridge.constants.Filter;

public interface CategoryDao {
	
	public List<Category> activeParentCategories(Filter filter) throws Exception;


}
