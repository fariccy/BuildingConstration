package com.example.Mayson.Controller;

import com.example.Mayson.Models.Building;
import com.example.Mayson.Models.Customer;
import com.example.Mayson.Repository.BuildingRepository;
import com.example.Mayson.Repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/building")
@CrossOrigin("*")

public class BuildingAPI {
    private final BuildingRepository buildingRepository;
    private final CustomerRepository customerRepository;

    public BuildingAPI(BuildingRepository buildingRepository, CustomerRepository customerRepository) {
        this.buildingRepository = buildingRepository;
        this.customerRepository = customerRepository;
    }


    @PostMapping("/add")
    public ResponseEntity<?> createUser (
            @RequestParam String location,
            @RequestParam(required = false) MultipartFile image, // Use MultipartFile for image
            @RequestParam String description,
            @RequestParam Long customerId // Assuming you want to link to a Customer by ID
    ) {
        try {
            Building building = new Building();
            building.setLocation(location);
            building.setDescription(description);

            // Handle the image upload
            if (image != null && !image.isEmpty()) {
                building.setImage(image.getBytes()); // Convert MultipartFile to byte array
            }

            // Find the Customer by ID
            Customer customer = customerRepository.findById(customerId).orElse(null);
            if (customer != null) {
                building.setCustomer(customer);
            } else {
                return ResponseEntity.badRequest().body("Customer not found");
            }

            // Save the Building entity
            Building createdBuilding = buildingRepository.save(building);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBuilding);
        } catch (Exception e) {
            // Handle the exception, e.g., log it and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Building>> getAllBuilding() {
        List<Building> buildings = buildingRepository.findAll();
        return ResponseEntity.ok(buildings);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        return buildingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to delete a customer by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        if (buildingRepository.existsById(id)) {
            buildingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
