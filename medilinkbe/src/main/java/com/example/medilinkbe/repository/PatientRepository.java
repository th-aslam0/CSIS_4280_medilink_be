package com.example.medilinkbe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.medilinkbe.model.PatientDTO;

@Repository
public interface PatientRepository extends MongoRepository<PatientDTO, String> {
	
}
