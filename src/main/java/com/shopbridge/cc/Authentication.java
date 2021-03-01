package com.shopbridge.cc;

import java.util.Date;


public class Authentication {

	private Long userId;

	private String uuid;

	private String username;

	private String email;

	private String mobile;

	private String firstName;

	private String middleName;

	private String lastName;

	private Integer gender;

	private Date dob;

	private String profilePhoto;

	private Integer registerBy;

	private Integer registerWith;

	private String socialUserId;

	private String password;

	private String oldPassword;

	private String otp;

	private String accessToken;

	private String authToken;
	
	private Integer userType;
	
	private  boolean subscribed;
	
	
	/*****
	 * 0 or null means unlimitted
	 * 1 Means at a time only one;
	 * 
	 */
	private Integer oneTimeLoginLimit;
	

	// For reset password
	// For confirmation ex mobile no
	private String otpType;

	// DEVICE INFO

	private Long loggerId;
	
	private String fcm;

	private String platform;

	private String version;

	private String manufacturer;

	private String model;

	private String uniqueDeviceId;

	private String loggedCity;

	private double latitudes;

	private double longitudes;

	private String deviceUuid;

	private boolean logged;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public Integer getRegisterBy() {
		return registerBy;
	}

	public void setRegisterBy(Integer registerBy) {
		this.registerBy = registerBy;
	}

	public Integer getRegisterWith() {
		return registerWith;
	}

	public void setRegisterWith(Integer registerWith) {
		this.registerWith = registerWith;
	}


	public String getSocialUserId() {
		return socialUserId;
	}

	public void setSocialUserId(String socialUserId) {
		this.socialUserId = socialUserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOtpType() {
		return otpType;
	}

	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getFcm() {
		return fcm;
	}

	public void setFcm(String fcm) {
		this.fcm = fcm;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUniqueDeviceId() {
		return uniqueDeviceId;
	}

	public void setUniqueDeviceId(String uniqueDeviceId) {
		this.uniqueDeviceId = uniqueDeviceId;
	}

	public String getLoggedCity() {
		return loggedCity;
	}

	public void setLoggedCity(String loggedCity) {
		this.loggedCity = loggedCity;
	}

	public double getLatitudes() {
		return latitudes;
	}

	public void setLatitudes(double latitudes) {
		this.latitudes = latitudes;
	}

	public double getLongitudes() {
		return longitudes;
	}

	public void setLongitudes(double longitudes) {
		this.longitudes = longitudes;
	}

	public String getDeviceUuid() {
		return deviceUuid;
	}

	public void setDeviceUuid(String deviceUuid) {
		this.deviceUuid = deviceUuid;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public Long getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(Long loggerId) {
		this.loggerId = loggerId;
	}

	public Integer getOneTimeLoginLimit() {
		return oneTimeLoginLimit;
	}

	public void setOneTimeLoginLimit(Integer oneTimeLoginLimit) {
		this.oneTimeLoginLimit = oneTimeLoginLimit;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	
	
	
}
