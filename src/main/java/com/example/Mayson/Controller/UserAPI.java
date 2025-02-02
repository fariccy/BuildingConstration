package com.example.Mayson.Controller;

import com.example.Mayson.Models.User;
import com.example.Mayson.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserAPI {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> createUser (@RequestBody User user) {
        try {
            User createdUser  = userRepository.save(user);
            return new ResponseEntity<>(createdUser , HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle the exception, e.g., log it and return an appropriate response
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }



    // Endpoint to delete a user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUsernameByUsername(@PathVariable String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isEmpty()){
                return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(user,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Connection fail",HttpStatus.BAD_REQUEST);
        }
    }




}
