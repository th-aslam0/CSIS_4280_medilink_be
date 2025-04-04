package com.example.medilinkbe.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medilinkbe.exception.AlreadyExistsException;
import com.example.medilinkbe.exception.NotFoundException;
import com.example.medilinkbe.model.Doctor;
import com.example.medilinkbe.model.DoctorMG;
import com.example.medilinkbe.repository.DoctorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepo;
	
	public List<DoctorMG> listDoctors() {
		return doctorRepo.findAll();
	}	
	
    public DoctorMG getDoctorById(String id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));
    }
    
    public DoctorMG updateDoctor( String id,  DoctorMG updatedDoctor) {
        DoctorMG existing = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));

        existing = updatedDoctor;

        return doctorRepo.save(existing);
    }	
	

	public DoctorMG addDoctor(DoctorMG doctor) {
		return doctorRepo.save(doctor);
	}
	
	public String deleteDoctor( String id) {
        doctorRepo.deleteById(id);
        return "Doctor with ID " + id + " deleted successfully.";
    }



}
