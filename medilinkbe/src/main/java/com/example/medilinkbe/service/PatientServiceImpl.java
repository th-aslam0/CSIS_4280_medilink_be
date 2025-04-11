package com.example.medilinkbe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.medilinkbe.exception.PatientCollectionException;
import com.example.medilinkbe.model.PatientDTO;
import com.example.medilinkbe.repository.PatientRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private Cloudinary cloudinary;

	@Override
	public void createPatient(PatientDTO patient) throws ConstraintViolationException, PatientCollectionException {
		Optional<PatientDTO> patientOptional = patientRepo.findByEmail(patient.getEmail());
		if (patientOptional.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.PatientAlreadyExists());
		} else {
			patientRepo.save(patient);
		}
	}

	@Override
	public List<PatientDTO> getAllPatients() {
		List<PatientDTO> patients = patientRepo.findAll();
		if (patients.size() > 0) {
			return patients;
		} else {
			return new ArrayList<PatientDTO>();
		}
	}

	@Override
	public PatientDTO getSinglePatient(String id) throws PatientCollectionException {
		Optional<PatientDTO> optionalPatient = patientRepo.findById(id);
		if (!optionalPatient.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.NotFoundException(id));
		} else {
			return optionalPatient.get();
		}
	}

	@Override
	public void updatePatient(String id, PatientDTO patient) throws PatientCollectionException {
		Optional<PatientDTO> patientWithId = patientRepo.findById(id);
		Optional<PatientDTO> patientWithSameEmail = patientRepo.findByEmail(patient.getEmail());
		if (patientWithId.isPresent()) {
			if (patientWithSameEmail.isPresent() && !patientWithSameEmail.get().getId().equals(id)) {
				throw new PatientCollectionException(PatientCollectionException.PatientAlreadyExists());
			}
			PatientDTO patientToUpdate = patientWithId.get();

			if (patient.getFirstName() != null)
				patientToUpdate.setFirstName(patient.getFirstName());
			if (patient.getLastName() != null)
				patientToUpdate.setLastName(patient.getLastName());
			if (patient.getEmail() != null)
				patientToUpdate.setEmail(patient.getEmail());
			if (patient.getHomeAddress() != null)
				patientToUpdate.setHomeAddress(patient.getHomeAddress());
			if (patient.getDateOfBirth() != null)
				patientToUpdate.setDateOfBirth(patient.getDateOfBirth());
			if (patient.getPhoneNumber() != null)
				patientToUpdate.setPhoneNumber(patient.getPhoneNumber());
			if (patient.getMaritalStatus() != null)
				patientToUpdate.setMaritalStatus(patient.getMaritalStatus());
			if (patient.getGender() != null)
				patientToUpdate.setGender(patient.getGender());

			patientRepo.save(patientToUpdate);
		} else {
			throw new PatientCollectionException(PatientCollectionException.NotFoundException(id));
		}
	}

	@Override
	public void deletePatientById(String id) throws PatientCollectionException {

		Optional<PatientDTO> patientOptional = patientRepo.findById(id);

		if (!patientOptional.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.NotFoundException(id));
		} else {
			patientRepo.deleteById(id);
		}
	}

	@Override
	public PatientDTO uploadImage(String id, MultipartFile file) throws Exception {
		Optional<PatientDTO> optionalPatient = patientRepo.findById(id);
		if (!optionalPatient.isPresent()) {
			throw new PatientCollectionException(PatientCollectionException.NotFoundException(id));
		}

		Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		String imageUrl = (String) result.get("secure_url");
		String publicId = (String) result.get("public_id");

		PatientDTO patient = optionalPatient.get();
		patient.getImages().add(new PatientDTO.ImageData(imageUrl, publicId));
		patientRepo.save(patient);

		return patient;
	}

	@Override
	public void deleteImage(String patientId, String imageId) throws PatientCollectionException {
		Optional<PatientDTO> optionalPatient = patientRepo.findById(patientId);

		if (!optionalPatient.isPresent()) {
			throw new PatientCollectionException("Patient with ID " + patientId + " not found.");
		}

		PatientDTO patient = optionalPatient.get();
		List<PatientDTO.ImageData> images = patient.getImages();

		PatientDTO.ImageData imageToDelete = null;
		for (PatientDTO.ImageData image : images) {
			if (image.getPublicId().equals(imageId)) {
				imageToDelete = image;
				break;
			}
		}

		if (imageToDelete == null) {
			throw new PatientCollectionException("Image with ID " + imageId + " not found for patient with ID " + patientId + ".");
		}

		try {
			Map<?, ?> result = cloudinary.uploader().destroy(imageToDelete.getPublicId(), ObjectUtils.emptyMap());
			if (!"ok".equals(result.get("result"))) {
				throw new Exception("Failed to delete image from Cloudinary.");
			}
		} catch (Exception e) {
			throw new PatientCollectionException("Error deleting image from Cloudinary: " + e.getMessage());
		}

		images.remove(imageToDelete);
		patient.setImages(images);
		patientRepo.save(patient);
	}

}