package com.example.ucstores.data.repository;

import com.example.ucstores.data.models.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorRepository extends MongoRepository<Vendor, String> {
   Vendor  findByEmailAddress(String email);
}
