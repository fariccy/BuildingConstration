package com.example.Mayson.Controller;

import com.example.Mayson.Models.Constructor;
import com.example.Mayson.Models.Customer;
import com.example.Mayson.Repository.ConstructorRepository;
import com.example.Mayson.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/constructor")
@CrossOrigin("*")

public class ConstructorAPI {

    @Autowired
    private ConstructorRepository constructorRepository;

    @PostMapping("/add")
    public ResponseEntity<?> createUser (@RequestBody Constructor constructor) {
        try {
           Constructor createdUser  = constructorRepository.save(constructor);
            return new ResponseEntity<>(createdUser , HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle the exception, e.g., log it and return an appropriate response
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Constructor>> getAllCustomers() {
        List<Constructor> constructors = constructorRepository.findAll();
        return ResponseEntity.ok(constructors);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Constructor> getCustomerById(@PathVariable Long id) {
        return constructorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (constructorRepository.existsById(id)) {
           constructorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
