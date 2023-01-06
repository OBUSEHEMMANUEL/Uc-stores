package com.example.ucstores.dtos.request;

import com.example.ucstores.data.models.Categories;
import com.example.ucstores.data.models.Product;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private String vendorId;
    private String name;
    private BigDecimal price;
    private Categories categories;
    private int quantity;
}
