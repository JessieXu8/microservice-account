package com.ita.microservicetypeclient.microservicetypeclient.repositories;

import com.ita.microservicetypeclient.microservicetypeclient.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    List<Type> findAllByAccountKind(String accountKind);
}
