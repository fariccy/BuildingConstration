package com.example.Mayson.Repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.Mayson.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

//    User findByUsername(String username);

}
