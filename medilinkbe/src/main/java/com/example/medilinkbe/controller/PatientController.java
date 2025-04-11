package com.example.medilinkbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.medilinkbe.exception.PatientCollectionException;
import com.example.medilinkbe.model.PatientDTO;
import com.example.medilinkbe.model.ApiResponse;
import com.example.medilinkbe.service.PatientService;

import jakarta.validation.ConstraintViolationException;

@RequestMapping("/api")
@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/patients")
	public ResponseEntity<?> getAllPatients() {
		List<PatientDTO> patients = patientService.getAllPatients();
		if(patients.size() > 0) {
			return new ResponseEntity<>(new ApiResponse<>(true, "Patients retrieved successfully", patients), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiResponse<>(false, "No patients found", patients), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/patients")
	public ResponseEntity<?> createPatient(@RequestBody PatientDTO patient) {
		try {
			patientService.createPatient(patient);
			return new ResponseEntity<>(new ApiResponse<>(true, "Patient created successfully", patient), HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (PatientCollectionException e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/patients/{id}")
	public ResponseEntity<?> getPatient(@PathVariable("id") String id) {
		try {
			Object patient = patientService.getSinglePatient(id);
			return new ResponseEntity<>(new ApiResponse<>(true, "Patient retrieved successfully", patient), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/patients/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody PatientDTO patient) {
		try {
			patientService.updatePatient(id, patient);
			return new ResponseEntity<>(new ApiResponse<>(true, "Patient updated with id " + id, null), HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (PatientCollectionException e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/patients/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		try {
			patientService.deletePatientById(id);
			return new ResponseEntity<>(new ApiResponse<>(true, "Successfully deleted patient with id: " + id, null), HttpStatus.OK);
		} catch (PatientCollectionException e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/patients/{id}/upload")
	public ResponseEntity<?> uploadImage(
			@PathVariable String id,
			@RequestParam("file") MultipartFile file) {
		try {
			PatientDTO updatedPatient = patientService.uploadImage(id, file);
			return new ResponseEntity<>(new ApiResponse<>(true, "Image uploaded successfully", updatedPatient), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/patients/{id}/images/{imageId}")
	public ResponseEntity<?> deleteImage(
	        @PathVariable("id") String patientId,
	        @PathVariable("imageId") String imageId) {
	    try {
	        patientService.deleteImage(patientId, imageId);
	        return new ResponseEntity<>(new ApiResponse<>(true, "Image with ID " + imageId + " deleted for patient with ID " + patientId, null), HttpStatus.OK);
	    } catch (PatientCollectionException e) {
	        return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
