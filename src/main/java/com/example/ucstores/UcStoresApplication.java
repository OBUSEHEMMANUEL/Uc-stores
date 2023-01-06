package com.example.ucstores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class UcStoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcStoresApplication.class, args);
    }

}
