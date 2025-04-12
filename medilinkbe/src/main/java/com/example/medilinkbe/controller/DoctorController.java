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

import com.example.medilinkbe.model.Doctor;
import com.example.medilinkbe.repository.DoctorRepository;
import com.example.medilinkbe.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/api")
@RestController
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	
	
	@Operation(summary = "Get all doctors")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found a list of doctors", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Doctor.class)) }),
//			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
//					content = @Content),
//			@ApiResponse(responseCode = "404", description = "Player not found", 
//			content = @Content)
			})	
	@GetMapping("/doctors")
	public ResponseEntity<?> listDoctors() {
		List<Doctor> doctors = doctorService.listDoctors();
		if(doctors.size()>0) {
			return new ResponseEntity<List<Doctor>>(doctors,HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No doctors found",HttpStatus.NOT_FOUND);
		}
	}
	
	// READ ONE
	@Operation(summary = "Get a doctor by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found doctor by Id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Doctor.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Doctor not found", 
			content = @Content)
			})
    @GetMapping("/doctors/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable("id") String id) {
        Doctor doctor = doctorService.getDoctorById(id);
        
        if(doctor != null) {
        	return new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
        } else {
        	return new ResponseEntity<>("No doctors found",HttpStatus.NOT_FOUND);
        }
    }	

	
	@Operation(summary = "Add a new doctor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully added a doctor", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Doctor.class)) }),
//			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
//					content = @Content),
//			@ApiResponse(responseCode = "404", description = "Doctor not found", 
//			content = @Content)
			})
	@PostMapping("/doctors")
	public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) {
		try {
			doctorService.addDoctor(doctor);
			return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Delete a doctor by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully deleted a doctor by Id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Doctor.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Doctor not found", 
			content = @Content)
			})
	@DeleteMapping("doctors/{id}")
	public void deleteDoctor(@PathVariable("id") String id) {
		doctorService.deleteDoctor(id);
	}
	
	@Operation(summary = "Update an existing doctor by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully updated a doctor by Id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Doctor.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID supplied", 
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Doctor not found", 
			content = @Content)
			})
	@PutMapping("/doctors/{id}")
	public void updateDoctor(@PathVariable("id") String id, @RequestBody Doctor doctor) {
		doctorService.updateDoctor(id,doctor);
	}
	

}
