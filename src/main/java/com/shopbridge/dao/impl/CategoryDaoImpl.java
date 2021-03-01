package com.shopbridge.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.shopbridge.bean.Category;
import com.shopbridge.constants.Filter;
import com.shopbridge.dao.CategoryDao;
import com.shopbridge.setting.ConnectionDao;

@Repository(value = "CategoryDao")
@Transactional
public class CategoryDaoImpl extends ConnectionDao implements CategoryDao {



	@Override
	public List<Category> activeParentCategories(Filter filter) throws Exception {
		List<Category> categoryList = new ArrayList<Category>();
		Category category = null;
		Connection conn = null;
		Filter filter1 = null;
		try {
			conn = getDataSource().getConnection();
			String sql = "SELECT * FROM category c WHERE c.`status` = 1 AND c.parent = 0 AND c.parent_id IS NULL "
					+ " ORDER BY c.sequence_no";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				category = new Category();
				category.setCategoryId(rs.getLong("category_id"));
				category.setImage(rs.getString("image"));
				category.setName(rs.getString("name"));
				category.setParent(rs.getBoolean("parent"));
				category.setParentId(rs.getLong("parent_id"));
				category.setSequenceNo(rs.getInt("sequence_no"));
				category.setStatus(rs.getBoolean("status"));
				filter1 = new Filter();
				filter1.setParentId(category.getCategoryId());
				filter1.setStatus(true);
				categoryList.add(category);
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return categoryList;
	}

	

}
