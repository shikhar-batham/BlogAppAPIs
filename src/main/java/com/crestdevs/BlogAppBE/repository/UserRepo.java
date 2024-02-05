package com.crestdevs.BlogAppBE.repository;

import com.crestdevs.BlogAppBE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Integer> {

    //this function is written for loadUserByUsername
    //as we don't have username so we are using email as username
    Optional<User> findByEmail(String email);
}
