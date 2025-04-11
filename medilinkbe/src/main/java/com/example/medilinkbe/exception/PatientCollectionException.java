package com.example.medilinkbe.exception;

public class PatientCollectionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PatientCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Patient with id " + id + " not found.";
	}
	
	public static String PatientAlreadyExists() {
		return "Patient with given email already exists.";
	}

}
