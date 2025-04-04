package com.example.medilinkbe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="patients")
public class PatientDTO {
	 public PatientDTO() {
		super();
	}

	public PatientDTO(String id, @NotNull(message = "name cannot be null") String name,
			@NotNull(message = "email cannot be null") String email,
			@NotNull(message = "phone cannot be null") String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Id
	 private String id;
	 
	 @NotNull(message="name cannot be null")
	 private String name;
	 
	 @NotNull(message="email cannot be null")
	 private String email;
	 
	 @NotNull(message="phone cannot be null")
	 private String phone;
}
