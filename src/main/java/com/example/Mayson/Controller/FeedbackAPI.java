package com.example.Mayson.Controller;

import com.example.Mayson.Models.Building;
import com.example.Mayson.Models.Constructor;
import com.example.Mayson.Models.Customer;
import com.example.Mayson.Models.Feedback;
import com.example.Mayson.Repository.BuildingRepository;
import com.example.Mayson.Repository.ConstructorRepository;
import com.example.Mayson.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feedback")
@CrossOrigin("*")

public class FeedbackAPI {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ConstructorRepository constructorRepository;

    @PostMapping("/add")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        // Fetching the related entities
        Building building = buildingRepository.findById(feedback.getBuilding().getId()).orElse(null);
        Constructor constructor = constructorRepository.findById(feedback.getConstructor().getId()).orElse(null);

        // Check if building and constructor exist
        if (building == null || constructor == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Setting the relationships
        feedback.setBuilding(building);
        feedback.setConstructor(constructor);

        // Save feedback
        Feedback createdFeedback = feedbackRepository.save(feedback);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to delete a customer by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
