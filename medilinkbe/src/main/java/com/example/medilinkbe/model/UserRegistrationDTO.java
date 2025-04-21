package com.example.medilinkbe.model;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String homeAddress;
    private String dateOfBirth;
    private String phoneNumber;
    private String maritalStatus;
    private String gender;
    private String password;
    private String confirmPassword;
}
