package com.example.springproject4.repository;


import com.example.springproject4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByName(String name);
    Optional<User> findUserByEmail(String email);

}
