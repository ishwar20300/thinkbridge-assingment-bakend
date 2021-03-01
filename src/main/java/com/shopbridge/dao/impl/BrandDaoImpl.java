package com.shopbridge.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shopbridge.bean.Brand;
import com.shopbridge.constants.Filter;
import com.shopbridge.dao.BrandDao;
import com.shopbridge.setting.ConnectionDao;

@Repository(value = "BrandDao")
@Transactional
public class BrandDaoImpl extends ConnectionDao implements BrandDao {

	@Override
	public List<Brand> allActiveBrand(Filter filter) throws Exception {
		List<Brand> brandList = new ArrayList<Brand>();
		Brand brand = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = "SELECT * FROM brand b WHERE b.`status` = 1 AND  b.brand_id IS NOT NULL ORDER BY name";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				brand = new Brand();
				brand.setBrandId(rs.getLong("brand_id"));
				brand.setImage(rs.getString("image"));
				brand.setName(rs.getString("name"));
				brand.setStatus(rs.getBoolean("status"));
				brandList.add(brand);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return brandList;
	}

}
