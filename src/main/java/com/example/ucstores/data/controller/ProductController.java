package com.example.ucstores.data.controller;

import com.example.ucstores.data.models.Product;
import com.example.ucstores.dtos.request.AddProductRequest;
import com.example.ucstores.dtos.response.ProductResponse;
import com.example.ucstores.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
   @Autowired
   ProductService productService;
   @PostMapping("api/stores/product")
   public ResponseEntity<?> createProduct(AddProductRequest addproductRequest){
      try {
         return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(addproductRequest));
      }catch (RuntimeException exception){
         ProductResponse response = new ProductResponse();
         response.setStatusCode(401);
         response.setMessage(exception.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productService.createProduct(addproductRequest));
      }
   }
   @PostMapping("api/stores/vendor/register/addVendorProduct")
   public ResponseEntity<?> addVendorProduct(Product product){
      return ResponseEntity.ok(productService.addVendorProduct(product));
   }

}
