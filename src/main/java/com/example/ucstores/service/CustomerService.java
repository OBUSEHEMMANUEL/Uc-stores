package com.example.ucstores.service;

import com.example.ucstores.data.models.Customer;
import com.example.ucstores.dtos.request.LoginRequest;
import com.example.ucstores.dtos.request.ProductPurchaseRequest;
import com.example.ucstores.dtos.request.UpdateRequest;
import com.example.ucstores.dtos.request.UserRegistrationRequest;
import com.example.ucstores.dtos.response.CustomerResponse;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    CustomerResponse registration(UserRegistrationRequest request);
    CustomerResponse login(LoginRequest request);
    CustomerResponse update(UpdateRequest request);
    List<Customer> getAllCustomer();
    CustomerResponse order(ProductPurchaseRequest purchaseRequest);

    CustomerResponse delete(String id);

}
