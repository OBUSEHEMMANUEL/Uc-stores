package com.example.ucstores.data.controller;

import com.example.ucstores.data.exception.CustomerException;
import com.example.ucstores.dtos.request.LoginRequest;
import com.example.ucstores.dtos.request.ProductPurchaseRequest;
import com.example.ucstores.dtos.request.UpdateRequest;
import com.example.ucstores.dtos.request.UserRegistrationRequest;
import com.example.ucstores.dtos.response.CustomerResponse;
import com.example.ucstores.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("api/stores/customer/register")
    public ResponseEntity<?> registration(@RequestBody UserRegistrationRequest request){
        try{
          return   ResponseEntity.status(HttpStatus.CREATED).body(customerService.registration(request));
        }catch ( CustomerException exception){
            CustomerResponse response = new CustomerResponse();
            response.setStatusCode(401);
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customerService.registration(request));
        }
    }
    @PostMapping("api/stores/customer/register/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(customerService.login(request));
    }
    @PatchMapping("api/stores/customer/register/update")
    public ResponseEntity<?> update(@RequestBody UpdateRequest request){
        return ResponseEntity.ok(customerService.update(request));
    }
    @GetMapping("api/stores/customer/register/getCustomeer")
    public ResponseEntity<?> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }
    @PostMapping("api/stores/customer/register/order")
    public ResponseEntity<?>  order(@RequestBody ProductPurchaseRequest purchaseRequest){
        return ResponseEntity.ok(customerService.order(purchaseRequest));
    }
    @DeleteMapping("api/stores/customer/register/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
       return ResponseEntity.ok(customerService.delete(id));
    }

}
