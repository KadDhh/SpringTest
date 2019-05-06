package com.example.springtest.repos;

import com.example.springtest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

