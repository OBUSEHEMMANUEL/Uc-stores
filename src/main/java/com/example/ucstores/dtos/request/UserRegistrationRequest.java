package com.example.ucstores.dtos.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String password;
    private String emailAddress;
    private String phoneNumber;
}
