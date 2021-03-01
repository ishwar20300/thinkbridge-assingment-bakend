package com.shopbridge.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopbridge.bean.Category;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;
import com.shopbridge.constants.StatusConstance;
import com.shopbridge.service.CategoryService;


@RestController
@RequestMapping(value = "/v1/category")
public class CategoryController {

	
	private static final Logger logger = LogManager.getLogger(CategoryController.class);

	
	
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	/****
	 * ADD PARENT AS WELL AS CHILD CATEGORY
	 * 
	 **************/

	@CrossOrigin
	@PostMapping("/add")
	public Response add(@RequestBody Category category) {
		Response response = new Response();
		try {
			return categoryService.addnew(category);
		} catch (Exception e) {
			logger.error("Error In add product : ", e);
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * UPDATE PARENT AS WELL AS CHILD CATEGORY
	 * 
	 **************/

	@CrossOrigin
	@PutMapping("/update")
	public Response update(@RequestBody Category category) {
		Response response = new Response();
		try {
			return categoryService.update(category);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}
	

	/****
	 * GET CATEGORY DETAIL BY ID
	 *  NU
	 **************/
	@CrossOrigin
	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return categoryService.findById(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	
	
	
	/****
	 * All ACTIVE ONLY PARENT CATEGORIES
	 * 
	 **************/
	@CrossOrigin
	@PostMapping("/web/active-parent-category")
	public Response activeParentCategories(@RequestBody Filter filter) {
		Response response = new Response();
		try {
			return categoryService.activeParentCategories(filter);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}
	
	

}
