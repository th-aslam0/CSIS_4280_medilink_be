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
import org.springframework.web.bind.annotation.RestController;

import com.example.medilinkbe.exception.PatientCollectionException;
import com.example.medilinkbe.model.Appointment;
import com.example.medilinkbe.model.PatientDTO;
import com.example.medilinkbe.repository.PatientRepository;
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
	private PatientRepository patientRepo;
	
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
	public ResponseEntity<?> getAllPatients(){
		List<PatientDTO> patients = patientService.getAllPatients();
		return new ResponseEntity<>(patients, patients.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
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
			return new ResponseEntity<PatientDTO>(patient, HttpStatus.OK);
		} catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch(PatientCollectionException e) {
			return new ResponseEntity<> (e.getMessage(), HttpStatus.CONFLICT);
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
			return new ResponseEntity<>(patientService.getSinglePatient(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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
			return new ResponseEntity<>("Update Patient with id " + id, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (PatientCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		try {
			patientService.deletePatientById(id);
			return new ResponseEntity<>("Successfully deleted with id: " + id, HttpStatus.OK);
		} catch(PatientCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}

