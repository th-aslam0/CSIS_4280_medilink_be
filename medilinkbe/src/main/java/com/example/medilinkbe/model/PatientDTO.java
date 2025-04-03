package com.example.medilinkbe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	 private String name;
	 private String email;
	 private String phone;
}
