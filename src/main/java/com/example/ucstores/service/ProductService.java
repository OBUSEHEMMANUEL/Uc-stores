package com.example.ucstores.service;

import com.example.ucstores.data.models.Product;
import com.example.ucstores.dtos.request.AddProductRequest;
import com.example.ucstores.dtos.response.ProductResponse;

import java.util.Optional;

public interface ProductService {
ProductResponse createProduct(AddProductRequest addproductRequest);
//Optional<Product> getProductId(String product);


//ProductResponse delete(Product product);

    ProductResponse addVendorProduct(Product product);

    ProductResponse delete(Product product);
}
