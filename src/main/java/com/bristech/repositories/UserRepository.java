package com.bristech.repositories;

import com.bristech.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser getUserByUsername(String username);

}
