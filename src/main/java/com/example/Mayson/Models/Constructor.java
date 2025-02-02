package com.example.Mayson.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Constructor extends User {

    private String expertise;

    @ManyToMany
    @JoinTable(
            name = "constructor_feedback",
            joinColumns = @JoinColumn(name = "constructor_id"),
            inverseJoinColumns = @JoinColumn(name = "feedback_id")
    )
    private Set<Feedback> feedbacks;

    public Constructor(String expertise) {
        this.expertise = expertise;
    }

    public Constructor() {
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    // No need for explicit getters and setters due to @Data
}
