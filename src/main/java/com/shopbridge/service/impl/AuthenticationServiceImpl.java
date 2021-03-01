package com.shopbridge.service.impl;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import org.springframework.stereotype.Service;
import com.shopbridge.bean.User;
import com.shopbridge.bean.UserLogger;
import com.shopbridge.cc.Authentication;
import com.shopbridge.constants.Response;
import com.shopbridge.constants.StatusConstance;
import com.shopbridge.service.AuthenticationService;
import com.shopbridge.setting.RepositoryDao;
import com.shopbridge.util.RandomGenerator;
import com.shopbridge.util.SecurityEncorder;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final RepositoryDao repositoryDao;


	private static final SecureRandom secureRandom = new SecureRandom();

	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

	public AuthenticationServiceImpl(RepositoryDao repositoryDao) {
		super();
		this.repositoryDao = repositoryDao;

	}

	public static String generateNewToken() {
		byte[] randomBytes = new byte[100];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}

	@Override
	public Response loginWithPass(Authentication authentication) throws Exception {
		Response response = new Response();
		try {

			User user = null;
			if (authentication.getMobile() != null) {
				user = repositoryDao.findBySingleKey(User.class, "mobile", authentication.getMobile());
			}
			if (authentication.getEmail() != null) {
				user = repositoryDao.findBySingleKey(User.class, "email", authentication.getEmail());
			}
			if (user != null) {
				boolean isPasswordCorrect = SecurityEncorder.comparePassword(authentication.getPassword(),
						user.getPasswordHash(), user.getPasswordCode());
				repositoryDao.update(user);
				if (isPasswordCorrect) {
					Authentication authenticated = new Authentication();
					authenticated.setUserId(user.getUserId());
					authenticated.setFirstName(user.getFirstName());
					authenticated.setLastName(user.getLastName());
					authenticated.setEmail(user.getEmail());
					authenticated.setMiddleName(user.getMiddleName());
					authenticated.setDob(user.getDob());
					authenticated.setRegisterBy(user.getRegisterBy());
					authenticated.setRegisterWith(user.getRegisterWith());
					authenticated.setUsername(user.getUsername());
					authenticated.setMobile(user.getMobile());
					authenticated.setUuid(user.getUuid());
					authenticated.setAuthToken(user.getPasswordCode());
					authenticated.setAccessToken(user.getAccessToken());
					authenticated.setUserType(user.getUserType());

					UserLogger logger = new UserLogger();
					logger.setDeviceUuid("UDUI" + RandomGenerator.generateRandamNo());
					logger.setFcm(authentication.getFcm());
					logger.setLatitudes(authentication.getLatitudes());
					logger.setLoggedCity(authentication.getLoggedCity());
					logger.setLongitudes(authentication.getLongitudes());
					logger.setManufacturer(authentication.getManufacturer());
					logger.setModel(authentication.getModel());
					logger.setPlatform(authentication.getPlatform());
					logger.setLogged(true);
					logger.setUniqueDeviceId(authentication.getUniqueDeviceId());
					logger.setVersion(authentication.getVersion());
					logger.setUser(user);
					authenticated.setDeviceUuid(logger.getDeviceUuid());
					repositoryDao.addnew(logger);
					authenticated.setLoggerId(logger.getLoggerId());

					response.setResponse(authenticated);
					response.setTitle(StatusConstance.LOGIN_SUCCESSFULL);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.LOGIN_DONE);
				} else {
					response.setTitle(StatusConstance.WRONG_PASSWORD);
					response.setStatus(StatusConstance.ALREADY_PRESENT);
					response.setMessage(StatusConstance.WRONG_PASSWORD_MSG);
				}

			} else {
				response.setTitle(StatusConstance.ACCOUNT_NOT_FOUND_TITLE);
				response.setStatus(StatusConstance.NOT_FOUND);
				response.setMessage(StatusConstance.ACCOUNT_NOT_FOUND);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response signUp(Authentication authentication) throws Exception {
		Response response = new Response();
		try {
			User userMobile = null;
			User userEmail = null;

			if (authentication.getMobile() != null) {
				userMobile = repositoryDao.findBySingleKey(User.class, "mobile", authentication.getMobile());
			}
			if (authentication.getEmail() != null) {
				userEmail = repositoryDao.findBySingleKey(User.class, "email", authentication.getEmail());
			}

			if (userMobile != null) {
				response.setTitle(StatusConstance.ALREADY_PRESENT_TITLE);
				response.setStatus(StatusConstance.ALREADY_PRESENT_MOBILE);
				response.setMessage(StatusConstance.ALREADY_PRESENT_MOBILE_MSG);
			}
			if (userEmail != null) {
				response.setTitle(StatusConstance.ALREADY_PRESENT_TITLE);
				response.setStatus(StatusConstance.ALREADY_PRESENT_EMAIL);
				response.setMessage(StatusConstance.ALREADY_PRESENT_EMAIL_MSG);
			}
			if (userMobile == null && userEmail == null) {
				if (authentication.getPassword() != null) {
					User user = new User();
					user.setFirstName(authentication.getFirstName());
					user.setMiddleName(authentication.getMiddleName());
					user.setLastName(authentication.getLastName());
					user.setEmail(authentication.getEmail());
					user.setMobile(authentication.getMobile());
					String encodedPassword = SecurityEncorder.encode(authentication.getPassword());
					String[] ecodedArray = encodedPassword.split(":");
					user.setPasswordCode(ecodedArray[0]);
					user.setPasswordHash(ecodedArray[1]);
					user.setRegisterBy(authentication.getRegisterBy());
					user.setRegisterWith(1);
					user.setStatus(1);
					user.setUsername(authentication.getUsername());
					user.setUserType(2);
					user.setUserVerfied(1);
					user.setUuid("UUID" + RandomGenerator.generateRandamNo());
					user.setAccessToken(generateNewToken());
					repositoryDao.addnew(user);

					Authentication authenticated = new Authentication();
					authenticated.setUserId(user.getUserId());
					authenticated.setFirstName(user.getFirstName());
					authenticated.setLastName(user.getLastName());
					authenticated.setEmail(user.getEmail());
					authenticated.setMiddleName(user.getMiddleName());
					authenticated.setDob(user.getDob());
					authenticated.setRegisterBy(user.getRegisterBy());
					authenticated.setRegisterWith(user.getRegisterWith());
					authenticated.setUsername(user.getUsername());
					authenticated.setMobile(user.getMobile());
					authenticated.setUuid(user.getUuid());
					authenticated.setAuthToken(user.getPasswordCode());
					authenticated.setAccessToken(user.getAccessToken());
					authenticated.setUserType(user.getUserType());

					UserLogger logger = new UserLogger();
					logger.setDeviceUuid("UDUI" + RandomGenerator.generateRandamNo());
					logger.setFcm(authentication.getFcm());
					logger.setLatitudes(authentication.getLatitudes());
					logger.setLoggedCity(authentication.getLoggedCity());
					logger.setLongitudes(authentication.getLongitudes());
					logger.setManufacturer(authentication.getManufacturer());
					logger.setModel(authentication.getModel());
					logger.setPlatform(authentication.getPlatform());
					logger.setLogged(true);
					logger.setUniqueDeviceId(authentication.getUniqueDeviceId());
					logger.setVersion(authentication.getVersion());
					logger.setUser(user);
					authenticated.setDeviceUuid(logger.getDeviceUuid());
					repositoryDao.addnew(logger);
					authenticated.setLoggerId(logger.getLoggerId());

					response.setResponse(authenticated);

					response.setTitle(StatusConstance.REGISTRATION_DONE);
					response.setStatus(StatusConstance.SUCCESS);
					response.setMessage(StatusConstance.REGISTRATION_SUCCESSFULL);
				} else {
					response.setTitle(StatusConstance.PASSOWORD_REQ_TITILE);
					response.setStatus(StatusConstance.MANDATORY);
					response.setMessage(StatusConstance.PASSWORD_REQUIRED_MSG);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
