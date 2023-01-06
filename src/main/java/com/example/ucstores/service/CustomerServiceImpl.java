package com.example.ucstores.service;

import com.example.ucstores.data.exception.CustomerException;
import com.example.ucstores.data.models.Customer;
import com.example.ucstores.data.models.User;
import com.example.ucstores.data.repository.CustomerRepository;
import com.example.ucstores.data.repository.ProductRepository;
import com.example.ucstores.dtos.request.LoginRequest;
import com.example.ucstores.dtos.request.ProductPurchaseRequest;
import com.example.ucstores.dtos.request.UpdateRequest;
import com.example.ucstores.dtos.request.UserRegistrationRequest;
import com.example.ucstores.dtos.response.CustomerResponse;
import com.example.ucstores.validator.UserDetailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public CustomerResponse registration(UserRegistrationRequest request) {
        if (!UserDetailValidator.isValidPassword(request.getPassword())) throw new CustomerException("Password not set correctly");
       if (!UserDetailValidator.isValidEmailAddress(request.getEmailAddress())) throw new CustomerException("Email not set correctly");
       if (!UserDetailValidator.isValidPhoneNumber(request.getPhoneNumber())) throw new CustomerException("Phone Number not set correctly");
       var savedCustomer = buildCustomer(request);

      var savedResponse =  buildResponse(savedCustomer);
        return savedResponse;
    }

    private CustomerResponse buildResponse(Customer request) {
        CustomerResponse response = new CustomerResponse();
        response.setMessage("Created Successfully");
        response.setStatusCode(201);
        return response;
    }

    private Customer buildCustomer(UserRegistrationRequest request) {
        Customer customer = new Customer();
        customer.setEmailAddress(request.getEmailAddress());
        customer.setPassword(request.getPassword());
        customer.setPassword(request.getPassword());
        customer.setAddress(customer.getAddress());
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public CustomerResponse login(LoginRequest request) {
    var foundRequest = customerRepository.findByEmailAddress(request.getEmailAddress())
            .orElseThrow(()->new RuntimeException("Email not found"));

     CustomerResponse loginResponse = new CustomerResponse();
     if (foundRequest.getPassword().equals(request.getPassword())){
             loginResponse.setMessage("Login successful");
             return loginResponse;
     }
        loginResponse.setMessage("Authentication Failed");
     return loginResponse;
    }

    @Override
    public CustomerResponse update(UpdateRequest request) {
        var foundRequest = customerRepository.findByEmailAddress(request.getEmailAddress())
                .orElseThrow(()->new RuntimeException("Email not found"));
        foundRequest.setEmailAddress(request.getEmailAddress());
        foundRequest.setPassword(request.getPassword());
        foundRequest.setPhoneNumber(request.getPhoneNumber());
        foundRequest.setFirstName(request.getFirstName());
        foundRequest.setLastName(request.getLastName());
        foundRequest.setAddress(request.getAddress());
        customerRepository.save(foundRequest);
        CustomerResponse updateResponse = new CustomerResponse();
        updateResponse.setMessage("update successful");
        updateResponse.setStatusCode(201);
        return updateResponse;
    }

    @Override
    public List<Customer> getAllCustomer() {
      return   customerRepository.findAll();
    }

    @Override
    public CustomerResponse order(ProductPurchaseRequest purchaseRequest) {
        var foundRequest = customerRepository.findById(purchaseRequest.getCustomerId())
                .orElseThrow(()->new RuntimeException("Email not found"));
        var foundProduct = productRepository.findById(purchaseRequest.getProductId()).orElseThrow(()->new RuntimeException("Product not found"));
        if (foundProduct.getQuantity()>= purchaseRequest.getQuantity()){
            foundRequest.getOrders().add(foundProduct);
            foundProduct.setQuantity(foundProduct.getQuantity() - purchaseRequest.getQuantity());
            productRepository.save(foundProduct);

            CustomerResponse response = new CustomerResponse();
            response.setMessage("Ordered Successfully");
            response.setStatusCode(201);
            return response;

        }
        CustomerResponse response = new CustomerResponse();
        response.setMessage("Ordered Failed");
        response.setStatusCode(401);
        return response;

    }

    @Override
    public CustomerResponse delete(String id) {
        customerRepository.deleteById(id);
        CustomerResponse response = new CustomerResponse();
        response.setMessage("Deleted Successfully");
        response.setStatusCode(201);
        return response;
    }
}
