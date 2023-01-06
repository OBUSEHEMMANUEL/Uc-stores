package com.example.ucstores.dtos.request;

import lombok.Data;

@Data
public class VendorLoginRequest {
    private String id;
    private String password;
    private String emailAddress;
}
