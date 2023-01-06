package com.example.ucstores.service;

import com.example.ucstores.data.models.Product;
import com.example.ucstores.data.repository.ProductRepository;
import com.example.ucstores.dtos.request.AddProductRequest;
import com.example.ucstores.dtos.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    Product product;
    @Override
    public ProductResponse createProduct(AddProductRequest addproductRequest) {
         product = new Product();
        product.setName(addproductRequest.getName());
        product.setCategories(addproductRequest.getCategories());
        product.setPrice(addproductRequest.getPrice());
        product.setQuantity(addproductRequest.getQuantity());
        productRepository.save(product);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setMessage("Product Saved");
        productResponse.setStatusCode(201);
        return productResponse;
    }

    public Optional<Product> getProductId(String product) {
        return productRepository.findById(product);
    }

    @Override
    public ProductResponse delete(Product product) {
        productRepository.delete(product);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setMessage("Product deleted successfully");
        productResponse.setStatusCode(201);
        return productResponse;
    }

    @Override
    public ProductResponse addVendorProduct(Product vendorProduct) {
       productRepository.save(vendorProduct);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setMessage("Product Saved");
        productResponse.setStatusCode(201);
        return productResponse;
    }

}
