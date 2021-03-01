package com.shopbridge.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shopbridge.bean.Brand;
import com.shopbridge.bean.Category;
import com.shopbridge.bean.Product;
import com.shopbridge.bean.ProductCategory;
import com.shopbridge.bean.ProductImages;
import com.shopbridge.bean.ProductInformation;
import com.shopbridge.bean.ProductUnit;
import com.shopbridge.cc.CategoryCC;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;
import com.shopbridge.constants.StatusConstance;
import com.shopbridge.dao.ProductDao;
import com.shopbridge.service.ProductService;
import com.shopbridge.setting.RepositoryDao;
import com.shopbridge.util.RandomGenerator;
import com.shopbridge.util.SaveFile;



@Service
public class ProductServiceImpl implements ProductService {

	private final RepositoryDao repositoryDao;

	private final ProductDao productDao;

	private final SaveFile saveFile;

	public ProductServiceImpl(RepositoryDao repositoryDao, ProductDao productDao, SaveFile saveFile) {
		super();
		this.repositoryDao = repositoryDao;
		this.productDao = productDao;
		this.saveFile = saveFile;
	}

	@Override
	public Response addnew(Product product) throws Exception {
		Response response = new Response();
		try {

			if (product.getCategoryIds() != null && product.getCategoryIds().size() > 0) {
				if (product.getBrandId() != null) {
					Brand brand = repositoryDao.findById(Brand.class, product.getBrandId());
					if (brand != null) {
						product.setBrand(brand);
						product.setProductNo("PROD" + RandomGenerator.generateNum(5));
						repositoryDao.addnew(product);

						// Save Product Category
						Category category = null;
						ProductCategory productCategory = null;
						for (Long id : product.getCategoryIds()) {
						  if(id != null){
								category = repositoryDao.findById(Category.class, id);
								productCategory = new ProductCategory();
								productCategory.setCategory(category);
								productCategory.setProduct(product);
								repositoryDao.addnew(productCategory);
						  }
						}

						// Save Product Information
						for (ProductInformation prodInfo : product.getProductInformation()) {
							prodInfo.setProduct(product);
							repositoryDao.addnew(prodInfo);
						}

						// Save Product Units
						for (ProductUnit unit : product.getProductUnit()) {
							unit.setStatus(true);
							unit.setProduct(product);
							repositoryDao.addnew(unit);
						}

						// saveProductImage
						for (ProductImages image : product.getProductImages()) {
							if (image.getBase64() != null && !image.getBase64().isEmpty()
									&& image.getImageName() != null && !image.getImageName().isEmpty()) {
								image.setImage(saveFile.saveBase64(image.getBase64(), image.getImageName()));
								image.setProduct(product);
								repositoryDao.addnew(image);
								image.setBase64(null);
							}
						}

						response.setTitle(StatusConstance.SUCCESS_ADD);
						response.setStatus(StatusConstance.SUCCESS);
						response.setMessage(StatusConstance.RECORD_ADDED);
						response.setResponse(product);

					} else {
						response.setTitle(StatusConstance.DATA_NOT_FOUND);
						response.setStatus(StatusConstance.NOT_FOUND);
						response.setMessage("Brand Record Not found by your id = " + product.getBrandId());
					}
				} else {
					response.setTitle(StatusConstance.ID_REQ);
					response.setStatus(StatusConstance.MANDATORY);
					response.setMessage("Brand Is required to add a product");
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("At Least 1 Categroy is required to add product");
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response update(Product productData) throws Exception {
		Response response = new Response();
		try {
			if (productData.getProductId() != null) {
				Product product = repositoryDao.findById(Product.class, productData.getProductId());

				if (product != null) {
					if (productData.getCategoryIds() != null && productData.getCategoryIds().size() > 0) {

						if (productData.getBrandId() != null) {
							Brand brand = repositoryDao.findById(Brand.class, productData.getBrandId());
							if (brand != null) {
								product.setInStock(productData.isInStock());
								product.setName(productData.getName());
								product.setStatus(productData.isStatus());
								product.setBrand(brand);
								product.setOfferable(productData.isOfferable());
								product.setRecommendation(productData.isRecommendation());

								if (product.getProductNo() == null) {
									product.setProductNo("PROD" + RandomGenerator.generateNum(5));
								}

								repositoryDao.update(product);

								// Delete all category first and then add new
								@SuppressWarnings("unchecked")
								List<ProductCategory> pcc = (List<ProductCategory>) repositoryDao
										.list(ProductCategory.class, "product", "productCategoryId", product);

								if (pcc.size() > 0) {
									for (int i = 0; i < pcc.size(); i++) {
										ProductCategory pcc1 = repositoryDao.findById(ProductCategory.class,
												pcc.get(i).getProductCategoryId());
										if (pcc1 != null) {
											repositoryDao.deleteObj(pcc1);
										}
									}
								}
								// Save Product Category
								Category category = null;
								ProductCategory productCategory = null;
								for (Long id : productData.getCategoryIds()) {
									category = repositoryDao.findById(Category.class, id);
									productCategory = repositoryDao.findByKey(ProductCategory.class, "category",
											category, "product", product);
									if (productCategory == null) {
										productCategory = new ProductCategory();
										productCategory.setCategory(category);
										productCategory.setProduct(product);
										repositoryDao.addnew(productCategory);
									}
								}

								// Save Product Information
								ProductInformation info = null;
								for (ProductInformation prodInfo : productData.getProductInformation()) {
									if (prodInfo.getProductInformationId() != null) {
										info = repositoryDao.findById(ProductInformation.class,
												prodInfo.getProductInformationId());
										if (info != null) {
											info.setDescription(prodInfo.getDescription());
											info.setStatus(prodInfo.isStatus());
											info.setTitle(prodInfo.getTitle());
											info.setProduct(product);
											repositoryDao.update(info);
										} else {
											prodInfo.setProduct(product);
											repositoryDao.addnew(prodInfo);
										}
									} else {
										prodInfo.setProduct(product);
										repositoryDao.addnew(prodInfo);
									}
								}

								// Save Product Units
								ProductUnit prodUnit = null;
								for (ProductUnit unit : productData.getProductUnit()) {
									if (unit.getProductUnitId() != null) {
										prodUnit = repositoryDao.findById(ProductUnit.class, unit.getProductUnitId());
										if (prodUnit != null) {
											prodUnit.setAvailableIn(unit.getAvailableIn());
											prodUnit.setFinalPrice(unit.getFinalPrice());
											prodUnit.setInStock(unit.isInStock());
											prodUnit.setMrp(unit.getMrp());
											prodUnit.setSellingPrice(unit.getSellingPrice());
											prodUnit.setSize(unit.getSize());
											prodUnit.setStatus(true);
											prodUnit.setTaxinclusive(unit.isTaxinclusive());
											prodUnit.setUnit(unit.getUnit());
											prodUnit.setStockQuantity(unit.getStockQuantity());
											prodUnit.setTaxPercent(unit.getTaxPercent());
											prodUnit.setDiscountType(unit.getDiscountType());
											prodUnit.setDiscountValue(unit.getDiscountValue());
											prodUnit.setOfferable(unit.isOfferable());
											prodUnit.setSku(unit.getSku());
											prodUnit.setWeightInGm(unit.getWeightInGm());
											prodUnit.setProduct(product);
											repositoryDao.update(prodUnit);
										} else {
											unit.setFinalPrice(unit.getSellingPrice());
											unit.setProduct(product);
											unit.setStatus(true);
											repositoryDao.addnew(unit);
										}
									} else {
										unit.setProduct(product);
										repositoryDao.addnew(unit);
									}
								}

								// saveProductImage
								for (ProductImages image : productData.getProductImages()) {
									if (image.getBase64() != null && !image.getBase64().isEmpty()
											&& image.getImageName() != null && !image.getImageName().isEmpty()) {
										image.setImage(saveFile.saveBase64(image.getBase64(), image.getImageName()));
										image.setProduct(product);
										repositoryDao.addnew(image);
										image.setBase64(null);
									}
								}

								response.setTitle(StatusConstance.RECORD_UPDATED);
								response.setStatus(StatusConstance.SUCCESS);
								response.setMessage("Record updated successfully");

							} else {
								response.setTitle(StatusConstance.DATA_NOT_FOUND);
								response.setStatus(StatusConstance.NOT_FOUND);
								response.setMessage("Brand Record Not found by your id = " + product.getBrandId());
							}
						} else {
							response.setTitle(StatusConstance.ID_REQ);
							response.setStatus(StatusConstance.MANDATORY);
							response.setMessage("Brand Is required to add a product");
						}
					} else {
						response.setTitle(StatusConstance.ID_REQ);
						response.setStatus(StatusConstance.MANDATORY);
						response.setMessage("At Least 1 Categroy is required to add product");
					}
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response findById(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				Product product = repositoryDao.findById(Product.class, id);
				if (product != null) {
					product.setBrandId(product.getBrand().getBrandId());

					// Get Category Ids
					List<CategoryCC> categoryList = productDao.productCategories(product.getProductId());
					List<Long> categoryIds = new ArrayList<Long>();
					if (categoryList.size() > 0) {
						for (CategoryCC category : categoryList) {
							categoryIds.add(category.getCategoryId());
						}
						product.setCategoryIds(categoryIds);
					}

					response.setTitle(StatusConstance.DATA_FOUND);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.RECORD_FOUND);
					response.setResponse(product);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response productDetailWithRelatedProd(Long productId, Long categoryId) throws Exception {
		Response response = new Response();
		try {
			if (productId != null) {
				Product product = repositoryDao.findById(Product.class, productId);
				if (product != null) {
					product.setBrandId(product.getBrand().getBrandId());
					product.setBrandName(product.getBrand().getName());
					product.setRelatedProduct(productDao.relatedProduct(categoryId));
					response.setTitle(StatusConstance.DATA_FOUND);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.RECORD_FOUND);
					response.setResponse(product);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response delete(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				Product product = repositoryDao.findById(Product.class, id);
				if (product != null) {

					List<CategoryCC> productCategories = productDao.productCategories(product.getProductId());
					List<ProductImages> productImages = productDao.productImages(product.getProductId());
					List<ProductInformation> productInformation = productDao.productInformation(product.getProductId(),
							false);
					List<ProductUnit> productUnit = productDao.productUnit(product.getProductId(), false);

					for (CategoryCC cat : productCategories) {
						ProductCategory pc = repositoryDao.findById(ProductCategory.class, cat.getProductCategoryId());
						if (pc != null) {
							repositoryDao.deleteObj(pc);
						}
					}

					for (ProductImages img : productImages) {
						repositoryDao.deleteObj(img);
					}

					for (ProductInformation prodInfo : productInformation) {
						repositoryDao.deleteObj(prodInfo);
					}

					for (ProductUnit prodUnit : productUnit) {
						repositoryDao.deleteObj(prodUnit);
					}

					repositoryDao.deleteObj(product);
					response.setTitle(StatusConstance.DELETE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.DELETE_MSG);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response changeStatus(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				Product product = repositoryDao.findById(Product.class, id);
				if (product != null) {
					if (product.isStatus())
						product.setStatus(false);
					else
						product.setStatus(true);
					repositoryDao.update(product);
					response.setTitle(StatusConstance.STATUS_CHANGE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.STATUS_CHANGE_MSG + product.isStatus());
					response.setResponse(product);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response changeStockStatus(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				Product product = repositoryDao.findById(Product.class, id);
				if (product != null) {
					if (product.isInStock())
						product.setInStock(false);
					else
						product.setInStock(true);
					repositoryDao.update(product);
					response.setTitle(StatusConstance.STATUS_CHANGE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.PRODUCT_STOCK_STATUS);
					response.setResponse(product);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response deleteImage(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				ProductImages product = repositoryDao.findById(ProductImages.class, id);
				if (product != null) {
					repositoryDao.deleteObj(product);
					response.setTitle(StatusConstance.DELETE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.DELETE_MSG);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product IMAGE" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response deleteInformation(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				ProductInformation product = repositoryDao.findById(ProductInformation.class, id);
				if (product != null) {
					repositoryDao.deleteObj(product);
					response.setTitle(StatusConstance.DELETE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.DELETE_MSG);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product Information" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response changeInformationStatus(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				ProductInformation product = repositoryDao.findById(ProductInformation.class, id);
				if (product != null) {
					if (product.isStatus())
						product.setStatus(false);
					else
						product.setStatus(true);
					repositoryDao.update(product);
					response.setTitle(StatusConstance.STATUS_CHANGE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.STATUS_CHANGE_MSG + product.isStatus());
					response.setResponse(product);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response deleteUnit(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				ProductUnit product = repositoryDao.findById(ProductUnit.class, id);
				if (product != null) {
					repositoryDao.deleteObj(product);
					response.setTitle(StatusConstance.DELETE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.DELETE_MSG);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response changeUnitStatus(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				ProductUnit product = repositoryDao.findById(ProductUnit.class, id);
				if (product != null) {
					if (product.isStatus())
						product.setStatus(false);
					else
						product.setStatus(true);
					repositoryDao.update(product);
					response.setTitle(StatusConstance.STATUS_CHANGE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.STATUS_CHANGE_MSG + product.isStatus());
					response.setResponse(product);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response changeUnitStockStatus(Long id) throws Exception {
		Response response = new Response();
		try {
			if (id != null) {
				ProductUnit product = repositoryDao.findById(ProductUnit.class, id);
				if (product != null) {
					if (product.isInStock())
						product.setInStock(false);
					else
						product.setInStock(true);
					repositoryDao.update(product);
					response.setTitle(StatusConstance.STATUS_CHANGE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.PRODUCT_UNIT_STOCK_STATUS);
					response.setResponse(product);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Product Unit" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response removeCategory(Long prodId, Long catId) throws Exception {
		Response response = new Response();
		try {
			Product product = repositoryDao.findById(Product.class, prodId);
			Category category = repositoryDao.findById(Category.class, catId);
			if (product == null) {
				response.setTitle(StatusConstance.DATA_NOT_FOUND);
				response.setStatus(StatusConstance.NOT_FOUND);
				response.setMessage(StatusConstance.PRODUCR_NOT_FOUND);
			} else if (category == null) {
				response.setTitle(StatusConstance.DATA_NOT_FOUND);
				response.setStatus(StatusConstance.NOT_FOUND);
				response.setMessage(StatusConstance.CATEGORY_NOT_FOUND);
			} else {
				ProductCategory productCat = repositoryDao.findByKey(ProductCategory.class, "category", category,
						"product", product);
				if (productCat != null) {
					repositoryDao.deleteObj(productCat);
					response.setTitle(StatusConstance.DELETE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.DELETE_MSG);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.PRODUCT_CATEOGRY_NOT_FOUND);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response getAllProduct(Filter filter) throws Exception {
		Response response = new Response();
		try {
			response.setTitle(StatusConstance.LIST_DATA_TITLE);
			response.setStatus(StatusConstance.SUCCESS);
			response.setMessage(StatusConstance.LIST_DATA);
			response.setResponse(productDao.getAllProduct(filter));
			response.setCount(productDao.allProductCount(filter));
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response productByCategory(Filter filter) throws Exception {
		Response response = new Response();
		try {
			response.setTitle(StatusConstance.LIST_DATA_TITLE);
			response.setStatus(StatusConstance.SUCCESS);
			response.setMessage(StatusConstance.LIST_DATA);
			response.setResponse(productDao.productByCategory(filter));
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response getAllActiveProduct(Filter filter) throws Exception {
		Response response = new Response();
		try {
			response.setTitle(StatusConstance.LIST_DATA_TITLE);
			response.setStatus(StatusConstance.SUCCESS);
			response.setMessage(StatusConstance.LIST_DATA);
			response.setResponse(productDao.getAllActiveProduct(filter));
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response productReport(Filter filter) throws Exception {
		Response response = new Response();
		try {
			response.setTitle(StatusConstance.LIST_DATA_TITLE);
			response.setStatus(StatusConstance.SUCCESS);
			response.setMessage(StatusConstance.LIST_DATA);
			response.setResponse(productDao.productReport(filter));
			response.setCount(productDao.productReportCount(filter));
		} catch (Exception e) {
			throw e;
		}
		return response;
	}



}
