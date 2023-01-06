package com.example.ucstores.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Data
@Document
public class Product {
    @Id
    private String  productId;
    private String vendorId;
    private String name;
    private Categories categories;
    private int quantity;
    private BigDecimal price;
}
