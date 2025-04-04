package com.example.medilinkbe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medilinkbe.exception.PatientCollectionException;
import com.example.medilinkbe.model.PatientDTO;
import com.example.medilinkbe.repository.PatientRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patientRepo;

	@Override
	public void createPatient(PatientDTO patient) throws ConstraintViolationException, PatientCollectionException {
		Optional<PatientDTO> patientOptional = patientRepo.findByEmail(patient.getEmail());
		if(patientOptional.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.PatientAlreadyExists());
		} else {
			patientRepo.save(patient);
		}
	}

	@Override
	public List<PatientDTO> getAllPatients() {
		List<PatientDTO> patients = patientRepo.findAll();
		if(patients.size() > 0) {
			return patients;
		} else {
			return new ArrayList<PatientDTO>();
		}
	}

	@Override
	public PatientDTO getSinglePatient(String id) throws PatientCollectionException {
		Optional<PatientDTO> optionalPatient = patientRepo.findById(id);
		if(!optionalPatient.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.NotFoundException(id));
		} else {
			return optionalPatient.get();
		}
	}

	@Override
	public void updatePatient(String id, PatientDTO patient) throws PatientCollectionException {
		Optional<PatientDTO> patientWithId = patientRepo.findById(id);
		Optional<PatientDTO> patientWithSameEmail = patientRepo.findByEmail(patient.getEmail());
		if(patientWithId.isPresent()) {
			
			if(patientWithSameEmail.isPresent() && !patientWithSameEmail.get().getId().equals(id)) {
				throw new PatientCollectionException(PatientCollectionException.PatientAlreadyExists());
			}
			
			PatientDTO patientToUpdate = patientWithId.get();
			patientToUpdate.setName(patient.getName());
			patientToUpdate.setEmail(patient.getEmail());
			patientToUpdate.setPhone(patient.getPhone());
			patientRepo.save(patientToUpdate);
		} else {
			throw new PatientCollectionException(PatientCollectionException.NotFoundException(id));
		}
	}

	@Override
	public void deletePatientById(String id) throws PatientCollectionException {

		Optional<PatientDTO> patientOptional = patientRepo.findById(id);
		
		if(!patientOptional.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.NotFoundException(id));
		}else {
			patientRepo.deleteById(id);
		}
	}

}
