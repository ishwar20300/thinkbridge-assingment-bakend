package com.shopbridge.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopbridge.bean.Product;
import com.shopbridge.constants.Filter;
import com.shopbridge.constants.Response;
import com.shopbridge.constants.StatusConstance;
import com.shopbridge.service.ProductService;


@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(ProductController.class);

	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	/****
	 * ADD PRODUCT WITH IMAGES, INFORMATION,UNITS
	 **************/

	@CrossOrigin
	@PostMapping("/add")
	public Response add(@RequestBody Product product) {
		Response response = new Response();
		try {
			return productService.addnew(product);
		} catch (Exception e) {
			logger.error("Error In add  : ", e);
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * UPDATE PRODUCT WITH IMAGES, INFORMATION,UNITS
	 **************/

	@CrossOrigin
	@PutMapping("/update")
	public Response update(@RequestBody Product product) {
		Response response = new Response();
		try {
			return productService.update(product);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * GET PRODUCT DETAIL BY ID
	 **************/
	@CrossOrigin
	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.findById(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * GET PRODUCT DETAIL WITH RELATED PRODUCT
	 **************/
	@CrossOrigin
	@GetMapping("/product-with-related-prod/{productId}/{categoryId}")
	public Response productDetailWithRelatedProd(@PathVariable("productId") Long productId,
			@PathVariable("categoryId") Long categoryId) {
		Response response = new Response();
		try {
			return productService.productDetailWithRelatedProd(productId, categoryId);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * CHANGE PRODUCT STATUS
	 **************/
	@CrossOrigin
	@GetMapping("/change-status/{id}")
	public Response changeStatus(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.changeStatus(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * DELETE PRODUCT RECORD
	 **************/
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public Response delete(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.delete(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * MAKE PRODUCT IN-SOTCK OR OUT-STOCK
	 **************/
	@CrossOrigin
	@GetMapping("/change-stock-status/{id}")
	public Response changeStockStatus(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.changeStockStatus(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * MAKE PRODUCT PRODUCT INFORMATION STATUS
	 **************/
	@CrossOrigin
	@GetMapping("/change-information-status/{id}")
	public Response changeInformationStatus(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.changeInformationStatus(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * MAKE PRODUCT UNIT STOCK STATUS
	 **************/
	@CrossOrigin
	@GetMapping("/change-product-unit-status/{id}")
	public Response changeUnitStatus(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.changeUnitStatus(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * MAKE PRODUCT UNIT IN-SOTCK OR OUT-STOCK
	 **************/
	@CrossOrigin
	@GetMapping("/change-unit-stock-status/{id}")
	public Response changeUnitStockStatus(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.changeUnitStockStatus(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * DELETE PRODUCT IMAGE
	 **************/
	@CrossOrigin
	@DeleteMapping("/delete-product-image/{id}")
	public Response deleteImage(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.deleteImage(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * DELETE PRODUCT INFORMATION
	 **************/
	@CrossOrigin
	@DeleteMapping("/delete-product-information/{id}")
	public Response deleteInformation(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.deleteInformation(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * DELETE PRODUCT UNIT
	 **************/
	@CrossOrigin
	@DeleteMapping("/delete-product-unit/{id}")
	public Response deleteUnit(@PathVariable("id") Long id) {
		Response response = new Response();
		try {
			return productService.deleteUnit(id);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * REMOVE PRODUCT CATEGORY
	 **************/
	@CrossOrigin
	@DeleteMapping("/remove-product-category/{prodId}/{catId}")
	public Response removeCategory(@PathVariable("prodId") Long prodId, @PathVariable("catId") Long catId) {
		Response response = new Response();
		try {
			return productService.removeCategory(prodId, catId);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	/****
	 * GET ALL ACTIVE AND INACTIVE PRODUCT WITH PRODUCT RELATED DATA
	 * 
	 **************/
	@CrossOrigin
	@PostMapping("/admin/product-list")
	public Response allParentCategories(@RequestBody Filter filter) {
		Response response = new Response();
		try {
			return productService.getAllProduct(filter);
		} catch (Exception e) {
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}

	



}
