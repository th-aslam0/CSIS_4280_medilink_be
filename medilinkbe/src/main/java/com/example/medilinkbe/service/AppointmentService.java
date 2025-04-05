package com.example.medilinkbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medilinkbe.exception.NotFoundException;
import com.example.medilinkbe.model.Appointment;
import com.example.medilinkbe.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;
	
	public List<Appointment> listAppointment(){
		return appointmentRepo.findAll();
	}
	
	public Appointment getAppointment(String id) {
		return appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
	}
	
	public Appointment createAppointment(Appointment app) {
		return appointmentRepo.save(app);
	}
	
	
    public Appointment updateAppointment( String id, Appointment updatedAppointment) {
        Appointment existing = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));

        existing = updatedAppointment;

        return appointmentRepo.save(existing);
    }
    
    public String deleteAppointment( String id) {
        appointmentRepo.deleteById(id);
        return "Appointment with ID " + id + " deleted successfully.";
    }
    
    //get appointment by doctor id
    public List<Appointment> getAppointmentByDoctorId(String doctorId) {
    	List<Appointment> listApp = appointmentRepo.findByDoctorId(doctorId);
    	if(listApp.isEmpty()) {
    		throw new NotFoundException("This doctor dont have any appointments");
    	} else {
    		return listApp;
    	}
    }
    
    //get appointments by patient id
    public List<Appointment> getAppointmentByPatientId(String patientId) {
    	List<Appointment> listApp = appointmentRepo.findByPatientId(patientId);
    	if(listApp.isEmpty()) {
    		throw new NotFoundException("This patient dont have any appointments");
    	} else {
    		return listApp;
    	}
    }	
    
    
}
