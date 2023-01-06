package com.example.ucstores.data.repository;

import com.example.ucstores.data.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    Optional<Customer> findByEmailAddress(String email);

}
