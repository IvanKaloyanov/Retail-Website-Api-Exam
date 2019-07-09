package com.koev.retailwebsite.api.repository;

import com.koev.retailwebsite.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
