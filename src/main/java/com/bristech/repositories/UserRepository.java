package com.bristech.repositories;

import com.bristech.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String username);
}
