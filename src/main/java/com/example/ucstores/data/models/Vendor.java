package com.example.ucstores.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Document
public class Vendor extends User{
    @Id
    private String vendorId;
    private String storeName;
    private String address;
    private String firstName;
    private String lastName;
    @DBRef
    private List<Product> addProducts = new ArrayList<>();
}
