package com.example.springproject4.repository;

import com.example.springproject4.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //@Query("select r from Role r where r =:name")
    Optional<Role> findRoleByName(String name);
}
