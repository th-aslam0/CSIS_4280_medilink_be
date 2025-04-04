package com.example.medilinkbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.medilinkbe.model.Doctor;
import com.example.medilinkbe.model.DoctorMG;

@Repository
public interface DoctorRepository extends MongoRepository<DoctorMG, String> {

	
}
