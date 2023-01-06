package com.example.ucstores.service;

import com.example.ucstores.data.models.Categories;
import com.example.ucstores.dtos.request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VendorServiceImplTest {
    @Autowired
    VendorService vendorService;
    VendorUpdateRequest vendorUpdateRequest;
    VendorRegistrationRequest registrationRequest;

    VendorRegistrationRequest registrationRequest2;
    VendorLoginRequest loginRequest;
    VendorProductRequest productRequest;
    UpdateProductRequest updateProductRequest;


    @BeforeEach
    void setUp() {
        registrationRequest = new VendorRegistrationRequest();
        registrationRequest.setEmailAddress("Benidict12@gmail.com");
        registrationRequest.setPhoneNumber("07065830431");
        registrationRequest.setPassword("benedict!123");

        registrationRequest2 = new VendorRegistrationRequest();
        registrationRequest2.setEmailAddress("Temi@gmail.com");
        registrationRequest2.setPhoneNumber("08064527485");
        registrationRequest2.setPassword("Temioluwa*123");

        loginRequest = new VendorLoginRequest();
        loginRequest.setEmailAddress("Temi@gmail.com");
        loginRequest.setPassword("Temioluwa*123");



        vendorUpdateRequest = new VendorUpdateRequest();
        vendorUpdateRequest.setAddress("iwaya road sabo lagos");
        vendorUpdateRequest.setFirstName("vincent");
        vendorUpdateRequest.setLastName("Emeka");
        vendorUpdateRequest.setPhoneNumber("08023562398");
        vendorUpdateRequest.setEmailAddress("Emeka@gmail.com");
        vendorUpdateRequest.setPassword("vicEme@123");
        vendorUpdateRequest.setEmailAddress("Temi@gmail.com");


        productRequest = new VendorProductRequest();
        productRequest.setVendorId("63b7b59b2351985de523df8a");
        productRequest.setName("milk");
        productRequest.setQuantity(20);
        productRequest.setPrice(BigDecimal.valueOf(60));
        productRequest.setCategories(Categories.valueOf("ELECTRONIC"));
    }

    @Test
    void registrationTest() {
        var savedVendor = vendorService.registration(registrationRequest);
        vendorService.registration(registrationRequest2);
        assertEquals(201,savedVendor.getStatusCode());
    }

    @Test
    void loginTest() {
        var loggedInVendor = vendorService.login(loginRequest);
        assertEquals(201,loggedInVendor.getStatusCode());
    }

    @Test
    void updateTest() {
        var updatedVendor = vendorService.update(vendorUpdateRequest);
        assertEquals(201,updatedVendor.getStatusCode());
    }

    @Test
    void getAllVendorTest() {
      assertEquals(2, vendorService.getAllVendor().size());
    }

    @Test
    void addProductTest() {
       assertEquals(201,vendorService.addProduct(productRequest).getStatusCode());

    }
    @Test
    void updateProductTest(){
        updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setProductId("63b7b929caf6c8236444114c");
        updateProductRequest.setName("ovlatine");
        updateProductRequest.setQuantity(23);
        assertEquals(201,vendorService.updateProduct(updateProductRequest).getStatusCode());
    }
    @Test
    void deleteVendorTest(){
        assertEquals(2, vendorService.getAllVendor().size());
        vendorService.delete("63b7b59b2351985de523df89");
        assertEquals(1, vendorService.getAllVendor().size());

    }
}