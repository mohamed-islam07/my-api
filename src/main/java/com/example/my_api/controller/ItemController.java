package com.example.my_api.controller;

import com.example.my_api.model.Item;
import com.example.my_api.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // --- TEMPORARY DEBUG: remove after checking ---
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @PostConstruct
    public void checkMongoUri() {
        System.out.println("Resolved spring.data.mongodb.uri starts with: " +
                mongoUri.substring(0, Math.min(20, mongoUri.length())));
    }
    // --- END DEBUG ---

    // 1. GET ALL
    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // 2. GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. CREATE (POST)
    @PostMapping("/create")
    public Item createItem(@RequestBody Item item) {
        System.out.println(">>> RECEIVED ITEM: " + item.getName() + " - " + item.getPrice());
        return itemRepository.save(item);
    }

    // 4. UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item itemDetails) {
        return itemRepository.findById(id).map(existingItem -> {
            existingItem.setName(itemDetails.getName());
            existingItem.setPrice(itemDetails.getPrice());
            Item updatedItem = itemRepository.save(existingItem);
            return ResponseEntity.ok(updatedItem);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}