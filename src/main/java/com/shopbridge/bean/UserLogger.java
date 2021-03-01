package com.shopbridge.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_logger")
public class UserLogger implements Serializable {

	private static final long serialVersionUID = -6350362369328216620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logger_id")
	private Long loggerId;

	@Column(name = "fcm")
	private String fcm;

	@Column(name = "platform")
	private String platform;

	@Column(name = "version")
	private String version;

	@Column(name = "manufacturer")
	private String manufacturer;

	@Column(name = "model")
	private String model;

	@Column(name = "unique_device_id")
	private String uniqueDeviceId;

	@Column(name = "logged_city")
	private String loggedCity;

	// Track logged device location
	@Column(name = "latitudes")
	private double latitudes;

	// Track logged device location
	@Column(name = "longitudes")
	private double longitudes;

	@Column(name = "logged", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean logged;

	@Column(name = "device_uuid", unique = true, nullable = false)
	private String deviceUuid;
	
	/***
	 * 1 for Manually 2 For Google 3 for Facebook 4 for Instagram
	 *********/
	@Column(name = "register_with", nullable = true)
	private Integer loggedBy;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public Long getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(Long loggerId) {
		this.loggerId = loggerId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getDeviceUuid() {
		return deviceUuid;
	}

	public void setDeviceUuid(String deviceUuid) {
		this.deviceUuid = deviceUuid;
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


	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public Integer getLoggedBy() {
		return loggedBy;
	}

	public void setLoggedBy(Integer loggedBy) {
		this.loggedBy = loggedBy;
	}



}
