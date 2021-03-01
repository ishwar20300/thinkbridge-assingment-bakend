package com.shopbridge.service.impl;

import org.springframework.stereotype.Service;

import com.shopbridge.bean.Brand;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;
import com.shopbridge.constants.StatusConstance;
import com.shopbridge.dao.BrandDao;
import com.shopbridge.service.BrandService;
import com.shopbridge.setting.RepositoryDao;
import com.shopbridge.util.SaveFile;

@Service
public class BrandServiceImpl implements BrandService {

	private final RepositoryDao repositoryDao;

	private final BrandDao brandDao;

	private final SaveFile saveFile;

	public BrandServiceImpl(RepositoryDao repositoryDao, BrandDao brandDao, SaveFile saveFile) {
		super();
		this.repositoryDao = repositoryDao;
		this.brandDao = brandDao;
		this.saveFile = saveFile;
	}

	@Override
	public Response addnew(Brand brand) throws Exception {
		Response response = new Response();
		try {

			if (brand.getBase64() != null && !brand.getBase64().isEmpty() && brand.getImageName() != null
					&& !brand.getImageName().isEmpty()) {
				brand.setImage(saveFile.saveBase64(brand.getBase64(), brand.getImageName()));
				brand.setBase64(null);
			}

			repositoryDao.addnew(brand);
			response.setTitle(StatusConstance.SUCCESS_ADD);
			response.setStatus(StatusConstance.SUCCESS);
			response.setMessage(StatusConstance.RECORD_UPDATED);
			response.setResponse(brand);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response update(Brand brandData) throws Exception {
		Response response = new Response();
		try {

			if (brandData.getBrandId() != null) {
				Brand brand = repositoryDao.findById(Brand.class, brandData.getBrandId());
				if (brand != null) {
					brand.setName(brandData.getName());
					brand.setStatus(brandData.isStatus());

					if (brandData.getBase64() != null && !brandData.getBase64().isEmpty()
							&& brandData.getImageName() != null && !brandData.getImageName().isEmpty()) {
						brand.setImage(saveFile.saveBase64(brandData.getBase64(), brandData.getImageName()));
						brand.setBase64(null);
					}
					repositoryDao.update(brand);
					response.setTitle(StatusConstance.SUCCESS_UPDATE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.RECORD_UPDATED);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.RECORD_NOT_FOUNDED);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Brand" + StatusConstance.ID_REQ_TO_UPDATE);
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
				Brand brand = repositoryDao.findById(Brand.class, id);
				if (brand != null) {
					response.setTitle(StatusConstance.DATA_FOUND);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.RECORD_FOUND);
					response.setResponse(brand);
				} else {
					response.setTitle(StatusConstance.DATA_NOT_FOUND);
					response.setStatus(StatusConstance.NOT_FOUND);
					response.setMessage(StatusConstance.RECORD_NOT_FOUNDED);
				}
			} else {
				response.setTitle(StatusConstance.ID_REQ);
				response.setStatus(StatusConstance.MANDATORY);
				response.setMessage("Brand" + StatusConstance.ID_REQ_TO_UPDATE);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response allActiveBrand(Filter filter) throws Exception {
		Response response = new Response();
		try {
			response.setTitle(StatusConstance.LIST_DATA_TITLE);
			response.setStatus(StatusConstance.SUCCESS);
			response.setMessage(StatusConstance.LIST_DATA);
			response.setResponse(brandDao.allActiveBrand(filter));
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
