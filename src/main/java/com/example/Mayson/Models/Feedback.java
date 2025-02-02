package com.example.Mayson.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Feedback {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String comments;

        @ManyToOne
        @JoinColumn(name = "building_id") // Foreign key in Feedback table
        private Building building; // Single Building reference

        @ManyToOne
        @JoinColumn(name = "constructor_id")
        private Constructor constructor;

        @ManyToMany(mappedBy = "feedbacks")
        @JsonIgnore // Prevents infinite recursion during serialization
        private Set<Building> buildings; // This is for the many-to-many relationship

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getComments() {
                return comments;
        }

        public void setComments(String comments) {
                this.comments = comments;
        }

        public Building getBuilding() {
                return building;
        }

        public void setBuilding(Building building) {
                this.building = building;
        }

        public Constructor getConstructor() {
                return constructor;
        }

        public void setConstructor(Constructor constructor) {
                this.constructor = constructor;
        }

        public Set<Building> getBuildings() {
                return buildings;
        }

        public void setBuildings(Set<Building> buildings) {
                this.buildings = buildings;
        }

        // No need for explicit getters and setters due to @Data
}

//package com.example.Mayson.Models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.Set;
//
//@Data
//@Entity
//public class Feedback {
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        private String comments;
//
//        @ManyToMany(mappedBy = "feedbacks", cascade = CascadeType.ALL)
//        @JsonIgnore // Prevents infinite recursion during serialization
//        private Set<Building> buildings;
//
//        public Long getId() {
//                return id;
//        }
//
//        public void setId(Long id) {
//                this.id = id;
//        }
//
//        public String getComments() {
//                return comments;
//        }
//
//        public void setComments(String comments) {
//                this.comments = comments;
//        }
//
//        public Set<Building> getBuildings() {
//                return buildings;
//        }
//
//        public void setBuildings(Set<Building> buildings) {
//                this.buildings = buildings;
//        }
//
//        // No need for explicit getters and setters due to @Data
//}
