package com.shopbridge.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopbridge.bean.Brand;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;
import com.shopbridge.constants.StatusConstance;
import com.shopbridge.service.BrandService;

@RestController
@RequestMapping(value = "/v1/brand")
public class BrandController {

	private final BrandService brandService;

	public BrandController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	/****
	 * ADD BRAND
	 **************/

	@CrossOrigin
	@PostMapping("/add")
	public Response addnew(@RequestBody Brand brand) {
		Response response = new Response();
		try {
			return brandService.addnew(brand);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * UPDATE BRAND
	 **************/

	@CrossOrigin
	@PutMapping("/update")
	public Response update(@RequestBody Brand brand) {
		Response response = new Response();
		try {
			return brandService.update(brand);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * GET BRAND DETAIL BY ID
	 **************/
	@CrossOrigin
	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return brandService.findById(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * ALL ACTIVE BRAND
	 * 
	 **************/
	@CrossOrigin
	@PostMapping("/web/all-active-brand")
	public Response allActiveCategories(@RequestBody Filter filter) {
		Response response = new Response();
		try {
			return brandService.allActiveBrand(filter);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	

}
