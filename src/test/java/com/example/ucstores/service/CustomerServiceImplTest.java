package com.example.ucstores.service;

import com.example.ucstores.dtos.request.LoginRequest;
import com.example.ucstores.dtos.request.ProductPurchaseRequest;
import com.example.ucstores.dtos.request.UpdateRequest;
import com.example.ucstores.dtos.request.UserRegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    CustomerService customerService;
    UserRegistrationRequest registrationRequest;
    UserRegistrationRequest registrationRequest2;
    ProductPurchaseRequest request;


    @BeforeEach
    void setUp() {
        registrationRequest = new UserRegistrationRequest();
        registrationRequest.setEmailAddress("DerekManuel@gmail.com");
        registrationRequest.setPhoneNumber("07069052656");
        registrationRequest.setPassword("Manuel#123");

        registrationRequest2 = new UserRegistrationRequest();
        registrationRequest2.setEmailAddress("Perry@gmail.com");
        registrationRequest2.setPhoneNumber("07069052656");
        registrationRequest2.setPassword("perry@123");



    }

    @Test
    void registrationTest() {
      var savedCustomer =  customerService.registration(registrationRequest2);
        assertEquals(201,savedCustomer.getStatusCode());


    }

    @Test
    void loginTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setId("63a28d05f338304adc0f03d9");
        loginRequest.setEmailAddress("Perry@gmail.com");
        loginRequest.setPassword("perry@123");
        var loginCustomer = customerService.login(loginRequest);
        assertEquals("Login successful", loginCustomer.getMessage());
    }

    @Test
    void updateTest() {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setEmailAddress("Perry@gmail.com");
        updateRequest.setFirstName("DEREK");
        updateRequest.setLastName("Manuel");
        updateRequest.setPhoneNumber("09056661496");
        updateRequest.setAddress("Semicolon sabo bus-stop");
        updateRequest.setPassword("derek@233");
        var updateCustomer = customerService.update(updateRequest);
        assertEquals("update successful", updateCustomer.getMessage());

    }

    @Test
    void getAllCustomerTest() {

        assertEquals(1,  customerService.getAllCustomer().size());
    }

    @Test
    void orderTest() {
        request = new ProductPurchaseRequest();
        request.setCustomerId("63b7b9524a9af60537212f8b");
        request.setQuantity(10);
        request.setProductId("63b7b929caf6c8236444114c");

        assertEquals(201,customerService.order(request).getStatusCode());

    }
}