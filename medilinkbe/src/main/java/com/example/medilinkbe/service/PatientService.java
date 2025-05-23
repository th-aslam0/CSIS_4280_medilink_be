package com.example.medilinkbe.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.medilinkbe.exception.PatientCollectionException;
import com.example.medilinkbe.model.PatientDTO;

import jakarta.validation.ConstraintViolationException;

public interface PatientService {

	public void createPatient(PatientDTO patient) throws ConstraintViolationException, PatientCollectionException;
	
	public List<PatientDTO> getAllPatients();
	
	public PatientDTO getSinglePatient(String id) throws PatientCollectionException;
	
	public void updatePatient(String id, PatientDTO patient) throws PatientCollectionException;
	
	public void deletePatientById(String id) throws PatientCollectionException;

	public PatientDTO uploadImage(String id, MultipartFile file) throws Exception;

	public void deleteImage(String patientId, String imageId) throws PatientCollectionException;
}
