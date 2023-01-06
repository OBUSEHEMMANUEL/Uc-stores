package com.example.ucstores.data.repository;

import com.example.ucstores.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
