package com.example.Mayson.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Customer extends User {

        @Column(nullable = false)
        private String address;

        @OneToMany(mappedBy = "customer")
        private Set<Building> buildings;

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        // No need for explicit getters and setters due to @Data
}

//package com.example.Mayson.Models;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.OneToMany;
//import lombok.Data;
//
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Data
//public class Customer extends User{
//
//        @Column( nullable = false)
//
//        private String address;
//
////        @OneToMany(mappedBy = "customer")
////        private List<Building> buildings;
//
//        @OneToMany(mappedBy = "customer")
//        //@JsonBackReference // This will be the back part of the reference
//        private Set<Building> buildings;
//
////        public Customer(){
////                super();
////        }
//
//
////        public Customer(Long id, String username, String password, Role role) {
////        super(id, username, password, role);
////        }
//
//        // Getters and Setters
//        public String getAddress() {
//                return address;
//        }
//
//        public void setAddress(String address) {
//                this.address = address;
//        }
//
//
//}
