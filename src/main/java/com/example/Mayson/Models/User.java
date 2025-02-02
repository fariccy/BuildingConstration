package com.example.Mayson.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false)
        private String password;

//        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private Role role;

        public void setId(Long id) {
                this.id = id;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public void setRole(Role role) {
                this.role = role;
        }

        public Long getId() {
                return id;
        }

        public String getUsername() {
                return username;
        }

        public String getPassword() {
                return password;
        }

        public Role getRole() {
                return role;
        }

        // No need for explicit getters and setters due to @Data
}