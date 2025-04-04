package com.example.medilinkbe.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "doctors")

public class DoctorMG {
	
	@Id
	private String id;
	private String first_name;
	private String last_name;
	private String specialization;
	private String email;
	private String phone_number;
	private OfficeAddress office_address;
	private List<Availability> availability;
	private String consultationFee;
	private String image;
	
		
	public DoctorMG(String id, String firstName, String lastName, String specialization, String email,
			String phoneNumber, OfficeAddress officeAddress, List<Availability> availability, String consultationFee,
			String image) {
		super();
		this.id = id;
		this.first_name = firstName;
		this.last_name = lastName;
		this.specialization = specialization;
		this.email = email;
		this.phone_number = phoneNumber;
		this.office_address = officeAddress;
		this.availability = availability;
		this.consultationFee = consultationFee;
		this.image = image;
	}
		
	
	public DoctorMG() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String lastName) {
		this.last_name = lastName;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phone_number;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phone_number = phoneNumber;
	}
	public OfficeAddress getOfficeAddress() {
		return office_address;
	}
	public void setOfficeAddress(OfficeAddress officeAddress) {
		this.office_address = officeAddress;
	}
	public List<Availability> getAvailability() {
		return availability;
	}
	public void setAvailability(List<Availability> availability) {
		this.availability = availability;
	}
	public String getConsultationFee() {
		return consultationFee;
	}
	public void setConsultationFee(String consultationFee) {
		this.consultationFee = consultationFee;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	

}

