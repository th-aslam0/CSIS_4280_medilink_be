package com.example.medilinkbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.medilinkbe.model.Doctor;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {

	
}
