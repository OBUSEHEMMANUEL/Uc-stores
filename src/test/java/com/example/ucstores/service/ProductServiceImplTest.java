package com.example.ucstores.service;

import com.example.ucstores.data.models.Categories;
import com.example.ucstores.data.models.Product;
import com.example.ucstores.data.repository.ProductRepository;
import com.example.ucstores.dtos.request.AddProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    AddProductRequest addProductRequest;
    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setName("milo");
        product.setQuantity(15);
        product.setPrice(BigDecimal.valueOf(50));
        product.setCategories(Categories.valueOf("BEVERAGES"));


        addProductRequest = new AddProductRequest();
        addProductRequest.setName("milk");
        addProductRequest.setQuantity(15);
        addProductRequest.setPrice(BigDecimal.valueOf(50));
        addProductRequest.setCategories(Categories.valueOf("BEVERAGES"));
    }

    @Test
    void addProduct() {
        var requestProduct =productService.createProduct(addProductRequest);
        assertEquals(201,requestProduct.getStatusCode());
    }

    @Test
    void delete(){
product.setProductId("63b7b6cb2549984fd9f02322");
        assertEquals("Product deleted successfully",  productService.delete(product).getMessage());
    }
}