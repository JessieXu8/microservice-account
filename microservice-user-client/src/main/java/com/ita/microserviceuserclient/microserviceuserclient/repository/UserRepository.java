package com.ita.microserviceuserclient.microserviceuserclient.repository;

import com.ita.microserviceuserclient.microserviceuserclient.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserId(String userId);
}
