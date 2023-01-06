package com.example.ucstores.dtos.request;

import com.example.ucstores.data.models.Categories;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateProductRequest {
    private String productId;
    private String name;
    private BigDecimal price;
    private Categories categories;
    private int quantity;

}
