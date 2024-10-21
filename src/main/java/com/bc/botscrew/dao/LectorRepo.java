package com.bc.botscrew.dao;

import com.bc.botscrew.entities.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectorRepo extends JpaRepository<Lector, Integer> {
    List<Lector> findByNameContainingIgnoreCase(String template);
}
