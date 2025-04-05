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

import com.example.medilinkbe.model.Appointment;
import com.example.medilinkbe.service.AppointmentService;

@RequestMapping("/api/appointments")
@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	// GET all appointments
    @GetMapping
    public ResponseEntity<?> listAppointments() {
        List<Appointment> appointments = appointmentService.listAppointment();
        if (!appointments.isEmpty()) {
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No appointments found", HttpStatus.NOT_FOUND);
        }
    }
    
 // GET by patientId
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getByPatientId(@PathVariable String patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentByPatientId(patientId);
        if (!appointments.isEmpty()) {
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No appointments found for patientId: " + patientId, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getByDoctorId(@PathVariable String doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentByDoctorId(doctorId);
        if (!appointments.isEmpty()) {
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No appointments found for doctorId: " + doctorId, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            Appointment appointment = appointmentService.getAppointment(id);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
 // CREATE
    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
    }
    
 // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Appointment appointment) {
        try {
            return new ResponseEntity<>(appointmentService.updateAppointment(id, appointment), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
 // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>("Appointment with ID " + id + " deleted", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
	

}
