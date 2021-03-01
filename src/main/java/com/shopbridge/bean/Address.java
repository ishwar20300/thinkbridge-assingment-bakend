	package com.shopbridge.bean;
	
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name = "address")
	public class Address {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "address_id")
		private Long addressId;
		
		
		@Column(name = "line1")
		private String line1;
		
		@Column(name = "line2")
		private String line2;
		
		@Column(name = "landmark")
		private String landmark;
		
		@Column(name = "area")
		private String area;
		
		@Column(name = "city")
		private String city;
		
		@Column(name = "state")
		private String state;
		
		@Column(name = "country")
		private String country;
		
		@Column(name = "postal_code")
		private String postalCode;
		
		@Column(name = "full_address", columnDefinition = "LONGTEXT DEFAULT NULL")
		private String fullAddress;
		
		@Column(name = "latitudes")
		private double latitudes;
		
		@Column(name = "longitudes")
		private double longitudes ;
		
		@Column(name = "type" , nullable=true)
		private String type;

		/**
		 * @return the addressId
		 */
		public Long getAddressId() {
			return addressId;
		}

		/**
		 * @param addressId the addressId to set
		 */
		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}

		/**
		 * @return the line1
		 */
		public String getLine1() {
			return line1;
		}

		/**
		 * @param line1 the line1 to set
		 */
		public void setLine1(String line1) {
			this.line1 = line1;
		}



		/**
		 * @return the line2
		 */
		public String getLine2() {
			return line2;
		}

		/**
		 * @param line2 the line2 to set
		 */
		public void setLine2(String line2) {
			this.line2 = line2;
		}

		/**
		 * @return the landmark
		 */
		public String getLandmark() {
			return landmark;
		}

		/**
		 * @param landmark the landmark to set
		 */
		public void setLandmark(String landmark) {
			this.landmark = landmark;
		}

		/**
		 * @return the area
		 */
		public String getArea() {
			return area;
		}

		/**
		 * @param area the area to set
		 */
		public void setArea(String area) {
			this.area = area;
		}

		/**
		 * @return the city
		 */
		public String getCity() {
			return city;
		}

		/**
		 * @param city the city to set
		 */
		public void setCity(String city) {
			this.city = city;
		}

		/**
		 * @return the state
		 */
		public String getState() {
			return state;
		}

		/**
		 * @param state the state to set
		 */
		public void setState(String state) {
			this.state = state;
		}

		/**
		 * @return the country
		 */
		public String getCountry() {
			return country;
		}

		/**
		 * @param country the country to set
		 */
		public void setCountry(String country) {
			this.country = country;
		}

		/**
		 * @return the postalCode
		 */
		public String getPostalCode() {
			return postalCode;
		}

		/**
		 * @param postalCode the postalCode to set
		 */
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		/**
		 * @return the fullAddress
		 */
		public String getFullAddress() {
			return fullAddress;
		}

		/**
		 * @param fullAddress the fullAddress to set
		 */
		public void setFullAddress(String fullAddress) {
			this.fullAddress = fullAddress;
		}

		/**
		 * @return the latitudes
		 */
		public double getLatitudes() {
			return latitudes;
		}

		/**
		 * @param latitudes the latitudes to set
		 */
		public void setLatitudes(double latitudes) {
			this.latitudes = latitudes;
		}

		/**
		 * @return the longitudes
		 */
		public double getLongitudes() {
			return longitudes;
		}

		/**
		 * @param longitudes the longitudes to set
		 */
		public void setLongitudes(double longitudes) {
			this.longitudes = longitudes;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}
		
		
		
		
	
	}
