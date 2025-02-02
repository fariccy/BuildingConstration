package com.example.Mayson.Controller;

import com.example.Mayson.Models.Customer;
import com.example.Mayson.Models.User;
import com.example.Mayson.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@CrossOrigin("*")
@RestController("/api/customer")
public class CustomerAPI {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public ResponseEntity<?> createUser (@RequestBody Customer customer) {
        try {
            Customer createdUser  = customerRepository.save(customer);
            return new ResponseEntity<>(createdUser , HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle the exception, e.g., log it and return an appropriate response
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to delete a customer by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
