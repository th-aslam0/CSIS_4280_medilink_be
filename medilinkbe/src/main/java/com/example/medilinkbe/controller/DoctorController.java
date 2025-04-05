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

@RequestMapping("/api")
@RestController
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
		
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable String id) {
        Doctor doctor = doctorService.getDoctorById(id);
        
        if(doctor != null) {
        	return new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
        } else {
        	return new ResponseEntity<>("No doctors found",HttpStatus.NOT_FOUND);
        }
    }	

	@PostMapping("/doctors")
	public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) {
		try {
			doctorService.addDoctor(doctor);
			return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("doctors/{id}")
	public void deleteDoctor(@PathVariable String id) {
		doctorService.deleteDoctor(id);
	}
	
	
	@PutMapping("/doctors/{id}")
	public void updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
		doctorService.updateDoctor(id,doctor);
	}
	

}
