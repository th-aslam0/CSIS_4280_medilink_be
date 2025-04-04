package com.example.medilinkbe.model;

import java.util.List;

public record Doctor(
		String id,
	    String firstName,
	    String lastName,
	    String specialization,
	    String email,
	    String phoneNumber,
	    //OfficeAddress officeAddress,
	    //List<Availability> availability,
	    int consultationFee,
	    String image
	) {
	    
	    //public record OfficeAddress(String street, String city, String province, String postalCode) {}
	    
	    //public record Availability(String day, String hours) {}
	}
    
   
    

