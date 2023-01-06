package com.example.ucstores.dtos.request;

import lombok.Data;

@Data
public class VendorUpdateRequest {
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String storeName;
}
