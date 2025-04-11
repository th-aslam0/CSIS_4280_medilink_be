package com.example.medilinkbe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointments")
public class Appointment {
	@Id
	private String id;
	private String patientId;
	private String doctorId;
	private String date; //yyyy-mm-dd
	private String patientDesc;	
	private String status;
	private String fee;
	private String prescription;
	private String specialization;
	
	public Appointment() {
		super();
	}
	

	public Appointment(String id, String patientId, String doctorId, String date, String patientDesc, String status,
			String fee, String prescription, String specialization) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.patientDesc = patientDesc;
		this.status = status;
		this.fee = fee;
		this.prescription = prescription;
		this.specialization = specialization;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPatientDesc() {
		return patientDesc;
	}
	public void setPatientDesc(String patientDocu) {
		this.patientDesc = patientDocu;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getFee() {
		return fee;
	}


	public void setFee(String fee) {
		this.fee = fee;
	}


	public String getPrescription() {
		return prescription;
	}


	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}


	public String getSpecialization() {
		return specialization;
	}


	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	
	

}
