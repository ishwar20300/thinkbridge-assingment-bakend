/**
 * 
 */
package com.shopbridge.service;

import com.shopbridge.cc.Authentication;
import com.shopbridge.constants.Response;

public interface AuthenticationService {

	public Response loginWithPass(Authentication authentication) throws Exception;

	public Response signUp(Authentication authentication) throws Exception;

}
