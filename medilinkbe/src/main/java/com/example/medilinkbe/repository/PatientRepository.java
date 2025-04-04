package com.example.medilinkbe.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medilinkbe.model.PatientDTO;

@Repository
public interface PatientRepository extends MongoRepository<PatientDTO, String> {
	
	@Query("{'email': ?0}")
	Optional<PatientDTO> findByEmail(String email);
}
