package com.shopbridge.service.impl;

import org.springframework.stereotype.Service;
import com.shopbridge.bean.Category;
import com.shopbridge.bean.CategoryImages;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;
import com.shopbridge.constants.StatusConstance;
import com.shopbridge.dao.CategoryDao;
import com.shopbridge.service.CategoryService;
import com.shopbridge.setting.RepositoryDao;
import com.shopbridge.util.SaveFile;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final RepositoryDao repositoryDao;

	private final CategoryDao categoryDao;

	private final SaveFile saveFile;

	public CategoryServiceImpl(RepositoryDao repositoryDao, CategoryDao categoryDao, SaveFile saveFile) {
		super();
		this.repositoryDao = repositoryDao;
		this.categoryDao = categoryDao;
		this.saveFile = saveFile;
	}

	@Override
	public Response addnew(Category category) throws Exception {
		Response response = new Response();
		try {
			if (category.isParent()) {
				if (category.getParentId() != null) {
					Category parent = repositoryDao.findById(Category.class, category.getParentId());
					if (parent != null) {

						// saveProductImage

						if (category.getBase64() != null && !category.getBase64().isEmpty()
								&& category.getImageName() != null && !category.getImageName().isEmpty()) {
							category.setImage(saveFile.saveBase64(category.getBase64(), category.getImageName()));
							category.setBase64(null);
						}

						repositoryDao.addnew(category);

						if (category.getImages() != null && category.getImages().size() > 0) {
							CategoryImages categoryImages = null;

							for (CategoryImages image : category.getImages()) {
								categoryImages = new CategoryImages();
								if (image.getBase64() != null && !image.getBase64().isEmpty()
										&& image.getImageName() != null && !image.getImageName().isEmpty()) {
									categoryImages
											.setImage(saveFile.saveBase64(image.getBase64(), image.getImageName()));
									image.setBase64(null);
								}
								categoryImages.setCategory(category);
								repositoryDao.addnew(categoryImages);
							}
						}

						response.setTitle(StatusConstance.SUCCESS_ADD);
						response.setStatus(StatusConstance.SUCCESS);
						response.setMessage(StatusConstance.CHILD_CAT_ADD);
						response.setResponse(category);
					} else {
						response.setTitle(StatusConstance.DATA_NOT_FOUND);
						response.setStatus(StatusConstance.NOT_FOUND);
						response.setMessage(StatusConstance.CATEGORY_NOT_FOUND);
					}
				} else {
					response.setTitle(StatusConstance.ID_REQ);
					response.setStatus(StatusConstance.MANDATORY);
					response.setMessage(StatusConstance.PARENT_REQ);
				}
			} else {
				if (category.getBase64() != null && !category.getBase64().isEmpty() && category.getImageName() != null
						&& !category.getImageName().isEmpty()) {
					category.setImage(saveFile.saveBase64(category.getBase64(), category.getImageName()));
					category.setBase64(null);
				}
				repositoryDao.addnew(category);

				if (category.getImages() != null && category.getImages().size() > 0) {
					CategoryImages categoryImages = null;

					for (CategoryImages image : category.getImages()) {
						categoryImages = new CategoryImages();
						if (image.getBase64() != null && !image.getBase64().isEmpty() && image.getImageName() != null
								&& !image.getImageName().isEmpty()) {
							categoryImages.setImage(saveFile.saveBase64(image.getBase64(), image.getImageName()));
							image.setBase64(null);
						}
						categoryImages.setCategory(category);
						repositoryDao.addnew(categoryImages);
					}
				}

				response.setTitle(StatusConstance.SUCCESS_ADD);
				response.setStatus(StatusConstance.SUCCESS);
				response.setMessage(StatusConstance.PARENT_CAT_ADD);
				response.setResponse(category);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response update(Category categoryData) throws Exception {
		Response response = new Response();
		try {

			if (categoryData.getCategoryId() != null) {
				Category category = repositoryDao.findById(Category.class, categoryData.getCategoryId());
				if (category != null) {
					if (category.isParent()) {
						if (categoryData.getParentId() != null) {
							Category parent = repositoryDao.findById(Category.class, categoryData.getParentId());
							if (parent != null) {

								if (categoryData.getBase64() != null && !categoryData.getBase64().isEmpty()
										&& categoryData.getImageName() != null
										&& !categoryData.getImageName().isEmpty()) {
									category.setImage(
											saveFile.saveBase64(categoryData.getBase64(), categoryData.getImageName()));
									category.setBase64(null);
								}

								category.setName(categoryData.getName());
								category.setParent(true);
								category.setParentId(categoryData.getParentId());
								category.setSequenceNo(categoryData.getSequenceNo());
								category.setStatus(categoryData.isStatus());
								repositoryDao.update(category);

								if (categoryData.getImages() != null && categoryData.getImages().size() > 0) {
									CategoryImages categoryImages = null;
									for (CategoryImages image : categoryData.getImages()) {
										categoryImages = new CategoryImages();
										if (image.getBase64() != null && !image.getBase64().isEmpty()
												&& image.getImageName() != null && !image.getImageName().isEmpty()) {
											categoryImages.setImage(
													saveFile.saveBase64(image.getBase64(), image.getImageName()));
											image.setBase64(null);
										}
										categoryImages.setCategory(category);
										repositoryDao.addnew(categoryImages);
									}
								}

								response.setTitle(StatusConstance.SUCCESS_UPDATE);
								response.setStatus(StatusConstance.SUCCESS);
								response.setMessage(StatusConstance.RECORD_UPDATED);
								response.setResponse(category);
							} else {
								response.setTitle(StatusConstance.DATA_NOT_FOUND);
								response.setStatus(StatusConstance.NOT_FOUND);
								response.setMessage(StatusConstance.CATEGORY_NOT_FOUND);
							}
						} else {
							response.setTitle(StatusConstance.ID_REQ);
							response.setStatus(StatusConstance.MANDATORY);
							response.setMessage(StatusConstance.PARENT_REQ);
						}
					} else {
						if (categoryData.getBase64() != null && !categoryData.getBase64().isEmpty()
								&& categoryData.getImageName() != null && !categoryData.getImageName().isEmpty()) {
							category.setImage(
									saveFile.saveBase64(categoryData.getBase64(), categoryData.getImageName()));
							category.setBase64(null);
						}
						category.setName(categoryData.getName());
						category.setParent(false);
						category.setSequenceNo(categoryData.getSequenceNo());
						category.setStatus(categoryData.isStatus());
						repositoryDao.update(category);

						System.out.println("5");
						if (categoryData.getImages() != null && categoryData.getImages().size() > 0) {
							System.out.println("6");
							CategoryImages categoryImages = null;

							for (CategoryImages image : categoryData.getImages()) {
								System.out.println("7");
								categoryImages = new CategoryImages();
								if (image.getBase64() != null && !image.getBase64().isEmpty()
										&& image.getImageName() != null && !image.getImageName().isEmpty()) {
									categoryImages
											.setImage(saveFile.saveBase64(image.getBase64(), image.getImageName()));
									image.setBase64(null);
								}
								categoryImages.setCategory(category);
								repositoryDao.addnew(categoryImages);
							}
						}

						response.setTitle(StatusConstance.SUCCESS_UPDATE);
						response.setStatus(StatusConstance.SUCCESS);
						response.setMessage(StatusConstance.RECORD_UPDATED);
						response.setResponse(category);
					}
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.CATEGORY_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Category" + StatusConstance.ID_REQ_TO_UPDATE);
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
				Category category = repositoryDao.findById(Category.class, id);
				if (category != null) {
					response.setTitle(StatusConstance.DATA_FOUND);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.RECORD_FOUND);
					response.setResponse(category);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.CATEGORY_NOT_FOUND);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Category" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}


	@Override
	public Response activeParentCategories(Filter filter) throws Exception {
		Response response = new Response();
		try {
			response.setTitle(StatusConstance.LIST_DATA_TITLE);
			response.setStatus(StatusConstance.SUCCESS);
			response.setMessage(StatusConstance.LIST_DATA);
			response.setResponse(categoryDao.activeParentCategories(filter));
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	
}
