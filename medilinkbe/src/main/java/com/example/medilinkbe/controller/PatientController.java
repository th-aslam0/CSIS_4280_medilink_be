package com.example.medilinkbe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.medilinkbe.model.PatientDTO;
import com.example.medilinkbe.repository.PatientRepository;

@RestController
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepo;
	
	@GetMapping("/patients")
	public ResponseEntity<?> getAllPatients(){
		List<PatientDTO> patients = patientRepo.findAll();
		if(patients.size() > 0) {
			return new ResponseEntity<List<PatientDTO>>(patients, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No patients available", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/patients")
	public ResponseEntity<?> createPatient(@RequestBody PatientDTO patien) {
		try {
			patientRepo.save(patien);
			return new ResponseEntity<PatientDTO>(patien, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/patients/{id}")
	public ResponseEntity<?> getPatient(@PathVariable("id") String id) {
		Optional<PatientDTO> patientOptional = patientRepo.findById(id);
		if(patientOptional.isPresent()) {
			return new ResponseEntity<>(patientOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Patient not found with id " + id, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/patients/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody PatientDTO patient) {
		Optional<PatientDTO> patientOptional = patientRepo.findById(id);
		if(patientOptional.isPresent()) {
			PatientDTO patientToSave = patientOptional.get();
			patientToSave.setName(patient.getName() != null ? patient.getName() : patientToSave.getName());
			patientToSave.setEmail(patient.getEmail() != null ? patient.getEmail() : patientToSave.getEmail());
			patientToSave.setPhone(patient.getPhone() != null ? patient.getPhone() : patientToSave.getPhone());
			patientRepo.save(patientToSave);
			return new ResponseEntity<>(patientToSave, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Patient not found with id " + id, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		try {
			patientRepo.deleteById(id);
			return new ResponseEntity<>("Successfully deleted with id: " + id, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}

