package com.shopbridge.dao;

import java.util.List;

import com.shopbridge.bean.Brand;
import com.shopbridge.constants.Filter;

public interface BrandDao {

	public List<Brand> allActiveBrand(Filter filter) throws Exception;

}
