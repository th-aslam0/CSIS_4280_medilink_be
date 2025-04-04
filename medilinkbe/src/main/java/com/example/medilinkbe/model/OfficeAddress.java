package com.example.medilinkbe.model;

import lombok.Getter;
import lombok.Setter;

public class OfficeAddress {

	private String street;
	private String city;
	private String province;
	private String postalCode;	
	
	public OfficeAddress() {
		super();
	}
	
	public OfficeAddress(String street, String city, String province, String postalCode) {
		super();
		this.street = street;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
}
