package com.example.medilinkbe.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;


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

	 private List<ImageData> images = new ArrayList<>(); 

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

	 public List<ImageData> getImages() {
		 return images;
	 }

	 public void setImages(List<ImageData> images) {
		 this.images = images;
	 }

	 // Inner class to store image data
    public static class ImageData {
        private String url;       
        private String publicId;  

        public ImageData(String url, String publicId) {
            this.url = url;
            this.publicId = publicId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPublicId() {
            return publicId;
        }

        public void setPublicId(String publicId) {
            this.publicId = publicId;
        }
    }
	 
}
