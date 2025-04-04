package com.example.medilinkbe.model;

import lombok.Getter;
import lombok.Setter;

public class Availability {

	private String day;
	private String hours;
	
	public Availability() {
		super();
	}
	
	public Availability(String day, String hours) {
		super();
		this.day = day;
		this.hours = hours;
	}

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	
		
	
}
