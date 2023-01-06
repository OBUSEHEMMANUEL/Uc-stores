package com.example.ucstores.dtos.request;

import lombok.Data;

@Data
public class ProductPurchaseRequest {
    private String customerId;
    private String ProductId;
    private int quantity;
}
