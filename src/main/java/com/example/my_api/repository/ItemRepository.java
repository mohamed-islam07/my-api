package com.example.my_api.repository;

import com.example.my_api.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    // Spring Data MongoDB automatically provides save(), findAll(), findById(), deleteById(), etc.
}