package com.example.medilinkbe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointments")
public class Appointment {
	@Id
	private String id;
	private String patientId;
	private String doctorId;
	private String date;
	private String patientDocu;
	private String doctorDocu;
	private String status;
	
	public Appointment() {
		super();
	}
	
	public Appointment(String id, String patientId, String doctorId, String date, String patientDocu,
			String doctorDocu, String status) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.patientDocu = patientDocu;
		this.doctorDocu = doctorDocu;
		this.status = status;
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
	public String getPatientDocu() {
		return patientDocu;
	}
	public void setPatientDocu(String patientDocu) {
		this.patientDocu = patientDocu;
	}
	public String getDoctorDocu() {
		return doctorDocu;
	}
	public void setDoctorDocu(String doctorDocu) {
		this.doctorDocu = doctorDocu;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
