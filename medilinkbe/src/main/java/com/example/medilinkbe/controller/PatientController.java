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
import com.example.medilinkbe.model.Appointment;
import com.example.medilinkbe.model.PatientDTO;
import com.example.medilinkbe.model.ApiResponse;
import com.example.medilinkbe.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolationException;

@RequestMapping("/api")
@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	
	@Operation(summary = "Get all patients")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully get a list of patients", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class)) }),
//			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
//					content = @Content),
			@ApiResponse(responseCode = "404", description = "Patients not found", 
			content = @Content)
			})
	@GetMapping("/patients")
	public ResponseEntity<?> getAllPatients() {
		List<PatientDTO> patients = patientService.getAllPatients();
		if(patients.size() > 0) {
			return new ResponseEntity<>(new ApiResponse<>(true, "Patients retrieved successfully", patients), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiResponse<>(false, "No patients found", patients), HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Create a new patient")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully created a new patient", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class)) }),
//			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
//					content = @Content),
//			@ApiResponse(responseCode = "404", description = "Appointment not found", 
//			content = @Content)
			})
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

	
	@Operation(summary = "Get a patient by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully get a patient", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Patient not found", 
			content = @Content)
			})
	@GetMapping("/patients/{id}")
	public ResponseEntity<?> getPatient(@PathVariable("id") String id) {
		try {
			Object patient = patientService.getSinglePatient(id);
			return new ResponseEntity<>(new ApiResponse<>(true, "Patient retrieved successfully", patient), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);
		}
	}

	
	@Operation(summary = "Update a patient by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully updated a patient", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Patient not found", 
			content = @Content)
			})
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

	
	@Operation(summary = "Delete a patient by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully deleted a patient", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Patient not found", 
			content = @Content)
			})
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
