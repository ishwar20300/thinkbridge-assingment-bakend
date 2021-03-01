/**
 * 
 */
package com.shopbridge.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -6350362369328216620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username", unique = true, nullable = true, length = 50)
	private String username;

	@Column(name = "email", unique = true, length = 255)
	private String email;

	@Column(name = "mobile", unique = true, length = 20)
	private String mobile;

	@Column(name = "first_name", nullable = true, length = 25)
	private String firstName;

	@Column(name = "middle_name", nullable = true, length = 25)
	private String middleName;

	@Column(name = "last_name", nullable = true, length = 25)
	private String lastName;

	@Column(name = "dob", nullable = true)
	private Date dob;

	@Column(name = "uuid", unique = true, nullable = false, length = 50)
	private String uuid;

	@Column(name = "access_token", columnDefinition = "LONGTEXT DEFAULT NULL")
	private String accessToken;

	/***
	 * 1 for Webiste 2 For Android 3 for Ios
	 *********/
	@Column(name = "register_by", nullable = true)
	private Integer registerBy;

	/***
	 * 1 for Manually 2 For Google 3 for Facebook 4 for Instagram
	 *********/
	@Column(name = "register_with", nullable = true)
	private Integer registerWith;

	/***
	 * 0 For In-active 1 For active 2 For Inactive by admin
	 *********/
	@Column(name = "status", nullable = true, columnDefinition = "INTEGER DEFAULT 1")
	private Integer status;

	/***
	 * 1 For Manager 2 For Employee
	 *********/
	@Column(name = "user_type", nullable = true, columnDefinition = "INTEGER DEFAULT 2")
	private Integer userType;

	@Column(name = "login_alert", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean loginAlert;

	@Column(name = "social_user", nullable = true)
	private Long socialUserId;

	@Column(name = "password_reset_date", nullable = true)
	private String passwordResetDate;

	/***
	 * 1 For Verified 2 For Un-Verified
	 *********/
	@Column(name = "user_verfied", nullable = true, columnDefinition = "INTEGER DEFAULT 1")
	private Integer userVerfied;

	@JsonIgnore
	@Column(name = "password_hash", nullable = true)
	private String passwordHash;

	@JsonIgnore
	@Column(name = "password_code", nullable = true)
	private String passwordCode;

	@Column(name = "user_remember_hash", nullable = true)
	private String userRememberHash;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_date")
	private Date lastLogging;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public boolean isLoginAlert() {
		return loginAlert;
	}

	public void setLoginAlert(boolean loginAlert) {
		this.loginAlert = loginAlert;
	}

	public Long getSocialUserId() {
		return socialUserId;
	}

	public void setSocialUserId(Long socialUserId) {
		this.socialUserId = socialUserId;
	}

	public String getPasswordResetDate() {
		return passwordResetDate;
	}

	public void setPasswordResetDate(String passwordResetDate) {
		this.passwordResetDate = passwordResetDate;
	}

	public Integer getUserVerfied() {
		return userVerfied;
	}

	public void setUserVerfied(Integer userVerfied) {
		this.userVerfied = userVerfied;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordCode() {
		return passwordCode;
	}

	public void setPasswordCode(String passwordCode) {
		this.passwordCode = passwordCode;
	}

	public String getUserRememberHash() {
		return userRememberHash;
	}

	public void setUserRememberHash(String userRememberHash) {
		this.userRememberHash = userRememberHash;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getLastLogging() {
		return lastLogging;
	}

	public void setLastLogging(Date lastLogging) {
		this.lastLogging = lastLogging;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
