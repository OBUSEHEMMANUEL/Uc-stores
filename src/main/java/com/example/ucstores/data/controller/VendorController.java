package com.example.ucstores.data.controller;

import com.example.ucstores.data.exception.VendorException;
import com.example.ucstores.dtos.request.*;
import com.example.ucstores.dtos.response.VendorResponse;
import com.example.ucstores.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendorController {
    @Autowired
    VendorService vendorService;
    @PostMapping("api/stores/vendor/register")
    public ResponseEntity<?> registration(@RequestBody VendorRegistrationRequest request){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(vendorService.registration(request));
        }catch (VendorException exception){
            VendorResponse response = new VendorResponse();
            response.setMessage(exception.getMessage());
            response.setStatusCode(401);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vendorService.registration(request));
        }
    }
    @PostMapping("api/stores/vendor/register/login")
    public ResponseEntity<?> login(@RequestBody VendorLoginRequest request){
        return ResponseEntity.ok(vendorService.login(request));
    }
    @PatchMapping("api/stores/vendor/register/update")
    public ResponseEntity<?> update(@RequestBody VendorUpdateRequest request){
        return ResponseEntity.ok(vendorService.update(request));
    }
    @GetMapping("api/stores/vendor/register/getAllVendor")
    public ResponseEntity<?> getAllVendor(){
        return ResponseEntity.ok(vendorService.getAllVendor());
    }

    @PostMapping("api/stores/vendor/register/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody VendorProductRequest request){
        return ResponseEntity.ok(vendorService.addProduct(request));
    }
    @PatchMapping("api/stores/vendor/register/addVendorProduct")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest updateRequest){
        return ResponseEntity.ok(vendorService.updateProduct(updateRequest));
    }
}
