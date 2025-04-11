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

    @NotNull(message="first name cannot be null")
    private String firstName;

    @NotNull(message="last name cannot be null")
    private String lastName;

    @NotNull(message="email cannot be null")
    private String email;

    @NotNull(message="home address cannot be null")
    private String homeAddress;

    @NotNull(message="date of birth cannot be null")
    private String dateOfBirth; // or LocalDate if preferred

    @NotNull(message="phone number cannot be null")
    private String phoneNumber;

    private String maritalStatus;
    private String gender;

    private List<ImageData> images = new ArrayList<>();

    public PatientDTO() {
        super();
    }

    public PatientDTO(String id, 
                      @NotNull(message="first name cannot be null") String firstName,
                      @NotNull(message="last name cannot be null") String lastName,
                      @NotNull(message="email cannot be null") String email,
                      @NotNull(message="home address cannot be null") String homeAddress,
                      @NotNull(message="date of birth cannot be null") String dateOfBirth,
                      @NotNull(message="phone number cannot be null") String phoneNumber) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.homeAddress = homeAddress;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
