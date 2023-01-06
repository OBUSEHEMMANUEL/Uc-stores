package com.example.ucstores.dtos.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String id;
    private String password;
    private String emailAddress;
}
