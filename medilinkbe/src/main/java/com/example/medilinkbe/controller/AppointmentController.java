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

import com.example.medilinkbe.model.ApiResponse;
import com.example.medilinkbe.model.Appointment;
import com.example.medilinkbe.model.Doctor;
import com.example.medilinkbe.model.PatientDTO;
import com.example.medilinkbe.service.AppointmentService;
import com.example.medilinkbe.service.DoctorService;
import com.example.medilinkbe.service.EmailService;
import com.example.medilinkbe.service.PatientService;

@RequestMapping("/api/appointments")
@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private EmailService emailService;
	
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
    public ResponseEntity<?> getByPatientId(@PathVariable("patientId") String patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentByPatientId(patientId);
        if (!appointments.isEmpty()) {
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No appointments found for patientId: " + patientId, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getByDoctorId(@PathVariable("doctorId") String doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentByDoctorId(doctorId);
        if (!appointments.isEmpty()) {
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No appointments found for doctorId: " + doctorId, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            Appointment appointment = appointmentService.getAppointment(id);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
 // CREATE
    @PostMapping("/book")
    public ResponseEntity<?> create(@RequestBody Appointment appointment) {
        try {
            // Create the appointment
            Appointment createdAppointment = appointmentService.createAppointment(appointment);

            // Fetch patient details using patientId
            String patientId = createdAppointment.getPatientId();
            PatientDTO patient = patientService.getSinglePatient(patientId);

            // Fetch doctor details using doctorId
            String doctorId = createdAppointment.getDoctorId();
            Doctor doctor = doctorService.getDoctorById(doctorId);

            // Send confirmation email
            String patientEmail = patient.getEmail();
            String patientName = patient.getFirstName() + " " + patient.getLastName();
            String doctorFirstName = doctor.getFirstName();
            String subject = "Appointment Confirmation";
            String body = "Dear " + patientName + ",\n\n" +
                        "Your appointment with " + doctorFirstName +
                        " on " + createdAppointment.getDate() +
                        " has been successfully scheduled.\n\n" +
                        "Thank you,\nMediLink Team";

            emailService.sendConfirmationEmail(patientEmail, subject, body);

            return new ResponseEntity<>(new ApiResponse<>(true, "Appointment created successfully", createdAppointment), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Appointment appointment) {
        try {
            return new ResponseEntity<>(appointmentService.updateAppointment(id, appointment), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
 // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        try {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>("Appointment with ID " + id + " deleted", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
	

}
