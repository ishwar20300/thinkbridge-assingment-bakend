package com.shopbridge.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopbridge.cc.Authentication;
import com.shopbridge.constants.Response;
import com.shopbridge.constants.StatusConstance;
import com.shopbridge.service.AuthenticationService;


@RestController
@RequestMapping(value = "/v1/authentication")
public class AuthenticationController {
	
	private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

	private final AuthenticationService authenticationService;

	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	/****
	 * REGISTRATION OF NEW USER
	 **************/

	@CrossOrigin
	@PostMapping("/sign-up")
	public Response addnew(@RequestBody Authentication authentication) {
		Response response = new Response();
		try {
			return authenticationService.signUp(authentication);
		} catch (Exception e) {
			logger.error("Error In sign-up : ", e);
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}
	

	/****
	 * Login With Password
	 **************/

	@CrossOrigin
	@PostMapping("/login-with-password")
	public Response loginWithPass(@RequestBody Authentication authentication) {
		Response response = new Response();
		try {
			return authenticationService.loginWithPass(authentication);
		} catch (Exception e) {
			logger.error("Error In login-with-password : ", e);
			response.setStatus(StatusConstance.SERVER_ERROR);
			response.setMessage(StatusConstance.SERVER_ERROR_MSG);
			return response;
		}
	}
	
		
	
}
