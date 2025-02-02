package com.example.Mayson.Repository;

import com.example.Mayson.Models.Constructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, Long> {

}
