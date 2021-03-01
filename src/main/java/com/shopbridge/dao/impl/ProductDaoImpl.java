package com.shopbridge.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shopbridge.bean.Brand;
import com.shopbridge.bean.ProductImages;
import com.shopbridge.bean.ProductInformation;
import com.shopbridge.bean.ProductUnit;
import com.shopbridge.cc.CategoryCC;
import com.shopbridge.cc.ProductCC;
import com.shopbridge.constants.Filter;
import com.shopbridge.dao.ProductDao;
import com.shopbridge.setting.ConnectionDao;
import com.shopbridge.setting.RepositoryDao;


@Repository(value = "productDao")
@Transactional
public class ProductDaoImpl extends ConnectionDao implements ProductDao {

	private final RepositoryDao repositoryDao;

	public ProductDaoImpl(RepositoryDao repositoryDao) {
		super();
		this.repositoryDao = repositoryDao;
	}

	@Override
	public List<ProductCC> getAllProduct(Filter filter) throws Exception {
		List<ProductCC> productList = new ArrayList<ProductCC>();
		ProductCC product = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT p.* FROM product p INNER JOIN product_category pc ON p.product_id = pc.product_id  "
					+ " INNER JOIN category c ON c.category_id = pc.category_id  INNER JOIN brand b ON p.brand_id = b.brand_id  "
					+ " WHERE p.product_id IS NOT NULL ";
			if (filter.getSearchBy() != null && !filter.getSearchBy().isEmpty()) {
				sql += " AND p.name LIKE  ? OR  c.name LIKE ? OR b.name LIKE ? ";
			}

			sql += " GROUP BY p.product_id ORDER BY p.product_id DESC ";
			if (filter.getPage() > 0 && filter.getSize() > 0) {
				sql += " LIMIT  ?,? ";
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			if (filter.getSearchBy() != null && !filter.getSearchBy().isEmpty()) {
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
			}
			if (filter.getPage() > 0 && filter.getSize() > 0) {
				ps.setLong(++count, (filter.getPage() - 1) * filter.getSize());
				ps.setLong(++count, filter.getSize());
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductCC();
				product.setProductId(rs.getLong("product_id"));
				product.setName(rs.getString("name"));
				product.setStatus(rs.getBoolean("status"));
				product.setInStock(rs.getBoolean("in_stock"));
				product.setSoldQty(rs.getDouble("sold_qty"));
				product.setProductNo(rs.getString("product_no"));
				product.setBrandId(rs.getLong("brand_id"));
				product.setOfferable(rs.getBoolean("offerable"));
				product.setRecommendation(rs.getBoolean("recommendation"));
				if (product.getBrandId() != null) {
					Brand brand = repositoryDao.findById(Brand.class, product.getBrandId());
					product.setBrandName(brand.getName());
				}

				product.setCategory(productCategories(product.getProductId()));
				product.setProductImages(productImages(product.getProductId()));
				product.setProductInformation(productInformation(product.getProductId(), false));
				product.setProductUnit(productUnit(product.getProductId(), false));
				List<ProductImages> prodImage = productImages(product.getProductId());
				if (prodImage != null && prodImage.size() > 0) {
					product.setImage(prodImage.get(0).getImage());
				}
				productList.add(product);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productList;
	}

	@Override
	public Long allProductCount(Filter filter) throws Exception {
		Long allRecordCount = 0L;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT Count(DISTINCT p.product_id ) as count FROM product p INNER JOIN product_category pc ON p.product_id = pc.product_id  "
					+ " INNER JOIN category c ON c.category_id = pc.category_id  INNER JOIN brand b ON p.brand_id = b.brand_id  "
					+ " WHERE p.product_id IS NOT NULL ";
			if (filter.getSearchBy() != null && !filter.getSearchBy().isEmpty()) {
				sql += " AND p.name LIKE  ? OR  c.name LIKE ? OR b.name LIKE ? ";
			}



			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			if (filter.getSearchBy() != null && !filter.getSearchBy().isEmpty()) {
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
			}
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				allRecordCount = rs.getLong("count");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return allRecordCount;
	}

	@Override
	public List<CategoryCC> productCategories(Long productId) throws Exception {
		List<CategoryCC> categoryList = new ArrayList<CategoryCC>();
		CategoryCC category = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = "SELECT * FROM product_category pc INNER JOIN category c ON pc.category_id = c.category_id "
					+ " WHERE pc.product_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, productId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				category = new CategoryCC();
				category.setCategoryId(rs.getLong("category_id"));
				category.setProductCategoryId(rs.getLong("product_category_id"));
				category.setImage(rs.getString("image"));
				category.setName(rs.getString("name"));
				category.setParent(rs.getBoolean("parent"));
				category.setParentId(rs.getLong("parent_id"));
				category.setSequenceNo(rs.getInt("sequence_no"));
				category.setStatus(rs.getBoolean("status"));
				categoryList.add(category);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return categoryList;
	}

	public List<ProductImages> productImages(Long prodId) throws Exception {
		List<ProductImages> productImagesList = new ArrayList<ProductImages>();
		ProductImages productImages = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = "SELECT * FROM product_images pi WHERE pi.product_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, prodId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productImages = new ProductImages();
				productImages.setImageId(rs.getLong("image_id"));
				productImages.setImage(rs.getString("name"));
				productImagesList.add(productImages);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productImagesList;
	}

	public List<ProductInformation> productInformation(Long prodId, boolean status) throws Exception {
		List<ProductInformation> productInformationList = new ArrayList<ProductInformation>();
		ProductInformation productInformation = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT * FROM product_information pi WHERE pi.product_id = ? ";
			if (status)
				sql += " AND pi.`status` = 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, prodId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productInformation = new ProductInformation();
				productInformation.setProductInformationId(rs.getLong("product_information_id"));
				productInformation.setTitle(rs.getString("title"));
				productInformation.setDescription(rs.getString("description"));
				productInformation.setStatus(rs.getBoolean("status"));
				productInformationList.add(productInformation);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productInformationList;
	}

	public List<ProductUnit> productUnit(Long prodId, boolean status) throws Exception {
		List<ProductUnit> productUnitList = new ArrayList<ProductUnit>();
		ProductUnit productUnit = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = "SELECT * FROM product_unit pu WHERE pu.product_id = ? ";
			if (status)
				sql += " AND pu.`status` = 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, prodId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productUnit = new ProductUnit();
				productUnit.setProductUnitId(rs.getLong("product_unit_id"));
				productUnit.setSize(rs.getDouble("size"));
				productUnit.setUnit(rs.getString("unit"));
				productUnit.setAvailableIn(rs.getString("available_in"));
				productUnit.setMrp(rs.getBigDecimal("mrp"));
				productUnit.setSellingPrice(rs.getBigDecimal("selling_price"));
				productUnit.setFinalPrice(rs.getBigDecimal("final_price"));
				productUnit.setAvailableIn(rs.getString("available_in"));
				productUnit.setStatus(rs.getBoolean("status"));
				productUnit.setInStock(rs.getBoolean("in_stock"));
				productUnit.setDiscountType(rs.getInt("discount_type"));
				productUnit.setDiscountValue(rs.getBigDecimal("discount_value"));
				productUnit.setStockQuantity(rs.getLong("stock_quantity"));
				productUnit.setTaxPercent(rs.getInt("tax_percent"));
				productUnit.setOfferable(rs.getBoolean("offerable"));
				productUnit.setSoldQty(rs.getDouble("sold_qty"));
				productUnitList.add(productUnit);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productUnitList;
	}

	@Override
	public List<ProductCC> productByCategory(Filter filter) throws Exception {
		List<ProductCC> productList = new ArrayList<ProductCC>();
		ProductCC product = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT * FROM product p  INNER JOIN product_category pc "
					+ " ON p.product_id = pc.product_id WHERE  pc.category_id = ? ";
			if (filter.isStatus()) {
				sql += " AND p.`status` = 1";
			}
			sql += " ORDER BY p.name";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, filter.getCategoryId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductCC();
				product.setProductId(rs.getLong("product_id"));
				product.setName(rs.getString("name"));
				product.setStatus(rs.getBoolean("status"));
				product.setInStock(rs.getBoolean("in_stock"));
				product.setSoldQty(rs.getDouble("sold_qty"));
				product.setBrandId(rs.getLong("brand_id"));
				if (product.getBrandId() != null) {
					Brand brand = repositoryDao.findById(Brand.class, product.getBrandId());
					product.setBrandName(brand.getName());
				}
				product.setProductImages(productImages(product.getProductId()));
				product.setProductInformation(productInformation(product.getProductId(), true));
				product.setProductUnit(productUnit(product.getProductId(), true));
				List<ProductImages> prodImage = productImages(product.getProductId());
				if (prodImage != null && prodImage.size() > 0) {
					product.setImage(prodImage.get(0).getImage());
				}

				productList.add(product);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productList;
	}

	@Override
	public List<ProductCC> relatedProduct(Long categoryId) throws Exception {
		List<ProductCC> productList = new ArrayList<ProductCC>();
		ProductCC product = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT * FROM product p  INNER JOIN product_category pc "
					+ " ON p.product_id = pc.product_id WHERE  pc.category_id = ?  AND p.`status` = 1";
			sql += " ORDER BY p.name";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, categoryId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductCC();
				product.setProductId(rs.getLong("product_id"));
				product.setName(rs.getString("name"));
				product.setStatus(rs.getBoolean("status"));
				product.setInStock(rs.getBoolean("in_stock"));
				product.setSoldQty(rs.getDouble("sold_qty"));
				product.setProductNo(rs.getString("product_no"));
				product.setBrandId(rs.getLong("brand_id"));
				product.setOfferable(rs.getBoolean("offerable"));
				product.setRecommendation(rs.getBoolean("recommendation"));
				if (product.getBrandId() != null) {
					Brand brand = repositoryDao.findById(Brand.class, product.getBrandId());
					product.setBrandName(brand.getName());
				}
				product.setProductImages(productImages(product.getProductId()));
				product.setProductInformation(productInformation(product.getProductId(), true));
				product.setProductUnit(productUnit(product.getProductId(), true));
				List<ProductImages> prodImage = productImages(product.getProductId());
				if (prodImage != null && prodImage.size() > 0) {
					product.setImage(prodImage.get(0).getImage());
				}

				productList.add(product);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productList;
	}

	@Override
	public List<ProductCC> getAllActiveProduct(Filter filter) throws Exception {
		List<ProductCC> productList = new ArrayList<ProductCC>();
		ProductCC product = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT * FROM product p";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductCC();
				product.setProductId(rs.getLong("product_id"));
				product.setName(rs.getString("name"));
				product.setStatus(rs.getBoolean("status"));
				product.setInStock(rs.getBoolean("in_stock"));
				product.setSoldQty(rs.getDouble("sold_qty"));
				product.setBrandId(rs.getLong("brand_id"));
				product.setProductNo(rs.getString("product_no"));
				product.setOfferable(rs.getBoolean("offerable"));
				product.setRecommendation(rs.getBoolean("recommendation"));
				if (product.getBrandId() != null) {
					Brand brand = repositoryDao.findById(Brand.class, product.getBrandId());
					product.setBrandName(brand.getName());
				}

				product.setCategory(productCategories(product.getProductId()));
				product.setProductImages(productImages(product.getProductId()));
				product.setProductInformation(productInformation(product.getProductId(), false));
				product.setProductUnit(productUnit(product.getProductId(), false));
				List<ProductImages> prodImage = productImages(product.getProductId());
				if (prodImage != null && prodImage.size() > 0) {
					product.setImage(prodImage.get(0).getImage());
				}


				productList.add(product);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productList;
	}

	@Override
	public List<ProductCC> favouriteList(Filter filter) throws Exception {
		List<ProductCC> productList = new ArrayList<ProductCC>();
		if (filter.getUserId() != null) {
			productList = getfavouriteList(filter);

			// push all product id in array and then get recommanded list except
			// favourite product ids
			List<Long> ids = new ArrayList<Long>();
			for (ProductCC product : productList) {
				ids.add(product.getProductId());
			}

			// if (productList.size() < 4) {
			List<ProductCC> productList1 = recommandedProduct(filter);
			for (int i = 0; i < productList1.size(); i++) {
				if (!ids.contains(productList1.get(i).getProductId())) {
					productList.add(productList1.get(i));
				}
			}
			// }
		} else {
			List<ProductCC> productList1 = getAllActiveProduct(filter);
			for (int i = 0; i < 7; i++) {
				productList.add(productList1.get(i));
			}
		}
		return productList;

	}

	public List<ProductCC> getfavouriteList(Filter filter) throws Exception {
		List<ProductCC> productList = new ArrayList<ProductCC>();
		ProductCC product = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT * FROM product p INNER JOIN user_favourite pf ON p.product_id = pf.product_id "
					+ " WHERE pf.user_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, filter.getUserId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductCC();
				product.setProductId(rs.getLong("product_id"));
				product.setName(rs.getString("name"));
				product.setStatus(rs.getBoolean("status"));
				product.setInStock(rs.getBoolean("in_stock"));
				product.setSoldQty(rs.getDouble("sold_qty"));
				product.setBrandId(rs.getLong("brand_id"));
				product.setProductNo(rs.getString("product_no"));
				product.setUserFavouriteId(rs.getLong("user_faourite_id"));
				product.setFavourite(true);
				if (product.getBrandId() != null) {
					Brand brand = repositoryDao.findById(Brand.class, product.getBrandId());
					product.setBrandName(brand.getName());
				}

				product.setCategory(productCategories(product.getProductId()));
				product.setProductImages(productImages(product.getProductId()));
				product.setProductInformation(productInformation(product.getProductId(), false));
				product.setProductUnit(productUnit(product.getProductId(), false));
				List<ProductImages> prodImage = productImages(product.getProductId());
				if (prodImage != null && prodImage.size() > 0) {
					product.setImage(prodImage.get(0).getImage());
				}
				productList.add(product);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productList;
	}

	@Override
	public List<ProductCC> recommandedProduct(Filter filter) throws Exception {
		List<ProductCC> productList = new ArrayList<ProductCC>();
		ProductCC product = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT * FROM product p WHERE p.recommendation = 1 ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductCC();
				product.setProductId(rs.getLong("product_id"));
				product.setName(rs.getString("name"));
				product.setStatus(rs.getBoolean("status"));
				product.setInStock(rs.getBoolean("in_stock"));
				product.setSoldQty(rs.getDouble("sold_qty"));
				product.setBrandId(rs.getLong("brand_id"));
				product.setOfferable(rs.getBoolean("offerable"));

				product.setRecommendation(rs.getBoolean("recommendation"));
				if (product.getBrandId() != null) {
					Brand brand = repositoryDao.findById(Brand.class, product.getBrandId());
					product.setBrandName(brand.getName());
				}

				product.setCategory(productCategories(product.getProductId()));
				product.setProductImages(productImages(product.getProductId()));
				product.setProductInformation(productInformation(product.getProductId(), false));
				product.setProductUnit(productUnit(product.getProductId(), false));
				List<ProductImages> prodImage = productImages(product.getProductId());
				if (prodImage != null && prodImage.size() > 0) {
					product.setImage(prodImage.get(0).getImage());
				}

	

				productList.add(product);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productList;
	}

	@Override
	public List<ProductCC> productReport(Filter filter) throws Exception {
		List<ProductCC> productList = new ArrayList<ProductCC>();
		ProductCC product = null;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT p.*, pu.* FROM product p INNER JOIN product_unit pu ON p.product_id = pu.product_id "
					+ "  INNER JOIN product_category pc ON p.product_id = pc.product_id  INNER JOIN category c ON c.category_id = pc.category_id "
					+ "  INNER JOIN brand b ON b.brand_id = p.brand_id WHERE pu.product_unit_id IS NOT NULL ";

			if (filter.getByNumStatus() > 0) {
				if (filter.getByNumStatus() == 1) {
					sql += " AND pu.status = 1";
				}
				if (filter.getByNumStatus() == 2) {
					sql += " AND pu.status = 0";
				}
			}

			if (filter.getStock() > 0) {
				if (filter.getStock() == 1) {
					sql += " AND pu.in_stock = 1";
				}
				if (filter.getStock() == 2) {
					sql += " AND pu.in_stock = 0";
				}
			}

			if (filter.getStockQuanity() > 0) {
				if (filter.getStockQuanity() == 1) {
					sql += " AND pu.stock_quantity = 0";
				}
				if (filter.getStockQuanity() != 1) {
					sql += " AND pu.stock_quantity = " + filter.getStockQuanity();
				}
			}

			if (filter.getSearchBy() != null && !filter.getSearchBy().isEmpty()) {
				sql += " AND p.name LIKE  ? OR  c.name LIKE ? OR b.name LIKE ? OR p.product_no LIKE ? ";
			}

			if (filter.getIds() != null && filter.getIds().size() > 0) {
				for (int i = 0; i < filter.getIds().size(); i++) {
					sql += " AND pc.category_id = " + filter.getIds().get(i);
				}
			}
			sql += "  GROUP BY pu.product_unit_id ORDER BY " + filter.getOrderBy();
			if (filter.getPage() > 0 && filter.getSize() > 0) {
				sql += " LIMIT  ?,? ";
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			if (filter.getSearchBy() != null && !filter.getSearchBy().isEmpty()) {
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
			}
			if (filter.getPage() > 0 && filter.getSize() > 0) {
				ps.setLong(++count, (filter.getPage() - 1) * filter.getSize());
				ps.setLong(++count, filter.getSize());
			}
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			while (rs.next()) {
				product = new ProductCC();
				product.setProductId(rs.getLong("product_id"));
				product.setName(rs.getString("name"));
				product.setStatus(rs.getBoolean("status"));
				product.setInStock(rs.getBoolean("in_stock"));
				product.setSoldQty(rs.getDouble("sold_qty"));
				product.setBrandId(rs.getLong("brand_id"));
				product.setOfferable(rs.getBoolean("offerable"));
				product.setProductNo(rs.getString("product_no"));
				product.setRecommendation(rs.getBoolean("recommendation"));
				if (product.getBrandId() != null) {
					Brand brand = repositoryDao.findById(Brand.class, product.getBrandId());
					product.setBrandName(brand.getName());
				}
				product.setCategory(productCategories(product.getProductId()));
				if (rs.getLong("product_unit_id") > 0) {
					product.setAttribute(repositoryDao.findById(ProductUnit.class, rs.getLong("product_unit_id")));
				}

				productList.add(product);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return productList;
	}

	@Override
	public Long productReportCount(Filter filter) throws Exception {
		Long totalRecord = 0L;
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			String sql = " SELECT COUNT(DISTINCT product_unit_id ) as totalRecord FROM product p INNER JOIN product_unit pu ON p.product_id = pu.product_id "
					+ "  INNER JOIN product_category pc ON p.product_id = pc.product_id  INNER JOIN category c ON c.category_id = pc.category_id "
					+ "  INNER JOIN brand b ON b.brand_id = p.brand_id WHERE pu.product_unit_id IS NOT NULL ";

			if (filter.getByNumStatus() > 0) {
				if (filter.getByNumStatus() == 1) {
					sql += " AND pu.status = 1";
				}
				if (filter.getByNumStatus() == 2) {
					sql += " AND pu.status = 0";
				}
			}

			if (filter.getStock() > 0) {
				if (filter.getStock() == 1) {
					sql += " AND pu.in_stock = 1";
				}
				if (filter.getStock() == 2) {
					sql += " AND pu.in_stock = 0";
				}
			}

			if (filter.getStockQuanity() > 0) {
				if (filter.getStockQuanity() == 1) {
					sql += " AND pu.stock_quantity = 0";
				}
				if (filter.getStockQuanity() != 1) {
					sql += " AND pu.stock_quantity = " + filter.getStockQuanity();
				}
			}

			if (filter.getSearchBy() != null && !filter.getSearchBy().isEmpty()) {
				sql += " AND p.name LIKE  ? OR  c.name LIKE ? OR b.name LIKE ? OR p.product_no LIKE ? ";
			}

			if (filter.getIds() != null && filter.getIds().size() > 0) {
				for (int i = 0; i < filter.getIds().size(); i++) {
					sql += " AND pc.category_id = " + filter.getIds().get(i);
				}
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = 0;
			if (filter.getSearchBy() != null && !filter.getSearchBy().isEmpty()) {
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
				ps.setString(++count, "%" + filter.getSearchBy() + "%");
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				totalRecord = rs.getLong("totalRecord");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
		return totalRecord;
	}

}
