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
import com.example.medilinkbe.repository.PatientRepository;
import com.example.medilinkbe.service.PatientService;

import jakarta.validation.ConstraintViolationException;

@RequestMapping("/api")
@RestController
public class PatientController {

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private PatientService patientService;

	@GetMapping("/patients")
	public ResponseEntity<?> getAllPatients() {
		List<PatientDTO> patients = patientService.getAllPatients();
		return new ResponseEntity<>(patients, patients.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PostMapping("/patients")
	public ResponseEntity<?> createPatient(@RequestBody PatientDTO patient) {
		try {
			patientService.createPatient(patient);
			return new ResponseEntity<PatientDTO>(patient, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (PatientCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/patients/{id}")
	public ResponseEntity<?> getPatient(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(patientService.getSinglePatient(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

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

	@DeleteMapping("/patients/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		try {
			patientService.deletePatientById(id);
			return new ResponseEntity<>("Successfully deleted with id: " + id, HttpStatus.OK);
		} catch (PatientCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/patients/{id}/upload")
	public ResponseEntity<?> uploadImage(
			@PathVariable String id,
			@RequestParam("file") MultipartFile file) {
		try {
			PatientDTO updatedPatient = patientService.uploadImage(id, file);
			return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/patients/{id}/images/{imageId}")
	public ResponseEntity<?> deleteImage(
	        @PathVariable("id") String patientId,
	        @PathVariable("imageId") String imageId) {
	    try {
	        patientService.deleteImage(patientId, imageId);
	        return new ResponseEntity<>("Image with ID " + imageId + " successfully deleted for patient with ID " + patientId, HttpStatus.OK);
	    } catch (PatientCollectionException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
