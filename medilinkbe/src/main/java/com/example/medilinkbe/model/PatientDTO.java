package com.example.medilinkbe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="patients")
public class PatientDTO {
	 @Id
	 private String id;
	 
	 @NotNull(message="name cannot be null")
	 private String name;
	 
	 @NotNull(message="email cannot be null")
	 private String email;
	 
	 @NotNull(message="phone cannot be null")
	 private String phone;
}
